package Elements;

import static org.junit.Assert.*;

import org.junit.Test;

public class FieldTest {
    @Test
    public void testSetTower() {
        Field field = new Field(new Color(Color.BLUE), 0, 0);
        Tower tower = new Tower(new Color(Color.BLUE), new Color(Color.WHITE));
        
        field.setTower(tower);
        
        assertEquals(tower, field.getTower());
        assertFalse(field.isFree());
        assertFalse(field.isPossible());
    }

    @Test
    public void testRemoveTower() {
        Field field = new Field(new Color(Color.BLUE), 2, 1);
        Tower tower = new Tower(new Color(Color.BLUE), new Color(Color.WHITE));
        field.setTower(tower);
        
        Tower removedTower = field.removeTower();
        
        assertEquals(tower, removedTower);
        assertTrue(field.isFree());
    }

    @Test
    public void testSetFreedome() {
        Field field = new Field(new Color(Color.BLUE), 0, 5);
        
        field.setFreedome(false);
        assertFalse(field.isFree());
        
        field.setFreedome(true);
        assertTrue(field.isFree());
    }

    @Test
    public void testSetPossible() {
        Field field = new Field(new Color(Color.BLUE), 7, 5);
        field.setPossible(true);
        assertTrue(field.isPossible());
    }

    @Test
    public void testSetSelected() {
        Field field = new Field(new Color(Color.BLUE), 4, 2);
        field.setSelected(true);
        assertTrue(field.isSelected());
    }
}