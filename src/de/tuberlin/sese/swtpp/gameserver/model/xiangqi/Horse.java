package de.tuberlin.sese.swtpp.gameserver.model.xiangqi;

import java.io.Serializable;

public class Horse extends Figure implements Serializable {

	public Horse(int[] pos, boolean color, char repr) {
		super(pos, color, repr);
	}

	@Override
	public boolean tryMove() {
		// TODO Auto-generated method stub
		return false;
	}


}
