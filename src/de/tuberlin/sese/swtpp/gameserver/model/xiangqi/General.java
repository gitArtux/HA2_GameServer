package de.tuberlin.sese.swtpp.gameserver.model.xiangqi;

import java.io.Serializable;

public class General extends Figure implements Serializable {
	
	private static final long serialVersionUID = 3589471797532078075L;
	
	public General(int[] pos, boolean color, char repr, Board board) {
		super(pos, color, repr, board);
		addToCheckable();
	}
	
	@Override
	public boolean tryMove(int[] square) { // red = false, black = true; red beginnt & ist unten
		// TODO Auto-generated method stub
		
		if(outOfBoard(square)) {
			return false;
		}
		
		if (!(inPalace(square, this.getColor()))) {
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
