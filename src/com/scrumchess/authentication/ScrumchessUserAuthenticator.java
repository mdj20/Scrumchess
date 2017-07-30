package com.scrumchess.authentication;

import com.scrumchess.authentication.google.GoogleAuthenticatorString;
import com.scrumchess.authentication.pre.type.Type;

//class is used to Authenticate transit requests 

public class ScrumchessUserAuthenticator {
	public static boolean authenticate(UserAuthenticationObject<String> userAuthenticationObject){
		boolean ret = false;
		UserAuthenticationObject<String> uao = userAuthenticationObject;
		switch (uao.getAuthenticationType()) {
			case Debug:
				ret = true;
				uao.setAuthentication(new SimpleAuthentication(uao.getUserToken()));
				break;
			case Google:
				GoogleAuthenticatorString googleAuthenticatorString = new GoogleAuthenticatorString();
				googleAuthenticatorString.Authenticate(uao);
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
