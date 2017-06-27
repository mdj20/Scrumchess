package com.scrumchess.transit.response;

import com.scrumchess.transit.game.CompleteGameInfo;

public abstract class AbstractValuedFailableTransitResponse<T> implements ValuedFailableTransitResponse<T> {
	private boolean success;
	private T responseObject;
	private int requestType;
	private String failReason;
	
	
	public AbstractValuedFailableTransitResponse(int requestType, boolean success, T responseObject){
		this.requestType = requestType;
		this.success = success;
		this.responseObject = responseObject;
	}
	
	@Override
	public boolean successful() {
		return success;
	}

	@Override
	public T getReturnableObject() {
		return responseObject;
	}

	@Override
	public int getRequestType() {
		return requestType;
	}

	@Override
	public String getReasonMsg() {
		// TODO Auto-generated method stub
		return this.failReason;
	}

	@Override
	public void setFailReason(String reason) {
		this.failReason = reason;	
	}
	
	

}
