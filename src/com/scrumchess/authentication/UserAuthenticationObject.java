package com.scrumchess.authentication;

import java.util.Date;

import com.scrumchess.transit.auth.pre.UserPreAuthentication;

public interface UserAuthenticationObject extends UserPreAuthentication, Authentication, Authenticable{
	public boolean isAuthenticated();
	public UserPreAuthentication getUserPreAuthentication();
}
