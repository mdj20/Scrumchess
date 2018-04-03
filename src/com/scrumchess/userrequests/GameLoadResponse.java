package com.scrumchess.userrequests;

import com.scrumchess.data.GameMovelistComposite;

public class GameLoadResponse extends AbstractUserResponse<GameMovelistComposite> {
	
	public GameLoadResponse(boolean success, GameMovelistComposite gameMoveListComposite) {
		super(success, gameMoveListComposite);
	}


	public GameLoadResponse(boolean success, UniversalFailureReason reason) {
		super(success, reason);
	}

}
