package com.scrumchess.transit.response;

import com.scrumchess.transit.game.CompleteGameInfo;
import com.scrumchess.transit.request.AbstractClientRequest;
import com.scrumchess.transit.request.RequestType;

public class GameInfoResponse extends GameInfoReturnObject {
	public static enum ResponseReason implements FailReason{
		GAME_NOT_FOUND ("Game Not Found");
		private String reason;
		ResponseReason(String value){
			reason = value;
		}
		@Override
		public String getReasonMsg() {
			return reason;
		}
	}
	
	public GameInfoResponse(boolean success, CompleteGameInfo responseObject) {
		super(RequestType.GAME_INFO_REQUEST, success, responseObject);
		// TODO Auto-generated constructor stub
	}

}
