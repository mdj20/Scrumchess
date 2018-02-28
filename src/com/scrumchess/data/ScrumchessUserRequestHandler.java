package com.scrumchess.data;

import com.google.appengine.api.datastore.EntityNotFoundException;
import com.scrumchess.authentication.ScrumchessUserAuthenticator;
import com.scrumchess.userrequests.AbstractUserRequest;
import com.scrumchess.userrequests.AbstractUserResponse;
import com.scrumchess.userrequests.GameInfoRequest;
import com.scrumchess.userrequests.GameInfoResponse;
import com.scrumchess.userrequests.MoveRequest;
import com.scrumchess.userrequests.MoveRequestResponse;
import com.scrumchess.userrequests.NewGameRequest;
import com.scrumchess.userrequests.NewGameResponse;
import com.scrumchess.userrequests.UnversalFailureReason;

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
			ret = new GameInfoResponse(false,UnversalFailureReason.AUTHERNTICATION_FAILURE);
		}
		else {
			try {
				returnObject = sdf.getGameById(gameInfoRequest.getGameID());
				String userId = gameInfoRequest.getUserIdentifier();
				if(returnObject.getBlack().equals(userId)||returnObject.getWhite().equals(userId)){
					ret = new GameInfoResponse(true,returnObject);
				}
				else {
					ret = new GameInfoResponse(false,UnversalFailureReason.USER_NOT_OWNER);
				}
			} catch (EntityNotFoundException e) {
				ret = new GameInfoResponse(false,UnversalFailureReason.ENTITY_NOT_FOUND);
			}
		}
		return ret;
	}
	
	
	public MoveRequestResponse tryMoveRequest(MoveRequest moveRequest){
		MoveRequestResponse ret = null;
		if(!checkAuthentication(moveRequest)){
			ret = new MoveRequestResponse(false,UnversalFailureReason.AUTHERNTICATION_FAILURE);
		}
		else {
			try {
				if(sdf.evaluateMoveRequest(moveRequest)){
					if(sdf.commitMoveRequestAtomic(moveRequest)){
						ret = new MoveRequestResponse(true,moveRequest.getGame());
					}
				}
				else{
					ret = new MoveRequestResponse(false,UnversalFailureReason.INVALID_MOVE);
				}
			} catch (EntityNotFoundException e) {
				ret = new MoveRequestResponse(false,UnversalFailureReason.ENTITY_NOT_FOUND);
			}
		}
		return ret;
	}
	
	public NewGameResponse tryNewGameRequest(NewGameRequest newGameRequest){
		NewGameResponse ret = null;
		Game game = null;
		if(!checkAuthentication(newGameRequest)){
			ret = new NewGameResponse(false,UnversalFailureReason.AUTHERNTICATION_FAILURE);
		}
		else{
			switch (newGameRequest.getNewGameConfig()){
			case BLACK:
				
			case BLACK2:
				break;
			case WHITE:
				break;
			case WHITE2:
				break;
			default:
				break;
			};
			
			
		}
		
		return ret;
	}
	
	private boolean checkAuthentication(AbstractUserRequest aur){
		return ScrumchessUserAuthenticator.authenticate(aur); 
	}
	
}
