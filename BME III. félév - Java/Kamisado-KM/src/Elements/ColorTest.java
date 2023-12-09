package Elements;

import static org.junit.Assert.*;

import org.junit.Test;

public class ColorTest {
    @Test
    public void testGetValue() {
        Color color = new Color(Color.ORANGE);
        assertEquals(Color.ORANGE, color.getValue());
    }

    @Test
    public void testSetValue() {
        Color color = new Color(Color.ORANGE);
        color.setValue(Color.BLUE);
        assertEquals(Color.BLUE, color.getValue());
    }

    @Test
    public void testCompare() {
        Color color1 = new Color(Color.ORANGE);
        Color color2 = new Color(Color.ORANGE);
        assertTrue(color1.compare(color2));
    }

    @Test
    public void testToString() {
        Color color = new Color(Color.ORANGE);
        assertEquals("narancssárga", color.toString());
    }

    @Test
    public void testGetAwtColor() {
        Color color = new Color(Color.ORANGE);
        assertEquals(new java.awt.Color(255, 165, 0), color.getAwtColor());
    }
}