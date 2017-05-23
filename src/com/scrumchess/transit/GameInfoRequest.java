package com.scrumchess.transit;

import java.util.Date;

import com.scrumchess.transit.auth.pre.SimpleUserPreAuthentiation;
import com.scrumchess.transit.game.identification.GameIdentification;
import com.scrumchess.transit.game.identification.GameIdentificationInteger;
import com.scrumchess.transit.game.identification.SimpleGameIndentificationInteger;

public class GameInfoRequest extends AbstractClientRequest implements ClientRequest,GameIdentificationInteger {
	private SimpleUserPreAuthentiation simpleUserPreAuth;
	private SimpleGameIndentificationInteger simpleGameIdentificationInteger;
	private int requestType;
	
	public GameInfoRequest(String userToken, int authType, String gameID){
		super(new Date());
		simpleUserPreAuth = new SimpleUserPreAuthentiation(userToken, authType);
		simpleGameIdentificationInteger = new SimpleGameIndentificationInteger(gameID);
		requestType = super.GAME_REQUEST;
	}

	@Override
	public String getUserToken() {
		return simpleUserPreAuth.getUserToken();
	}

	@Override
	public int getAuthenticationType() {
		return simpleUserPreAuth.getAuthenticationType();
	}

	@Override
	public String getGameID() {
		return simpleGameIdentificationInteger.getGameID() ;
	}

	@Override
	public int getRequestType() {
		return requestType;
	}

	@Override
	public Date getRequestDate() {
		return super.getRequestDate();
	}

	@Override
	public Integer getGameInteger() {
		return simpleGameIdentificationInteger.getGameInteger();
	}
}
