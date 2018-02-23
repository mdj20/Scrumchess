package com.scrumchess.authentication;

import com.scrumchess.authentication.google.GoogleAuthenticatorString;

//class is used to Authenticate transit requests 

public class ScrumchessUserAuthenticator {
	public static boolean authenticate(AuthenticableUserRequest<String> userRequest){
		boolean ret = false;
		switch (userRequest.getUserCredentials().getAuthenticationType()) {
			case Debug:
				ret = true;
				userRequest.setUserIdentifier(userRequest.getUserCredentials().getUserToken());
				break;
			case Google:
				GoogleAuthenticatorString googleAuthenticatorString = new GoogleAuthenticatorString();
				googleAuthenticatorString.Authenticate(userRequest);
				ret = true;
				break;
			case None:
				break;
			default:
				break;
		}
	
		return ret;
	}
}
