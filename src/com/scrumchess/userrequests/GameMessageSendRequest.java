package com.scrumchess.userrequests;

import com.scrumchess.authentication.SimpleUserAuthenticationInfo;

public class GameMessageSendRequest extends GameInfoRequest {
	private String message;
	
	public GameMessageSendRequest(SimpleUserAuthenticationInfo<String> userAuthenticationInfo, long gameID, String message) {
		super(userAuthenticationInfo, gameID);
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
