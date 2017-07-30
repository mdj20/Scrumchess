package com.scrumchess.authentication;

import java.util.Date;

import com.scrumchess.authentication.pre.SimpleUserCredentials;
import com.scrumchess.authentication.pre.UserCredentials;
import com.scrumchess.authentication.pre.type.Type;

public class StringBaseUserAuthenticationObject implements UserAuthenticationObject<String>{
	
	private Authentication<String> authenticaton;
	private UserCredentials userCredentials;
	private boolean isA = false;
	
	public StringBaseUserAuthenticationObject(UserCredentials userCredentials ){
		this.userCredentials = userCredentials;
	}
	
	
	@Override
	public String getUserToken() {
		return userCredentials.getUserToken();
	}

	@Override
	public Type getAuthenticationType() {
		return userCredentials.getAuthenticationType();
	}

	@Override
	public String getUserIdentification() {
		return authenticaton.getUserIdentification();
	}

	@Override
	public Date getAuthenticationTime() {
		return authenticaton.getAuthenticationTime();
	}

	@Override
	public boolean isAuthenticated() {
		return this.isA;
	}

	@Override
	public boolean setAuthentication(Authentication<String> authentication) {
		this.authenticaton = authentication;
		this.isA = true;
		return isAuthenticated();
	}

	@Override
	public UserCredentials getUserPreAuthentication() {
		return userCredentials;
	}
}
