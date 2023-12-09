package Elements;

import java.io.Serializable;

//Az osztály a színek kezeléséért felelős a játékban

public class Color implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6300077481753295480L;
	public static final int ANY = -1;
	public static final int ORANGE = 0;
	public static final int BLUE = 1;
	public static final int PURPLE = 2;
	public static final int PINK = 3;
	public static final int YELLOW = 4;
	public static final int RED = 5;
	public static final int GREEN = 6;
	public static final int BROWN = 7;
	public static final int BLACK = 8;
	public static final int WHITE = 9;
	
	private int value;
	
	public Color(int value) {
		this.value = value;
	}
	
	// Másoló konstruktor
	public Color(Color other) {
	    this.value = other.value;
	}
	// Visszaadja a szín értékét
	public int getValue() {
		return value;
	}
	// Beállítja a szín értékét
	public void setValue(int value) {
		this.value = value;
	}
	// Összehasonlítja ezt a színt egy másik színnel
	public boolean compare(Color other) {
		return this.value == other.value;
	}
	
	// Visszaadja a szín szöveges reprezentációját
	public String toString() {
	    switch (value) {
	        case ORANGE:
	            return "narancssárga";
	        case BLUE:
	            return "kék";
	        case PURPLE:
	            return "lila";
	        case PINK:
	            return "rózsaszín";
	        case YELLOW:
	            return "sárga";
	        case RED:
	            return "piros";
	        case GREEN:
	            return "zöld";
	        case BROWN:
	            return "barna";
	        case BLACK:
	            return "FEKETE";
	        case WHITE:
	            return "FEHÉR";
	        default:
	            return "Ismeretlen szín";
	    }
	}
	// Visszaadja a szín java.awt.Color reprezentációját
	public java.awt.Color getAwtColor() {
	    switch (value) {
	        case ORANGE:
	            return new java.awt.Color(255, 165, 0);  // narancssárga
	        case BLUE:
	            return new java.awt.Color(100, 149, 237);  // kék
	        case PURPLE:
	            return new java.awt.Color(147, 112, 219);  // lila
	        case PINK:
	            return new java.awt.Color(255, 182, 193);  // rózsaszín
	        case YELLOW:
	            return new java.awt.Color(220, 220, 60);  // sárga
	        case RED:
	            return new java.awt.Color(205, 92, 92);  // piros
	        case GREEN:
	            return new java.awt.Color(144, 238, 144);  // zöld
	        case BROWN:
	            return new java.awt.Color(210, 180, 140);  // barna
	        case BLACK:
	        	return java.awt.Color.BLACK;  // fekete
	        case WHITE:
	        	return java.awt.Color.WHITE;  //fehér
	        default:
	            return java.awt.Color.BLACK;
	    }
	}
}
