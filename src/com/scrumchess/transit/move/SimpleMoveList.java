package com.scrumchess.transit.move;

import java.util.List;

public class SimpleMoveList implements MoveList {

	List<OrdinalMove> moves;
	
	SimpleMoveList(List<OrdinalMove> moves){
		this.moves = moves;
	}
	
	@Override
	public List<OrdinalMove> getMoves() {
		// TODO Auto-generated method stub
		return null;
	}

}
