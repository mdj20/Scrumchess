package com.scrumchess.userauthentication;

public class UserAuthenticationType {
	public static final int AUTH_TYPE_GOOGLE = 1;
	private int type = -1;
	
	UserAuthenticationType(int set){
		this.type = set;
	}
	
	public int getAuthenticationType(){
		return type;
	}
}
