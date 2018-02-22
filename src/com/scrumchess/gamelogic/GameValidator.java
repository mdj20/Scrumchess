package com.scrumchess.gamelogic;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.alonsoruibal.chess.Board;
import com.alonsoruibal.chess.Config;
import com.alonsoruibal.chess.Move;
import com.alonsoruibal.chess.search.SearchEngine;
import com.alonsoruibal.chess.search.SearchParameters;

/* 
 * 	GameValidator.java 
 */

public class GameValidator {
	private int DEFAULT_SEARCH_TIME = 2;
	private SearchEngine searchEngine;
	private Board board;
	private String fen;   // FEN
	private SearchParameters searchParameters = buildSearchParameters(DEFAULT_SEARCH_TIME);  //  build default search Paramerters 
	private int moveInt;
	private boolean moveReady;
	private final int DEFAULT_TIME = 1;

	// builds GameValidator with default configuration
	public GameValidator(String initialFen){
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
		if ( (ret = moveValid(tempMove))==true ){
			this.moveInt=tempMove;
			this.moveReady = true;
		}
		return ret;
	}
	
	private SearchEngine buildSearchEngine(String fen, Config config){
		SearchEngine ret = new SearchEngine(config);
		ret.getBoard().setFen(fen);
		return ret;
	}
	
	public String getFen(){
		return this.searchEngine.getBoard().getFen();
	}

	private Config buildConfig(){
		Config ret;
		ret = new Config();
		return ret;
	}
	
	public int getBestMove(){
		searchEngine.go(buildSearchParameters(1));
		return searchEngine.getBestMove();
	}
	
	public String getBestMoveString(){
		searchEngine.go(buildSearchParameters(1));
		return Move.toString(searchEngine.getBestMove());
	}
	
	public boolean isWhiteTurn(){
		return board.getTurn();
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
	
	public int isEndGame(){
		return board.isEndGame(); 
	}

	public static void main(String args[]){
		HashMap<Integer,Integer> set = new HashMap<Integer,Integer>();
		List<String> ends = new ArrayList<String>();
		List<Long> times = new LinkedList<Long>();
		long tempTime;
		
		for (int i = 0 ; i < 45 ; i++) {
			tempTime = System.currentTimeMillis(); 
			GameValidator gv = new GameValidator(Board.FEN_START_POSITION);
			smokeTally(set,gv.smokeTest());
			ends.add(gv.board.toString());
			times.add(System.currentTimeMillis()-tempTime);
		}
	
		for (Integer i:set.keySet()) {
			System.out.println(i+": "+set.get(i));
		}
		
		for(String s:ends) {
			System.out.println(s);
		}
	
	}
	
	private static void smokeTally(HashMap<Integer,Integer> map, Integer i) {
		if(map.containsKey(i)) {
			map.put(i,map.get(i)+1);
		}
		else {
			map.put(i,1);
		}
	}
	
	private int smokeTest(){
		SearchParameters sp = buildSearchParameters(DEFAULT_TIME);
		
		while(smokeCycle(sp)==0) {
		
		}
		
		return this.board.isEndGame();
	}
	
	private int smokeCycle(SearchParameters sp) {
		searchEngine.go(sp);
		int move = searchEngine.getBestMove();
		this.board.doMove(move);
		return this.board.isEndGame();
	}
}
