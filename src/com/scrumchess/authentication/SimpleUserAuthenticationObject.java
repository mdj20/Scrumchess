package com.scrumchess.authentication;

import java.util.Date;

import com.scrumchess.transit.auth.pre.SimpleUserPreAuthentication;
import com.scrumchess.transit.auth.pre.UserPreAuthentication;

public class SimpleUserAuthenticationObject implements UserAuthenticationObject{
	
	private Authentication authenticaton;
	private UserPreAuthentication userPreAuthentication;
	private boolean isA = false;
	
	public SimpleUserAuthenticationObject(UserPreAuthentication userPreAuthentication ){
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
	public long getUserIdentification() {
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
	public boolean setAuthentication(Authentication authentication) {
		this.authenticaton = authentication;
		this.isA = true;
		return isAuthenticated();
	}
	

}
