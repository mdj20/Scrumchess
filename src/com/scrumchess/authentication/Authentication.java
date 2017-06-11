package com.scrumchess.authentication;
import java.util.Date;

public interface Authentication<_InternalType> {
	public _InternalType getUserIdentification();
	public Date getAuthenticationTime();
}
