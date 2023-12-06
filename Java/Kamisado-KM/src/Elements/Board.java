package Elements;


import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
 * __BOARD COORDS______________________________
 * |0,0|0,1|0,2|0,3|0,4|0,5|0,6|0,7|
 * |1,0|1,1|1,2|1,3|1,4|1,5|1,6|1,7|
 * |2,0|2,1|2,2|2,3|2,4|2,5|2,6|2,7|
 * |3,0|3,1|3,2|3,3|3,4|3,5|3,6|3,7|
 * |4,0|4,1|4,2|4,3|4,4|4,5|4,6|4,7|
 * |5,0|5,1|5,2|5,3|5,4|5,5|5,6|5,7|
 * |6,0|6,1|6,2|6,3|6,4|6,5|6,6|6,7|
 * |7,0|7,1|7,2|7,3|7,4|7,5|7,6|7,7|
 * --------------------------------
 */

// Az osztály a játéktábla kezeléséért felelős a játékban. 
public class Board implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -541624829476798539L;
	private Field[][] board;
	private Color[][] colors = new Color[8][8];
	
	
	public Board(boolean randomized) {
		this.board = new Field[8][8];
		
		initColors(randomized);
		
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				board[i][j] = new Field(colors[i][j],i,j);
			}
		}
		
		initTowers();
	}
	
	// Visszaadja a tábla adott pozícióján lévő mezőt.
	public Field getFieldAtPlace(int i, int j) {
		return board[i][j];
	}
	
	// Másoló konstruktor
	public Board(Board other) {
	    this.board = new Field[8][8];
	    this.colors = new Color[8][8];
	    
	    for(int i = 0; i < 8; i++) {
	        for(int j = 0; j < 8; j++) {
	            this.board[i][j] = new Field(other.board[i][j]);
	            this.colors[i][j] = new Color(other.colors[i][j]);
	        }
	    }
	}

	// Inicializálja a tábla mezőinek színeit
	private void initColors(Boolean randomized) {
		Color ORANGE = new Color(Color.ORANGE);
		Color YELLOW = new Color(Color.YELLOW);
		Color BLUE = new Color(Color.BLUE);
		Color RED = new Color(Color.RED);
		Color GREEN = new Color(Color.GREEN);
		Color PINK = new Color(Color.PINK);
		Color BROWN = new Color(Color.BROWN);
		Color PURPLE = new Color(Color.PURPLE);
		
		
		if(!randomized) {
			colors[0][0]=ORANGE; colors[0][1]=BLUE; colors[0][2]=PURPLE; colors[0][3]=PINK;
			colors[0][4]=YELLOW; colors[0][5]=RED; colors[0][6]=GREEN; colors[0][7]=BROWN;
			
			colors[1][0]=RED; colors[1][1]=ORANGE; colors[1][2]=PINK; colors[1][3]=GREEN;
			colors[1][4]=BLUE; colors[1][5]=YELLOW; colors[1][6]=BROWN; colors[1][7]=PURPLE;
			
			colors[2][0]=GREEN; colors[2][1]=PINK; colors[2][2]=ORANGE; colors[2][3]=RED;
			colors[2][4]=PURPLE; colors[2][5]=BROWN; colors[2][6]=YELLOW; colors[2][7]=BLUE;
			
			colors[3][0]=PINK; colors[3][1]=PURPLE; colors[3][2]=BLUE; colors[3][3]=ORANGE;
			colors[3][4]=BROWN; colors[3][5]=GREEN; colors[3][6]=RED; colors[3][7]=YELLOW;
			
			colors[4][0]=YELLOW; colors[4][1]=RED; colors[4][2]=GREEN; colors[4][3]=BROWN;
			colors[4][4]=ORANGE; colors[4][5]=BLUE; colors[4][6]=PURPLE; colors[4][7]=PINK;
			
			colors[5][0]=BLUE; colors[5][1]=YELLOW; colors[5][2]=BROWN; colors[5][3]=PURPLE;
			colors[5][4]=RED; colors[5][5]=ORANGE; colors[5][6]=PINK; colors[5][7]=GREEN;
			
			colors[6][0]=PURPLE; colors[6][1]=BROWN; colors[6][2]=YELLOW; colors[6][3]=BLUE;
			colors[6][4]=GREEN;colors[6][5]=PINK; colors[6][6]=ORANGE; colors[6][7]=RED;
			
			colors[7][0]=BROWN; colors[7][1]=GREEN; colors[7][2]=RED; colors[7][3]=YELLOW;
			colors[7][4]=PINK; colors[7][5]=PURPLE; colors[7][6]=BLUE; colors[7][7]=ORANGE;
		}else {
		    List<Color> allColors = Arrays.asList(ORANGE, YELLOW, BLUE, RED, GREEN, PINK, BROWN, PURPLE);
		    for (int i = 0; i < colors.length; i++) {
		        Collections.shuffle(allColors);
		        for (int j = 0; j < colors[i].length; j++) {
		            colors[i][j] = allColors.get(j);
		        }
		    }
		}

	}
	
	// Inicializálja a játékosok tornyait a táblán.
	private void initTowers() {
		for(int i = 0; i < 8 ; i++) {
			Tower newest = new Tower(board[0][i].getColor(),new Color(Color.WHITE));
			board[0][i].setTower(newest);
		}
		for(int i = 0; i < 8 ; i++) {
			Tower newest = new Tower(board[7][i].getColor(),new Color(Color.BLACK));
			board[7][i].setTower(newest);
		}
	}
	
	// Megszünteti az összes lehetséges lépés 
	public void clearPossibilities() {
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				board[i][j].setPossible(false);
				board[i][j].setSelected(false);
			}
		}
	}
	
	// Megjelöli azokat a mezőket, amelyekre a játékos léphet az első lépésben
	public void setPossibleFieldsFirstMove(Player aktPlayer,Field choosenField){
		 setPossibleFieldsNow(aktPlayer,choosenField.getColor());
	}
	
	// Megjelöli azokat a mezőket, amelyekre a játékos léphet
	public Field setPossibleFieldsNow(Player aktPlayer,Color lastFieldColor){
		this.clearPossibilities();
		int cnt = 0;
		
		Field current = currentField(aktPlayer, lastFieldColor);
		int dir = 1;
		if(aktPlayer.getColor().compare(new Color(Color.BLACK))) dir = -1;
		
		// Egyenesen
		int i = current.getI() + dir;
		int j = current.getJ();
		while(i >= 0 && i < 8 && board[i][j].isFree()) {
		    board[i][j].setPossible(true);
		    i+=dir;
		    cnt++;
		}

		// Átlósan egyik irány
		i = current.getI() + dir;
		j = current.getJ() + 1;
		while(i >= 0 && i < 8 && j >= 0 && j < 8 && board[i][j].isFree()) {
		    board[i][j].setPossible(true);
		    i+=dir;
		    j+=1;
		    cnt++;
		}

		// Átlósan másik irány
		i = current.getI() + dir;
		j = current.getJ() - 1;
		while(i >= 0 && i < 8 && j >= 0 && j < 8 && board[i][j].isFree()) {
		    board[i][j].setPossible(true);
		    i+=dir;
		    j-=1;
		    cnt++;
		}
		
		if(cnt == 0) return null;
		
		current.setSelected(true);
		current.setFreedome(false);
		return current;
		
	}
	
	// Vissza adja azt a mezőt ahol az a torony van amivel a játékosnak lépni kell
	public Field currentField(Player aktPlayer,Color lastFieldColor) {
		for(int i = 0; i< 8; i++) {
			for(int j = 0; j < 8; j++) {
				if(!board[i][j].isFree()) {
					Tower aktTower = board[i][j].getTower();
					if(aktTower.getColor().compare(lastFieldColor)) {
						if(aktTower.getOwnerColor().compare(aktPlayer.getColor())) {
							return board[i][j];
						}
					}
				}
			}
		}
		return null;
		
	}
	public void flipBoard() {
	    Field[][] flippedBoard = new Field[8][8];
	    for (int i = 0; i < 8; i++) {
	        for (int j = 0; j < 8; j++) {
	            flippedBoard[i][j] = board[8 - i - 1][8 - j - 1];
	        }
	    }

	    this.board = flippedBoard;
	}
}