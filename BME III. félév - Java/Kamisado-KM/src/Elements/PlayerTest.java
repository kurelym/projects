package Elements;

import static org.junit.Assert.*;

import org.junit.Test;

public class PlayerTest {
	@Test
    public void testGetColor() {
        Color color = new Color(Color.WHITE);
        Player player = new Player(color);
        assertEquals(color, player.getColor());
    }
}