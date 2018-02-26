package com.scrumchess.userrequests;

public abstract class AbstractUserResponse<T> {
	protected boolean success;
	protected T responseObject;
	protected ResponseFailureReason responseFailureReason;
	
	AbstractUserResponse(boolean success, T  returnObject){
		this(success,BaseFailureReason.NA,returnObject);
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
	
	protected void setResponseObject(T value) {
		responseObject = value;
	}
	
	protected ResponseFailureReason GetRespnseFailureReason() {
		return this.responseFailureReason;
	}
	
	public enum BaseFailureReason implements ResponseFailureReason{
		
		AUTHERNTICATION_FAILURE("Authentication Failure"),
		SUCCESS ("Success"),
		ENTITY_NOT_FOUND("Entity not found in database"),
		NA("Failure Reason not available");
		
		private String reason;
		
		BaseFailureReason(String reason){
			this.reason = reason;
		}
		@Override
		public String getFailureReason() {
			return reason;
		}
		

	}
}
