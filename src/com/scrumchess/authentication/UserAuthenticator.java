package com.scrumchess.authentication;

import com.scrumchess.authentication.pre.UserCredentials;

public interface UserAuthenticator<T> {
	public boolean Authenticate(UserAuthenticationObject<T> userAuthenticationObject);
	public UserAuthenticationObject<T> Authenticaticate(UserCredentials userCredentials);	
}
