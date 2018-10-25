package edu.jsu.mcis;

import org.junit.*;
import static org.junit.Assert.*;

import java.awt.*;
import javax.swing.*;

public class MinesweeperTest {
    
    private static Component getComponentByName(Container root, String name) {
        if(name == null) return null;
        else if(root == null) return null;
        else {
            for(Component component : root.getComponents()) {
                if(name.equals(component.getName())) {
                    return component;
                }
                else if(component instanceof Container) {
                    Component c = getComponentByName((Container)component, name);
                    if(c != null) return c;
                }
            }
            return null;
        }
    }
    
    @Test
    public final void testWidgetsHaveCorrectNames() {
        Minesweeper m = new Minesweeper();
        assertNotNull("The flags label should have the name 'flags'.", getComponentByName(m, "flags"));
        assertNotNull("The ticker should have the name 'ticker'.", getComponentByName(m, "ticker"));
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                assertNotNull("The cell at (" + i + ", " + j + ") should have the name 'cell:" + i + ":" + j + "'.", 
                              getComponentByName(m, "cell:" + i + ":" + j));
            }
        }
    }
    
    @Test
    public final void testFlagWidgetIsInitializedCorrectly() {
        Minesweeper m = new Minesweeper();
        JLabel flagLabel = (JLabel)getComponentByName(m, "flags");
        assertEquals("The flag label should have the right number on it.", "10", flagLabel.getText());
    }
    
    @Test
    public final void testCellWidgetsAreInitializedCorrectly() {
        Minesweeper m = new Minesweeper();
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                String testExplanation = "The cell label at (" + i + ", " + j + ") should be ";
                JLabel cellLabel = (JLabel)getComponentByName(m, "cell:" + i + ":" + j);
                assertEquals(testExplanation + "50x50.", new Dimension(50, 50), cellLabel.getPreferredSize());
                assertEquals(testExplanation + "horizontally centered.", JLabel.CENTER, cellLabel.getHorizontalAlignment());
                assertEquals(testExplanation + "raised bevel bordered.", BorderFactory.createRaisedBevelBorder(), cellLabel.getBorder());
            }
        }
    }
    
}













