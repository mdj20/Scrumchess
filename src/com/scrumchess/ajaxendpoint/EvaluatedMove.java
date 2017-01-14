package com.scrumchess.ajaxendpoint;
import com.scrumchess.data.Game;

public class EvaluatedMove {
	private EvaluatedMove(Game game, String fen, UserMoveInfo umi, boolean dsr){
		this.game = game;
		this.updateFen = fen;
		this.userMoveInfo = umi;
		this.datastoreReady = dsr;
	}
	public static EvaluatedMove createValid(Game game, String fen, UserMoveInfo umi){
		return new EvaluatedMove(game,fen,umi,true);
	}
	public static EvaluatedMove createInvalid(Game game, String fen, UserMoveInfo umi){
		return new EvaluatedMove(game,fen,umi,false);
	}
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
	public boolean datastoreReady(){
		return this.datastoreReady;
	}
	private UserMoveInfo userMoveInfo;
	private Game game;
	private String updateFen;
	private boolean datastoreReady;
}
