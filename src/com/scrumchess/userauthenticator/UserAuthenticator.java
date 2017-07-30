package com.scrumchess.userauthenticator;
import java.util.Date;

import com.scrumchess.authentication.pre.UserCredentials;
import com.scrumchess.authentication.pre.type.BaseAuthenticationType;
import com.scrumchess.mdj20.GoogleAuthHelper;

public class UserAuthenticator {
	private GoogleAuthHelper googleHelper;
	public static AuthenticatedUserInfo authenticateUser(UserCredentials userInfo){
		AuthenticatedUserInfo ret = null;
		String retID = null;
		
		// Auth control and branch
		if (userInfo.getAuthenticationType()==BaseAuthenticationType.AUTH_TYPE_GOOGLE){
			retID = googleAuth(userInfo);
		}
		
		// results handle 
		if (retID != null){
			ret = new AuthenticatedUserInfo(retID,new Date());
		}
		return ret;	
	}
	
	// this is a bad method must develop later....
	private static String googleAuth(UserCredentials userInfo){
		return GoogleAuthHelper.getSubjectFromEndpoint(userInfo.getUserToken());
	}	
}
