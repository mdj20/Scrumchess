package com.scrumchess.transit.request;

import java.util.Date;

import com.scrumchess.transit.auth.pre.SimpleUserPreAuthentication;
import com.scrumchess.transit.auth.pre.UserPreAuthentication;
import com.scrumchess.transit.game.identification.GameIdentification;
import com.scrumchess.transit.game.identification.SimpleGameIdentification;

public class GameInfoRequest extends AbstractAuthenticableClientRequest implements GameIdentification {
	private SimpleGameIdentification simpleGameIdentification;
	public GameInfoRequest(UserPreAuthentication upe, long gameID){
		super(AbstractClientRequest.GAME_INFO_REQUEST,new Date(),upe);	
		simpleGameIdentification=  new SimpleGameIdentification(gameID);
	}
	
	public GameInfoRequest(GameInfoRequest gir){
		super(gir.getRequestType(),gir.getRequestDate(),gir.getUserPreAuthentication());
	}
	@Override
	public long getGameID() {
		return simpleGameIdentification.getGameID();
	}

	

}
