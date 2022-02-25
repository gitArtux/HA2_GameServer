package de.tuberlin.sese.swtpp.gameserver.model.xiangqi;

import java.io.Serializable;


public class General extends Figure implements Serializable {
	
	private static final long serialVersionUID = 3589471797532078075L;
	
	private int topB;
	private int botB;
	private int leftB = 3;
	private int rightB = 5;
	
	public General(int[] pos, boolean color, char repr, Board board) {
		super(pos, color, repr, board);
		addToCheckable();
		if(color) {	// black
			board.blackGeneral = this;
			topB=0;
			botB=2;
		} else {
			board.redGeneral = this;
			topB=7;
			botB=9;
		}
	}
	
	
	@Override
	public boolean reachable(int[] square)	{
		if((square[0]<=botB && square[0]>=topB) && (square[1]<=rightB && square[1]>=leftB) &&
				((Math.abs(square[0]-getPosition()[0])==1 && (square[1]==getPosition()[1])) || (Math.abs(square[1]-getPosition()[1])==1 && (square[0]==getPosition()[0])))){
			return true;
		}
		return false;
	}

}
