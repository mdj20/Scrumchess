package com.scrumchess.transit.game;

import com.scrumchess.transit.game.identification.GameIdentificationInteger;
import com.scrumchess.transit.game.playerconfiguration.PlayerConfiguration;
import com.scrumchess.transit.game.state.State;
import com.scrumchess.transit.move.MoveList;

public class CompleteGameInfoBuilder {

	private State state;
	private MoveList moveList;
	private GameIdentificationInteger gameIdentificationInteger;
	private PlayerConfiguration playerConfiguration;
	
	public CompleteGameInfoBuilder(){ 
		
	}
	
	public CompleteGameInfo build(){
		
		
		
		
		
		SimpleCompleteGameInfo ret = new SimpleCompleteGameInfo();
	}
	
	public boolean checkBuildable(){
		
	}
	
	
}
