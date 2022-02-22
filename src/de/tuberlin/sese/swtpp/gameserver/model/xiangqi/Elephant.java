package de.tuberlin.sese.swtpp.gameserver.model.xiangqi;

import java.io.Serializable;

public class Elephant extends Figure implements Serializable {
	
	
	
	public Elephant(int[] pos, boolean color, char repr, Board board) {
		super(pos, color, repr, board);
		checkable=false;
	}
	
	public boolean possibleMove(int[] square, int[] a) {
		
		if(square[0] == a[0]+2 || square[0] != a[0]-2) {
			if(square[1] == a[1]+2 || square[1] == a[1]-2) {
				return true;
			}
		}
		
		return false;
		
	}
	
	public boolean reachable(int[] square, int[] a) {
		
		int[] crossThrough = {-96, -96};
		
		if(square[0] == a[0]-2) {
			crossThrough[0] = a[0]-1;
		}else {
			crossThrough[0] = a[0]+1;
		}
		
		if(square[1] == a[1]-2) {
			crossThrough[1] = a[1]-1;
		}else {
			crossThrough[1] = a[1]+1;
		}
		
		return this.isEmpty(crossThrough);
		
	}
	
	public boolean flags(int[] square, int[] a, boolean color) {
		if (outOfRiver(square, color)) {
			return true;
		}
		
		if(!possibleMove(square, a)) {
			return true;
		}
		
		if(!reachable(square, a)) {
			return true;
		}
		
		return false;
	}
	
	@Override
	public boolean tryMove(int[] square) {
		
		int backUpPos[] = {this.getPostion()[0], this.getPostion()[1]};
		
		if(flags(square, backUpPos, this.getColor())) {
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
		return false;
	}

		

}
