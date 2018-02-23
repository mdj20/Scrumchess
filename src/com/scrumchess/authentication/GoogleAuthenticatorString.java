 package com.scrumchess.authentication.google;

import com.scrumchess.authentication.AuthenticableUserRequest;
import com.scrumchess.authentication.UserAuthenticator;
import com.scrumchess.authentication.UserCredentials;

public class GoogleAuthenticatorString implements UserAuthenticator<String> {
	
	@Override
	public boolean Authenticate(AuthenticableUserRequest<String> authenticableUserRequest) {
		boolean ret = false;
		String sub = GoogleAuthenticatorHelperWrapper.getSubjectFromEndpoint(authenticableUserRequest.getUserCredentials().getUserToken());
		if (sub!=null){ 
			authenticableUserRequest.setUserIdentifier(sub);
			ret = true;
		}
		authenticableUserRequest.setIsAuthenticated(true);
		return ret;
	}


}
