package com.scrumchess.transit.user.uniqueidentification;

public abstract class AbstractUserIdentificationString implements UserIdentification<String> {
	private String id;
	protected AbstractUserIdentificationString(String id){
		this.id = id;
	}
	
	public String getId(){
		return id;
	}
}
