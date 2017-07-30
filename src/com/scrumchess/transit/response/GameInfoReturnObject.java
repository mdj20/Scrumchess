package com.scrumchess.transit.response;

import com.scrumchess.transit.game.CompleteGameInfo;
import com.scrumchess.transit.request.RequestType;

public class GameInfoReturnObject extends AbstractValuedFailableTransitResponse<CompleteGameInfo> {
	GameInfoReturnObject(RequestType requestType, boolean success, CompleteGameInfo responseObject) {
		super(requestType, success, responseObject);
	}
}
