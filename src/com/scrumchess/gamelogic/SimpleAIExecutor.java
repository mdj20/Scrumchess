package com.scrumchess.gamelogic;

import java.util.ArrayList;

import com.alonsoruibal.chess.Config;
import com.alonsoruibal.chess.Move;
import com.alonsoruibal.chess.search.SearchEngine;
import com.alonsoruibal.chess.search.SearchParameters;

public class SimpleAIExecutor implements AIExecutor {
	
	private final int DEFAULT_SEARCH_TIME = 1;
	private SearchEngine searchEngine = new SearchEngine(buildConfig());
	private SearchParameters searchParameters = buildSearchParameters(DEFAULT_SEARCH_TIME);  //  build default search Paramerters 
	SimpleGameExecutor simpleGameExecutor = new SimpleGameExecutor(searchEngine.getBoard());
	
	public SimpleAIExecutor(){
		
	}
	
	@Override
	public String getAIMoveString() {
		searchEngine.go(searchParameters);
		String move = Move.toString(searchEngine.getBestMove());
		return move;
	}
	
	
	// builds new search params with default values
	private SearchParameters buildSearchParameters(int time){
		return SearchParameters.get(time);
	}

	private Config buildConfig() {
		Config ret; 
		ret = new Config();
		return ret;
	}


	@Override
	public boolean startGameFromFen(String fen) {
		searchEngine = new SearchEngine(buildConfig());
		searchEngine.getBoard().setFen(fen);
		simpleGameExecutor = new SimpleGameExecutor(searchEngine.getBoard());
		return true;
	}


	@Override
	public boolean executeMove(AlgebraicNotation algebraicNotation) {
		return simpleGameExecutor.executeMove(algebraicNotation);
	}


	@Override
	public boolean checkMove(AlgebraicNotation algebraicNotation) {
		return simpleGameExecutor.checkMove(algebraicNotation);
	}


	@Override
	public int numberOfMoves() {
		return simpleGameExecutor.numberOfMoves();
	}


	@Override
	public int getGameStatus() {
		return simpleGameExecutor.getGameStatus();
	}


	@Override
	public String getFen() {
		return simpleGameExecutor.getFen();
	}


	@Override
	public String getShortFen() {
		return simpleGameExecutor.getShortFen();
	}
	
	public static void main(String args[]) {
		SimpleAIExecutor saie = new SimpleAIExecutor();
		System.out.println(saie.getFen());
		System.out.println(saie.getGameStatus());
		while(saie.getGameStatus()==0) {
			String move = saie.getAIMoveString();
			TestClass tc = new TestClass(move);
			System.out.println(move);
			if(!saie.executeMove(tc))
				System.out.println("ERROR");
			System.out.println(saie.getFen());
		}

		System.out.println(saie.getGameStatus());
		
		System.out.println(saie.numberOfMoves());
	
	}
	
	static class TestClass implements AlgebraicNotation {
		String an;
		TestClass(String str){
			this.an=str;
		}
		@Override
		public String getAlabraicNotation() {
			return an;
		}
	}

	@Override
	public void undoMove() {
		simpleGameExecutor.undoMove();
	}

	@Override
	public boolean isWhiteTurn() {
		return simpleGameExecutor.isWhiteTurn();
	}

	@Override
	public boolean executeMove(String stringAN) {
		return simpleGameExecutor.executeMove(stringAN);
	}

	@Override
	public boolean checkMove(String stringAN) {
		return simpleGameExecutor.checkMove(stringAN);
	}
	
}
