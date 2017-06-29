package com.scrumchess.transit.game;

import com.scrumchess.transit.game.identification.GameIdentification;
import com.scrumchess.transit.game.playerconfiguration.PlayerConfiguration;
import com.scrumchess.transit.game.state.State;
import com.scrumchess.transit.move.MoveList;

public interface CompleteGameInfo extends State, GameIdentification, MoveList, PlayerConfiguration {
	
}
