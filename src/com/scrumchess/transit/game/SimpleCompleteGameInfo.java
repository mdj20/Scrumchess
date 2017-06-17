package com.scrumchess.transit.game;

import java.util.List;

import com.scrumchess.transit.game.identification.GameIdentificationInteger;
import com.scrumchess.transit.game.state.GameState;
import com.scrumchess.transit.move.MoveList;
import com.scrumchess.transit.move.OrdinalMove;

public class SimpleCompleteGameInfo implements CompleteGameInfo{
	private GameState gameState;
	private MoveList moveList;
	private GameIdentificationInteger gameIdentificationInteger;
	
	@Override
	public String getFen() {
		return gameState.getFen();
	}

	@Override
	public int getHalfMoveNumber() {
		return gameState.getHalfMoveNumber();
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

}
