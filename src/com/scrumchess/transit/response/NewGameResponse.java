package com.scrumchess.transit.response;

import com.scrumchess.transit.CompleteGameUserInfo;
import com.scrumchess.transit.request.RequestType;

public class NewGameResponse extends GameInfoReturnObject {
	public static enum ResponseReason implements FailReason{
		INVALID_MOVE ("Invalid Move"),
		COMMIT_FAILURE ("commit_failure");
		private String reason;
		ResponseReason(String value){
			reason = value;
		}
		@Override
		public String getReasonMsg() {
			return reason;
		}
	}

	public NewGameResponse(boolean success, CompleteGameUserInfo responseObject) {
		super(RequestType.NEW_GAME_REQUEST, success, responseObject);
		// TODO Auto-generated constructor stub
	}

}
