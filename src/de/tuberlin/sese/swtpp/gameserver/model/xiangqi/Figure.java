package de.tuberlin.sese.swtpp.gameserver.model.xiangqi;

import java.io.Serializable;



public abstract class Figure implements Serializable{
	protected int[] pos; 			// row, col
	protected boolean color; 		// red = false, black = true
	protected char repr;

	

	
	public Figure(int[] pos, boolean color, char repr) {
		setPosition(pos);
		setColor(color);
		setRepr(repr);
		//setBoard(board);
		
	}
	/*
	protected void setBoard(Board board) {
		this.board=board;
	}
	
	protected void addToCheckable() {
		if(color) {
			board.blackFigsCheckable.add(this);
		}
		else {
			board.redFigsCheckable.add(this);
		}
	}
	
	public void removeFromCheckable() {
		if(color) {
			board.blackFigsCheckable.remove(this);
		}
		else {
			board.redFigsCheckable.remove(this);
		}
	}
	*/
	
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
	
	// PUNI Begin
	
	public boolean outOfBoard(int[] square) {
		
		// Primitiven Datentypen (z.B. int) haben kein null => muss man nicht checken
		
		if(square[0] < 0 || square[0] > 9) {
			return true;
		}
		
		if(square[1] < 0 || square[1] > 8) {
			return true;
		}
		
		return false;
	}
	
	public boolean outOfRiver(int[] square, boolean color) {
		
		if(color) {
			if(square[0] >= 5){
			return true;
			}
		}
			
		if(!color) {
			if(square[0] <= 4){
				return true;
			}
		}
			
			return false;
				
	}   
		
		public boolean inPalace(int[] square, boolean color) {
			
			if(color) {
				if(square[0] >= 0 && square[0] <= 2 &&
						square[1] >= 3 && square[1] <= 5){
				return true;
				}
			}
				
			if(!color) {
				if(square[0] >= 7 && square[0] <= 9 &&
						square[1] >= 3 && square[1] <= 5){
				return true;
				}
			}
				
				return false;
					
		}   	
	
	// PUNI END
	
	public abstract boolean tryMove(int[] square);
	
	

}
