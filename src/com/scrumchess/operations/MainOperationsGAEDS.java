package com.scrumchess.operations;

import java.util.List;

import com.google.appengine.api.datastore.EntityNotFoundException;
import com.scrumchess.authentication.ScrumchessUserAuthenticator;
import com.scrumchess.authentication.UserAuthenticationObject;
import com.scrumchess.data.Game;
import com.scrumchess.data.GameMovelistComposite;
import com.scrumchess.data.ScrumchessDatastoreFacade;
import com.scrumchess.transit.CompleteGameUserInfo;
import com.scrumchess.transit.CompleteGameUserInfoBuilder;
import com.scrumchess.transit.MultiUserConfiguration;
import com.scrumchess.transit.game.CompleteGameInfo;
import com.scrumchess.transit.game.SimpleCompleteGameInfo;
import com.scrumchess.transit.game.playerconfiguration.PlayerConfiguration;
import com.scrumchess.transit.move.MoveAlgebraic;
import com.scrumchess.transit.request.AbstractAuthenticableClientRequest;
import com.scrumchess.transit.request.GameInfoRequest;
import com.scrumchess.transit.request.NewGameRequest;
import com.scrumchess.transit.response.GameInfoResponse;
import com.scrumchess.transit.response.NewGameResponse;

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

	@Override
	public GameInfoResponse getGameInfo( GameInfoRequest gameInfoRequest ) {
		GameInfoResponse ret = null;
		if ( authenticate(gameInfoRequest) ){
			
		}
		else {
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
		//TODO change this to utilize the PlayerConfiguration.Config enum
		switch (newGameRequest.getConfigurationValue()) {
			case WHITE :{
				try {
					Game game = sdf.newGameWhite( newGameRequest.getId() );
					ret = 
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
		NewGameResponse response=null;
		CompleteGameUserInfoBuilder builder = new CompleteGameUserInfoBuilder();
		builder.setFen(game.getFen());
		builder.setGameId(game.getId());
		builder.setPlayerConfiguration(muc.getConfigurationValue());
		for(int i = 0; i <2 ; i++){
			builder.setId(i,muc.getId(i));
			builder.setPseudonym(i, muc.getPseudonym(i));	
		}
		CompleteGameUserInfo returnObject = builder.build();
		response = new NewGameResponse(true,returnObject);
		return response;
	}
	
	private <T extends AbstractAuthenticableClientRequest & GameIdentification> gameInfoAttempt( GameInfoRequest gameInfoRequest ){
		GameInfoResponse ret = null;
		ScrumchessDatastoreFacade sdf = ScrumchessDatastoreFacade.getInstance();
		try {
			GameMovelistComposite gmlc = sdf.getFullGameInfo(gameInfoRequest.getGameID());
		} catch ( EntityNotFoundException e ) {
			e.printStackTrace();
		}
		return ret;
	}
	
	private CompleteGameInfo buildGameInfo(GameMovelistComposite gameMoveListComposite){
		List<MoveAlgebraic> moves = GoogleDataStoreTranslationUtility.translateMove(gameMoveListComposite.getMoves());
	}
}
