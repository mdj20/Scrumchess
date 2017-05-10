package com.scrumchess.transit.preauth.token;

public class SimpleUserToken implements UserToken{
	private String token;
	
	SimpleUserToken(String init){
		token = init;
	}

	@Override
	public String getUserToken() {
		return token;
	}
	
}
