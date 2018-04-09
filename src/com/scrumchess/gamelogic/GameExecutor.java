package com.scrumchess.gamelogic;

public interface GameExecutor {
	public boolean startGameFromFen(String fen);
	public boolean executeMove(AlgebraicNotation algebraicNotation);
	public boolean checkMove(AlgebraicNotation algebraicNotation);
	public int numberOfMoves();
	public int getGameStatus();
	public String getFen();
	public String getShortFen();
	public void undoMove();

}
