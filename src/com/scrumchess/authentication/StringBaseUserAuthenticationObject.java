package com.scrumchess.authentication;

import java.util.Date;

import com.scrumchess.transit.auth.pre.SimpleUserPreAuthentication;
import com.scrumchess.transit.auth.pre.UserPreAuthentication;

public class StringBaseUserAuthenticationObject implements UserAuthenticationObject<String>{
	
	private Authentication<String> authenticaton;
	private UserPreAuthentication userPreAuthentication;
	private boolean isA = false;
	
	public StringBaseUserAuthenticationObject(UserPreAuthentication userPreAuthentication ){
		this.userPreAuthentication = userPreAuthentication;
	}
	
	
	@Override
	public String getUserToken() {
		return userPreAuthentication.getUserToken();
	}

	@Override
	public int getAuthenticationType() {
		return userPreAuthentication.getAuthenticationType();
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
	public UserPreAuthentication getUserPreAuthentication() {
		return userPreAuthentication;
	}
}
