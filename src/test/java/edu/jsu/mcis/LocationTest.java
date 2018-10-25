package edu.jsu.mcis;

import org.junit.*;
import static org.junit.Assert.*;

public class LocationTest {
    private Location loc;

    @Before
    public final void setUp() {
        loc = new Location();
    }
    
    @Test
    public final void testReset() {
        loc.setType(Location.Type.FLAGGED);
        loc.setMine(true);
        loc.setHint(9);
        loc.reset();
        assertEquals(Location.Type.COVERED, loc.getType());
        assertFalse(loc.hasMine());
        assertEquals(0, loc.getHint());
    }
    
    @Test
    public final void testGetType() {
        assertEquals(Location.Type.COVERED, loc.getType());
    }
    
    @Test
    public final void testSetType() {
        assertEquals(Location.Type.COVERED, loc.getType());
        loc.setType(Location.Type.UNCOVERED);
        assertEquals(Location.Type.UNCOVERED, loc.getType());
    }
    
    @Test
    public final void testHasMine() {
        assertFalse(loc.hasMine());
    }
    
    @Test
    public final void testSetMine() {
        assertFalse(loc.hasMine());
        loc.setMine(true);
        assertTrue(loc.hasMine());
    }
    
    @Test
    public final void testGetHint() {
        assertEquals(0, loc.getHint());
    }
    
    @Test
    public final void testSetHint() {
        assertEquals(0, loc.getHint());
        loc.setHint(3);
        assertEquals(3, loc.getHint());
    }
}
