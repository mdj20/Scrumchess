package com.scrumchess.userrequests;

import com.scrumchess.data.Game;

public class GameInfoResponse extends AbstractUserResponse<Game> {
	public GameInfoResponse(boolean success, Game returnObject) {
		super(success, UnversalFailureReason.SUCCESS, returnObject);
	}
	public GameInfoResponse(boolean success, ResponseFailureReason reason ) {
		super(success, reason);
	}
}
