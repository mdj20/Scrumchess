package com.scrumchess.transit.request;

import java.util.Date;

import com.scrumchess.authentication.StringBaseUserAuthenticationObject;
import com.scrumchess.authentication.pre.UserCredentials;

public class SimpleClientRequestPreAuthComposite extends AbstractAuthenticableClientRequest {

	public SimpleClientRequestPreAuthComposite(RequestType requestType, Date date, StringBaseUserAuthenticationObject upaType) {
		super(requestType, date, upaType);
	}

	public static SimpleClientRequestPreAuthComposite getInstance(RequestType type, StringBaseUserAuthenticationObject upaObject){
		return new SimpleClientRequestPreAuthComposite(type,new Date(), upaObject);
	}
}
