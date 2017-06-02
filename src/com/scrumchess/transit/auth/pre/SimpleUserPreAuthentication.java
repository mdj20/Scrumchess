package com.scrumchess.transit.auth.pre;

import com.scrumchess.transit.auth.pre.token.SimpleUserToken;
import com.scrumchess.transit.auth.pre.type.BaseAuthenticationType;

public class SimpleUserPreAuthentication extends BaseAuthenticationType implements UserPreAuthentication {

	private SimpleUserToken simpleUserToken;
	
	public SimpleUserPreAuthentication(String token, int set) {
		super(set);
		simpleUserToken = new SimpleUserToken(token);
	}

	@Override
	public String getUserToken() {
		return simpleUserToken.getUserToken();
	}
}
