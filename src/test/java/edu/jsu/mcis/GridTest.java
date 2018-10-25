package edu.jsu.mcis;

import org.junit.*;
import static org.junit.Assert.*;

import java.util.*;
import java.awt.Point;

public class GridTest {
    private class MockRandom extends Random {
        private int[] values;
        private int currentIndex;
        
        public MockRandom(int[] values) {
            this.values = values;
            currentIndex = 0;
        }
        
        @Override
        public int nextInt(int n) {
            int val = values[currentIndex] % n;
            currentIndex = (currentIndex + 1) % values.length;
            return val;
        }
    }
    
    private class MockObserver implements Observer {
        private String message;
        public MockObserver() { message = ""; }
        public void update(Observable o, Object arg) { 
            if(message.length() > 0) {
                message += " " + (String)arg; 
            }
            else {
                message = (String)arg;
            }
        }
        public String getMessage() { return message; }
        public void clearMessage() {message = ""; }
    }
    
    private Grid grid1;
    private Grid grid2;
    private Grid gridMocked;
    private MockObserver observer;
    
    @Before
    public final void setUp() {
        grid1 = new Grid();
        grid2 = new Grid(20, 30, 5);
        
        
        // This will set up the following grid, where the numbers
        // represent hints and the asterisks represent mines:
        //     0123456789
        //  0  00001*2100
        //  1  000123*100
        //  2  0001*21100
        //  3  0112110000
        //  4  01*1001110
        //  5  1221001*10
        //  6  1*21101110
        //  7  112*211000
        //  8  01222*1011
        //  9  01*111101*
         
        int[] randomValues = {5, 7, 
                              4, 2, 
                              7, 3, 
                              1, 6, 
                              8, 5,
                              9, 2,
                              6, 1,
                              2, 4,
                              0, 5,
                              9, 9}; 
        gridMocked = new Grid(10, 10, 10, new MockRandom(randomValues));
        observer = new MockObserver();
        gridMocked.addObserver(observer);
    }
    
    @Test
    public final void testDefaultConstructor() {
        assertEquals(8, grid1.getWidth());
        assertEquals(8, grid1.getHeight());
        assertEquals(10, grid1.getMines());
        for(int i = 0; i < grid1.getHeight(); i++) {
            for(int j = 0; j < grid1.getWidth(); j++) {
                assertEquals(Location.Type.COVERED, grid1.getLocation(i, j).getType());
            }
        }
        int mineCount = 0;
        for(int i = 0; i < grid1.getHeight(); i++) {
            for(int j = 0; j < grid1.getWidth(); j++) {
                if(grid1.getLocation(i, j).hasMine()) {
                    mineCount++;
                }
            }
        }
        assertEquals(10, mineCount);
    }
    
    @Test
    public final void testParameterizedConstructor() {
        assertEquals(20, grid2.getWidth());
        assertEquals(30, grid2.getHeight());
        assertEquals(5, grid2.getMines());
        for(int i = 0; i < grid2.getHeight(); i++) {
            for(int j = 0; j < grid2.getWidth(); j++) {
                assertEquals(Location.Type.COVERED, grid2.getLocation(i, j).getType());
            }
        }
        int mineCount = 0;
        for(int i = 0; i < grid2.getHeight(); i++) {
            for(int j = 0; j < grid2.getWidth(); j++) {
                if(grid2.getLocation(i, j).hasMine()) {
                    mineCount++;
                }
            }
        }
        assertEquals(5, mineCount);
    }
        
    @Test
    public final void testHintsAreSetCorrectly() {
        // We will use 9 to represent "mine".
        int[][] expected = {{0, 0, 0, 0, 1, 9, 2, 1, 0, 0},
                            {0, 0, 0, 1, 2, 3, 9, 1, 0, 0},
                            {0, 0, 0, 1, 9, 2, 1, 1, 0, 0},
                            {0, 1, 1, 2, 1, 1, 0, 0, 0, 0},
                            {0, 1, 9, 1, 0, 0, 1, 1, 1, 0},
                            {1, 2, 2, 1, 0, 0, 1, 9, 1, 0},
                            {1, 9, 2, 1, 1, 0, 1, 1, 1, 0},
                            {1, 1, 2, 9, 2, 1, 1, 0, 0, 0},
                            {0, 1, 2, 2, 2, 9, 1, 0, 1, 1},
                            {0, 1, 9, 1, 1, 1, 1, 0, 1, 9}};
        for(int row = 0; row < gridMocked.getHeight(); row++) {
            for(int col = 0; col < gridMocked.getWidth(); col++) {
                if(expected[row][col] == 9) {
                    assertTrue(gridMocked.getLocation(row, col).hasMine());
                }
                else {
                    assertEquals(expected[row][col], gridMocked.getLocation(row, col).getHint());
                }
            }
        }
    }
    
    @Test
    public final void testReset() {
        for(int row = 0; row < gridMocked.getHeight(); row++) {
            for(int col = 0; col < gridMocked.getWidth(); col++) {
                gridMocked.getLocation(row, col).setType(Location.Type.UNCOVERED);
                gridMocked.getLocation(row, col).setHint(7);
            }
        }
        gridMocked.reset();
        // We will use 9 to represent "mine".
        int[][] expected = {{0, 0, 0, 0, 1, 9, 2, 1, 0, 0},
                            {0, 0, 0, 1, 2, 3, 9, 1, 0, 0},
                            {0, 0, 0, 1, 9, 2, 1, 1, 0, 0},
                            {0, 1, 1, 2, 1, 1, 0, 0, 0, 0},
                            {0, 1, 9, 1, 0, 0, 1, 1, 1, 0},
                            {1, 2, 2, 1, 0, 0, 1, 9, 1, 0},
                            {1, 9, 2, 1, 1, 0, 1, 1, 1, 0},
                            {1, 1, 2, 9, 2, 1, 1, 0, 0, 0},
                            {0, 1, 2, 2, 2, 9, 1, 0, 1, 1},
                            {0, 1, 9, 1, 1, 1, 1, 0, 1, 9}};
        for(int row = 0; row < gridMocked.getHeight(); row++) {
            for(int col = 0; col < gridMocked.getWidth(); col++) {
                assertEquals(Location.Type.COVERED, gridMocked.getLocation(row, col).getType());
                if(expected[row][col] == 9) {
                    assertTrue(gridMocked.getLocation(row, col).hasMine());
                }
                else {
                    assertEquals(expected[row][col], gridMocked.getLocation(row, col).getHint());
                }
            }
        }        
    }
    
    @Test
    public final void testFlagOperations() {
        assertFalse(gridMocked.isFlagAt(4, 7));
        gridMocked.placeFlagAt(4, 7);
        assertTrue(gridMocked.isFlagAt(4, 7));
        assertEquals("4:7:flag", observer.getMessage());
        
        observer.clearMessage();
        gridMocked.removeFlagAt(4, 7);
        assertFalse(gridMocked.isFlagAt(4, 7));
        assertEquals("4:7:unflag", observer.getMessage());

        observer.clearMessage();
        assertFalse(gridMocked.isFlagAt(6, 2));
        gridMocked.removeFlagAt(4, 7);
        assertFalse(gridMocked.isFlagAt(6, 2));
        assertEquals("", observer.getMessage());

        observer.clearMessage();
        assertFalse(gridMocked.isFlagAt(6, 2));
        gridMocked.placeFlagAt(6, 2);
        assertTrue(gridMocked.isFlagAt(6, 2));
        assertEquals("6:2:flag", observer.getMessage());        
        
        observer.clearMessage();
        gridMocked.placeFlagAt(6, 2);
        assertTrue(gridMocked.isFlagAt(6, 2));
        assertEquals("", observer.getMessage());        
    }
    
    private List<Point> messageToPoints(String message) {
        List<Point> list = new ArrayList<Point>();
        String[] msg = message.split(" ");
        for(String m : msg) {
            String[] parts = m.split(":");
            Point p = new Point(Integer.parseInt(parts[0]),
                                Integer.parseInt(parts[1]));
            list.add(p);
        }
        return list;
    }
    
    @Test
    public final void testCoverOperations() {
        // Empty space at (1, 1)
        // This should uncover the following spaces:
        //    (0, 0) (0, 1), (0, 2), (0, 3), (0, 4)
        //    (1, 0) (1, 1), (1, 2), (1, 3), (1, 4)
        //    (2, 0) (2, 1), (2, 2), (2, 3)
        //    (3, 0) (3, 1), (3, 2), (3, 3)
        //    (4, 0) (4, 1)
        //    (5, 0) (5, 1)
        //
        int[][] expected = {{0, 0}, {0, 1}, {0, 2}, {0, 3}, {0, 4},
                            {1, 0}, {1, 1}, {1, 2}, {1, 3}, {1, 4},
                            {2, 0}, {2, 1}, {2, 2}, {2, 3},
                            {3, 0}, {3, 1}, {3, 2}, {3, 3},
                            {4, 0}, {4, 1},
                            {5, 0}, {5, 1}};
        assertEquals(Location.Type.COVERED, gridMocked.getLocation(1, 1).getType());
        gridMocked.uncoverAt(1, 1);
        assertEquals(Location.Type.UNCOVERED, gridMocked.getLocation(1, 1).getType());
        List<Point> points = messageToPoints(observer.getMessage());
        assertEquals(expected.length, points.size());
        for(int i = 0; i < expected.length; i++) {
            Point p = new Point(expected[i][0], expected[i][1]);
            assertTrue(points.contains(p));
        }
        
        // Mine at (5, 7)
        //
        observer.clearMessage();
        assertEquals(Location.Type.COVERED, gridMocked.getLocation(5, 7).getType());
        gridMocked.uncoverAt(5, 7);
        assertEquals(Location.Type.UNCOVERED, gridMocked.getLocation(5, 7).getType());
        assertEquals("5:7:mine", observer.getMessage());
    }
}













