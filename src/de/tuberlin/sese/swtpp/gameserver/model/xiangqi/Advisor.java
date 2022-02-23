package de.tuberlin.sese.swtpp.gameserver.model.xiangqi;

import java.io.Serializable;

public class Advisor extends Figure implements Serializable {
	
	private static final long serialVersionUID = 3589471797532078076L;
	
	private int topB;
	private int botB;
	private int leftB = 4;
	private int rightB = 6;
	
	public Advisor(int[] pos, boolean color, char repr, Board board) {
		super(pos, color, repr, board);
		checkable=false;
		if (color) { //black
			topB=0;
			botB=2;
		}
		else {
			topB=7;
			botB=9;
		}
	}
	
	@Override 
	public boolean reachable(int[] square) {
		System.out.println("Advisor");
		if ((square[0]<=botB && square[0]>=topB) && (square[1]<=rightB && square[1]>=leftB)&&
		((square[0]-1==getPosition()[0] || square[0]+1==getPosition()[0])&&(square[1]-1==getPosition()[1] || square[1]+1==getPosition()[1]))) {
			return true;
		}
		return false;
	}
}
