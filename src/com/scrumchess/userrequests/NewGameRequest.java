package com.scrumchess.userrequests;

import com.scrumchess.authentication.SimpleUserAuthenticationInfo;



public class NewGameRequest extends AbstractUserRequest {
	private NewGameConfig newGameConfig;
	NewGameRequest(SimpleUserAuthenticationInfo<String> userAuthenticationInfo) {
		super(userAuthenticationInfo);
	}
	
	public NewGameConfig getNewGameConfig() {
		return newGameConfig;
	}
	
	public enum NewGameConfig{
		WHITE,
		BLACK,
	}
}
