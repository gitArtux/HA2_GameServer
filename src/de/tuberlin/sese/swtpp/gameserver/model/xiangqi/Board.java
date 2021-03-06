package de.tuberlin.sese.swtpp.gameserver.model.xiangqi;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import de.tuberlin.sese.swtpp.gameserver.model.Player;

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
	
	public boolean deathstare() {
		if (blackGeneral.getPosition()[1]==redGeneral.getPosition()[1]) {
			for (int y = blackGeneral.getPosition()[0]+1; y!=redGeneral.getPosition()[0]; y++) {
				if (getBoardEntry(new int[] {y,blackGeneral.getPosition()[1]})!=null) {
					return false;
				}
			}
			return true;
		}
		return false;
	}
	
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
		}
		return new General(pos, color, repr, this);
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
	
	/*
	// IsMate: BEGIN
	
	public boolean canMoveForIsMate(Figure f, int[] a, Figure backUpFig, int[] backUpPos) {
		
		boolean check;
		
		if(!f.reachable(a) || backUpFig.getColor() == f.getColor() || f== f.getBoard().getBoardEntry(a)) { // Skippt eigene Position um Fehler zu meiden
			return false;
		}
		
		if(backUpFig != null) { // Muss leider diese Methoden 2x mit "!=null"-ifs checken
			backUpFig.removeFromList();
		}
			
		f.setPosition(a);
			
		check = f.helperIsCheck();
			
		f.setPosition(backUpPos);
			
		if(backUpFig != null) {
			backUpFig.addToCheckable();
		}
		
		return !check; // returnt true, wenn check falsch ist aka. wenn kein Schack bei dem Zug, kann der Spieler dem Schach entfliehen
		
	}
	
	public boolean iteratorForIsMate(Figure f) {
		
		int[] backUpPos = f.getPosition();
		Figure backUpFig;
		int i, j;
		
		for(i = 0; i < 9; i++){
			for(j = 0; j < 8; j++){
				int[] a = {i, j};
				backUpFig = getBoardEntry(a);
				if(canMoveForIsMate(f, a, backUpFig, backUpPos)){ // Kann die Figur sich bewegen? Wenn JA, returnt iteratorForIsMate FALSE
					return false;
				}
			}	
		}
		
		return true;
		
	}
	
	public boolean isMate(List<Figure> l, Player player) { // l = Liste der gegnerischen Figuren
	
		for(Figure f : l) { // Geht mit allen Figuren zu der 2xLoop von iteratorForIsMate
			if(!iteratorForIsMate(f)) {
				return false;
			}
		}
		
		player.setWinner();
		player.finishGame();
		player.getGame().getNextPlayer().finishGame(); // Beide Spieler m??ssen ja finishGame() machen, oder? Ansonsten l??sch' diese Zeile
		
		return true;
		
	}
	
	// IsMate END
	*/
}

