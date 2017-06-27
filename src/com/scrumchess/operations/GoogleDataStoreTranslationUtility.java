package com.scrumchess.operations;

import java.util.ArrayList;
import java.util.List;

import com.scrumchess.data.Move;
import com.scrumchess.transit.move.MoveAlgebraic;
import com.scrumchess.transit.move.SimpleMoveAlgebraic;

public class GoogleDataStoreTranslationUtility {
	
	protected static ArrayList<MoveAlgebraic> translateMove(List<Move> move){
		ArrayList<MoveAlgebraic> ret = new ArrayList<MoveAlgebraic>();
		for(Move m:move){
			ret.add(translateMove(m));
		}
		return ret;
	}
	protected static MoveAlgebraic translateMove(Move move){
		return new SimpleMoveAlgebraic(move.getMoveString(),move.getNumber());
	}
	
}
