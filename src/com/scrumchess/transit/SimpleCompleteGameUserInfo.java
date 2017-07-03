package com.scrumchess.transit;

import com.scrumchess.transit.game.SimpleCompleteGameInfo;
import com.scrumchess.transit.game.identification.GameIdentification;
import com.scrumchess.transit.game.playerconfiguration.PlayerConfiguration;
import com.scrumchess.transit.game.state.State;
import com.scrumchess.transit.move.MoveList;
import com.scrumchess.transit.user.MultiUser;

public class SimpleCompleteGameUserInfo extends SimpleCompleteGameInfo implements CompleteGameUserInfo {
	
	MultiUser multiUser;

	protected SimpleCompleteGameUserInfo(State state, MoveList moveList, GameIdentification gameIdentification,
			PlayerConfiguration playerConfiguration, MultiUser multiUser) {
		super(state, moveList, gameIdentification, playerConfiguration);
		this.multiUser = multiUser;
	}
	

	@Override
	public String getPseudonym(int index) {
		return multiUser.getPseudonym(index);
	}

	@Override
	public boolean hasPseudonym(int index) {
		return multiUser.hasPseudonym(index);
	}

	@Override
	public String getId(int index) {
		return multiUser.getId(index);
	}

	@Override
	public String getId() {
		return multiUser.getId();
	}

	@Override
	public String getPseudonym() {
		return multiUser.getPseudonym();
	}

	@Override
	public boolean hasPseudonym() {
		return multiUser.hasPseudonym();
	}

}
