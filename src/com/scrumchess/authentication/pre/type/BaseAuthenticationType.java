package com.scrumchess.authentication.pre.type;

// base class for all userAUthentication information, it will contain a centralized set of integers as identifiers for user Authentication.
public abstract class BaseAuthenticationType implements AuthenticationType {


	private Type type ;
	protected BaseAuthenticationType(Type set){
		this.type = set;
	}
	public Type getAuthenticationType(){
		return type;
	}
}
