package com.scrumchess.authentication;

public interface UserCredentials {
	public String getUserToken();
	public AuthenticationType getAuthenticationType();
}
