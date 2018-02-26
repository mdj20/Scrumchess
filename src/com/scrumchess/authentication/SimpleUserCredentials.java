package com.scrumchess.authentication;

public class SimpleUserCredentials extends AbstractUserCredentials{
	private String userToken;
	private ScrumchessAuthenticationType scrumchessAuthenticationType;
	
	public SimpleUserCredentials(ScrumchessAuthenticationType type, String token){
		scrumchessAuthenticationType = type;
		userToken = token;
	}
	
	@Override
	String getUserToken() {
		return userToken;
	}

	@Override
	ScrumchessAuthenticationType getAuthenticationType() {
		return scrumchessAuthenticationType;
	}

}
