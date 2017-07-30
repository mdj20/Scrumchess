package com.scrumchess.authentication;

import com.scrumchess.authentication.pre.SimpleUserCredentials;
import com.scrumchess.authentication.pre.type.Type;

public class AuthenticationObjectBuilder {
	public static StringBaseUserAuthenticationObject getAuthenticationObject(Type type, String token) {
		return new StringBaseUserAuthenticationObject(new SimpleUserCredentials(token,type));
	}
}
