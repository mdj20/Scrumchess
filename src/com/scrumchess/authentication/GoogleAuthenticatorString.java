package com.scrumchess.authentication;

public class GoogleAuthenticatorString implements UserAuthenticator<String> {
	
	@Override
	public boolean Authenticate(AuthenticableUserRequest<String> authenticableUserRequest) {
		boolean ret = false;
		String sub = GoogleAuthenticatorHelperWrapper.getSubjectFromEndpoint(authenticableUserRequest.getUserCredentials().getUserToken());
		if (sub!=null){ 
			authenticableUserRequest.setUserIdentifier(sub);
			ret = true;
		}
		authenticableUserRequest.setIsAuthenticated(true);
		return ret;
	}
}
