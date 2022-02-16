package de.tuberlin.sese.swtpp.gameserver.model.xiangqi;

import java.io.Serializable;

public class Elephant extends Figure implements Serializable {
	
	
	
	public Elephant(int[] pos, boolean color, char repr, Board board) {
		super(pos, color, repr, board);
	}
	
	@Override
	public boolean tryMove(int[] square) {
		
		if(outOfBoard(square)) {
			return false;
		}
		
		if (outOfRiver(square, this.getColor())) {
			return false;
		}
		
		return false;
	}
	
	@Override
	public  boolean givesCheck() {
		// TODO:
		return false;
	}

		

}
