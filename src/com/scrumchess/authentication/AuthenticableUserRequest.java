package com.scrumchess.authentication;

public interface AuthenticableUserRequest<T> {
	UserCredentials getUserCredentials();
	public boolean isAuthenticated();
	void setIsAuthenticated(boolean value);
	public T getUserIdentifier();
	void setUserIdentifier(T Indentifier);
}
