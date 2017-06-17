package com.scrumchess.transit.game;

import com.scrumchess.transit.game.identification.GameIdentificationInteger;
import com.scrumchess.transit.game.state.GameState;
import com.scrumchess.transit.move.MoveList;

public interface CompleteGameInfo extends GameState, GameIdentificationInteger, MoveList {
	
}
