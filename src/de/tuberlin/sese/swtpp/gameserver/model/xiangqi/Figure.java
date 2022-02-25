package de.tuberlin.sese.swtpp.gameserver.model.xiangqi;

import java.io.Serializable;

import de.tuberlin.sese.swtpp.gameserver.model.Player;



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
	
	public int[] getPosition() {
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
	
	protected boolean helperIsCheck() {
		if (color && !getBoard().deathstare()) { // black
			for (Figure f: getBoard().redFigsCheckable) {
				if (f.reachable(board.blackGeneral.getPosition())) {
					return true;
				}
			}
			return false;
		} 
		else if (!getBoard().deathstare()){
			for (Figure f: getBoard().blackFigsCheckable) {
				if (f.reachable(board.redGeneral.getPosition())) {
					return true;
				}
			}
			return false;
		}
		return true;
	}
	
	public boolean isCheck(Figure f, int[] backupPosition) { 
		if (helperIsCheck()) {
			setPosition(backupPosition);
			if(f!=null) {
				f.setPosition(f.getPosition());
			}
			
			return false;
		}
		else if (f!=null){
			f.removeFromList();
		}
		return true;
	}
	
	
	public boolean tryMove(int[] square, Player player) {
		int backUpPos[] = {getPosition()[0], getPosition()[1]};
		Figure f = board.getBoardEntry(square);
		if ((f!=null && f.getColor()==getColor()) || !reachable(square)) {
			return false;
		}
		setPosition(square);
		boolean moveAllowed = isCheck(f, backUpPos);
		
		// ismate //
		
		return moveAllowed;
	}
	

	protected abstract boolean reachable(int[] square);

}
