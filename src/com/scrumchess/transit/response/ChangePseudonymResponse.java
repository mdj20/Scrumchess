package com.scrumchess.transit.response;

import com.scrumchess.transit.request.AbstractClientRequest;

public class ChangePseudonymResponse extends AbstractValuedFailableTransitResponse<String> {

	public ChangePseudonymResponse(boolean success, String responseObject) {
		super(AbstractClientRequest.NICKNAME_CHANGE_REQUEST, success, responseObject);
	}
}
