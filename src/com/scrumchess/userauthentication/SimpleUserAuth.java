package com.scrumchess.userauthentication;

public class SimpleUserAuth extends UserAuthenticationType implements UserAuth {
	
	
	private String token;
	private int authenticationType;
	
	SimpleUserAuth(String setToken, int type){
		super(type);
		token = setToken;
	}

	public String getUserToken(){
		return token;
	}

	public int getAuthenticationType(){
		return authenticationType;
	}
	
}
