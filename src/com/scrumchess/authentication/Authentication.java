package com.scrumchess.authentication;
import java.util.Date;

public interface Authentication<T> {
	public T getUserIdentification();
	public Date getAuthenticationTime();
}
