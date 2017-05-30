package com.scrumchess.transit;

/*	CLient request and user preauthorization composite used for many authentication required 
 * 
 */



import java.util.Date;
import com.scrumchess.transit.auth.pre.UserPreAuthentication;

public abstract class AbstractClientReqPreAuthComp extends AbstractClientRequest implements ClientRequest, UserPreAuthentication {
	private UserPreAuthentication userPreAuth;
	private int requestType;
	public AbstractClientReqPreAuthComp(int requestType, Date date, UserPreAuthentication upaType) {
		super(date);
		userPreAuth = upaType;
		this.requestType = requestType;
	}

	@Override
	public String getUserToken() {
		return userPreAuth.getUserToken();
	}

	@Override
	public int getAuthenticationType() {
		return userPreAuth.getAuthenticationType();
	}

	@Override
	public int getRequestType() {
		return requestType;
	}

	@Override
	public Date getRequestDate() {
		return super.getRequestDate();
	}
}
