package de.tuberlin.sese.swtpp.gameserver.model.xiangqi;

import java.io.Serializable;

public class General extends Figure implements Serializable {
	
	public General(int[] pos, boolean color, char repr) {
		super(pos, color, repr);
		//addToCheckable();
	}
	
	@Override
	public boolean tryMove(int[] square) {
		// TODO Auto-generated method stub
		return false;
	}
	

		

	
}