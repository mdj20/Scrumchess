package com.scrumchess.authentication;

import com.scrumchess.authentication.google.GoogleAuthenticatorString;

//class is used to Authenticate transit requests 

public class ScrumchessUserAuthenticator {
	public static boolean authenticate(UserAuthenticationObject<String> userAuthenticationObject){
		boolean ret = false;
		UserAuthenticationObject<String> uao = userAuthenticationObject;
		if (uao.getAuthenticationType()==uao.AUTH_TYPE_GOOGLE){
			GoogleAuthenticatorString googleAuthenticatorString = new GoogleAuthenticatorString();
			googleAuthenticatorString.Authenticate(uao);
			ret = true;
		}
		else if (uao.getAuthenticationType() == uao.NONE){
			// no authentication or used for debugging purposes/
			ret = true;
		}
		return ret;
	}
}
