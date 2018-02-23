package com.scrumchess.authentication;

public interface UserAuthenticator<T> {
	public boolean Authenticate(AuthenticableUserRequest<T> authenticableUserRequest);
}
