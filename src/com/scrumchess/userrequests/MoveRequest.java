package com.scrumchess.userrequests;

import com.scrumchess.authentication.SimpleUserAuthenticationInfo;
import com.scrumchess.data.Game;

public class MoveRequest extends GameInfoRequest{
	
	private boolean isEvaluated = false;
	private String move;
	private int moveNumber = -1;
	private String updatedFen;
	private Game gameInfo;
	
	
	public Game setGame(Game value){
		Game ret = gameInfo;
		gameInfo = value;
		return ret;
	}
	
	public Game getGame(){
		return gameInfo;
	}

	public void setUpdatedFen(String updatedFen) {
		this.updatedFen = updatedFen;
	}
	
	public String getUpdatedFen(){
		return updatedFen;
	}

	MoveRequest(SimpleUserAuthenticationInfo<String> userAuthenticationInfo, long gameID, String move) {
		super(userAuthenticationInfo, gameID);
	}
	
	public int setMoveNumber(int value){
		int ret = moveNumber;
		moveNumber = value;
		return ret;
	}
	
	public int getMoveNumber(){
		return moveNumber;
	}
	
	public boolean getIsEvaluated(){
		return isEvaluated;
	}
	
	public boolean setIsEvaluated(boolean value){
		boolean ret = isEvaluated;
		isEvaluated = value;
		return ret;
	}
	
	public String getMove() {
		return move;
	}
	
}
