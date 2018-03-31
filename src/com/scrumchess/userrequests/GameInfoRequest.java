package com.scrumchess.userrequests;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.scrumchess.authentication.AbstractUserCredentials;
import com.scrumchess.authentication.ScrumchessAuthenticationType;
import com.scrumchess.authentication.SimpleAbstractUserCredentialGsonAdapter;
import com.scrumchess.authentication.SimpleUserAuthenticationInfo;
import com.scrumchess.authentication.SimpleUserCredentials;

public class GameInfoRequest extends AbstractUserRequest {
	long gameID;
	
	public GameInfoRequest(){}
	
	public GameInfoRequest(SimpleUserAuthenticationInfo<String> userAuthenticationInfo, long gameID) {
		super(userAuthenticationInfo);
		this.gameID = gameID;
	}

	public long getGameID() {
		return gameID;
	}	
}
