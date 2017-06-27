package com.scrumchess.transit.game;

import java.util.List;

import com.scrumchess.transit.game.identification.GameIdentificationInteger;
import com.scrumchess.transit.game.identification.SimpleGameIdentificationInteger;
import com.scrumchess.transit.game.playerconfiguration.PlayerConfiguration;
import com.scrumchess.transit.game.playerconfiguration.SimplePlayerConfiguration;
import com.scrumchess.transit.game.state.GameState;
import com.scrumchess.transit.game.state.SimpleGameState;
import com.scrumchess.transit.move.MoveList;
import com.scrumchess.transit.move.OrdinalMove;

public class SimpleCompleteGameInfo implements CompleteGameInfo{
	private GameState gameState;
	private MoveList moveList;
	private GameIdentificationInteger gameIdentificationInteger;
	private PlayerConfiguration playerConfiguration;
	
	@Override
	public String getFen() {
		return gameState.getFen();
	}

	@Override
	public int getHalfMoveNumber() {
		return gameState.getHalfMoveNumber();
	}

	protected void setGameState(GameState gameState) {
		this.gameState = gameState;
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

	public SimpleCompleteGameInfo getEmptyMoveInstance(String fen, int playerConfiguration , String gameID){
		SimpleCompleteGameInfo ret = new SimpleCompleteGameInfo();
		ret.gameState = new SimpleGameState(fen,0);
		ret.gameIdentificationInteger = new SimpleGameIdentificationInteger(gameID);
		ret.playerConfiguration = new SimplePlayerConfiguration(playerConfiguration);
		return ret;
	}

	@Override
	public int getConfigurationValue() {
		return this.playerConfiguration.getConfigurationValue();
	}
}
