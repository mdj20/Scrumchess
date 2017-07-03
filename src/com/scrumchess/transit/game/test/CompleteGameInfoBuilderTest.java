package com.scrumchess.transit.game.test;
import com.scrumchess.transit.game.CompleteGameInfo;
import com.scrumchess.transit.game.CompleteGameInfoBuilder;
import com.scrumchess.transit.game.playerconfiguration.PlayerConfiguration;

public class CompleteGameInfoBuilderTest {
	CompleteGameInfoBuilder builder;
	static String testFen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR";
	
	private static void smokeTest(){
		CompleteGameInfoBuilderTest test = new  CompleteGameInfoBuilderTest();
		test.builder = new CompleteGameInfoBuilder();
		test.builder.setFen(testFen);
		test.builder.addMove("MOVE");
		test.builder.setPlayerConfiguration(PlayerConfiguration.Config.WHITE);
		test.builder.setGameId(12345);
		
		CompleteGameInfo cgi = test.builder.build();
		
		
		System.out.println(cgi.getFen());
		System.out.println(cgi.getMoves().get(0).getMoveAlgebraic());
		System.out.println(cgi.getGameID());
		System.out.println(cgi.getConfigurationValue());
		
	}
	
	public static void main(String agrs[]){
		smokeTest();
	}
}
