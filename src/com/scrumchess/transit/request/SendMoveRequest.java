package com.scrumchess.transit.request;

import java.util.Date;

import com.scrumchess.authentication.pre.UserCredentials;
import com.scrumchess.transit.move.MoveAlgebraic;
import com.scrumchess.transit.move.OrdinalMove;
import com.scrumchess.transit.move.SimpleMoveAlgebraic;

public class SendMoveRequest extends GameInfoRequest implements MoveAlgebraic {
	private MoveAlgebraic moveAlgebraic;
	public SendMoveRequest(GameInfoRequest gir, String move, int moveNumber){
		super(gir);
		moveAlgebraic = new SimpleMoveAlgebraic(move, moveNumber);
	}
	
	@Override
	public int getHalfMoveNumber() {
		return moveAlgebraic.getHalfMoveNumber();
	}

	@Override
	public int compareTo(OrdinalMove o) {
		return moveAlgebraic.compareTo(o);
	}

	@Override
	public String getMoveAlgebraic() {
		return moveAlgebraic.getMoveAlgebraic();
	}	
}