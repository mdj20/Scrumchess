package com.scrumchess.transit.auth.pre;

import com.scrumchess.transit.auth.pre.token.SimpleUserToken;
import com.scrumchess.transit.auth.pre.type.BaseAuthenticationType;

public class SimpleUserPreAuthentiation extends BaseAuthenticationType implements UserPreAuthentication {

	private SimpleUserToken simpleUserToken;
	
	SimpleUserPreAuthentiation(String token, int set) {
		super(set);
		simpleUserToken = new SimpleUserToken(token);
	}

	@Override
	public String getUserToken() {
		return simpleUserToken.getUserToken();
	}
}
