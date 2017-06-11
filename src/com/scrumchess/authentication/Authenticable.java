package com.scrumchess.authentication;

public interface Authenticable<_InternalType> {
	public boolean setAuthentication(Authentication<_InternalType> authentication);
}
