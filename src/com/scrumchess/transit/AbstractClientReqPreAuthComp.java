package com.scrumchess.transit;

/*	CLient request and user preauthorization composite used for many authentication required 
 * 
 */



import java.util.Date;

import com.scrumchess.authentication.Authentication;
import com.scrumchess.authentication.SimpleUserAuthenticationObject;
import com.scrumchess.authentication.UserAuthenticationObject;
import com.scrumchess.transit.auth.pre.UserPreAuthentication;

public abstract class AbstractClientReqPreAuthComp extends AbstractClientRequest implements ClientRequest, UserAuthenticationObject{
	private UserAuthenticationObject userAuthenticationObject;
	private int requestType;
	AbstractClientReqPreAuthComp(int requestType, Date date, UserPreAuthentication upaType) {
		super(date);
		userAuthenticationObject = new SimpleUserAuthenticationObject(upaType);
		this.requestType = requestType;
	}

	@Override
	public String getUserToken() {
		return userAuthenticationObject.getUserToken();
	}

	@Override
	public int getAuthenticationType() {
		return userAuthenticationObject.getAuthenticationType();
	}

	@Override
	public int getRequestType() {
		return requestType;
	}

	@Override
	public Date getRequestDate() {
		return super.getRequestDate();
	}

	@Override
	public long getUserIdentification() {
		return userAuthenticationObject.getUserIdentification();
	}

	@Override
	public Date getAuthenticationTime() {
		return userAuthenticationObject.getAuthenticationTime();
	}

	@Override
	public boolean setAuthentication(Authentication authentication) {
		return userAuthenticationObject.setAuthentication(authentication);
	}

	@Override
	public boolean isAuthenticated() {
		return userAuthenticationObject.isAuthenticated();
	}
	
	public UserPreAuthentication getUserPreAuthentication(){
		return userAuthenticationObject.getUserPreAuthentication();
	}
}