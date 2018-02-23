package com.scrumchess.userrequests;

import com.scrumchess.authentication.SimpleUserAuthenticationInfo;

public class GameInfoRequest extends AbstractUserRequest {
	long gameID;
	
	GameInfoRequest(SimpleUserAuthenticationInfo<String> userAuthenticationInfo, long gameID) {
		super(userAuthenticationInfo);
	}

	public long getGameID() {
		return gameID;
	}
}
