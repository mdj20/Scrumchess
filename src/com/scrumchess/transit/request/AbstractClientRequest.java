package com.scrumchess.transit.request;

import java.util.Date;

public abstract class AbstractClientRequest implements ClientRequest {
	
	private Date time;
	
	public AbstractClientRequest(Date date){
		time=date;
	}
	public Date getRequestDate() {
		return time;
	}
	
}
