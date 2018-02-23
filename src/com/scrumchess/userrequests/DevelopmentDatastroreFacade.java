package com.scrumchess.userrequests;

import com.scrumchess.authentication.AuthenticableUserRequest;
import com.scrumchess.userrequests.AbstractUserResponse.BaseFailureReason;

public class DevelopmentDatastroreFacade {

	public NewGameResponse tryNewGameRequest(NewGameRequest newGameRequest) {
		NewGameResponse response = null;
		if ( !checkAuthentication(newGameRequest) ) {
			response = new NewGameResponse(false,BaseFailureReason.AUTHERNTICATION_FAILURE);
		}
		else {
			
		}
		return response;
	}
	
	
	public MoveRequestResponse tryMoveRequest(MoveRequest moveRequest) {
		MoveRequestResponse response = null;
		if ( !checkAuthentication(moveRequest) ) {
			response = new MoveRequestResponse(false,BaseFailureReason.AUTHERNTICATION_FAILURE);
		}
		
		return response;
		
	}
	
	
	private boolean checkAuthentication(AuthenticableUserRequest<?> authenticableUserRequest) {
		return authenticableUserRequest.isAuthenticated();
	}
	
}
