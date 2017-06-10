package com.scrumchess.authentication;

import com.scrumchess.transit.auth.pre.UserPreAuthentication;

public interface UserAuthenticator {
	public boolean Authenticate(UserAuthenticationObject<Long> userAuthenticationObject);
	public boolean Authenticate(UserAuthenticationObject<String> userAuthenticationObject);
	public UserAuthenticationObject<?> Authenticaticate(UserPreAuthentication userPreAuthentication);
	
}
