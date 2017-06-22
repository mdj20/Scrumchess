 package com.scrumchess.authentication.google;

import com.scrumchess.authentication.SimpleAuthentication;
import com.scrumchess.authentication.UserAuthenticationObject;
import com.scrumchess.authentication.UserAuthenticator;
import com.scrumchess.transit.auth.pre.UserPreAuthentication;

public class GoogleAuthenticatorString implements UserAuthenticator<String> {
	
	@Override
	public boolean Authenticate(UserAuthenticationObject<String> userAuthenticationObject) {
		boolean ret = false;
		String sub = GoogleAuthenticatorHelperWrapper.getSubjectFromEndpoint(userAuthenticationObject.getUserToken());
		if (sub!=null){ 
			userAuthenticationObject.setAuthentication(new SimpleAuthentication(sub));
			ret = true;
		}
		return ret;
	}

	@Override
	public UserAuthenticationObject Authenticaticate(UserPreAuthentication userPreAuthentication) {
		// TODO Auto-generated method stub
		return null;
	}

}
