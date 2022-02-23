package de.tuberlin.sese.swtpp.gameserver.model.xiangqi;

import java.io.Serializable;

public class Elephant extends Figure implements Serializable {
	
	private int topB = 7;
	private int botB = 9;
	
	public Elephant(int[] pos, boolean color, char repr, Board board) {
		super(pos, color, repr, board);
		checkable=false;
		if(color) {
			topB = 0;
			botB = 2;
		}
	}
	
	
	@Override
	public boolean reachable(int[] square) {
		if (square[0]<=botB && square[0] >= topB && Math.abs(square[0]-getPosition()[0])==2 && Math.abs(square[1]-getPosition()[1])==2
		&& ((square[0]<getPosition()[0] && square[1]<getPosition()[1] && board.getBoardEntry(new int[] {getPosition()[0]-1, getPosition()[1]-1})==null)
		|| (square[0]>getPosition()[0] && square[1]<getPosition()[1] && board.getBoardEntry(new int[] {getPosition()[0]+1, getPosition()[1]-1})==null)
		|| (square[0]<getPosition()[0] && square[1]>getPosition()[1] && board.getBoardEntry(new int[] {getPosition()[0]-1, getPosition()[1]+1})==null)
		|| (square[0]>getPosition()[0] && square[1]>getPosition()[1] && board.getBoardEntry(new int[] {getPosition()[0]+1, getPosition()[1]+1})==null))) {
			return true;
		}
		
		return false;
	}
}
