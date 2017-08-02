package com.scrumchess.gamelogic.exploresearch;

import com.alonsoruibal.chess.Config;
import com.alonsoruibal.chess.search.SearchEngine;
import com.alonsoruibal.chess.search.SearchParameters;

public class ExploreSearchEngine {
	
	SearchEngine searchEngine;
	
	
	ExploreSearchEngine(){
		long time = System.nanoTime();
		searchEngine = new SearchEngine(buildConfig());
		searchEngine.init();
		searchEngine.go(new SearchParameters());
		int bestMove = searchEngine.getBestMove();
		System.out.println(bestMove);
		searchEngine.go(new SearchParameters());
		bestMove = searchEngine.getBestMove();
		System.out.println(bestMove);
		long end = System.nanoTime();
		//System.out.println(end-time);
	}
	
	
	private Config buildConfig(){
		Config ret;
		ret = new Config();
		return ret;
	}
	
	
	private int explore(){
		return 0;
	}
	
	public static void main(String args[]){		
		System.exit(new ExploreSearchEngine().explore());
	}

}
