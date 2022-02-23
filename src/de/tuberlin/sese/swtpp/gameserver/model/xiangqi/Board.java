package de.tuberlin.sese.swtpp.gameserver.model.xiangqi;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class Board implements Serializable{
	
	private static final long serialVersionUID = -2496645958349826119L;
	
	/* THE BOARD:
	 * 
	 * 		  board[0][0]
	 * 			  |
	 * 			  |	    Black
	 * 		9  [[ r h e a g a e h r ]
	 * 		8	[ 0 0 0 0 0 0 0 0 0 ] --- board[1]
	 * 		7	[ 0 c 0 0 0 0 0 c 0 ] 
	 * 		6	[ s 0 s 0 s 0 s 0 s ] 
	 * 		5	[ 0 0 0 0 0 0 0 0 0 ]
	 * 		4	[ 0 0 0 0 0 0 0 0 0 ]
	 * 		3	[ S 0 S 0 S 0 S 0 S ]
	 * 		2	[ 0 C 0 0 0 0 0 C 1 ]
	 * 		1	[ 0 0 0 0 0 0 0 0 0 ] 	
	 * 		0	[ R H E A G A E H R ]]
	 * 
	 * 			  a b c d e f g h i
	 * 					 Red
	 * 
	 */
	
	
	private Figure[][] board;
	
	public List<Figure> blackFigsCheckable = new LinkedList<>();
	public List<Figure> redFigsCheckable = new LinkedList<>();
	public Figure blackGeneral;
	public Figure redGeneral;
	

	
	// translates strPos to LogicPos for Example "a9" --> new int[] {0, 0}
	public int[] translateToPos(String strPos) {
		char[] charArrayPos = strPos.toCharArray();
		return new int[] {9-Character.getNumericValue(charArrayPos[1]), Character.getNumericValue(charArrayPos[0])-10};
	}
	
	
	// get boardFEN
	public String getBoard() {
		String boardFEN="";
		int count = 0;
		
		for (int row=0; row<10; row++) {
			for (int col=0; col<9; col++) {
				
				//  null
				if (board[row][col]==null && col==8) {	
					boardFEN += Character.forDigit(count+1, 10);
					count = 0;
					
				}
				
				else if (board[row][col]==null){
					count++;
				}

				
				// Figure
				else if (count!=0) {
					boardFEN += Character.forDigit(count, 10);
					count=0;
					boardFEN += board[row][col].getRepr();
				}
				else {
					boardFEN += board[row][col].getRepr();
				}
			}
			
			// end of row
			if (row!=9) {
				boardFEN+='/';
			}
		}
		return boardFEN;
		
	}
	
	// get & set boardEntry at Pos
	public Figure getBoardEntry(int[] pos) {
		return board[pos[0]][pos[1]];
	}
	
	public void setBoardEntry(int[] pos, Figure f) {
		board[pos[0]][pos[1]] = f;
	}
	
	// for setBoard()
	private Figure createFigure(char c, int[] pos) {
		Figure f = null;
		boolean color = false;
		char repr = c;
		
		if (Character.isLowerCase(c)){

			c = Character.toUpperCase(c);
			color = true;
		}
		System.out.println(c);
		switch(c) {
		case 'S':
			return new Soldier(pos, color, repr, this);
		case 'A':
			return new Advisor(pos, color, repr, this);
		case 'E':
			return new Elephant(pos, color, repr, this);
		case 'H':
			return new Horse(pos, color, repr, this);
		case 'R':
			return new Rook(pos, color, repr, this);
		case 'C':
			return new Cannon(pos, color, repr, this);
		case 'G':
			return new General(pos, color, repr, this);
		}
		return f;
	}
	
	// setBoard
	public void setBoard(String boardFEN) {
		
		// board
		String[] lines = boardFEN.split("/", 0);	
		// TODO: check validity / Exeptionhandling
		
		board = new Figure[10][9];
		
		for (int row=0;row<10;row++) {
			String strline = lines[row];
			int col = 0;
			for(int i = 0; i<strline.length(); i++) {
				char c = strline.charAt(i);
	
				 // case Digit
			    if (!Character.isDigit(c)) {
			    	board[row][col] = createFigure(c, new int[] {row, col});
			    	col++;
			    	continue;
			    	
			    }
			       
			     // case Figure
			    for (int t = 0; t<Character.getNumericValue(c); t++) {
		    		board[row][col] = null;
		    		col ++;
		    	}
			}
		}
	}
}
