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
	
	public boolean test() {
		return true;
	}
	
	@Override
	public boolean tryMove(int[] square) {
		
		int backUpPos[] = {getPostion()[0], getPostion()[1]};
		Figure f = board.getBoardEntry(square);
		if (f!=null && f.getColor()==getColor()) {
			return false;
		}
		
		//--TO CHANGE -------
		if ((square[0]<=botB && square[0]>=topB) && (square[1]<=rightB && square[1]>=leftB)) {
			if ((square[0]-1==getPostion()[0] || square[0]+1==getPostion()[0])&&(square[1]-1==getPostion()[1] || square[1]+1==getPostion()[1])) {
				setPosition(square);
				
			}
		}
		//--TO CHANGE -------
		
		
		return isCheck(f, backUpPos);
	}
	
	
	
	@Override
	public boolean givesCheck() {
		// TODO:
		return false;
	}
	

}
