package com.scrumchess.transit.response;

public interface FailReason extends FailableRequest {
	public static final String NOT_DEFINED = "Not Defined";
	public String getReasonMsg();
	public void setFailReason(String reason);
}
