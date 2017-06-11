package com.scrumchess.authentication;

import java.util.Date;

import com.scrumchess.transit.auth.pre.UserPreAuthentication;

public interface UserAuthenticationObject<_InternalType> extends UserPreAuthentication, Authentication<_InternalType>, Authenticable<_InternalType>{
	public boolean isAuthenticated();
	public UserPreAuthentication getUserPreAuthentication();
}
