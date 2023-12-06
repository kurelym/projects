package Elements;

import java.io.Serializable;

//Az osztály a játékosok kezeléséért felelős a játékban
public class Player implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5225720146983737734L;
	private Color color;
	
	public Player(Color color) {
		this.color = color;
	}
	
	// Másoló konstruktor
	public Player(Player other) {
		this.color = new Color(other.color);
	}
	public Color getColor() {
		return this.color;
	}
}
