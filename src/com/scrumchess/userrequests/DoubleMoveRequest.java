package com.scrumchess.userrequests;

import com.scrumchess.authentication.SimpleUserAuthenticationInfo;

public class DoubleMoveRequest extends MoveRequest {
	String doubleMove;
	DoubleMoveRequest(SimpleUserAuthenticationInfo<String> userAuthenticationInfo, long gameID, String move, String doubleMove){
		super(userAuthenticationInfo,gameID,move);
		this.doubleMove = doubleMove;
	}
	
	public String getDoubleMove() {
		return doubleMove;
	}
	
	public void setDoubleMove(String doubleMove) {
		this.doubleMove =doubleMove;
	}
}
