package com.scrumchess.userrequests;

public abstract class AbstractUserResponse<T> {
	protected boolean success;
	protected T responseObject;
	protected ResponseFailureReason responseFailureReason;
	
	AbstractUserResponse(boolean success, T  returnObject){
		this(success,UniversalFailureReason.NA,returnObject);
	}
	AbstractUserResponse(boolean success){
		this(success,null,null);
	}
	AbstractUserResponse(boolean success, ResponseFailureReason reason){
		this(success,reason,null);
	}
	AbstractUserResponse(boolean success, ResponseFailureReason reason, T returnObject){
		this.success = success;
		responseFailureReason = reason;
		this.responseObject = returnObject;
	}
	
	public boolean isSuccessful() {
		return success;
	}
	
	public void setResponseObject(T value) {
		responseObject = value;
	}
	
	public ResponseFailureReason getRespnseFailureReason() {
		return this.responseFailureReason;
	}
	public T getResponseObject() {
		return responseObject;
	}
	
}
