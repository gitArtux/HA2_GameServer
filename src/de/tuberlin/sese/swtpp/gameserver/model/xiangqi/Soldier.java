package de.tuberlin.sese.swtpp.gameserver.model.xiangqi;

import java.io.Serializable;

public class Soldier extends Figure implements Serializable {
	int dir = -1;
	
	public Soldier(int[] pos, boolean color, char repr, Board board) {
		super(pos, color, repr, board);
		addToCheckable();
		if (color) {
			dir = 1;
		}
	}
	
	@Override
	public boolean reachable(int[] square) {
		if ((getPosition()[0]+dir==square[0] && getPosition()[1]==square[1]) || 
		(((color && getPosition()[0]>=5) || (!color && getPosition()[0]<=4)) && Math.abs(getPosition()[1]-square[1])==1 )) {
			return true;
		}
		return false;
	}
}
