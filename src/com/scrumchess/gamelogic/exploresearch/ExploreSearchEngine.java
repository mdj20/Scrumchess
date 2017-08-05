package com.scrumchess.gamelogic.exploresearch;

import java.util.ArrayList;
import java.util.HashMap;

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
		searchEngine.go(SearchParameters.get(5));
		int move = searchEngine.getBestMove();
		board.doMove(move);
	}
	

	private int explore(){	
		while(0==board.isEndGame()) {
			cycle();
		}
		return board.isEndGame();
	}
	
	private static HashMap<Integer,Integer> tally(ArrayList<Integer> list){
		HashMap<Integer,Integer> results = new HashMap<Integer, Integer>();
		for(Integer i : list) {
			if(results.containsKey(i)) {
				results.put(i, results.get(i)+1);
			}
			else {
				results.put(i,1);
			}
		}
		return results;
	}
	
	public static void main(String args[]){		
		ArrayList<Integer> results = new ArrayList<Integer>();
		ArrayList<Long> times = new ArrayList<Long>();
		long time;
		
		for (int i = 0 ; i < 25 ; i++) {
			time = System.currentTimeMillis();
			results.add(new ExploreSearchEngine().explore());	
			times.add(System.currentTimeMillis()-time);
		}
		HashMap<Integer,Integer> tally = tally(results);
		for(Integer i:tally.keySet() ) {
			System.out.println(i+" :"+tally.get(i));
		}
		int i = 0;
		for(Long l:times) {
			System.out.println(i+": "+l);
			i++;
		}
	}
}
