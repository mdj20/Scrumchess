package com.scrumchess.transit.request;
import java.util.Date;

import com.scrumchess.authentication.pre.UserCredentials;

public interface ClientRequest{
	public RequestType getRequestType();
	public Date getRequestDate();
}
