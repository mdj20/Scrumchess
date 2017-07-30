package com.scrumchess.authentication.pre;

import com.scrumchess.authentication.pre.token.SimpleUserToken;
import com.scrumchess.authentication.pre.type.BaseAuthenticationType;
import com.scrumchess.authentication.pre.type.Type;

public class SimpleUserCredentials extends BaseAuthenticationType implements UserCredentials {

	private SimpleUserToken simpleUserToken;
	
	public SimpleUserCredentials(String token, Type set) {
		super(set);
		simpleUserToken = new SimpleUserToken(token);
	}

	@Override
	public String getUserToken() {
		return simpleUserToken.getUserToken();
	}
}
