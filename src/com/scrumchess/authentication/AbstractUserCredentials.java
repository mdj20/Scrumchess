package com.scrumchess.authentication;

public abstract class AbstractUserCredentials {
	abstract String getUserToken();
	abstract  AuthenticationType getAuthenticationType();
}
