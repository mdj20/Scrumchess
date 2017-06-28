package com.scrumchess.transit.game.state;

import java.util.Collection;

public interface State {
	public String getFen();
	public int getHalfMoveNumber();
}
