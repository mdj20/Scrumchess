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
		int tempMove = Move.getFromString(board, an, true);
		if ((ret = moveValid(tempMove))==true){
			this.move=tempMove;
			this.moveReady = true;
		}
		return ret;
	}
	public String doMove(){
		String ret = badMove;
		if (this.moveReady){
			this.board.doMove(this.move);
			ret = board.getFen();
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
	
	// smoketesting methods 
	public static void main(String args[]){
		smokeTest();
	}
	public static void smokeTest(){
		System.out.println("SmokeTest");
		MoveValidator mv = MoveValidator.createWithFen(Board.FEN_START_POSITION);
		System.out.println(mv.getFen());
		System.out.println(mv.setMove("e2e4"));
		System.out.println(mv.doMove());
	}
}
