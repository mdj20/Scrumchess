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
import com.scrumchess.userrequests.UniversalFailureReason;

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
			ret = new GameInfoResponse(false,UniversalFailureReason.AUTHERNTICATION_FAILURE);
		}
		else {
			try {
				returnObject = sdf.getGameById(gameInfoRequest.getGameID());
				String userId = gameInfoRequest.getUserIdentifier();
				if(returnObject.getBlack().equals(userId)||returnObject.getWhite().equals(userId)){
					ret = new GameInfoResponse(true,returnObject);
				}
				else {
					ret = new GameInfoResponse(false,UniversalFailureReason.USER_NOT_OWNER);
				}
			} catch (EntityNotFoundException e) {
				ret = new GameInfoResponse(false,UniversalFailureReason.ENTITY_NOT_FOUND);
			}
		}
		return ret;
	}
	
	
	public MoveRequestResponse tryMoveRequest(MoveRequest moveRequest){
		MoveRequestResponse ret = null;
		if(!checkAuthentication(moveRequest)){
			ret = new MoveRequestResponse(false,UniversalFailureReason.AUTHERNTICATION_FAILURE);
		}
		else {
			try {
				if(sdf.evaluateMoveRequest(moveRequest)){
					if(sdf.commitMoveRequestAtomic(moveRequest)){
						ret = new MoveRequestResponse(true,moveRequest.getGame());
					}
				}
				else{
					ret = new MoveRequestResponse(false,UniversalFailureReason.INVALID_MOVE);
				}
			} catch (EntityNotFoundException e) {
				ret = new MoveRequestResponse(false,UniversalFailureReason.ENTITY_NOT_FOUND);
			}
		}
		return ret;
	}
	
	public NewGameResponse tryNewGameRequest(NewGameRequest newGameRequest){
		NewGameResponse ret = null;
		if(!checkAuthentication(newGameRequest)){
			ret = new NewGameResponse(false,UniversalFailureReason.AUTHERNTICATION_FAILURE);
		}
		else{
			switch (newGameRequest.getNewGameConfig()){
			case BLACK:
				ret = tryNewGameBlack(newGameRequest);
			case BLACK2:
				ret = tryNewGame2Black(newGameRequest);
				break;
			case WHITE:
				ret = tryNewGameWhite(newGameRequest);
				break;
			case WHITE2:
				ret = tryNewGame2White(newGameRequest);
				break;
			default:
				ret = new NewGameResponse(false,UniversalFailureReason.NA);
			};	
		}
		return ret;
	}
	
	private NewGameResponse tryNewGameBlack(NewGameRequest newGameRequest){
		NewGameResponse ret = null;
		Game gameObject = null;
		try {
			gameObject = sdf.newGameBlack(newGameRequest.getUserIdentifier());
			ret = new NewGameResponse(true,gameObject);
		} catch (EntityNotFoundException e) {
			ret = new NewGameResponse(false,UniversalFailureReason.AUTHERNTICATION_FAILURE);
		}
		return ret;
	}
	
	private NewGameResponse tryNewGameWhite(NewGameRequest newGameRequest){
		NewGameResponse ret = null;
		Game gameObject = null;
		try {
			gameObject = sdf.newGameWhite(newGameRequest.getUserIdentifier());
			ret = new NewGameResponse(true,gameObject);
		} catch (EntityNotFoundException e) {
			ret = new NewGameResponse(false,UniversalFailureReason.AUTHERNTICATION_FAILURE);
		}
		return ret;
	}
	
	private NewGameResponse tryNewGame2Black(NewGameRequest newGameRequest){
		NewGameResponse ret = null;
		Game gameObject = null;
		try {
			gameObject = sdf.newTwoPlayerGame(newGameRequest.getOtherPlayerId(),newGameRequest.getUserIdentifier() );
			ret = new NewGameResponse(true,gameObject);
		} catch (EntityNotFoundException e) {
			ret = new NewGameResponse(false,UniversalFailureReason.AUTHERNTICATION_FAILURE);
		}
		return ret;
	}	
	
	private NewGameResponse tryNewGame2White(NewGameRequest newGameRequest){
		NewGameResponse ret = null;
		Game gameObject = null;
		try {
			gameObject = sdf.newTwoPlayerGame(newGameRequest.getUserIdentifier(),newGameRequest.getOtherPlayerId());
			ret = new NewGameResponse(true,gameObject);
		} catch (EntityNotFoundException e) {
			ret = new NewGameResponse(false,UniversalFailureReason.AUTHERNTICATION_FAILURE);
		}
		return ret;
	}
	
	
	
	private boolean checkAuthentication(AbstractUserRequest aur){
		return ScrumchessUserAuthenticator.authenticate(aur); 
	}
	
}
