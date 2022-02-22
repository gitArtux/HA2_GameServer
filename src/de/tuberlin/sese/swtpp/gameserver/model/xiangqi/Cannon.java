package de.tuberlin.sese.swtpp.gameserver.model.xiangqi;

import java.io.Serializable;

public class Cannon extends Figure implements Serializable {

	public Cannon(int[] pos, boolean color, char repr, Board board) {
		super(pos, color, repr, board);
		addToCheckable();
	}
	
	@Override
	public boolean tryMove(int[] square) {
		return false;
	}

	@Override
	public  boolean givesCheck() {
		// TODO:
		return false;
	}

		

}
