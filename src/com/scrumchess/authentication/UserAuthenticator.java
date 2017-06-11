package com.scrumchess.authentication;

import com.scrumchess.transit.auth.pre.UserPreAuthentication;

public interface UserAuthenticator<T> {
	public boolean Authenticate(UserAuthenticationObject<T> userAuthenticationObject);
	public UserAuthenticationObject<T> Authenticaticate(UserPreAuthentication userPreAuthentication);
	
}
