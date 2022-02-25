package de.tuberlin.sese.swtpp.gameserver.model.xiangqi;

import java.io.Serializable;

public class Horse extends Figure implements Serializable {
	
	private static final long serialVersionUID = -1691581958969084331L;

	public Horse(int[] pos, boolean color, char repr, Board board) {
		super(pos, color, repr, board);
		addToCheckable();
	}
	
	@Override
	public boolean reachable(int[] square) {
		if ((((square[0]==getPosition()[0]-2 && square[1]==getPosition()[1]-1) || (square[0]==getPosition()[0]-2 && square[1]==getPosition()[1]+1)) && board.getBoardEntry(new int[] {getPosition()[0]-1, getPosition()[1]})==null)
		|| (((square[0]==getPosition()[0]-1 && square[1]==getPosition()[1]-2) || (square[0]==getPosition()[0]+1 && square[1]==getPosition()[1]-2)) && board.getBoardEntry(new int[] {getPosition()[0], getPosition()[1]-1})==null)
		|| (((square[0]==getPosition()[0]-1 && square[1]==getPosition()[1]+2) || (square[0]==getPosition()[0]+1 && square[1]==getPosition()[1]+2)) && board.getBoardEntry(new int[] {getPosition()[0], getPosition()[1]+1})==null)
		|| (((square[0]==getPosition()[0]+2 && square[1]==getPosition()[1]-1) || (square[0]==getPosition()[0]+2 && square[1]==getPosition()[1]+1)) && board.getBoardEntry(new int[] {getPosition()[0]+1, getPosition()[1]})==null)){
			return true;
		}
		return false;
	}

}
