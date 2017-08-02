package com.scrumchess.gamelogic;

import java.util.Random;

import com.alonsoruibal.chess.Board;
import com.alonsoruibal.chess.movegen.LegalMoveGenerator;

public class GameLogicTest {
	
	MoveValidator moveValidator;
	Board board = new Board();
	LegalMoveGenerator legalMoveGenerator = new LegalMoveGenerator();
	int moves[] = new int[256];
	Random rando = new Random(System.currentTimeMillis());
	
	public static void main(String argsp[]) {
		GameLogicTest glt = new GameLogicTest();
		glt.smokeTest();
		
	}
	
	public void smokeTest() {
		System.out.println("Start SmokeTest!");
		board.setFen(Board.FEN_START_POSITION);
		legalMoveGenerator.generateMoves(board, moves, 0);
		for(int i =0; i< 512; i++) {
			cycle();
			System.out.println(i+". : "+board.getFen()+" END? : "+
					board.isEndGame()+" "+board.isMate()+" "+board.isDraw());
		}
		board.isEndGame();
	}
	
	private int game() {
		return 0;
	}
	
	
	private void cycle() {
		int i = legalMoveGenerator.generateMoves(board, moves, 0);
		System.out.println(i);
		i = (i<1)?1:i;
		board.doMove(moves[rando.nextInt(i)]);
	
	}
	
}
