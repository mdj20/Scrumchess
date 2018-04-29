package com.scrumchess.gamelogic;

public interface GameExecutor {
	public boolean startGameFromFen(String fen);
	public boolean executeMove(AlgebraicNotation algebraicNotation);
	public boolean executeMove(String stringAN);
	public boolean checkMove(AlgebraicNotation algebraicNotation);
	public boolean checkMove(String stringAN);
	public int numberOfMoves();
	public int getGameStatus();
	public String getFen();
	public String getShortFen();
	public void undoMove();
	public boolean isWhiteTurn();
	public boolean isCheck();
}
