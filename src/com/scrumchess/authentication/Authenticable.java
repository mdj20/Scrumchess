package com.scrumchess.authentication;

public interface Authenticable<_internalType> {
	public boolean setAuthentication(Authentication<_internalType> authentication);
}
