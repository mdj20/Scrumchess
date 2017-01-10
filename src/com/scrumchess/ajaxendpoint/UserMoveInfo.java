package com.scrumchess.ajaxendpoint;

// Class that describes a user move that has been received but not validated to a game 
public class UserMoveInfo {
	public String getUserId() {
		return userId;
	}
	public String getMoveAlgebraic() {
		return moveAlgebraic;
	}
	public long getGame() {
		return game;
	}
	protected void setUserId(String userId) {
		this.userId = userId;
	}
	protected void setMoveAlgebraic(String moveAlgebraic) {
		this.moveAlgebraic = moveAlgebraic;
	}
	protected void setGame(long game) {
		this.game = game;
	}
	private String userId;
	private String moveAlgebraic;
	private long game;
}
