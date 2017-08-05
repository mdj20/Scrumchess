package com.scrumchess.gamelogic;


import com.alonsoruibal.chess.Board;
import com.alonsoruibal.chess.Config;
import com.alonsoruibal.chess.Move;
import com.alonsoruibal.chess.search.SearchEngine;
import com.alonsoruibal.chess.search.SearchParameters;


public class GameValidator {
	
	private SearchEngine searchEngine;
	private Board board;
	private String fen;   // FEN
	private SearchParameters searchParameters;  // FEN 
	private int moveInt;
	private boolean moveReady;
	private final int DEFAULT_TIME = 1;

	
	// builds GameValidator with default configuration
	GameValidator(String initialFen){
		this.searchEngine = new SearchEngine(buildConfig());
		this.board = searchEngine.getBoard();
		board.setFen(initialFen);
	}
	private GameValidator(){}
	
	public boolean doMove(){
		boolean ret = false;
		if (this.moveReady){
			this.board.doMove(this.moveInt);
			ret = true;
		}
		return ret;
		
	}
	
	public boolean setMove(String an){
		boolean ret = false;
		int tempMove = Move.getFromString(board, an , true); // Move is from com.alonsoruibal.chess
		if ((ret = moveValid(tempMove))==true){
			this.moveInt=tempMove;
			this.moveReady = true;
		}
		return ret;
	}
	
	public String getFen(){
		return this.board.getFen();
	}
	
	public Config buildConfig(){
		Config ret;
		ret = new Config();
		return ret;
	}
	
	public int getBestMove(){
		searchEngine.go(buildSearchParameters(1));
		return searchEngine.getBestMove();
	}
	
	private boolean moveValid(int checkMove){
		boolean ret = false;
		if (Move.NONE != checkMove && Move.NULL != checkMove){
			ret = true;
		}
		return ret;
	}
	// builds new search params with default values
	private SearchParameters buildSearchParameters(int time){
		return SearchParameters.get(time);
	}

	public static void main(String args[]){
		new GameValidator(Board.FEN_START_POSITION).smokeTest();
	}
	
	private int smokeTest(){
		return 0;
	}
	
	
}
