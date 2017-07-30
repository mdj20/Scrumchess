package com.scrumchess.transit.response;

import com.scrumchess.transit.request.RequestType;

public class ChangePseudonymResponse extends AbstractValuedFailableTransitResponse<String> {

	public ChangePseudonymResponse(boolean success, String responseObject) {
		super(RequestType.NICKNAME_CHANGE_REQUEST, success, responseObject);
	}
}
