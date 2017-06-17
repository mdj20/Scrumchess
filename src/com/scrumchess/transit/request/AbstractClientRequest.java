package com.scrumchess.transit.request;

import java.util.Date;

public abstract class AbstractClientRequest implements ClientRequest {
	public static final int GAME_INFO_REQUEST = 0;
	public static final int SEND_MOVE_REQUEST = 1;
	public static final int NICKNAME_CHANGE_REQUEST = 2;  // NOT IMPLIMENTED YET
	public static final int NEW_GAME_REQUEST = 3;		// NOT IMPLIMENTED YET

	
	private Date time;
	
	public AbstractClientRequest(Date date){
		time=date;
	}
	public Date getRequestDate() {
		return time;
	}
	
}
