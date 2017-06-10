package com.scrumchess.authentication.google;

import com.scrumchess.authentication.SimpleAuthentication;
import com.scrumchess.authentication.UserAuthenticationObject;
import com.scrumchess.authentication.UserAuthenticator;
import com.scrumchess.transit.auth.pre.UserPreAuthentication;

public class GoogleAuthenticator implements UserAuthenticator {

	@Override
	public boolean Authenticate(UserAuthenticationObject userAuthenticationObject) {
		boolean ret = false;
		String sub = GoogleAuthenticatorHelperWrapper.getSubjectFromEndpoint(userAuthenticationObject.getUserToken());
		if (sub!=null){ 
			// auth success
			
			userAuthenticationObject.setAuthentication(new SimpleAuthentication(Long.parseLong(sub)));
			
		}
		return ret;
	}

	@Override
	public UserAuthenticationObject Authenticaticate(UserPreAuthentication userPreAuthentication) {
		// TODO Auto-generated method stub
		return null;
	}

}
