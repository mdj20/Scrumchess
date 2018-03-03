package com.scrumchess.userrequests;

import com.scrumchess.authentication.SimpleUserAuthenticationInfo;

public class GameMessagePollRequest extends GameInfoRequest {

	// Indicates the number of messages that the request can skip, a value of 0 would mean that the request is asking for the entire array,
	// of messages.
	private int skipMessageIndex;
	
	public GameMessagePollRequest(SimpleUserAuthenticationInfo<String> userAuthenticationInfo, long gameID, int skipMessagesUnder) {
		super(userAuthenticationInfo, gameID);
		this.skipMessageIndex = skipMessagesUnder;
	}
	
	public int getSkipMessageIndex() {
		return skipMessageIndex;
	}

}
