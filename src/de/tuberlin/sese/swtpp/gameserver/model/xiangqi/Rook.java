package de.tuberlin.sese.swtpp.gameserver.model.xiangqi;

import java.io.Serializable;

public class Rook extends Figure implements Serializable {
	
	private static final long serialVersionUID = -8473244559854737517L;
	
	public Rook(int[] pos, boolean color, char repr, Board board) {
		super(pos, color, repr, board);
		addToCheckable();
	}

	private boolean canMoveVertical(int from, int to) {
		for (int y = from; y==to; y++) {
			System.out.println("Checking: "+y+""+getPosition()[1]);
			if(board.getBoardEntry(new int[] {y, getPosition()[1]})!=null) {
				return false;
			}
		}
		System.out.println("test");
		return true;
	}
	
	private boolean canMoveHorizontal(int from, int to) {
		for (int x = from; x==to; x++) {
			System.out.println("Checking: "+getPosition()[0]+""+x);
			if(board.getBoardEntry(new int[] {getPosition()[0], x})!=null) {
				return false;
			}
		}
		System.out.println("test");
		return true;
	}
	
	@Override
	public boolean reachable(int[] square) {
		System.out.println("Rook");
		if (square[0]<getPosition()[0] && square[1]==getPosition()[1]) {
			return canMoveVertical(square[0], getPosition()[0]);
		}
		else if (square[0]>getPosition()[0] && square[1]==getPosition()[1]){
			return canMoveVertical(getPosition()[0], square[0]);
		}
		else if (square[1]<getPosition()[1] && square[0]==getPosition()[0]) {
			return canMoveHorizontal(square[1], getPosition()[1]);
		}
		else if (square[1]>getPosition()[1] && square[0]==getPosition()[0]) {
			return canMoveHorizontal( getPosition()[1], square[1]);
		}
		else {
			return false;
		}
	}
}
