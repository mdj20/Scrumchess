package com.scrumchess.userrequests;

public enum UniversalFailureReason implements ResponseFailureReason {

	AUTHERNTICATION_FAILURE ("Authentication Failure"),
	SUCCESS ("Success"),
	ENTITY_NOT_FOUND ("Entity not found in database"),
	USER_NOT_OWNER ("User doesn't own entity"),
	INVALID_MOVE ("Invalid Move"),
	NA ("Failure Reason not available");
	
	private String reason;
	
	UniversalFailureReason(String reason){
		this.reason = reason;
	}
	@Override
	public String getFailureReason() {
		return reason;
	}
	
}
