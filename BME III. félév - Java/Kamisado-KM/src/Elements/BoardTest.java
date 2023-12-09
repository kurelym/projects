package Elements;

import static org.junit.Assert.*;

import org.junit.Test;

public class BoardTest {
    @Test
    public void testGetFieldAtPlace() {
        Board board = new Board(false);
        Field field = board.getFieldAtPlace(0, 0);
        assertNotNull(field);
    }

    @Test
    public void testClearPossibilities() {
        Board board = new Board(false);
        Player player = new Player(new Color(Color.WHITE));
        board.setPossibleFieldsNow(player,new Color(Color.ORANGE));
  
        board.clearPossibilities();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Field field = board.getFieldAtPlace(i, j);
                assertFalse(field.isPossible());
                assertFalse(field.isSelected());
            }
        }
    }

    @Test
    public void testSetPossibleFieldsFirstMove() {
        Board board = new Board(false);
        Player player = new Player(new Color(Color.WHITE));
        Field choosenField = board.getFieldAtPlace(0, 3);
        board.clearPossibilities();
        board.setPossibleFieldsFirstMove(player, choosenField);
        
        for(int i = 0; i < 8 ; i++) {
        	for(int j = 0; j < 8; j++) {
        		Field field = board.getFieldAtPlace(i, j);
        		
        		if(i == 0) {
        			assertFalse(field.isPossible());
        			continue;
        		}
        		else if(i == 7) {
        			assertFalse(field.isPossible());
        			continue;
        		}
        		else if(j == 3) {
        			assertTrue(field.isPossible());
        			continue;
        		}
        		else if(j - 3 == i) {
        			assertTrue(field.isPossible());
        			continue;
        		}
        		else if(j + i == 3) {
        			assertTrue(field.isPossible());
        			continue;
        		}
        		
        		else {
        			assertFalse(field.isPossible());
        		}		
        	}
        }
    }    
}
