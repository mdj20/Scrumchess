package com.scrumchess.userrequests;

import com.scrumchess.authentication.SimpleUserAuthenticationInfo;



public class NewGameRequest extends AbstractUserRequest {
	private NewGameConfig newGameConfig;
	private String otherPlayerId;
	public NewGameRequest(SimpleUserAuthenticationInfo<String> userAuthenticationInfo, NewGameConfig newGameConfig) {
		this(userAuthenticationInfo,newGameConfig,null);
	}
	
	NewGameRequest(SimpleUserAuthenticationInfo<String> userAuthenticationInfo, NewGameConfig newGameConfig, String otherPlayerId){
		super(userAuthenticationInfo);
		this.newGameConfig=newGameConfig;
		this.otherPlayerId=otherPlayerId;
	}
	
	public NewGameConfig getNewGameConfig() {
		return newGameConfig;
	}
	
	public String getOtherPlayerId(){
		return otherPlayerId;
	}
	
	public enum NewGameConfig{
		WHITE, // SINGLE PLAYER
		BLACK,
		BLACK2, // 2 PLAYER
		WHITE2;
	}
}
