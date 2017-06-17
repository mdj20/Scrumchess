package com.scrumchess.transit.game.state;

public class SimpleGameState implements GameState{
	private String fen;
	private int moveNum;
	
	SimpleGameState(String fen, int moveNum){
		this.fen = fen;
		this.moveNum = moveNum;
	}
	public String getFen(){
		return fen;
	}
	@Override
	public int getHalfMoveNumber(){
		return moveNum;
	}	
}
