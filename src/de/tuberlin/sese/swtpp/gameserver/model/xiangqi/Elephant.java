package de.tuberlin.sese.swtpp.gameserver.model.xiangqi;

import java.io.Serializable;

public class Elephant extends Figure implements Serializable {
	
	public Elephant(int[] pos, boolean color, char repr) {
		super(pos, color, repr);
	}
	
	@Override
	public boolean tryMove(int[] square) {
		// TODO Auto-generated method stub
		return false;
	}


		

}
