package com.scrumchess.transit;

import java.util.Date;

public abstract class AbstractClientRequest implements ClientRequest {
	public static final int GAME_REQUEST = 0;
	public static final int SEND_MOVE_REQUEST = 1;
	private Date time;
	
	public AbstractClientRequest(Date date){
		time=date;
	}
	public Date getRequestDate() {
		return time;
	}
	
}
