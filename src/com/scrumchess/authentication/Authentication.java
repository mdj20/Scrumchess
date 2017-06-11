package com.scrumchess.authentication;
import java.util.Date;

public interface Authentication<_internalType> {
	public _internalType getUserIdentification();
	public Date getAuthenticationTime();
}
