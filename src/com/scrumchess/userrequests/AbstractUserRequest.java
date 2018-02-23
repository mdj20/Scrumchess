package com.scrumchess.userrequests;

import com.scrumchess.authentication.AbstractUserCredentials;
import com.scrumchess.authentication.AuthenticableUserRequest;
import com.scrumchess.authentication.SimpleUserAuthenticationInfo;


public  class AbstractUserRequest implements AuthenticableUserRequest<String> {
	private SimpleUserAuthenticationInfo<String> userAuthenticationInfo;
	
	AbstractUserRequest(SimpleUserAuthenticationInfo<String> userAuthenticationInfo){
		this.userAuthenticationInfo = userAuthenticationInfo;
	}
	
	@Override
	public AbstractUserCredentials getUserCredentials() {
		return userAuthenticationInfo.getUserCredentials();
	}

	@Override
	public boolean isAuthenticated() {
		return userAuthenticationInfo.isAuthenticated();
	}

	@Override
	public void setIsAuthenticated(boolean value) {
		userAuthenticationInfo.setIsAuthenticated(value);
	}

	@Override
	public String getUserIdentifier() {
		return userAuthenticationInfo.getUserIdentifier();
	}

	@Override
	public void setUserIdentifier(String identifier) {
		userAuthenticationInfo.setUserIdentifier(identifier);
	}

}
