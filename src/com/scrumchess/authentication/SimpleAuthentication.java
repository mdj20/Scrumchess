package com.scrumchess.authentication;

import java.util.Date;

public class SimpleAuthentication<T> implements Authentication<T> {
	private String id;
	private Date date;
	public SimpleAuthentication(long id){
		this(String, new Date());
	}
	public SimpleAuthentication(String id, Date date){
		this.id=id;
		this.date = date;
	}
	@Override
	public T getUserIdentification() {
		return id;
	}

	@Override
	public Date getAuthenticationTime() {
		return date;
	}

}
