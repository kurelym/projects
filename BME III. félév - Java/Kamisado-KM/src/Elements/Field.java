package Elements;

import java.io.Serializable;

//Az osztály a játéktábla egy mezőjét kezeli
public class Field implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8866166117587337273L;
	private Color color;
	private boolean freedome;
	private boolean possible;
	private boolean selected;
	private Tower tower;
	private int[] coordinates;
	
	public Field(Color color,int i, int j) {
		this.color  = color;
		this.freedome = true;
		this.selected = false;
		this.tower = null;
		this.coordinates = new int[2];
		this.coordinates[0] = i;
		this.coordinates[1] = j;
	}
	
	// Másoló konstruktor
	public Field(Field other) {
	    this.color = new Color(other.color);
	    this.freedome = other.freedome;
	    this.possible = other.possible;
	    this.selected = other.selected;
	    this.tower = (other.tower == null) ? null : new Tower(other.tower);
	    
	    this.coordinates = other.coordinates.clone();
	}
	// Beállítja a mező tornyát, ha a mező szabad
	public void setTower(Tower tower) {
		if(this.isFree()) {
			this.tower = tower;
			this.setFreedome(false);
			this.setPossible(false);
		}
	}
	public Tower getTower() {
		return this.tower;
	}
	public Tower removeTower() {
		this.setFreedome(true);
		return this.tower;
	}
	
	public boolean isFree() {
		return this.freedome;
	}
	public boolean isPossible() {
		return this.possible;
	}
	
	public void setFreedome(boolean freedome) {
		this.freedome = freedome;
	}
	
	public void setPossible(boolean possible) {
		this.possible = possible;
	}
	
	public Color getColor() {
		return this.color;
	}
	
	public int getI() {
		return coordinates[0];
	}
	public int getJ() {
		return coordinates[1];
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
}
