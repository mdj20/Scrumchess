package com.scrumchess.gamelogic;
import com.alonsoruibal.chess.Board;
import com.alonsoruibal.chess.Move;

// this class will determine the validity of a move give a game state and a move in algebraic notation
public class MoveValidator {
	private static final String badMove = "BADMOVE";
	private Board board;
	private int move; // int representation of move 
	private boolean moveReady;
	
	private MoveValidator(String fen){
		board = new Board();
		board.setFen(fen);
		this.moveReady = false;
		this.move = Move.NONE;
	}
	public static MoveValidator createWithFen(String fen){
		return new MoveValidator(fen);
	}
	// sets move via algebraic notation
	public boolean setMove(String an){
		boolean ret = false;
		int tempMove = Move.getFromString(board, an, true); // Move is from com.alonsoruibal.chess
		if ((ret = moveValid(tempMove))==true){
			this.move=tempMove;
			this.moveReady = true;
		}
		return ret;
	}
	// executes move returns fen representation of board after move.
	public boolean doMove(){ 
		boolean ret = false;
		if (this.moveReady){
			this.board.doMove(this.move);
			ret = true;
		}
		return ret;
	}
	public String getFen(){
		return board.getFen();
	}
	private boolean moveValid(int checkMove){
		boolean ret = false;
		if (Move.NONE != checkMove && Move.NULL != checkMove){
			ret = true;
		}
		return ret;
	}

	// returns true if turn is white
	public boolean isWhiteTurn(){
		return board.getTurn();
	}
	// smoke testing methods 
	public static void main(String args[]){
		smokeTest();
	}
	public static void smokeTest(){
		System.out.println("SmokeTest");
		MoveValidator mv;
		mv = MoveValidator.createWithFen(Board.FEN_START_POSITION);
		String move = AlgebraicNotation.createFromCoordinates(0,6, 0, 4);

	}

}
