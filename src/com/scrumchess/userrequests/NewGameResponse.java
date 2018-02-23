package com.scrumchess.userrequests;

import com.scrumchess.data.Game;

public class NewGameResponse extends AbstractUserResponse<Game> {
	NewGameResponse(boolean success, ResponseFailureReason reason){
		super(success,reason);
	}
	NewGameResponse(boolean success,Game game){
		super(success,game);
	}
}
