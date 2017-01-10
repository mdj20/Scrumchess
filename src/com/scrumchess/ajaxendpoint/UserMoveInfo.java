package com.scrumchess.ajaxendpoint;

// Class that describes a user move that has been received but not validated to a game 
public class UserMoveInfo {
	public String getUserToken() {
		return userToken;
	}
	public String getMoveAlgebraic() {
		return moveAlgebraic;
	}
	public long getGame() {
		return game;
	}
	protected void setUserToken(String userToken) {
		this.userToken = userToken;
	}
	protected void setMoveAlgebraic(String moveAlgebraic) {
		this.moveAlgebraic = moveAlgebraic;
	}
	protected void setGame(long game) {
		this.game = game;
	}
	private String userToken;
	private String moveAlgebraic;
	private long game;
}
