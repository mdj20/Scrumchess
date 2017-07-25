package com.scrumchess.operations.google;

import java.util.List;

import com.google.appengine.api.datastore.EntityNotFoundException;
import com.scrumchess.ajaxendpoint.AuthenticatedUserMoveInfo;
import com.scrumchess.ajaxendpoint.EvaluatedMove;
import com.scrumchess.authentication.ScrumchessUserAuthenticator;
import com.scrumchess.authentication.UserAuthenticationObject;
import com.scrumchess.data.Game;
import com.scrumchess.data.GameMovelistComposite;
import com.scrumchess.data.ScrumchessDatastoreFacade;
import com.scrumchess.data.User;
import com.scrumchess.operations.MainUserOperations;
import com.scrumchess.transit.CompleteGameUserInfo;
import com.scrumchess.transit.CompleteGameUserInfoBuilder;
import com.scrumchess.transit.MultiUserConfiguration;
import com.scrumchess.transit.game.CompleteGameInfo;
import com.scrumchess.transit.game.SimpleCompleteGameInfo;
import com.scrumchess.transit.game.identification.GameIdentification;
import com.scrumchess.transit.game.playerconfiguration.PlayerConfiguration;
import com.scrumchess.transit.game.state.SimpleState;
import com.scrumchess.transit.game.state.State;
import com.scrumchess.transit.move.MoveAlgebraic;
import com.scrumchess.transit.request.AbstractAuthenticableClientRequest;
import com.scrumchess.transit.request.GameInfoRequest;
import com.scrumchess.transit.request.NewGameRequest;
import com.scrumchess.transit.response.ChangePseudonymResponse;
import com.scrumchess.transit.response.GameInfoResponse;
import com.scrumchess.transit.response.NewGameResponse;
import com.scrumchess.transit.response.SendMoveResponse;
import com.scrumchess.transit.user.CompositeUserIdentification;


/// Implimentation of the MainOperations interface designed to work with Google app engine's datastore.

public class MainOperationsGAEDS implements MainUserOperations {
	
	@Override
	public <T extends AbstractAuthenticableClientRequest & MultiUserConfiguration> NewGameResponse newGame( T newGameRequest ) {
		NewGameResponse ret = null;
		if ( authenticate(newGameRequest) ){
			ret = newGameAttempt(newGameRequest);
		}
		else{
			ret = new NewGameResponse(false,null);
			ret.setFailReason("Unable To Authenticate User");
		}
		return ret;
	}


	public static MainOperationsGAEDS getInstance(){
		return new MainOperationsGAEDS();
	}
	
	private boolean authenticate( UserAuthenticationObject<String> uao ){
		return (ScrumchessUserAuthenticator.authenticate(uao));
	}
	
	private NewGameResponse newGameAttempt( MultiUserConfiguration newGameRequest ){
		NewGameResponse ret= null;
		ScrumchessDatastoreFacade sdf = ScrumchessDatastoreFacade.getInstance();
		switch (newGameRequest.getConfigurationValue()) {
			case WHITE :{
				try {
					Game game = sdf.newGameWhite( newGameRequest.getId() );
					ret = buildNewGameResponse(game,newGameRequest);
				} catch (EntityNotFoundException e) {
					// user not found in database
					ret = new NewGameResponse( false , null );
					ret.setFailReason("User Not Found");
				}
		
			}
			case BLACK :{
				
			}
			case BOTH :{
				
			}
			case NONE:{
				
			}
		}
		return ret;
	}
	
	private NewGameResponse buildNewGameResponse(Game game, MultiUserConfiguration muc){
		NewGameResponse ret=null;
		CompleteGameUserInfoBuilder builder = new CompleteGameUserInfoBuilder();
		builder.setFen(game.getFen());
		builder.setGameId(game.getId());
		builder.setPlayerConfiguration(muc.getConfigurationValue());
		for(int i = 0; i <2 ; i++){
			builder.setId(i,muc.getId(i));
			builder.setPseudonym(i, muc.getPseudonym(i));	
		}
		CompleteGameUserInfo returnObject = builder.build();
		ret = new NewGameResponse(true,returnObject);
		return ret;
	}
	
	// not used 
	private <T extends AbstractAuthenticableClientRequest & GameIdentification> GameInfoResponse gameInfoAttempt( T gameInfoRequest ){
		GameInfoResponse ret = null;
		ScrumchessDatastoreFacade sdf = ScrumchessDatastoreFacade.getInstance();
		try {
			GameMovelistComposite gmlc = sdf.getFullGameInfo(gameInfoRequest.getGameID());
		} catch ( EntityNotFoundException e ) {
			ret = new GameInfoResponse(false,null);
			ret.setFailReason("Game Not Found");
		}
		return ret;
	}

	@Override
	public NewGameResponse newGame(NewGameRequest newGameRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T extends AbstractAuthenticableClientRequest & GameIdentification> GameInfoResponse getGameInfo(T gameInfoRequest) {
		GameInfoResponse ret = null;
		if ( authenticate(gameInfoRequest) ){
			ret = fullGameInfoAttempt(gameInfoRequest.getGameID());
		}
		else{
			ret = new GameInfoResponse(false,null);
			ret.setFailReason("Unable To Authenticate User");
		}
		return ret;
	}
	
	private GameInfoResponse fullGameInfoAttempt(long gameId){
		GameInfoResponse ret = null;
		ScrumchessDatastoreFacade sdf = ScrumchessDatastoreFacade.getInstance();
		try {
			CompleteGameInfo completeGameInfo = GoogleDataStoreTranslationUtility.buildCompleteGameInfo(sdf.getFullGameInfo(gameId));
			ret = new GameInfoResponse(true,completeGameInfo);
		} catch (EntityNotFoundException e) {
			ret = new GameInfoResponse(false,null);
			ret.setFailReason("Unable to find game");
		}
		return ret;
	}
	
	private SendMoveResponse sendMoveAttempt(MoveAlgebraic moveAlgebraic, long gameId, String userId){
		SendMoveResponse ret = null;
		ScrumchessDatastoreFacade sdf = ScrumchessDatastoreFacade.getInstance();
		AuthenticatedUserMoveInfo aumi = GoogleDataStoreTranslationUtility.buildAuthenticatedUserMoveInfo(moveAlgebraic, gameId, userId);
		EvaluatedMove evaluatedMove = sdf.evaluateMove(aumi);
		if(evaluatedMove.datastoreReady()){
			boolean success = sdf.commitMoveAtomic(evaluatedMove);
			if (success){
				State state = new SimpleState(evaluatedMove.getUpdateFen(),evaluatedMove.getGame().getMoveNum()+1);
				ret = new SendMoveResponse(true,state);
			}
			else{
				ret= new SendMoveResponse(false,null);
				ret.setFailReason("Unable to commit Move");
			}
		}
		else {
			ret = new SendMoveResponse(false,null);
			ret.setFailReason("Invalid Move!");
		}
		return ret;
	}

	@Override
	public <T extends AbstractAuthenticableClientRequest & GameIdentification & MoveAlgebraic> SendMoveResponse sendMove(
			T sendMoveRequest) {
		SendMoveResponse ret = null;
		if ( authenticate(sendMoveRequest) ){
			ret = sendMoveAttempt(sendMoveRequest, sendMoveRequest.getGameID(),sendMoveRequest.getUserIdentification());
		}
		else{
			ret = new SendMoveResponse(false,null);
			ret.setFailReason("Unable To Authenticate User");
		}
		return ret;
		
	}

	@Override
	public <T extends AbstractAuthenticableClientRequest & CompositeUserIdentification> ChangePseudonymResponse 
		changeUserPseusonym(T changePseudonymRequest) {
				ChangePseudonymResponse ret = null;
				if ( authenticate(changePseudonymRequest) ){
					ret = changePseudonymAttempt(changePseudonymRequest); 
				}
				else{
					ret = new ChangePseudonymResponse(false,null);
					ret.setFailReason("Unable To Authenticate User");
				}
				return ret;
	}
	
	private <T extends AbstractAuthenticableClientRequest & CompositeUserIdentification> ChangePseudonymResponse 
		changePseudonymAttempt(T changePseudonymRequest ){
			ChangePseudonymResponse ret = null;
			ScrumchessDatastoreFacade sdf = ScrumchessDatastoreFacade.getInstance();
			try {
				User user = sdf.changePseudonym(changePseudonymRequest.getId(),changePseudonymRequest.getPseudonym());
				ret = new ChangePseudonymResponse(true,user.getName());
			} catch (EntityNotFoundException e) {
				ret = new ChangePseudonymResponse(false,null);
				ret.setFailReason("Unable To Find User");
			}
			return ret;
	}
}
