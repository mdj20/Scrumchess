package com.scrumchess.authentication;

public class SimpleUserCredentials extends AbstractUserCredentials{
	private String userToken;
	private AuthenticationType authenticationType;
	
	public SimpleUserCredentials(AuthenticationType type, String token){
		authenticationType = type;
		userToken = token;
	}
	
	@Override
	String getUserToken() {
		return userToken;
	}

	@Override
	AuthenticationType getAuthenticationType() {
		return authenticationType;
	}

}
