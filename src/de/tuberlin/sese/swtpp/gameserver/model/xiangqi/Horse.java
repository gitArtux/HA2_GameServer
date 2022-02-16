package de.tuberlin.sese.swtpp.gameserver.model.xiangqi;

import java.io.Serializable;

public class Horse extends Figure implements Serializable {

	public Horse(int[] pos, boolean color, char repr) {
		super(pos, color, repr);
		//addToCheckable();
	}
	
	@Override
	public boolean tryMove(int[] square) {

		if(outOfBoard(square)) {
			return false;
		}
		
		return false;
	}
	



}
