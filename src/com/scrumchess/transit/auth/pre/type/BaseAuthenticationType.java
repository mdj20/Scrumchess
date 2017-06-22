package com.scrumchess.transit.auth.pre.type;

// base class for all userAUthentication information, it will contain a centralized set of integers as identifiers for user Authentication.
public abstract class BaseAuthenticationType implements AuthenticationType {

	private int type = -1;
	protected BaseAuthenticationType(int set){
		this.type = set;
	}
	public int getAuthenticationType(){
		return type;
	}
}
