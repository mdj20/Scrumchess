package com.scrumchess.authentication;

import java.util.Date;

import com.scrumchess.authentication.pre.UserCredentials;

public interface UserAuthenticationObject<_InternalType> extends UserCredentials, Authentication<_InternalType>, Authenticable<_InternalType>{
	public boolean isAuthenticated();
	public UserCredentials getUserPreAuthentication();
}
