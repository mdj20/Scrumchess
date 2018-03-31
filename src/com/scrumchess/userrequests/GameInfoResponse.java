package com.scrumchess.userrequests;

import com.scrumchess.data.Game;

public class GameInfoResponse extends AbstractUserResponse<Game> {
	

	
	public GameInfoResponse(boolean success, Game returnObject) {
		super(success, UniversalFailureReason.SUCCESS, returnObject);
	}
	public GameInfoResponse(boolean success, UniversalFailureReason reason ) {
		super(success, reason);
	}
}
