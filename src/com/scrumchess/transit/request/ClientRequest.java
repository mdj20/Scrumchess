package com.scrumchess.transit.request;
import java.util.Date;
import com.scrumchess.transit.auth.pre.UserPreAuthentication;

public interface ClientRequest{
	public RequestType getRequestType();
	public Date getRequestDate();
}
