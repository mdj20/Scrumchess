package com.scrumchess.operations;

import com.scrumchess.transit.MultiUserConfiguration;
import com.scrumchess.transit.request.AbstractAuthenticableClientRequest;
import com.scrumchess.transit.request.GameInfoRequest;
import com.scrumchess.transit.request.MoveSendRequest;
import com.scrumchess.transit.request.NewGameRequest;
import com.scrumchess.transit.response.GameInfoResponse;
import com.scrumchess.transit.response.NewGameResponse;
import com.scrumchess.transit.response.TransitResponse;

public interface MainUserOperations {
	public <T extends AbstractAuthenticableClientRequest & MultiUserConfiguration> NewGameResponse newGame(T newGameRequest);
	public NewGameResponse newGame(NewGameRequest newGameRequest);
	public GameInfoResponse getGameInfo(GameInfoRequest gameInfoRequest);
	//public TransitResponse sendMove(MoveSendRequest moveSendRequest);
}
