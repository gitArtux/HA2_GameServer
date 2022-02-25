package de.tuberlin.sese.swtpp.gameserver.model.xiangqi;

import java.io.Serializable;

public class Cannon extends Figure implements Serializable {
	
	private static final long serialVersionUID = 4132731319741905868L;

	public Cannon(int[] pos, boolean color, char repr, Board board) {
		super(pos, color, repr, board);
		addToCheckable();
	}
	
	private boolean canMoveVertical(int from, int to, int dir) {
		boolean figBetween=false;
		for (int y = from; y!=to; y+= dir) {
			if(board.getBoardEntry(new int[] {y, getPosition()[1]})!=null && !figBetween) {
				figBetween=true;
			}
			else if(board.getBoardEntry(new int[] {y, getPosition()[1]})!=null && figBetween) {
				return false;
			}
		}
		if(board.getBoardEntry(new int[] {to, getPosition()[1]})!=null && !figBetween) {
			return false;
		}
		return true;
	}
	
	private boolean canMoveHorizontal(int from, int to, int dir) {
		boolean figBetween=false;
		for (int x = from; x!=to; x+= dir) {			
			if(board.getBoardEntry(new int[] {getPosition()[0], x})!=null && !figBetween) {
				figBetween=true;
			}
			else if (board.getBoardEntry(new int[] {getPosition()[0], x})!=null && figBetween){
				return false;
			}
		}
		if(board.getBoardEntry(new int[] {getPosition()[0], to})!=null && !figBetween) {
			return false;
		}
		return true;
	}
	
	@Override
	public boolean reachable(int[] square) {
		if (square[0]<getPosition()[0] && square[1]==getPosition()[1]) {
			return canMoveVertical(getPosition()[0]-1,square[0], -1);
		}
		else if (square[0]>getPosition()[0] && square[1]==getPosition()[1]){
			return canMoveVertical(getPosition()[0]+1, square[0], 1);
		}
		else if (square[1]<getPosition()[1] && square[0]==getPosition()[0]) {
			return canMoveHorizontal(getPosition()[1]-1,square[1], -1);
		}
		else if (square[1]>getPosition()[1] && square[0]==getPosition()[0]) {
			return canMoveHorizontal(getPosition()[1]+1, square[1], 1);
		}
		else {
			return false;
		}
	}

}
