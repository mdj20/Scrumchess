package com.scrumchess.transit.response;

public enum BaseFailReason implements FailReason {
	NOT_DEFINED("Not Defined"),
	AUTHENTICATION_FAILURE("Authentication Failure"),
	USER_NOT_FOUND("User Not Found"),
	INVALID_REQUEST_CONFIGURATION("Invalid Request Configuration");
	private String message;
	BaseFailReason(String value){
		this.message = value;
	}
	@Override
	public String getReasonMsg() {
		return message;
	}
}
