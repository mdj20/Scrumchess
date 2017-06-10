package com.scrumchess.authentication;

import java.util.Date;

import com.scrumchess.transit.auth.pre.UserPreAuthentication;

public interface UserAuthenticationObject<T> extends UserPreAuthentication, Authentication<T>, Authenticable<T>{
	public boolean isAuthenticated();
	public UserPreAuthentication getUserPreAuthentication();
}
