package com.scrumchess.userauthenticator;

import java.util.Date;

public class AuthenticatedUserInfo {
	private Date date;
	public String authenticatedUserID;
	AuthenticatedUserInfo(String user, Date time){
		this.date = time;
		this.authenticatedUserID = user;
	}
}
