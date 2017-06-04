package com.scrumchess.authentication.google;

import com.scrumchess.authentication.UserAuthenticationObject;
import com.scrumchess.authentication.UserAuthenticator;
import com.scrumchess.transit.auth.pre.UserPreAuthentication;

public class GoogleAuthticator implements UserAuthenticator {

	@Override
	public boolean Authenticate(UserAuthenticationObject userAuthenticationObject) {
		return false;
	}

	@Override
	public UserAuthenticationObject Authenticaticate(UserPreAuthentication userPreAuthentication) {
		// TODO Auto-generated method stub
		return null;
	}

}
