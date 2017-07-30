package com.scrumchess.transit.request;

public enum RequestType {	
	GAME_INFO_REQUEST (0),
	SEND_MOVE_REQUEST (1),
	NICKNAME_CHANGE_REQUEST (2),  
	NEW_GAME_REQUEST (3);	
	RequestType(int value){
		this.intRep = value;
	}
	public int getIntValue(){
		return this.intRep;
	}
	private int intRep;
}
