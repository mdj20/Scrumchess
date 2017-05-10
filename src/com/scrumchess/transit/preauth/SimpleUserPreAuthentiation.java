package com.scrumchess.transit.preauth;

import com.scrumchess.transit.preauth.token.SimpleUserToken;
import com.scrumchess.transit.preauth.type.BaseAuthenticationType;

public class SimpleUserPreAuthentiation extends BaseAuthenticationType implements UserPreAuthentication {

	private SimpleUserToken simpleUserToken;
	
	SimpleUserPreAuthentiation(String token, int set) {
		super(set);
		simpleUserToken = new SimpleUserToken(token);
	}

	@Override
	public String getUserToken() {
		// TODO Auto-generated method stub
		return simpleUserToken.getUserToken();
	}
}
