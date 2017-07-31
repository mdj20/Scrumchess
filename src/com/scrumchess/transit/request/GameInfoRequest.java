package com.scrumchess.transit.request;

import java.util.Date;

import com.scrumchess.authentication.StringBaseUserAuthenticationObject;
import com.scrumchess.authentication.pre.SimpleUserCredentials;
import com.scrumchess.authentication.pre.UserCredentials;
import com.scrumchess.transit.game.identification.GameIdentification;
import com.scrumchess.transit.game.identification.SimpleGameIdentification;

public class GameInfoRequest extends AbstractAuthenticableClientRequest implements GameIdentification {

	private SimpleGameIdentification simpleGameIdentification;

	public GameInfoRequest(StringBaseUserAuthenticationObject upe, long gameID){
		super(RequestType.GAME_INFO_REQUEST,new Date(),upe);	
		simpleGameIdentification=  new SimpleGameIdentification(gameID);
	}
	
	public GameInfoRequest(GameInfoRequest gir){
		super(gir.getRequestType(),gir.getRequestDate(),gir.getUserAuthenticationObject());
		simpleGameIdentification = new SimpleGameIdentification(gir.getGameID());
	}
	
	@Override
	public long getGameID() {
		return simpleGameIdentification.getGameID();
	}

	protected StringBaseUserAuthenticationObject getUserAuthenticationObject() {
		return super.getUserAuthenticationObject();
	}
	

}
