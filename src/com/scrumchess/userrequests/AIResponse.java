package com.scrumchess.userrequests;

public class AIResponse extends AbstractUserResponse<String[]> {

	public AIResponse(boolean success, UniversalFailureReason reason) {
		super(success, reason);
	}
	
	AIResponse(boolean success, String[] moves){
		super(success, moves);
	}

}
