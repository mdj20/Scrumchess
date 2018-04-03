package com.scrumchess.userrequests;

import com.scrumchess.authentication.SimpleUserAuthenticationInfo;

public class AIRequest extends GameInfoRequest {
	
	private int number=3;
	private int depth=2;
	
	
	AIRequest(SimpleUserAuthenticationInfo<String> userAuthenticationInfo, long gameID, int number, int depth){
		this.number = number;
		this.depth = depth;
	}
	
	protected void setNumber(int number) {
		this.number = number;
	}
	protected void setDepth(int depth) {
		this.depth = depth;
	}
	public int getNumber() {
		return number;
	}
	public int getDepth() {
		return depth;
	}
}
