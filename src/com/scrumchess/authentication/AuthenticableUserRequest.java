package com.scrumchess.authentication;

public interface AuthenticableUserRequest<T> {
	public T getUserIdentifier();
	public boolean isAuthenticated();
	AbstractUserCredentials getUserCredentials();
	void setIsAuthenticated(boolean value);
	void setUserIdentifier(T identifier);
}
