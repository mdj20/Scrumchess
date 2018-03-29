package com.scrumchess.userrequests;

import com.scrumchess.data.Game;

public class MoveRequestResponse extends AbstractUserResponse<Game> {
	public MoveRequestResponse(boolean success, UniversalFailureReason reason) {
		super(success, reason);
	}

	public MoveRequestResponse(boolean success, Game game) {
		super(success,game);
	}
}
