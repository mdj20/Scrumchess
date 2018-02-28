 package com.scrumchess.authentication;

//class is used to Authenticate transit requests 

public class ScrumchessUserAuthenticator {
	public static boolean authenticate(AuthenticableUserRequest<String> userRequest){
		boolean ret = false;
		switch (userRequest.getUserCredentials().getAuthenticationType()) {
			case DEBUG:
				ret = true;
				userRequest.setUserIdentifier(userRequest.getUserCredentials().getUserToken());
				break;
			case GOOGLE:
				GoogleAuthenticatorString googleAuthenticatorString = new GoogleAuthenticatorString();
				googleAuthenticatorString.Authenticate(userRequest);
				ret = true;
				break;
			case NONE:
				break;
			default:
				break;
		}
	
		return ret;
	}
}
