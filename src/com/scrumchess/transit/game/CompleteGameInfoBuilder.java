package com.scrumchess.transit.game;

import java.util.ArrayList;

import com.scrumchess.transit.game.identification.GameIdentificationInteger;
import com.scrumchess.transit.game.identification.SimpleGameIdentificationInteger;
import com.scrumchess.transit.game.playerconfiguration.PlayerConfiguration;
import com.scrumchess.transit.game.state.State;
import com.scrumchess.transit.move.MoveAlgebraic;
import com.scrumchess.transit.move.MoveList;
import com.scrumchess.transit.move.SimpleMoveAlgebraic;

public class CompleteGameInfoBuilder {

	private String fen;
	private ArrayList<SimpleMoveAlgebraic> moves;
	
	
	private State state;
	private MoveList moveList;
	private GameIdentificationInteger gameIdentificationInteger;
	private PlayerConfiguration playerConfiguration;
	
	public CompleteGameInfoBuilder(){ 
		
	}
	
	public void setFen(String fen){
		this.fen = fen;
	}
	public int addMove(String move){
		if (moves==null){
			moves = new ArrayList<SimpleMoveAlgebraic>();
		}
		moves.add(new SimpleMoveAlgebraic(move,moves.size()));
		return moves.size();
	}
	
	public int addMove(Iterable<String> imoves){
		if (moves==null){
			moves = new ArrayList<SimpleMoveAlgebraic>();
		}
		int i=moves.size();
		for(String s:imoves){
			moves.add(new SimpleMoveAlgebraic(s,i++));
		}
		return moves.size();
	}
	
	public void setGameId(long gameID){
		gameIdentificationInteger = new SimpleGameIdentificationInteger(gameID);
	}
	
	
	
	public CompleteGameInfo build(){
		SimpleCompleteGameInfo ret = new SimpleCompleteGameInfo();
	}
	
	public boolean checkBuildable(){
		
	}
	
	
}
