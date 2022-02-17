package de.tuberlin.sese.swtpp.gameserver.model.xiangqi;

import java.io.Serializable;

public class General extends Figure implements Serializable {
	
	private static final long serialVersionUID = 3589471797532078075L;
	
	public General(int[] pos, boolean color, char repr, Board board) {
		super(pos, color, repr, board);
		addToCheckable();
	}
	
		
	public boolean inPalace(int[] square, boolean color) {	
			
		if(color) {
			int[] a = {0, 2};
			return palaceCalc(a, square);
		}
			
		if(!color) {
			int[] a = {7, 9};
			return palaceCalc(a, square);
		}
			
		return false;
				
	}   
		
	boolean palaceCalc(int[] a, int[] square) {
		
		if(square[0] >= a[0] && square[0] <= a[1] &&
				square[1] >= 3 && square[1] <= 5){
					return true;
			}
			
			return false;
		}
	
	public boolean possibleMove(int[] square, int[] a) {
		
		if(square[0] == a[0]+1 	&& square[1] == a[1]
				||
				square[0] == a[0]-1 && square[1] == a[1]
				||
				square[0] == a[0] 	&& square[1] == a[1]+1	
				||
				square[0] == a[0] 	&& square[1] == a[1]-1){
			
			return true;
		}
		
		return false;
	}
	
	
	@Override
	public boolean tryMove(int[] square) { // red = false, black = true; red beginnt & ist unten
	
		if(outOfBoard(square)) { // Checkt ob square[] im Board ist & nicht OutOfBounds
			return false;
		}
		
		if (!(inPalace(square, this.getColor()))) { // Checkt, ob square[] im Palast befindet
			return false;
		}
		
		int a[] = {this.getPostion()[0], this.getPostion()[1]}; 
		
		if(possibleMove(square, a)) {
			
			if(this.isEmpty(square)) {
				return true;
			}
			
			if(this.sameColor(square)) {
				return false;
			}
			
		}
		return false;
		
	}
	
	@Override
	public  boolean givesCheck() {
		// TODO:
		return false;
	}
	

		

	
}
