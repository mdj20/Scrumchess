package com.scrumchess.authentication.google;

import com.scrumchess.mdj20.GoogleAuthHelper;

public class GoogleAuthenticatorHelperWrapper {
	public static String getSubjectFromEndpoint(String token){
		return GoogleAuthHelper.getSubjectFromEndpoint(token);
	}
}
