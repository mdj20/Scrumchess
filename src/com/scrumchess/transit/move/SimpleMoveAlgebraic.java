package com.scrumchess.transit.move;

public class SimpleMoveAlgebraic implements MoveAlgebraic {
	private String move;
	private int moveNumber;

	public SimpleMoveAlgebraic(String initMove, int number){
		move=initMove;
		moveNumber = number;
	}
	
	@Override
	public String getMoveAlgebraic() {
		return move;
	}

	@Override
	public int getMoveNumber() {
		return moveNumber;
	}

	@Override
	public int compareTo(OrdinalMove o) {
		return this.getMoveNumber()-o.getMoveNumber();
	}

}
