package Elements;

import java.io.Serializable;

public class Tower implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8882454560473066681L;
	private Color color;
	private Color owner;
	
	public Tower(Color color, Color owner) {
		this.color = color;
		this.owner = owner;
	}
	
	//másoló konstruktor
	public Tower(Tower other) {
	    this.color = new Color(other.color);
	    this.owner = new Color(other.owner);
	}
	public Color getColor() {
		return color;
	}
	
	public Color getOwnerColor() {
		return owner;
	}
}
