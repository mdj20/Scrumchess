package com.scrumchess.operations;

import com.google.appengine.api.datastore.EntityNotFoundException;
import com.scrumchess.authentication.ScrumchessUserAuthenticator;
import com.scrumchess.authentication.UserAuthenticationObject;
import com.scrumchess.authentication.UserAuthenticator;
import com.scrumchess.authentication.google.GoogleAuthenticatorString;
import com.scrumchess.data.Game;
import com.scrumchess.data.ScrumchessDatastoreFacade;
import com.scrumchess.transit.request.GameInfoRequest;
import com.scrumchess.transit.request.NewGameRequest;
import com.scrumchess.transit.response.GameInfoResponse;
import com.scrumchess.transit.response.NewGameResponse;

public class MainOperationsGAEDS implements MainUserOperations {

	@Override
	public NewGameResponse newGame(NewGameRequest newGameRequest) {
		boolean ret=false;
		if (authenticate(newGameRequest)){
			
			
		}
		return null;
	}

	@Override
	public GameInfoResponse getGameInfo(GameInfoRequest gameInfoRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	public static MainOperationsGAEDS getInstance(){
		return new MainOperationsGAEDS();
	}
	
	private boolean authenticate(UserAuthenticationObject uao){
		return (ScrumchessUserAuthenticator.authenticate(uao));
	}
	
	private NewGameResponse newGameAttempt(NewGameRequest newGameRequest){
		NewGameResponse ret= null;
		ScrumchessDatastoreFacade sdf = ScrumchessDatastoreFacade.getInstance();
		if(newGameRequest.getConfigurationValue()==newGameRequest.WHITE){
			try {
				Game game = sdf.newGameWhite(newGameRequest.getUserIdentification());
			} catch (EntityNotFoundException e) {
				
				
				
				
				
				
				
			}
		}
		
		
		
		
		return ret;
	}
	
}
