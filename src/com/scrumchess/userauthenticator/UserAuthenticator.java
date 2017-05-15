package com.scrumchess.userauthenticator;
import java.util.Date;
import com.scrumchess.mdj20.GoogleAuthHelper;
import com.scrumchess.transit.auth.pre.UserPreAuthentication;
import com.scrumchess.transit.auth.pre.type.BaseAuthenticationType;

public class UserAuthenticator {
	private GoogleAuthHelper googleHelper;
	public static AuthenticatedUserInfo authenticateUser(UserPreAuthentication userInfo){
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
	private static String googleAuth(UserPreAuthentication userInfo){
		return GoogleAuthHelper.getSubjectFromEndpoint(userInfo.getUserToken());
	}	
}
