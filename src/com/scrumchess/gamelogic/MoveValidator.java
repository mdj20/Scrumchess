package com.scrumchess.gamelogic;
import com.alonsoruibal.chess.Board;
import com.alonsoruibal.chess.Move;


/**Determines whether a move is valid for a particular fen, and returns the new fen if the move was valid.
 * 
 * @author Matthew D. Jeffreys
 *
 */

// this class will determine the validity of a move give a game state and a move in algebraic notation
public class MoveValidator {
	
	private static final String badMove = "BADMOVE";
	private Board board;
	private int move; // int representation of move 
	private boolean moveReady = false;
	private boolean moveValid = false;
	
	
	/**
	 * 
	 * 
	 * @param fen BoardConfiguration 
	 */
	
	private MoveValidator(String fen){
		board = new Board();
		board.setFen(fen);
		this.moveReady = false;
		this.move = Move.NONE;
		moveValid = false;
	}
	
	/**Creates a new MoveValidator
	 * 
	 * @param fen inital board configuration
	 * @return MoveValidator object
	 */
	
	public static MoveValidator createWithFen(String fen){
		return new MoveValidator(fen);
	}
	
	/**
	 * Queues up one move to be executed
	 * 
	 * @param an Move in algebraic notation
	 * @return true if move was a valid format, (not necessarily legal)
	 */
	
	
	// sets move via algebraic notation
	public boolean setMove(String an){
		boolean ret = false;
		this.moveReady = false;
		int tempMove = Move.getFromString(board, an, true); // Move is from com.alonsoruibal.chess
		if ((ret = moveValid(tempMove))==true){
			this.move=tempMove;
			this.moveReady = true;
		}
		return ret;
	}
	
	/** Executes queued move
	 * 
	 * @return true if move was legal and executed, false if either the move was not set or was illegal.
	 */
	
	// executes move returns fen representation of board after move.
	public boolean doMove(){ 
		boolean ret = false;
		if (this.moveReady){
			this.board.doMove(this.move);
			ret = true;
		}
		return ret;
	}
	
	/**
	 *  returns either new fen configuration or the original configuration MoveValidator was created with.
	 * @return FEN
	 */
	
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
	
	/**
	 * returns true if the current board configuration states that it is whites move.
	 * @return
	 */

	
	// returns true if turn is white
	public boolean isWhiteTurn(){
		return board.getTurn();
	}
	public int isEndGame(){
		return this.board.isEndGame();
	} 
}
