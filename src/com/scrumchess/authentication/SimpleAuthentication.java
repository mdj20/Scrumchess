package com.scrumchess.authentication;

import java.util.Date;

public class SimpleAuthentication implements Authentication {
	private long id;
	private Date date;
	protected SimpleAuthentication(long id){
		this(id, new Date());
	}
	protected SimpleAuthentication(long id, Date date){
		this.id=id;
		this.date = date;
	}
	@Override
	public long getUserIdentification() {
		return id;
	}

	@Override
	public Date getAuthenticationTime() {
		return date;
	}

}
