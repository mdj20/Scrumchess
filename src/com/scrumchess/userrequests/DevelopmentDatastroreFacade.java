package com.scrumchess.userrequests;

import com.scrumchess.authentication.AuthenticableUserRequest;

public class DevelopmentDatastroreFacade {

	public NewGameResponse tryNewGameRequest(NewGameRequest newGameRequest) {
		NewGameResponse response = null;
		if ( !checkAuthentication(newGameRequest) ) {
			response = new NewGameResponse(false,UniversalFailureReason.AUTHERNTICATION_FAILURE);
		}
		else {
			
		}
		return response;
	}
	
	
	public MoveRequestResponse tryMoveRequest(MoveRequest moveRequest) {
		MoveRequestResponse response = null;
		if ( !checkAuthentication(moveRequest) ) {
			response = new MoveRequestResponse(false,UniversalFailureReason.AUTHERNTICATION_FAILURE);
		}
		
		return response;
		
	}
	
	
	private boolean checkAuthentication(AuthenticableUserRequest<?> authenticableUserRequest) {
		return authenticableUserRequest.isAuthenticated();
	}
	
}
