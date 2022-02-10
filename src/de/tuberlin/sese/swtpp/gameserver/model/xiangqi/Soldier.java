package de.tuberlin.sese.swtpp.gameserver.model.xiangqi;

import java.io.Serializable;

public class Soldier extends Figure implements Serializable {

	public Soldier(int[] pos, boolean color, char repr) {
		super(pos, color, repr);
	}

	@Override
	public boolean tryMove() {
		// TODO Auto-generated method stub
		return false;
	}


}
