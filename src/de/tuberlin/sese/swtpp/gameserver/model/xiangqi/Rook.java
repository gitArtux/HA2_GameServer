package de.tuberlin.sese.swtpp.gameserver.model.xiangqi;

import java.io.Serializable;

public class Rook extends Figure implements Serializable {
	
	public Rook(int[] pos, boolean color, char repr, Board board) {
		super(pos, color, repr, board);
		addToCheckable();
	}

	public boolean possibleMove(int[] square, int[] a) {
		
		if(square[0] != a[0] && square[1] == a[1]){
				return true;
		}
		
		if(square[0] == a[0] && square[1] != a[1]){
			return true;
		}
		
		return false;
	}
	
	public boolean verticalRange(int[] square, int[] a, boolean forward) {
		
		if(forward) {
			for(; square[0] > a[0]; ++square[0]) {
				if(!this.isEmpty(square)) {
					return false;
				}
			}
		}else {
			for(; square[0] < a[0]; --square[0]) {
				if(!this.isEmpty(square)) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	public boolean horizontalRange(int[] square, int[] a, boolean forward) {
		
		if(forward) {
			for(; square[1] < a[1]; ++square[0]) {
				if(!this.isEmpty(square)) {
					return false;
				}
			}
		}else {
			for(; square[1] > a[1]; --square[0]) {
				if(!this.isEmpty(square)) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	public boolean closeRange(int[] square, int[] a) {
		if((square[0] == a[0]+1) ^ (square[1] == a[1]+1) ^ 
				(square[0] == a[0]-1) ^ (square[1] == a[1]-1) ){
			return true;
		} else {
			return false;
		}
	}
	
	public boolean reachable(int[] square, int[] a) {
		
		if(closeRange(square, a)) {
			return true;
		}
		
		if(square[0] != a[0]) {
			if(square[0] > a[0]) {
				return verticalRange(square, a, true);
			}else {
				return verticalRange(square, a, false);
			}	
		} else if(square[1] != a[1]) {
			if(square[1] > a[1]) {
				return  horizontalRange(square, a, true);
			}else {
				return  horizontalRange(square, a, false);
			}
		}
		
		return false;
	}
	
	public boolean flags(int[] square, int[] a) {
		
		if(outOfBoard(square)) {
			return true;
		}
		
		return false;
	}
	
	@Override
	public boolean tryMove(int[] square) {

		int backUpPos[] = {this.getPostion()[0], this.getPostion()[1]};
		
		if(flags(square, backUpPos)) {
			return false;
		}
		
		
		
		
		
		// Nur da zum testen, NICHT FINAL
		if(!this.isEmpty(square)) {
			if(this.sameColor(square)) {
				return false;
			}else {
				board.getBoardEntry(square).removeFromCheckable();
			}
		}
			
		this.setPosition(square); // Muss bearbeitet werden
		
		return true;
		//
		
	}
	
	@Override
	public  boolean givesCheck() {
		// TODO:
		return false;
	}
	



}
