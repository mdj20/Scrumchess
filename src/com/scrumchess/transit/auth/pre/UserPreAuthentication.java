package com.scrumchess.transit.auth.pre;

import com.scrumchess.transit.auth.pre.token.UserToken;
import com.scrumchess.transit.auth.pre.type.AuthenticationType;
public interface UserPreAuthentication extends UserToken, AuthenticationType{
	
}
