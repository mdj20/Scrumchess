package com.scrumchess.transit.game.state;

public class SimpleState implements State{
	private String fen;
	private int moveNum;
	
	public SimpleState(String fen, int moveNum){
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
