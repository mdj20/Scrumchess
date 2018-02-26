package com.scrumchess.data;

import com.google.appengine.api.datastore.EntityNotFoundException;
import com.scrumchess.authentication.ScrumchessUserAuthenticator;
import com.scrumchess.userrequests.AbstractUserRequest;
import com.scrumchess.userrequests.AbstractUserResponse;
import com.scrumchess.userrequests.GameInfoRequest;
import com.scrumchess.userrequests.GameInfoResponse;

public class ScrumchessUserRequestHandler {
	private ScrumchessDatastoreFacade sdf;
	
	private ScrumchessUserRequestHandler(){
		sdf= ScrumchessDatastoreFacade.getInstance();
	}
	
	public static ScrumchessUserRequestHandler getInstance(){
		return new ScrumchessUserRequestHandler();
	}
	
	
	public GameInfoResponse tryGameInfoRequest(GameInfoRequest gameInfoRequest){
		GameInfoResponse ret = null;
		Game returnObject = null;
		if(!checkAuthentication(gameInfoRequest)){
			ret = new GameInfoResponse(false,AbstractUserResponse.BaseFailureReason.AUTHERNTICATION_FAILURE);
		}
		else {
			try {
				returnObject = sdf.getGameById(gameInfoRequest.getGameID());
				ret = new GameInfoResponse(true,returnObject);
				
			} catch (EntityNotFoundException e) {
				ret = new GameInfoResponse(false,AbstractUserResponse.BaseFailureReason.ENTITY_NOT_FOUND);
			}
		}
		return ret;
	}
	
	private boolean checkAuthentication(AbstractUserRequest aur){
		return ScrumchessUserAuthenticator.authenticate(aur); 
	}
	
}
