package com.scrumchess.transit;

import java.util.Date;

public class MoveSendRequest extends AbstractClientReqPreAuthComp implements ClientRequest {
	

	public MoveSendRequest(Date date) {
		super(AbstractClientRequest.SEND_MOVE_REQUEST, date, userAuthenticationObject);
	}

	@Override
	public String getUserToken() {
		return null;
	}

	@Override
	public int getAuthenticationType() {
		return 0;
	}

	@Override
	public int getRequestType() {
		return 0;
	}
}
