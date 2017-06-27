package com.scrumchess.operations;

import com.google.appengine.api.datastore.EntityNotFoundException;
import com.scrumchess.authentication.ScrumchessUserAuthenticator;
import com.scrumchess.authentication.UserAuthenticationObject;
import com.scrumchess.authentication.UserAuthenticator;
import com.scrumchess.authentication.google.GoogleAuthenticatorString;
import com.scrumchess.data.Game;
import com.scrumchess.data.ScrumchessDatastoreFacade;
import com.scrumchess.transit.game.CompleteGameInfo;
import com.scrumchess.transit.game.SimpleCompleteGameInfo;
import com.scrumchess.transit.request.GameInfoRequest;
import com.scrumchess.transit.request.NewGameRequest;
import com.scrumchess.transit.response.GameInfoResponse;
import com.scrumchess.transit.response.NewGameResponse;

public class MainOperationsGAEDS implements MainUserOperations {

	@Override
	public NewGameResponse newGame( NewGameRequest newGameRequest ) {
		NewGameResponse ret = null;
		if ( authenticate(newGameRequest) ){
			ret = newGameAttempt(newGameRequest);
		}
		return ret;
	}

	@Override
	public GameInfoResponse getGameInfo(GameInfoRequest gameInfoRequest) {
		return null;
	}

	public static MainOperationsGAEDS getInstance(){
		return new MainOperationsGAEDS();
	}
	
	private boolean authenticate( UserAuthenticationObject<String> uao ){
		return (ScrumchessUserAuthenticator.authenticate(uao));
	}
	
	private NewGameResponse newGameAttempt( NewGameRequest newGameRequest ){
		NewGameResponse ret= null;
		ScrumchessDatastoreFacade sdf = ScrumchessDatastoreFacade.getInstance();
		if( newGameRequest.getConfigurationValue() == newGameRequest.WHITE ){
			try {
				Game game = sdf.newGameWhite( newGameRequest.getUserIdentification() );
				ret = new NewGameResponse( true, SimpleCompleteGameInfo.getNewGameInstance( game.getFen(), SimpleCompleteGameInfo.WHITE, game.getWhite()));
			} catch (EntityNotFoundException e) {
				// user not found in database
				ret = new NewGameResponse(false,null);
				ret.setFailReason("User Not Found");
			}
		}
		else{
			ret = new NewGameResponse( false, null );
			ret.setFailReason("invalid configuration");
		}
		return ret;
	}
	
}
