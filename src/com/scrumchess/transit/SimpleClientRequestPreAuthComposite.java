package com.scrumchess.transit;

import java.util.Date;

import com.scrumchess.transit.auth.pre.UserPreAuthentication;

public class SimpleClientRequestPreAuthComposite extends AbstractClientReqPreAuthComp {

	public SimpleClientRequestPreAuthComposite(int requestType, Date date, UserPreAuthentication upaType) {
		super(requestType, date, upaType);
	}

	public static SimpleClientRequestPreAuthComposite getInstance(int type, UserPreAuthentication upaObject){
		return new SimpleClientRequestPreAuthComposite(type,new Date(), upaObject);
	}
	
	
	
}
