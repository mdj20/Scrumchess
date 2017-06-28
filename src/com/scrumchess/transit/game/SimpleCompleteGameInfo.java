package com.scrumchess.transit.game;

import java.util.List;

import com.scrumchess.transit.game.identification.GameIdentificationInteger;
import com.scrumchess.transit.game.identification.SimpleGameIdentificationInteger;
import com.scrumchess.transit.game.playerconfiguration.PlayerConfiguration;
import com.scrumchess.transit.game.playerconfiguration.SimplePlayerConfiguration;
import com.scrumchess.transit.game.state.State;
import com.scrumchess.transit.game.state.SimpleState;
import com.scrumchess.transit.move.MoveList;
import com.scrumchess.transit.move.OrdinalMove;

public class SimpleCompleteGameInfo implements CompleteGameInfo{
	private State state;
	private MoveList moveList;
	private GameIdentificationInteger gameIdentificationInteger;
	private PlayerConfiguration playerConfiguration;
	
	SimpleCompleteGameInfo(String fen, List<OrdinalMove> moves, int gameID, int PlayerConfiguration){
		
	}
	
	private SimpleCompleteGameInfo(){}
	
	@Override
	public String getFen() {
		return state.getFen();
	}

	@Override
	public int getHalfMoveNumber() {
		return state.getHalfMoveNumber();
	}

	protected void setGameState(State state) {
		this.state = state;
	}

	protected void setMoveList(MoveList moveList) {
		this.moveList = moveList;
	}

	protected void setGameIdentificationInteger(GameIdentificationInteger gameIdentificationInteger) {
		this.gameIdentificationInteger = gameIdentificationInteger;
	}

	@Override
	public Integer getGameInteger() {
		return gameIdentificationInteger.getGameInteger();
	}

	@Override
	public String getGameID() {
		return gameIdentificationInteger.getGameID();
	}

	@Override
	public List<OrdinalMove> getMoves() {
		return moveList.getMoves();
	}

	public static SimpleCompleteGameInfo getNewGameInstance(String fen, int playerConfiguration , String gameID){
		SimpleCompleteGameInfo ret = new SimpleCompleteGameInfo();
		ret.state = new SimpleState(fen,0);
		ret.gameIdentificationInteger = new SimpleGameIdentificationInteger(gameID);
		ret.playerConfiguration = new SimplePlayerConfiguration(playerConfiguration);
		return ret;
	}

	@Override
	public int getConfigurationValue() {
		return this.playerConfiguration.getConfigurationValue();
	}
}
