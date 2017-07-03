package com.scrumchess.transit.game;

import java.util.ArrayList;
import com.scrumchess.transit.game.identification.GameIdentification;
import com.scrumchess.transit.game.identification.SimpleGameIdentification;
import com.scrumchess.transit.game.playerconfiguration.PlayerConfiguration;
import com.scrumchess.transit.game.playerconfiguration.SimplePlayerConfiguration;
import com.scrumchess.transit.game.state.SimpleState;
import com.scrumchess.transit.game.state.State;
import com.scrumchess.transit.move.MoveAlgebraic;
import com.scrumchess.transit.move.MoveList;
import com.scrumchess.transit.move.SimpleMoveAlgebraic;
import com.scrumchess.transit.move.SimpleMoveList;

public class CompleteGameInfoBuilder {

	private String fen = null ;
	private boolean fenSet = false ;
	private ArrayList<MoveAlgebraic> moves = new ArrayList<MoveAlgebraic>();
	
	private State state = null;
	private MoveList moveList = null;
	private GameIdentification gameIdentification ;
	private PlayerConfiguration playerConfiguration ;
	
	public CompleteGameInfoBuilder(){ }
	
	public void setFen(String fen){
		this.fen = fen;
		fenSet=true;
	}
	
	public int addMove(String move){
		if (moves==null){
			moves = new ArrayList<MoveAlgebraic>();
		}
		moves.add(new SimpleMoveAlgebraic(move,moves.size()));
		return moves.size();
	}
	
	public int addMove(Iterable<String> imoves){
		if (moves==null){
			moves = new ArrayList<MoveAlgebraic>();
		}
		int i=moves.size();
		for(String s:imoves){
			moves.add(new SimpleMoveAlgebraic(s,i++));
		}
		return moves.size();
	}
	
	public void setPlayerConfiguration(int pc){
		this.playerConfiguration = new SimplePlayerConfiguration(pc);
	}
	
	public void setGameId(long gameID){
		gameIdentification = new SimpleGameIdentification(gameID);
	}
	
	public CompleteGameInfo build(){
		SimpleCompleteGameInfo ret = null;
		if(checkBuildable()){
			this.state = new SimpleState(fen,moves.size());
			this.moveList = new SimpleMoveList(moves);
			ret = new SimpleCompleteGameInfo(this.state,this.moveList,this.gameIdentification,this.playerConfiguration);
		}
		return ret;
	}
	
	public boolean checkBuildable(){
		boolean ret = false;
			if (fenSet&&gameIdentification!=null&&playerConfiguration!=null){
				ret = true;
			}	
		return ret;
	}
}
