package com.scrumchess.transit.request;

import java.util.Date;

import com.scrumchess.transit.auth.pre.SimpleUserPreAuthentication;
import com.scrumchess.transit.auth.pre.UserPreAuthentication;
import com.scrumchess.transit.game.identification.GameIdentification;
import com.scrumchess.transit.game.identification.GameIdentificationInteger;
import com.scrumchess.transit.game.identification.SimpleGameIdentificationInteger;

public class GameInfoRequest extends AbstractClientReqPreAuthComp implements GameIdentificationInteger {
	private SimpleGameIdentificationInteger simpleGameIdentificationInteger;
	public GameInfoRequest(UserPreAuthentication upe, String gameID){
		super(AbstractClientRequest.GAME_INFO_REQUEST,new Date(),upe);	
		simpleGameIdentificationInteger = new SimpleGameIdentificationInteger(gameID);
	}
	
	public GameInfoRequest(GameInfoRequest gir){
		super(gir.getRequestType(),gir.getRequestDate(),gir.getUserPreAuthentication());
	}
	@Override
	public long getGameID() {
		return simpleGameIdentificationInteger.getGameID();
	}

	@Override
	public Integer getGameInteger() {
		return simpleGameIdentificationInteger.getGameInteger();
	}
}
