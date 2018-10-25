package edu.jsu.mcis;

import org.junit.*;
import static org.junit.Assert.*;

public class TickerTest {
    private Ticker ticker;
    
    @Before
    public final void setUp() {
        ticker = new Ticker();
    }
    
    @Test
    public final void testConstructor() {
        assertFalse(ticker.isRunning());
        assertEquals("00:00", ticker.getText());
    }
    
    @Test
    public final void testStart() {
        assertFalse(ticker.isRunning());
        ticker.start();
        assertTrue(ticker.isRunning());
        try {
            Thread.currentThread().sleep(2000);
            assertFalse("00:00".equals(ticker.getText()));
        }
        catch(InterruptedException e) {
            assertEquals("", "Thread was interrupted. Run test again.");
        }
    }
    
    @Test
    public final void testStop() {
        assertFalse(ticker.isRunning());
        ticker.start();
        assertTrue(ticker.isRunning());
        ticker.stop();
        assertFalse(ticker.isRunning());        
    }
    
    @Test
    public final void testReset() {
        assertFalse(ticker.isRunning());
        ticker.start();
        assertTrue(ticker.isRunning());
        try {
            Thread.currentThread().sleep(2000);
            assertFalse("00:00".equals(ticker.getText()));
        }
        catch(InterruptedException e) {
            assertEquals("", "Thread was interrupted. Run test again.");
        }
        ticker.reset();
        assertFalse(ticker.isRunning());
        assertEquals("00:00", ticker.getText());
    }

    @Test
    public final void testActionPerformed() {
        assertEquals("00:00", ticker.getText());
        for(int i = 0; i < 652; i++) {
            ticker.actionPerformed(null);
        }
        assertEquals("10:52", ticker.getText());
    }
}
