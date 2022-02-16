package de.tuberlin.sese.swtpp.gameserver.model.xiangqi;

import java.io.Serializable;

public class Rook extends Figure implements Serializable {
	
	public Rook(int[] pos, boolean color, char repr, Board board) {
		super(pos, color, repr, board);
		addToCheckable();
	}

	@Override
	public boolean tryMove(int[] square) {

		if(outOfBoard(square)) {
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
