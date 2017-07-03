package com.scrumchess.transit;

import java.util.List;

import com.scrumchess.transit.game.CompleteGameInfo;
import com.scrumchess.transit.game.SimpleCompleteGameInfo;
import com.scrumchess.transit.move.OrdinalMove;

public class SimpleCompleteGameUserInfo extends SimpleCompleteGameInfo implements CompleteGameUserInfo {

	SimpleCompleteGameUserInfo(String fen, List<OrdinalMove> moves, int gameID, int PlayerConfiguration) {
		super(fen, moves, gameID, PlayerConfiguration);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getFen() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getHalfMoveNumber() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Integer getGameInteger() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getGameID() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrdinalMove> getMoves() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Config getConfigurationValue() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getPseudonym(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasPseudonym(int index) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getId(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPseudonym() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasPseudonym() {
		// TODO Auto-generated method stub
		return false;
	}

}
