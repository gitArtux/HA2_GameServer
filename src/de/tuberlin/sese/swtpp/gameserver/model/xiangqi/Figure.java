package de.tuberlin.sese.swtpp.gameserver.model.xiangqi;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;


public abstract class Figure implements Serializable {
	protected int[] pos; 			// row, col
	protected boolean color; 		// red = false, black = true
	protected char repr;
	
	protected static List<Figure> redFigsCheckable = new LinkedList<>();
	protected static List<Figure> blackFigsCheckable = new LinkedList<>();
	
	public Figure(int[] pos, boolean color, char repr) {
		setPosition(pos);
		setColor(color);
		setRepr(repr);
	}
	
	protected void addToCheckable() {
		if(color) {
			blackFigsCheckable.add(this);
		}
		else {
			redFigsCheckable.add(this);
		}
	}
	
	public void removeFromCheckable() {
		if(color) {
			blackFigsCheckable.remove(this);
		}
		else {
			redFigsCheckable.remove(this);
		}
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
	
	public abstract boolean givesCheck();

}
