package com.scrumchess.transit.game.state;

import java.util.Collection;

public interface GameState {
	public String getFen();
	public int getMoveNumber();
}
