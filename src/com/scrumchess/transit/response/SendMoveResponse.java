package com.scrumchess.transit.response;

import com.scrumchess.transit.game.state.GameState;
import com.scrumchess.transit.request.AbstractClientRequest;

public class SendMoveResponse extends AbstractValuedFailableTransitResponse<GameState> {

	public SendMoveResponse(boolean success, GameState responseObject) {
		super(AbstractClientRequest.SEND_MOVE_REQUEST, success, responseObject);
		// TODO Auto-generated constructor stub
	}

}
