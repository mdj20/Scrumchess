package com.scrumchess.userrequests;

import com.scrumchess.data.Game;

public class MoveRequestResponse extends AbstractUserResponse<Game> {

	MoveRequestResponse(boolean success, ResponseFailureReason reason) {
		super(success, reason);
	}
	
}
