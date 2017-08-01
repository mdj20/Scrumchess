package com.scrumchess.operations.google.test;

import java.util.ArrayList;

import com.alonsoruibal.chess.Board;
import com.alonsoruibal.chess.movegen.LegalMoveGenerator;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.scrumchess.authentication.AuthenticationObjectBuilder;
import com.scrumchess.authentication.StringBaseUserAuthenticationObject;
import com.scrumchess.authentication.pre.type.Type;
import com.scrumchess.operations.google.MainOperationsGAEDS;
import com.scrumchess.transit.MultiUserConfiguration;
import com.scrumchess.transit.SimpleMultiUserConfiguration;
import com.scrumchess.transit.game.playerconfiguration.PlayerConfigurationStaticTypes.Config;
import com.scrumchess.transit.request.GameInfoRequest;
import com.scrumchess.transit.request.NewGameRequest;
import com.scrumchess.transit.request.SendMoveRequest;
import com.scrumchess.transit.response.GameInfoResponse;
import com.scrumchess.transit.response.NewGameResponse;
import com.scrumchess.transit.response.SendMoveResponse;
import com.scrumchess.transit.user.MultiUser;
import com.scrumchess.transit.user.SimpleCompositeUserIdetification;
import com.scrumchess.transit.user.SimpleMultiUser;


public class GoogleAppEngineOperationsTest {
	
	private final LocalServiceTestHelper helper =
		      new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());
	
	
	MainOperationsGAEDS mainOps = new MainOperationsGAEDS();
	String userToken0 = "usertoken0";
	String userToken1 = "userToken1";
	
	ArrayList<Long> games = new ArrayList<Long>();
	
	//
	private int smokeTest(){
		System.out.println("start Test");
		mainOps = new MainOperationsGAEDS();
		StringBaseUserAuthenticationObject uao0 = buildAuthenticationObject(userToken0);
		StringBaseUserAuthenticationObject uao1 = buildAuthenticationObject(userToken1);
		NewGameResponse response = newGameAttemptWhite(uao0);
		games.add(response.getReturnableObject().getGameID());
		analyzeNewGameResponse(response);
		GameInfoResponse gir = gameInfoAttempt(uao0,response.getReturnableObject().getGameID());
		analyzeGameInfoResponse(gir);
		SendMoveResponse smr = newMoveAttempt(gir.getReturnableObject().getGameID(),uao0,"e2e4",gir.getReturnableObject().getHalfMoveNumber()+10);
		analyzeSendMoveResponse(smr);
		response = newGameAttemptBlack(uao0);
		games.add(response.getReturnableObject().getGameID());
		analyzeNewGameResponse(response);
		response = newGameAttempt2P(uao0,uao1);
		games.add(response.getReturnableObject().getGameID());
		analyzeNewGameResponse(response);
		for (Long l:games){
			System.out.println(l);
		}
		Board board = new Board();
		board.setFen(response.getReturnableObject().getFen());
		LegalMoveGenerator lmg = new LegalMoveGenerator();
		int moves[] = new int[20];
		System.out.println(lmg.generateMoves(board, moves,0));
		
		return 0;		
	}
	
	
	private void analyzeSendMoveResponse(SendMoveResponse smr) {
		if (smr.successful()) {
			System.out.println(smr.successful());
			System.out.println("FEN: "+smr.getReturnableObject().getFen());
			System.out.println("Move Num: "+smr.getReturnableObject().getHalfMoveNumber());
		}
		else {
			System.out.println(smr.getReasonMsg());
		}
	}
	
	private void analyzeNewGameResponse(NewGameResponse ngr){
		System.out.println(ngr.successful());
		System.out.println("Game ID: "+ngr.getReturnableObject().getGameID());
		System.out.println("FEN: "+ngr.getReturnableObject().getFen());
		System.out.println("Move Num: "+ngr.getReturnableObject().getHalfMoveNumber());
	}
	private void analyzeGameInfoResponse(GameInfoResponse gir) {
		System.out.println(gir.successful());
		System.out.println("Game ID: "+gir.getReturnableObject().getGameID());
		System.out.println("FEN: "+gir.getReturnableObject().getFen());
		System.out.println("Move Num: "+gir.getReturnableObject().getHalfMoveNumber());
	}
	
	private StringBaseUserAuthenticationObject buildAuthenticationObject(String token){
		return AuthenticationObjectBuilder.getAuthenticationObject(Type.Debug, token);
	}
	
	public GameInfoRequest buildGameInfoRequest(StringBaseUserAuthenticationObject uao, long gameId) {
		return new GameInfoRequest(uao,gameId);
	}
	
	public GameInfoResponse gameInfoAttempt(StringBaseUserAuthenticationObject uao, long gameId) {
		GameInfoRequest gir = buildGameInfoRequest(uao,gameId);
		GameInfoResponse ret = mainOps.getGameInfo(gir);
		return ret;
	}
	
	public SendMoveRequest buildSendMoveRequest(long gameId, String move, int moveNum, StringBaseUserAuthenticationObject uao) {
		return new SendMoveRequest(buildGameInfoRequest(uao,gameId),move,moveNum);
	}
	
	public SendMoveResponse newMoveAttempt(long gameID,StringBaseUserAuthenticationObject uao, String move, int moveNum ) {
		SendMoveRequest msr = buildSendMoveRequest(gameID,move, moveNum,uao );
		SendMoveResponse  response =  mainOps.sendMove(msr);
		return response;
	}
	
	private NewGameResponse newGameAttemptWhite(StringBaseUserAuthenticationObject uao){
		SimpleMultiUserConfiguration muc = new SimpleMultiUserConfiguration(Config.WHITE, new SimpleMultiUser(uao.getUserToken()));
		NewGameRequest newGameReq = buildNewGameRequest(uao,muc);
		NewGameResponse response = mainOps.newGame(newGameReq);
		return response;
	}
	private NewGameResponse newGameAttemptBlack(StringBaseUserAuthenticationObject uao){
		SimpleMultiUserConfiguration muc = new SimpleMultiUserConfiguration(Config.BLACK, new SimpleMultiUser(uao.getUserToken()));
		NewGameRequest newGameReq = buildNewGameRequest(uao,muc);
		NewGameResponse response = mainOps.newGame(newGameReq);
		return response;
	}
	private NewGameResponse newGameAttempt2P(StringBaseUserAuthenticationObject uao0,StringBaseUserAuthenticationObject uao1){
		SimpleMultiUser smu = new SimpleMultiUser(
					new SimpleCompositeUserIdetification(uao0.getUserToken()),
					new SimpleCompositeUserIdetification(uao1.getUserToken())
				);
		SimpleMultiUserConfiguration muc = new SimpleMultiUserConfiguration(Config.BOTH, smu);
		NewGameRequest newGameReq = buildNewGameRequest(uao0,muc);
		NewGameResponse response = mainOps.newGame(newGameReq);
		return response;
	}
	
	private NewGameRequest buildNewGameRequest(StringBaseUserAuthenticationObject uao, MultiUserConfiguration muc){
		NewGameRequest ret = new NewGameRequest(uao,muc);
		return ret;
	}
	
	GoogleAppEngineOperationsTest(){helper.setUp();}
	
	public static void main(String args[]){
		GoogleAppEngineOperationsTest test = new GoogleAppEngineOperationsTest();
		System.exit(test.smokeTest());
	}
}
