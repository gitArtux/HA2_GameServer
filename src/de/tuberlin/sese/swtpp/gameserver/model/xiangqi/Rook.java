package de.tuberlin.sese.swtpp.gameserver.model.xiangqi;

import java.io.Serializable;

public class Rook extends Figure implements Serializable {
	
	public Rook(int[] pos, boolean color, char repr, Board board) {
		super(pos, color, repr, board);
		addToCheckable();
	}

	public boolean possibleMove(int[] square, int[] a) {
		
		
		return false;
	}
	
	public boolean reachable(int[] square, int[] a) {
		
		return false;
	}
	
	public boolean flags(int[] square, int[] a) {
		if(outOfBoard(square)) {
			return true;
		}
		
		if(!possibleMove(square, a)) {
			return true;
		}
		
		if(!reachable(square, a)) {
			return true;
		}
		
		return false;
	}
	
	@Override
	public boolean tryMove(int[] square) {

		if(outOfBoard(square)) {
			return false;
		}
		
		
		
		// Nur da zum testen, NICHT FINAL
		if(!this.isEmpty(square)) {
			if(this.sameColor(square)) {
				return false;
			}else {
				board.getBoardEntry(square).removeFromCheckable();
			}
		}
			
		this.setPosition(square); // Muss bearbeitet werden
		
		return true;
		//
		
	}
	
	@Override
	public  boolean givesCheck() {
		// TODO:
		return false;
	}
	



}
