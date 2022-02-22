package de.tuberlin.sese.swtpp.gameserver.model.xiangqi;

import java.io.Serializable;
import java.util.List;



public abstract class Figure implements Serializable{
	
	private static final long serialVersionUID = -2084306411289420422L;
	
	protected boolean checkable=true;
	protected int[] pos; 			// row, col
	protected boolean color; 		// red = false, black = true
	protected char repr;
	protected Board board;

	

	
	public Figure(int[] pos, boolean color, char repr, Board board) {
		this.pos=pos;
		setColor(color);
		setRepr(repr);
		setBoard(board);
		
	}
	
	protected void setBoard(Board board) {
		this.board=board;
	}
	
	protected Board getBoard() {
		return board;
	}
	
	protected void addToCheckable() {
		if(!color) {
			board.redFigsCheckable.add(this);
		}
		else {
			board.blackFigsCheckable.add(this);
		}
	}
	
	public void removeFromCheckable() {
		if(!color) {
			board.redFigsCheckable.remove(this);
		}
		else {
			board.blackFigsCheckable.remove(this);
		}
	}

	
	public void setPosition(int[] pos) {
		getBoard().setBoardEntry(this.pos, null);
		this.pos = pos.clone();
		getBoard().setBoardEntry(this.pos, this);
	}
	
	public int[] getPostion() {
		return pos.clone();
	}
	
	public void setColor(boolean color) {
		this.color = color;
	}
	
	public boolean getColor() {
		return color;
	}
	
	public void setRepr(char repr) {
		this.repr = repr;
	}
	
	public char getRepr() {
		return repr;
	}
	
	protected void removeFromList() { // this
		if(checkable && color) { //black
			getBoard().blackFigsCheckable.remove(this);
		}
		else if(checkable && !color){
			getBoard().redFigsCheckable.remove(this);
		}
	}
	
	
	
	// PUNI Begin
	
	
	public boolean outOfRiver(int[] square, boolean color) {
		
		if(color) {
			if(square[0] >= 5){
			return true;
			}
		}
			
		if(!color) {
			if(square[0] <= 4){
				return true;
			}
		}
			
			return false;
				
	}   
	
	public boolean sameColor(int[] square) {
		if(board.getBoardEntry(square).getColor() == this.getColor()) {
			return true;
		}
		
		return false;
	}
	
	public boolean isEmpty(int[] square) {
		if(board.getBoardEntry(square) == null) {
			return true;
		}
		
		return false;
	}
	
	public boolean isEnemyGeneral(int[] square, boolean c) {
		if(board.getBoardEntry(square).getRepr() == 'g' && !c) {
			return true;
		}
		if(board.getBoardEntry(square).getRepr() == 'G' && c) {
			return true;
		}
		return false;
	}
	
	public int[] getEnemyGeneralPos(boolean color) {
		if(color) {
			return board.redGeneral.getPostion();
		} else {
			return board.blackGeneral.getPostion();
		}
	}
	
	protected boolean helperIsCheck() {
		if (color) { // black
			for (Figure f: getBoard().blackFigsCheckable) {
				if (f.givesCheck()) {
					return true;
				}
			}
		} 
		else {
			for (Figure f: getBoard().blackFigsCheckable) {
				if (f.givesCheck()) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean isCheck(Figure f, int[] backupPosition) { // 
		if (helperIsCheck()) {
			setPosition(backupPosition);
			f.setPosition(getPostion());
			return false;
		}
		else {
			f.removeFromList();
			return true;
		}
		
	}
	
	// PUNI END
	
	public abstract boolean tryMove(int[] square);
	
	
	public abstract boolean givesCheck();
	
	

}
