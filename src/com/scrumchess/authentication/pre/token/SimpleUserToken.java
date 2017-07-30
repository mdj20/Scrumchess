package com.scrumchess.authentication.pre.token;

public class SimpleUserToken implements UserToken{
	private String token;
	
	public SimpleUserToken(String init){
		token = init;
	}

	@Override
	public String getUserToken() {
		return token;
	}
	
}
