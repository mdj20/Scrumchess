package com.scrumchess.userrequests;

import com.scrumchess.data.Game;

public class GameInfoResponse extends AbstractUserResponse<Game> {
	public GameInfoResponse(boolean success, Game returnObject) {
		super(success, AbstractUserResponse.BaseFailureReason.SUCCESS, returnObject);
	}
	public GameInfoResponse(boolean success, ResponseFailureReason reason ) {
		super(success, reason);
	}
}
