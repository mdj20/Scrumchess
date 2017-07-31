package com.scrumchess.transit.game;

import java.util.List;

import com.scrumchess.transit.game.identification.GameIdentification;
import com.scrumchess.transit.game.identification.SimpleGameIdentification;
import com.scrumchess.transit.game.playerconfiguration.PlayerConfiguration;
import static com.scrumchess.transit.game.playerconfiguration.PlayerConfigurationStaticTypes.Config;
import com.scrumchess.transit.game.playerconfiguration.SimplePlayerConfiguration;
import com.scrumchess.transit.game.state.State;
import com.scrumchess.transit.game.state.SimpleState;
import com.scrumchess.transit.move.MoveAlgebraic;
import com.scrumchess.transit.move.MoveList;
import com.scrumchess.transit.move.OrdinalMove;

public class SimpleCompleteGameInfo implements CompleteGameInfo{
	private State state;
	private MoveList moveList;
	private GameIdentification gameIdentification;
	private PlayerConfiguration playerConfiguration;
	
	protected SimpleCompleteGameInfo(State state, MoveList moveList,
			GameIdentification gameIdentification, PlayerConfiguration playerConfiguration){
		this.state = state;
		this.moveList = moveList;
		this.gameIdentification = gameIdentification;
		this.playerConfiguration = playerConfiguration;	
	}
	
	SimpleCompleteGameInfo(){}
	
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

	protected void setGameIdentificationInteger(GameIdentification gameIdentification) {
		this.gameIdentification = gameIdentification;
	}

	@Override
	public long getGameID() {
		return gameIdentification.getGameID();
	}

	@Override
	public List<MoveAlgebraic> getMoves() {
		return moveList.getMoves();
	}

	public static SimpleCompleteGameInfo getNewGameInstance(String fen, int playerConfiguration, long gameID){
		SimpleCompleteGameInfo ret = new SimpleCompleteGameInfo();
		ret.state = new SimpleState(fen,0);
		ret.gameIdentification = new SimpleGameIdentification(gameID);
		ret.playerConfiguration = new SimplePlayerConfiguration(playerConfiguration);
		return ret;
	}
	
	static SimpleCompleteGameInfo build(String fen, MoveList moves, long gameID, int playerConfiguration){
		SimpleCompleteGameInfo ret = new SimpleCompleteGameInfo();
		int nMoves = moves.getMoves().size();
		ret.state = new SimpleState(fen,nMoves);
		ret.moveList = moves;
		ret.gameIdentification = new SimpleGameIdentification(gameID);
		return ret;
	}

	@Override
	public Config getConfigurationValue() {
		return this.playerConfiguration.getConfigurationValue();
	}
}
