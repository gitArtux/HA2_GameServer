package de.tuberlin.sese.swtpp.gameserver.model.xiangqi;

import java.io.Serializable;

public class Rook extends Figure implements Serializable {
	
	private static final long serialVersionUID = -8473244559854737517L;
	
	public Rook(int[] pos, boolean color, char repr, Board board) {
		super(pos, color, repr, board);
		addToCheckable();
	}

	@Override
	public boolean reachable(int[] square) {
		if (square[0]<getPosition()[0] && square[1]==getPosition()[1]) {
			for (int x = getPosition()[0]; x==square[0]; x--) {
				if(board.getBoardEntry(new int[] {x, getPosition()[1]})!=null) {
					return false;
				}
			}
		}
		else if (square[0]>getPosition()[0] && square[1]==getPosition()[1]){
			for (int x = getPosition()[0]; x==square[0]; x++) {
				if(board.getBoardEntry(new int[] {x, getPosition()[1]})!=null) {
					return false;
				}
			}
		}
		else if (square[1]<getPosition()[1] && square[0]==getPosition()[0]) {
			for (int x = getPosition()[1]; x==square[1]; x--) {
				if(board.getBoardEntry(new int[] {getPosition()[0], x})!=null) {
					return false;
				}
			}
		}
		else if (square[1]>getPosition()[1] && square[0]==getPosition()[0]) {
			for (int x = getPosition()[1]; x==square[1]; x++) {
				if(board.getBoardEntry(new int[] {getPosition()[0], x})!=null) {
					return false;
				}
			}
		}
		else {
			return false;
		}
		return true;
	}
}
