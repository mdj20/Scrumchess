package com.scrumchess.authentication;
import java.util.Date;

public interface Authentication {
	public long getUserIdentification();
	public Date getAuthenticationTime();
}
