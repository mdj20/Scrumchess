package com.scrumchess.userrequests;

import com.scrumchess.data.Game;

public class DoubleMoveResponse extends MoveRequestResponse {
	public DoubleMoveResponse(boolean success, Game game) {
		super(success, game);
		
	}
	public DoubleMoveResponse(boolean success, UniversalFailureReason reason) {
		super(success,reason);
	}
}
