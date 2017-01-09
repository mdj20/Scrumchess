package com.scrumchess.chesslogic;

import com.alonsoruibal.chess.Board;
import com.alonsoruibal.chess.Move;

public class BoardValidator {
	
	private Board _board; 
	
	// creates a board from a fen string
	BoardValidator(String fen){
		_board = new Board();
		_board.setFen(fen);
	}
	// dmake move from albreic notation
	public boolean tryMove(String move){
		boolean ret  = false;
		int moveInt = Move.getFromString(_board,move,true);
		if (moveIntValid(moveInt)){
			ret = _board.doMove(moveInt);
		}
	}
	
	private boolean moveIntValid(int move){
		boolean ret=true;
		if(move == Move.NONE || move == Move.NULL ){
			ret = false;
		}
		return ret;
	}

}
