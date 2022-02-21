package de.tuberlin.sese.swtpp.gameserver.model.xiangqi;

import java.io.Serializable;

public class Soldier extends Figure implements Serializable {
	
	public Soldier(int[] pos, boolean color, char repr, Board board) {
		super(pos, color, repr, board);
		addToCheckable();
	}
	
	public boolean possibleMove(int[] square, int[] a, boolean color) { // not Finished
		
		int b;
		
		if(color) {
			b = a[0]+1;
		}else {
			b = a[0]-1;
		}
		
		if(square[0] == b && square[1] == a[1]) {
			return true;
		}
		
		if(this.outOfRiver(a, color)) {
			if(square[0] == b && (square[1] == a[1]+1 ^ square[1] == a[1]-1)){
				return true;
			}
		}
			
			return false;
		
	}
	
	@Override
	public boolean tryMove(int[] square) {
		
		int backUpPos[] = {this.getPostion()[0], this.getPostion()[1]};
		
		if(outOfBoard(square)) {
			return false;
		}
		
		if(!possibleMove(square, backUpPos, color)) {
			return false;
		}
		
		if(!this.isEmpty(square)) {
			if(this.sameColor(square)) {
				return false;
			}else {
				board.getBoardEntry(square).removeFromCheckable();
			}
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
		if(!this.outOfRiver(this.getPostion(), this.getColor())){
			return false;
		}
		
		int backUpPos[] = {this.getPostion()[0], this.getPostion()[1]};
		
		return false;
	}


}
