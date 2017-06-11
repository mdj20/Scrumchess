package com.scrumchess.authentication;

import java.util.Date;

public class SimpleAuthentication implements Authentication<String> {
	private String id;
	private Date date;
	public SimpleAuthentication(String id){
		this(id, new Date());
	}
	public SimpleAuthentication(String id, Date date){
		this.id=id;
		this.date = date;
	}
	@Override
	public String getUserIdentification() {
		return id;
	}

	@Override
	public Date getAuthenticationTime() {
		return date;
	}

}
