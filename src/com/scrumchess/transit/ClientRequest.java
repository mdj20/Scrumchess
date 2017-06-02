package com.scrumchess.transit;
import java.util.Date;
import com.scrumchess.transit.auth.pre.UserPreAuthentication;

public interface ClientRequest{
	public int getRequestType();
	public Date getRequestDate();
}
