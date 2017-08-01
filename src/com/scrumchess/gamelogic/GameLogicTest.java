package com.scrumchess.gamelogic;

import com.alonsoruibal.chess.Board;
import com.alonsoruibal.chess.movegen.LegalMoveGenerator;

public class GameLogicTest {
	
	MoveValidator moveValidator;
	Board board = new Board();
	LegalMoveGenerator legalMoveGenerator = new LegalMoveGenerator();
	int moves[] = new int[256];
	
	public static void main(String argsp[]) {
		GameLogicTest glt = new GameLogicTest();
		glt.smokeTest();
		
	}
	
	public void smokeTest() {
		System.out.println("Start SmokeTest!");
		board.setFen(Board.FEN_START_POSITION);
		legalMoveGenerator.generateMoves(board, moves, 0);
		for(int i =0; i< 256; i++) {
			cycle();
			System.out.println(i+". : "+board.getFen()+" END? : "+
					board.isEndGame());
		}
		board.isEndGame();
	}
	
	private int game() {
		return 0;
	}
	
	
	private void cycle() {
		legalMoveGenerator.generateMoves(board, moves, 0);
		board.doMove(moves[0]);
	}
	
}
