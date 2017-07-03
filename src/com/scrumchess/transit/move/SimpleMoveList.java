package com.scrumchess.transit.move;

import java.util.ArrayList;
import java.util.List;

public class SimpleMoveList implements MoveList {

	List<MoveAlgebraic> moves;
	
	public SimpleMoveList(ArrayList<MoveAlgebraic> moves2){
		this.moves = moves2;
	}
	
	@Override
	public List<MoveAlgebraic> getMoves() {
		// TODO Auto-generated method stub
		return null;
	}

}
