package com.scrumchess.gamelogic;

import java.util.ArrayList;

import com.alonsoruibal.chess.Board;
import com.alonsoruibal.chess.Move;

public class SimpleGameExecutor implements GameExecutor {
	
	Board board;
	ArrayList<AlgebraicNotation> moveHistory;

	
	SimpleGameExecutor(){
		this.board = new Board();
		moveHistory = new ArrayList<AlgebraicNotation>();
		board.startPosition();
	}
	
	
	@Override
	public boolean startGameFromFen(String fen) {
		boolean ret = false;
		board = new Board();
		board.setFen(fen);
		moveHistory = new ArrayList<AlgebraicNotation>();
		return ret;
	}

	@Override
	public boolean executeMove(AlgebraicNotation algebraicNotation) {
		boolean ret = false;
		int move = moveIntFromString(algebraicNotation.getAlabraicNotation());
		if(moveValid(move)) {
			if(board.doMove(move)) {
				ret = true;
				moveHistory.add(algebraicNotation);
			}
		}
		return ret;
	}

	@Override
	public boolean checkMove(AlgebraicNotation algebraicNotation) {
		boolean ret = false;
		int move = moveIntFromString(algebraicNotation.getAlabraicNotation());
		if(moveValid(move)) {
			ret = true;
		}
		return ret;
	}

	@Override
	public int numberOfMoves() {
		return moveHistory.size();
	}

	@Override
	public int getGameStatus() {
		return board.isEndGame();
	}

	@Override
	public String getFen() {
		return board.getFen();
	}

	@Override
	public String getShortFen() {
		return FenUtility.getBoardFenSection(board.getFen());
	}
	
	private int moveIntFromString(String str) {
		int ret = -1;
		ret = Move.getFromString(board, str, true);
		return ret;
	}
	
	private boolean moveValid(int checkMove){
		boolean ret = false;
		if (Move.NONE != checkMove && Move.NULL != checkMove){
			ret = true;
		}
		return ret;
	}
	
	

}
