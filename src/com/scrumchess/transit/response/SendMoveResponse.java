package com.scrumchess.transit.response;

import com.scrumchess.transit.game.state.State;
import com.scrumchess.transit.request.AbstractClientRequest;
import com.scrumchess.transit.request.RequestType;

public class SendMoveResponse extends AbstractValuedFailableTransitResponse<State> {

	public SendMoveResponse(boolean success, State responseObject) {
		super(RequestType.SEND_MOVE_REQUEST, success, responseObject);
		// TODO Auto-generated constructor stub
	}
}
