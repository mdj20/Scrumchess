package com.scrumchess.authentication;

public interface Authenticable<T> {
	public boolean setAuthentication(Authentication<T> authentication);
}
