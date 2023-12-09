package GameControl;

import java.io.Serializable;

import Elements.Board;
import Elements.Color;
import Elements.Field;
import Elements.Player;

//Az osztály a játék logikájának kezeléséért felelős
public class Game implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1363733060696158533L;
	private Player blackPlayer;
	private Player whitePlayer;
	private Board board;
	
	private Player aktPlayer;
	private Player winnerPlayer;
	private boolean over = false;
	private boolean first = true;
	private Field currentField;
	private boolean patt = false;
	
	private Game previousState;
	
	private boolean saved;
	
	public Game() {
		this.whitePlayer = new Player(new Color(Color.WHITE));
		this.blackPlayer = new Player(new Color(Color.BLACK));
		this.over = false;
		this.board = new Board(false);
		this.aktPlayer = whitePlayer;
	}
	
	// Másoló konstruktor
	public Game(Game other) {
	    this.blackPlayer = new Player(other.blackPlayer);
	    this.whitePlayer = new Player(other.whitePlayer);
	    this.board = new Board(other.board);
	    this.aktPlayer = (other.aktPlayer == other.blackPlayer) ? this.blackPlayer : this.whitePlayer;
	    this.over = other.over;
	    this.first = other.first;
	    this.currentField = board.getFieldAtPlace(other.currentField.getI(), other.currentField.getJ());
	    this.patt = other.patt;
	    this.previousState = (other.previousState == null) ? null : new Game(other.previousState);
	    this.saved = false;
	}
	
	public Board getBoard() {
		return this.board;
	}
	
	// Elvégzi a következő lépést
	public void nextMove(int x, int y) {
	    this.setSaved(false);
	    if(first) {
	        this.currentField = this.board.getFieldAtPlace(x, y);
	        if(!currentField.getTower().getOwnerColor().compare(this.aktPlayer.getColor())) {
	            return;
	        }
	        
	        this.previousState = new Game(this);
	        first = false;
	        
	        board.setPossibleFieldsFirstMove(this.aktPlayer,this.currentField);
	        
	        return;
	    } 
	    if(!first) {
	        Field selected = this.board.getFieldAtPlace(x, y);
	        if(!selected.isPossible()) return;
	        
	        this.previousState = new Game(this);
	        
	        this.currentField.setFreedome(true);
	        selected.setTower(this.currentField.removeTower());
	        
	        this.currentField = selected;
	        if(this.isFinishingMove()) {
	            this.over = true;
	            this.winnerPlayer = this.aktPlayer;
	            this.board.clearPossibilities();
	            return;
	        }
	        this.updateAktPLayer();
	        this.currentField = this.board.setPossibleFieldsNow(this.aktPlayer,this.currentField.getColor());
	        if(currentField == null) {
	            this.patt  = true;
	            this.updateAktPLayer();
	            this.winnerPlayer = this.aktPlayer;
	            return;
	        }
	    }
	}
	// Frissíti az aktuális játékost
	
	private void updateAktPLayer() {
		if(aktPlayer.getColor().compare(new Color(Color.WHITE))) {
			this.aktPlayer = this.blackPlayer;
			return;
		}
		this.aktPlayer = this.whitePlayer;
	}
	
	public boolean isOver() {
		return this.over;
	}
	public boolean isPatt() {
		return this.patt;
	}

	public Player getWinnerPlayer() {
		return winnerPlayer;
	}
	
	// Ellenörzi, hogy az adott lépés befejező lépés-e
	private boolean isFinishingMove() {
	
		if(currentField.getI() == 0 & currentField.getTower().getOwnerColor().compare(new Color(Color.BLACK))) {
			return true;
		}
		if(currentField.getI() == 7 & currentField.getTower().getOwnerColor().compare(new Color(Color.WHITE))) {
			return true;
		}
		return false;
		
	}

	public boolean isSaved() {
		return saved;
	}
	
	public boolean isFirst() {
		return first;
	}

	public void setSaved(boolean saved) {
		this.saved = saved;
	}
	// Visszalép egy lépést
	public Game backOneMove() {
		return  new Game(this.previousState);
	}
	public Player getAktPlayer() {
		return this.aktPlayer;
	}
	public Player getWhitePlayer() {
		return this.whitePlayer;
	}
}