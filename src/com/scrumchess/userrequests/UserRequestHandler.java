package com.scrumchess.userrequests;

public interface UserRequestHandler {

	public NewGameResponse tryNewGameRequest(NewGameRequest request);
	public GameInfoResponse tryGameInfoRequest(GameInfoRequest request);
	public MoveRequestResponse tryMoveRequest(MoveRequest moveRequest);
	public DoubleMoveResponse tryDoubleMoveRequest(DoubleMoveRequest doubleMoveRequest);
	public AIResponse tryAIrequest(AIRequest aiRequest);
	public GameLoadResponse tryGameLoadRequest(GameLoadRequest gameLoadRequest);

}
