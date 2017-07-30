package com.scrumchess.transit.response;

public interface FailableRequest {
	public boolean successful();
	public String getReasonMsg();
	public void setFailReason(FailReason reason);
}
