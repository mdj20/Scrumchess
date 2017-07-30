package com.scrumchess.operations.google.test;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.scrumchess.authentication.AuthenticationObjectBuilder;
import com.scrumchess.authentication.StringBaseUserAuthenticationObject;
import com.scrumchess.authentication.pre.type.Type;
import com.scrumchess.operations.google.MainOperationsGAEDS;
import com.scrumchess.transit.MultiUserConfiguration;
import com.scrumchess.transit.SimpleMultiUserConfiguration;
import com.scrumchess.transit.game.playerconfiguration.PlayerConfigurationStaticTypes.Config;
import com.scrumchess.transit.request.NewGameRequest;
import com.scrumchess.transit.response.NewGameResponse;
import com.scrumchess.transit.user.MultiUser;
import com.scrumchess.transit.user.SimpleMultiUser;


public class GoogleAppEngineOperationsTest {
	
	private final LocalServiceTestHelper helper =
		      new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());
	
	
	MainOperationsGAEDS mainOps = new MainOperationsGAEDS();
	String userToken0 = "usertoken0";
	String userToken1 = "userToken1";
	
	private int smokeTest(){
		System.out.println("start Test");
		MainOperationsGAEDS mainOps = new MainOperationsGAEDS();
		StringBaseUserAuthenticationObject uao0 = buildAuthenticationObject(userToken0);
		StringBaseUserAuthenticationObject uao1 = buildAuthenticationObject(userToken1);
		NewGameResponse response = newGameAttempt(uao0);
		analyzeNewGameResponse(response);
		return 0;
	}
	
	private void analyzeNewGameResponse(NewGameResponse ngr){
		System.out.println(ngr.successful());
		System.out.println(ngr.getReturnableObject().getGameID());
	}
		
	private StringBaseUserAuthenticationObject buildAuthenticationObject(String token){
		return AuthenticationObjectBuilder.getAuthenticationObject(Type.Debug, token);
	}
	
	private NewGameResponse newGameAttempt(StringBaseUserAuthenticationObject uao){
		SimpleMultiUserConfiguration muc = new SimpleMultiUserConfiguration(Config.WHITE, new SimpleMultiUser(uao.getUserToken()));
		NewGameRequest newGameReq = buildNewGameRequest(uao,muc);
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
