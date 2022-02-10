package de.tuberlin.sese.swtpp.gameserver.model.xiangqi;

import java.io.Serializable;

public class Board implements Serializable{
	private Figure[][] board;
	private String boardFEN;
	
	
	private Figure createFigure(char c, int[] pos) {
		if (Character.isUpperCase(c)){
			switch(c) {
			case 'S':
				return new Soldier(pos, true, c);
			case 'A':
				return new Advisor(pos, true, c);
			case 'E':
				return new Elephant(pos, true, c);
			case 'H':
				return new Horse(pos, true, c);
			case 'R':
				return new Rook(pos, true, c);
			case 'C':
				return new Cannon(pos, true, c);
			case 'G':
				return new General(pos, true, c);
			}
		}
		
		switch(c) {
		case 's':
			return new Soldier(pos, false, c);
		case 'a':
			return new Advisor(pos, false, c);
		case 'e':
			return new Elephant(pos, false, c);
		case 'h':
			return new Horse(pos, false, c);
		case 'r':
			return new Rook(pos, false, c);
		case 'c':
			return new Cannon(pos, false, c);
		case 'g':
			return new General(pos, false, c);
			
		// always return
		// TODO: Exeption Handling
		default:
			return null;
		}
	}
	
	
	public String getBoard() {
		return String.copyValueOf(boardFEN.toCharArray());
	}
	
	
	public Figure getBoardEntry(int[] pos) {
		return board[pos[0]][pos[1]];
	}
	
	public void setBoardEntry(int[] pos, Figure f) {
		board[pos[0]][pos[1]] = f;
	}
	
	// rheagaehr/9/1c5c1/s1s1s1s1s/9/9/S1S1S1S1S/1C5C1/9/RHEAGAEHR
	public void setBoard(String boardFEN) {
		this.boardFEN=String.copyValueOf(boardFEN.toCharArray());
		String[] lines = boardFEN.split("/", 0);	
		// TODO: check validity // Exeptionhandling
		
		board = new Figure[10][9];
		for (int row=0;row<10;row++) {
			String strline = lines[row];
			int col = 0;
			 for(int i = 0; i<strline.length(); i++) {
				 char c = strline.charAt(i);

				 // case Digit
			     if (Character.isDigit(c)) {
			    	 for (int t = 0; t<Character.getNumericValue(c); t++) {
			    		 board[row][col] = null;
			    		 col ++;
			    	 }
			     }
			     
			     // case Figure
			     else {
			    	 board[row][col] = createFigure(c, new int[] {row, col});
			    	 col++;
			    	 
			     }
			 }
		}
	}
}
