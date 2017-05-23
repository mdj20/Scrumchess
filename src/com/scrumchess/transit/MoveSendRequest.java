package com.scrumchess.transit;

import java.util.Date;

public class MoveSendRequest extends AbstractClientRequest implements ClientRequest {
	

	public MoveSendRequest(Date date) {
		super(date);
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
