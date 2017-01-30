package com.scrumchess.ajaxendpoint;

// Class that describes a user move that has been received but not validated to a game 
public class UserMoveInfo {
	
	public UserMoveInfo(String userToken,String moveAlgebraic, long game){
		this.userToken = userToken;
		this.moveAlgebraic = moveAlgebraic;
		this.gameID=game;
	}
	
	public UserMoveInfo(UserMoveInfo umi) {
		this.userToken = umi.getUserToken();
		this.moveAlgebraic = umi.getMoveAlgebraic();
		this.gameID = umi.getGameID();
	}

	public String getUserToken() {
		return userToken;
	}
	public String getMoveAlgebraic() {
		return moveAlgebraic;
	}
	public long getGameID() {
		return gameID;
	}
	protected void setUserToken(String userToken) {
		this.userToken = userToken;
	}
	protected void setMoveAlgebraic(String moveAlgebraic) {
		this.moveAlgebraic = moveAlgebraic;
	}
	protected void setGameID(long game) {
		this.gameID = game;
	}
	private String userToken;
	private String moveAlgebraic;
	private long gameID;
}
