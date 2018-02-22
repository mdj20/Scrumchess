package com.scrumchess.data;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.scrumchess.ajaxendpoint.AuthenticatedUserMoveInfo;
import com.scrumchess.ajaxendpoint.EvaluatedMove;
import com.scrumchess.ajaxendpoint.UserMoveInfo;
import com.scrumchess.gamelogic.GameValidator;

public class ScrumchessDatastoreFacadeGameRunTest {

	static ScrumchessDatastoreFacade sdf; 
	static String user1 ="01";
	static String user2 = "02";
	static Game game;
	static GameValidator gameValidator;
	private static LocalServiceTestHelper helper;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		helper = new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());
		helper.setUp();
		sdf = ScrumchessDatastoreFacade.getInstance();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
	}
	
	@Before
	public  void before() throws Exception {
		sdf = ScrumchessDatastoreFacade.getInstance();
	}
	
	@After
	public  void after() throws Exception {
		sdf =null;
		System.gc();
	}

	@Test
	public void test1() {
		try {
			game = sdf.newTwoPlayerGame(user1, user2);
		} catch (EntityNotFoundException e) {
			e.printStackTrace();
		}	
	}
	
	@Test
	public void test2() throws EntityNotFoundException{
		game = sdf.getGameById(game.getId());
		gameValidator = new GameValidator(game.getFen());
		while(gameValidator.isEndGame()==0){
			String bestMove = gameValidator.getBestMoveString();
			String user = (gameValidator.isWhiteTurn())? user1 : user2;
			EvaluatedMove em = sdf.evaluateMove(createAUMI(user,bestMove,game.getId()));
			sdf.commitMoveAtomic(em);
			game = sdf.getGameById(game.getId());
			gameValidator = new GameValidator(game.getFen());
		}
		System.out.println(gameValidator.isEndGame());
	}

	private AuthenticatedUserMoveInfo createAUMI(String user, String move, long gameid){
		UserMoveInfo umi = new UserMoveInfo(user,move,gameid);
		return  AuthenticatedUserMoveInfo.overrideToken(umi, user); 
	}
	
}
