package com.scrumchess.userrequests;

import com.scrumchess.authentication.SimpleUserAuthenticationInfo;

public class GameInfoRequest extends AbstractUserRequest {
	long gameID;
	
	public GameInfoRequest(SimpleUserAuthenticationInfo<String> userAuthenticationInfo, long gameID) {
		super(userAuthenticationInfo);
		this.gameID = gameID;
	}

	public long getGameID() {
		return gameID;
	}
}
