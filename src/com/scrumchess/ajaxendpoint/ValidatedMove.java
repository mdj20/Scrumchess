package com.scrumchess.ajaxendpoint;
import com.scrumchess.data.Game;

public class ValidatedMove {
	protected void setUserMoveInfo(UserMoveInfo userMoveInfo) {
		this.userMoveInfo = userMoveInfo;
	}
	protected void setGame(Game game) {
		this.game = game;
	}
	protected void setUpdateFen(String updateFen) {
		this.updateFen = updateFen;
	}
	public UserMoveInfo getUserMoveInfo() {
		return userMoveInfo;
	}
	public Game getGame() {
		return game;
	}
	public String getUpdateFen() {
		return updateFen;
	}
	private UserMoveInfo userMoveInfo;
	private Game game;
	private String updateFen;
}
