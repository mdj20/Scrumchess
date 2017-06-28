package com.scrumchess.transit.game;

import com.scrumchess.transit.game.identification.GameIdentificationInteger;
import com.scrumchess.transit.game.playerconfiguration.PlayerConfiguration;
import com.scrumchess.transit.game.state.State;
import com.scrumchess.transit.move.MoveList;

public interface CompleteGameInfo extends State, GameIdentificationInteger, MoveList, PlayerConfiguration {
	
}
