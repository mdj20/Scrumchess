package com.scrumchess.transit;

import java.util.Date;

public abstract class AbstractClientRequest implements ClientRequest {
	public static final int GAME_REQUEST = 0;
	private Date time;
	
	public AbstractClientRequest(Date date){
		time=date;
	}
	public Date getRequestDate() {
		return time;
	}
	
}
