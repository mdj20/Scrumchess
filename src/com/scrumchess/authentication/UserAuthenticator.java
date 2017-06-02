package com.scrumchess.authentication;

import com.scrumchess.transit.auth.pre.UserPreAuthentication;

public interface UserAuthenticator {
	public boolean Authenticate(UserAuthenticationObject userAuthenticationObject);
	public UserAuthenticationObject Authenticaticate(UserPreAuthentication userPreAuthentication);
}
