package de.tuberlin.sese.swtpp.gameserver.model.xiangqi;

import java.io.Serializable;

public class Advisor extends Figure implements Serializable {
	
	public Advisor(int[] pos, boolean color, char repr, Board board) {
		super(pos, color, repr, board);
	}
	
		
	public boolean inPalace(int[] square, boolean color) {
		
		if(color) {
			int[] a = {0, 2, 1};
			return palaceCalc(a, square);	
		}	
		if(!color) {
			int[] a = {7, 9, 8};
			return palaceCalc(a, square);
		}
			
			return false;	
	}   	
	
	boolean palaceCalc(int[] a, int[] square) {
		if((square[0] == a[0] && square[1]  == (3^5))
				||
			(square[0] == a[1] && square[1]  == (3^5))
				||
			square[0] == a[2] && square[1] == 4){
			return true;
		}
		
		return false;
	}
		
	public boolean possibleMove(int[] square) { // not Finished
		
		if(this.getPostion()[1] != 4) {
			if(square[1] != 4) {
				return false;
			}
		}
			
			return true;
		
	}

	@Override
	public boolean tryMove(int[] square) {
		
		int backUpPos[] = {this.getPostion()[0], this.getPostion()[1]};
		
		System.out.println(this.getRepr() + " ruft tryMove() grad");
		
		if(outOfBoard(square)) {
			return false;
		}
		
		if (!(inPalace(square, this.getColor()))) {
			return false;
		}
		
		if (!possibleMove(square)) {
			return false;
		}
		
		if(!this.isEmpty(square)) {
			if(this.sameColor(square)) {
				return false;
			}
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
