package com.scrumchess.transit.request;

/*	CLient request and user preauthorization composite used for many authentication required 
 * 
 */



import java.util.Date;

import com.scrumchess.authentication.Authentication;
import com.scrumchess.authentication.StringBaseUserAuthenticationObject;
import com.scrumchess.authentication.UserAuthenticationObject;
import com.scrumchess.authentication.pre.UserCredentials;
import com.scrumchess.authentication.pre.type.Type;

public abstract class AbstractAuthenticableClientRequest extends AbstractClientRequest implements ClientRequest, UserAuthenticationObject<String>{
	private StringBaseUserAuthenticationObject userAuthenticationObject;
	private RequestType requestType;
	AbstractAuthenticableClientRequest(RequestType requestType, Date date, StringBaseUserAuthenticationObject userAuth) {
		super(date);
		userAuthenticationObject = userAuth;
		this.requestType = requestType;
	}
	
	protected StringBaseUserAuthenticationObject getUserAuthenticationObject() {
		return this.userAuthenticationObject;
	}

	@Override
	public String getUserToken() {
		return userAuthenticationObject.getUserToken();
	}

	@Override
	public Type getAuthenticationType() {
		return userAuthenticationObject.getAuthenticationType();
	}

	@Override
	public RequestType getRequestType() {
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
	public UserCredentials getUserPreAuthentication(){
		return userAuthenticationObject.getUserPreAuthentication();
	}
}
