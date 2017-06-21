package com.scrumchess.transit.response;

import com.scrumchess.transit.game.CompleteGameInfo;
import com.scrumchess.transit.request.AbstractClientRequest;

public class GameInfoResponse extends GameInfoReturnObject {

	GameInfoResponse(boolean success, CompleteGameInfo responseObject) {
		super(AbstractClientRequest.GAME_INFO_REQUEST, success, responseObject);
		// TODO Auto-generated constructor stub
	}

}
