package com.scrumchess.ajaxendpoint;

public class AuthenticatedUserMoveInfo extends UserMoveInfo {
	
	protected String authenicatedID;
	protected AuthenticatedUserMoveInfo(UserMoveInfo umi, String internalID){
		super(umi);
		this.authenicatedID = internalID;
	}
	
	// used for creating a AUMI with a specified ID, useful for creating test cases...
	public static AuthenticatedUserMoveInfo overrideToken(UserMoveInfo umi, String idToken){
		return new AuthenticatedUserMoveInfo(umi,idToken);
	}
	
	protected void setAuthenticatedID(String inID){
		this.authenicatedID = inID;
	}
	public String getAuthenitcatedID(){
		return this.authenicatedID;
	}
	
	@Override
	public String getUserToken(){
		return this.authenicatedID;
	}
}
