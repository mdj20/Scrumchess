package com.scrumchess.gamelogic.exploresearch;

import com.alonsoruibal.chess.Board;
import com.alonsoruibal.chess.Config;
import com.alonsoruibal.chess.search.SearchEngine;
import com.alonsoruibal.chess.search.SearchParameters;

public class ExploreSearchEngine {
	
	SearchEngine searchEngine;
	Board board;
	
	ExploreSearchEngine(){
		searchEngine = new SearchEngine(buildConfig());
		searchEngine.init();
		board = searchEngine.getBoard();
	}
	
	
	private Config buildConfig(){
		Config ret;
		ret = new Config();
		return ret;
	}
	
	private void cycle() {
		searchEngine.go(new SearchParameters());
		int move = searchEngine.getBestMove();
		board.doMove(move);
	}
	
	
	private int explore(){
		while(0==board.isEndGame()) {
			cycle();
			System.out.println(board.getFen());
		}
		System.out.println(board.isEndGame());
		return 0;
	}
	
	public static void main(String args[]){		
		System.exit(new ExploreSearchEngine().explore());
	}

}
