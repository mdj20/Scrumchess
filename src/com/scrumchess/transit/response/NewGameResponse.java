package com.scrumchess.transit.response;

import com.scrumchess.transit.CompleteGameUserInfo;
import com.scrumchess.transit.request.AbstractClientRequest;

public class NewGameResponse extends GameInfoReturnObject {

	public NewGameResponse(boolean success, CompleteGameUserInfo responseObject) {
		super(AbstractClientRequest.NEW_GAME_REQUEST, success, responseObject);
		// TODO Auto-generated constructor stub
	}

}
