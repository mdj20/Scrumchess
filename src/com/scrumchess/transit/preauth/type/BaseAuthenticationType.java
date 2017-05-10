package com.scrumchess.transit.preauth.type;

// base class for all userAUthentication information, it will contain a centralized set of integers as identifiers for user Authentication.
public abstract class BaseAuthenticationType implements AuthenticationType {
	public static final int AUTH_TYPE_GOOGLE = 1;
	private int type = -1;
	protected BaseAuthenticationType(int set){
		this.type = set;
	}
	
	public int getAuthenticationType(){
		return type;
	}
}
