package de.tuberlin.sese.swtpp.gameserver.model.xiangqi;

import java.io.Serializable;

public abstract class Figure implements Serializable {
	protected int[] pos; 			// row, col
	protected boolean color; 		// red = false, black = true
	protected char repr;
	
	public Figure(int[] pos, boolean color, char repr) {
		setPosition(pos);
		setColor(color);
		setRepr(repr);
	}
	
	public void setPosition(int[] pos) {
		this.pos = pos.clone();
	}
	
	public int[] getPostion() {
		return pos.clone();
	}
	
	public void setColor(boolean color) {
		this.color = color;
	}
	
	public boolean getColor() {
		return color;
	}
	
	public void setRepr(char repr) {
		this.repr = repr;
	}
	
	public char getRepr() {
		return repr;
	}
	
	
	public abstract boolean tryMove(int[] square);

}
