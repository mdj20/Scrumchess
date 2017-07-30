package com.scrumchess.transit.response;

import com.scrumchess.transit.request.RequestType;

public abstract class AbstractValuedFailableTransitResponse<T> implements ValuedFailableTransitResponse<T> {
	private boolean success;
	private T responseObject;
	private RequestType requestType;
	private FailReason failReason;
	
	public AbstractValuedFailableTransitResponse(RequestType requestType, boolean success, T responseObject){
		this.requestType = requestType;
		this.success = success;
		this.responseObject = responseObject;
	}
	
	@Override
	public boolean successful() {
		return this.success;
	}

	@Override
	public T getReturnableObject() {
		return responseObject;
	}

	@Override
	public RequestType getRequestType() {
		return requestType;
	}

	@Override
	public String getReasonMsg() {
		return this.failReason.getReasonMsg();
	}

	@Override
	public void setFailReason(FailReason reason) {
		this.failReason = reason;	
	}
}
