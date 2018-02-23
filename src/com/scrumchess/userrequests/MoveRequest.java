package com.scrumchess.userrequests;

import com.scrumchess.authentication.SimpleUserAuthenticationInfo;

public class MoveRequest extends GameInfoRequest{
	private String move;
	
	MoveRequest(SimpleUserAuthenticationInfo<String> userAuthenticationInfo, long gameID, String move) {
		super(userAuthenticationInfo, gameID);
	}
	
	public String getMove() {
		return move;
	}
}
