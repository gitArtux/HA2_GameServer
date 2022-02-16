package de.tuberlin.sese.swtpp.gameserver.model.xiangqi;

import java.io.Serializable;

public class Advisor extends Figure implements Serializable {
	
	public Advisor(int[] pos, boolean color, char repr) {
		super(pos, color, repr);
	}
	
	public boolean possibleMoves(int[] square, boolean color) { // not Finished
		// switch
		
		return false;
	}

	@Override
	public boolean tryMove(int[] square) {
		
		if(outOfBoard(square)) {
			return false;
		}
		
		if (!(inPalace(square, this.getColor()))) {
			return false;
		}
		

		
		return false;
		
	}
	

}
