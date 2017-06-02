package com.scrumchess.transit;

import java.util.Date;

import com.scrumchess.transit.auth.pre.SimpleUserPreAuthentication;
import com.scrumchess.transit.game.identification.GameIdentification;
import com.scrumchess.transit.game.identification.GameIdentificationInteger;
import com.scrumchess.transit.game.identification.SimpleGameIndentificationInteger;

public class GameInfoRequest extends AbstractClientReqPreAuthComp implements GameIdentificationInteger {
	private SimpleGameIndentificationInteger simpleGameIdentificationInteger;
	public GameInfoRequest(String userToken, int authType, String gameID){
		super(AbstractClientRequest.GAME_REQUEST,new Date(), new SimpleUserPreAuthentication(userToken, authType));	
		simpleGameIdentificationInteger = new SimpleGameIndentificationInteger(gameID);
	}

	@Override
	public String getGameID() {
		return simpleGameIdentificationInteger.getGameID();
	}

	@Override
	public Integer getGameInteger() {
		return simpleGameIdentificationInteger.getGameInteger();
	}
}
