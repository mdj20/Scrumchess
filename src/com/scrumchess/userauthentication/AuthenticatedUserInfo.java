package com.scrumchess.userauthentication;

import java.util.Date;

public class AuthenticatedUserInfo {
	
	private Date date;
	private String authenticatedUserID;
	AuthenticatedUserInfo(String user, Date time){
		this.date = time;
		this.authenticatedUserID = user;
	}
}
