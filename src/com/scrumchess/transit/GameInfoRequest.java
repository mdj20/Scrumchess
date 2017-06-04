package com.scrumchess.transit;

import java.util.Date;

import com.scrumchess.transit.auth.pre.SimpleUserPreAuthentication;
import com.scrumchess.transit.auth.pre.UserPreAuthentication;
import com.scrumchess.transit.game.identification.GameIdentification;
import com.scrumchess.transit.game.identification.GameIdentificationInteger;
import com.scrumchess.transit.game.identification.SimpleGameIndentificationInteger;

public class GameInfoRequest extends AbstractClientReqPreAuthComp implements GameIdentificationInteger {
	private SimpleGameIndentificationInteger simpleGameIdentificationInteger;
	public GameInfoRequest(UserPreAuthentication upe, String gameID){
		super(AbstractClientRequest.GAME_REQUEST,new Date(),upe);	
		simpleGameIdentificationInteger = new SimpleGameIndentificationInteger(gameID);
	}
	
	public GameInfoRequest(GameInfoRequest gir){
		super(gir.getRequestType(),gir.getRequestDate(),gir.getUserPreAuthentication());
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
