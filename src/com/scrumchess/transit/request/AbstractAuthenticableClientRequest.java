package com.scrumchess.transit.request;

/*	CLient request and user preauthorization composite used for many authentication required 
 * 
 */



import java.util.Date;

import com.scrumchess.authentication.Authentication;
import com.scrumchess.authentication.StringBaseUserAuthenticationObject;
import com.scrumchess.authentication.UserAuthenticationObject;
import com.scrumchess.transit.auth.pre.UserPreAuthentication;

public abstract class AbstractAuthenticableClientRequest extends AbstractClientRequest implements ClientRequest, UserAuthenticationObject<String>{
	private StringBaseUserAuthenticationObject userAuthenticationObject;
	private int requestType;
	AbstractAuthenticableClientRequest(int requestType, Date date, UserPreAuthentication upaType) {
		super(date);
		userAuthenticationObject = new StringBaseUserAuthenticationObject(upaType);
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
	public String getUserIdentification() {
		return userAuthenticationObject.getUserIdentification();
	}

	@Override
	public Date getAuthenticationTime() {
		return userAuthenticationObject.getAuthenticationTime();
	}

	@Override
	public boolean setAuthentication(Authentication<String> authentication) {
		return userAuthenticationObject.setAuthentication(authentication);
	}

	@Override
	public boolean isAuthenticated() {
		return userAuthenticationObject.isAuthenticated();
	}
	
	@Override
	public UserPreAuthentication getUserPreAuthentication(){
		return userAuthenticationObject.getUserPreAuthentication();
	}
}
