package de.tuberlin.sese.swtpp.gameserver.model.xiangqi;

import java.io.Serializable;

public class General extends Figure implements Serializable {
	
	private static final long serialVersionUID = 3589471797532078075L;
	
	public General(int[] pos, boolean color, char repr, Board board) {
		super(pos, color, repr, board);
		addToCheckable();
		if(color) {
			board.blackGeneral = this;
		} else {
			board.redGeneral = this;
		}
	}
	
		
	public boolean inPalace(int[] square, boolean color) {	
		if(color) {
			int[] a = {0, 2};
			return palaceCalc(a, square);
		}else {
			int[] a = {7, 9};
			return palaceCalc(a, square);
		}		
	}   
		
	boolean palaceCalc(int[] a, int[] square) {
		if(square[0] >= a[0] && square[0] <= a[1] &&
				square[1] >= 3 && square[1] <= 5){
					return true;
		}
		
		return false;
	}
	
	public boolean possibleMove(int[] square, int[] a) {
		
		if(square[0] == a[0]+1 	&& square[1] == a[1]
				||
				square[0] == a[0]-1 && square[1] == a[1]
				||
				square[0] == a[0] 	&& square[1] == a[1]+1	
				||
				square[0] == a[0] 	&& square[1] == a[1]-1){
			return true;
		}
		
		return false;
	}
	
	
	@Override
	public boolean tryMove(int[] square) { // red = false, black = true; red beginnt & ist unten

		System.out.println(this.getRepr() + " ruft tryMove() grad");
		int backUpPos[] = {this.getPostion()[0], this.getPostion()[1]};
		// Figure backUp0 = board.getBoardEntry(square); // Wie save ich die Figur?
		
		if(outOfBoard(square)) {
			return false;
		}
		
		if (!(inPalace(square, this.getColor()))) { 
			return false;
		}
		
		if (!possibleMove(square, backUpPos)) {
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
	
	public boolean blackDeathStare() {
		for(int[] i = {this.getPostion()[0], this.getPostion()[1]}; i[0] < 9; ++i[0]) {
			if(!this.isEmpty(i)) {
				if(this.isEnemyGeneral(i, this.getColor())) {
					return true;
				} else {
					return false;
				}
			}
			
		}
		return false;
	}
	
	public boolean redDeathStare() {
		for(int[] i = {this.getPostion()[0], this.getPostion()[1]}; 0 < i[0]; --i[0]) {
			if(!this.isEmpty(i)) {
				if(this.isEnemyGeneral(i, this.getColor())) {
					return true;
				} else {
					return false;
				}
			}
		}
		return false;
	}	
		
	@Override
	public  boolean givesCheck() { // General hat Deathstare als givesCheck
		
		if(color){
			if(blackDeathStare()) {
				return true;
			}
		
		} else {
			if(redDeathStare()) {
				return true;
			}
		}	
	
		return false;
	}
	
}
