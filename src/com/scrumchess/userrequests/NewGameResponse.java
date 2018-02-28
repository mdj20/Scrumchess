package com.scrumchess.userrequests;

import com.scrumchess.data.Game;

public class NewGameResponse extends AbstractUserResponse<Game> {
	public NewGameResponse(boolean success, ResponseFailureReason reason){
		super(success,reason);
	}
	public NewGameResponse(boolean success,Game game){
		super(success,game);
	}
}