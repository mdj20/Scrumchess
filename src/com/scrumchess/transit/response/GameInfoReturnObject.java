package com.scrumchess.transit.response;

import com.scrumchess.transit.game.CompleteGameInfo;

public class GameInfoReturnObject extends AbstractValuedFailableTransitResponse<CompleteGameInfo> {
	GameInfoReturnObject(int requestType, boolean success, CompleteGameInfo responseObject) {
		super(requestType, success, responseObject);
	}
}
