package de.tuberlin.sese.swtpp.gameserver.model.xiangqi;

import java.io.Serializable;

public class Advisor extends Figure implements Serializable {
	
	public Advisor(int[] pos, boolean color, char repr) {
		super(pos, color, repr);
	}

	@Override
	public boolean tryMove(int[] square) {
		// TODO Auto-generated method stub
		return false;
	}
	

}
