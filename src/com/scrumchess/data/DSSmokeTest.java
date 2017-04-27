package com.scrumchess.data;

// class will smoke test the datastore, this will be removed after datastore is operational

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.alonsoruibal.chess.Board;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.QueryResultList;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.scrumchess.ajaxendpoint.AuthenticatedUserMoveInfo;
import com.scrumchess.ajaxendpoint.EvaluatedMove;
import com.scrumchess.ajaxendpoint.UserMoveInfo;
import com.scrumchess.data.ScrumchessDatastoreFacade;
import com.scrumchess.data.UserFacade;
import com.scrumchess.data.MoveFacade;
import com.scrumchess.gamelogic.MoveValidator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;

import org.junit.Test;

public class DSSmokeTest {
	
	private final LocalServiceTestHelper helper =
		      new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());
	
	private ScrumchessDatastoreFacade sdf;
	private UserFacade uf;
	private DatastoreService dss;
	private GameFacade gf;
	private static String testUserID_0 = "00001";
	private static String testUserID_1 = "00002";
	private ArrayList<Key> gameKeys;

	DSSmokeTest(){
		  helper.setUp();
		  sdf = ScrumchessDatastoreFacade.getInstance();
		  dss = DatastoreServiceFactory.getDatastoreService();
		  uf = new UserFacade(dss);
		  gf = new GameFacade(dss);
		  gameKeys = new ArrayList<Key>();
	}
	private void cleanUp(){
		helper.tearDown();
		System.exit(0);
	}
	private void CreateUser(String id){
		uf.createNewUser(id);
	}
	private void createGame(String white, String black){
		gameKeys.add(gf.newGameToUsers(white, black));
	}
	private Game commitMoveAtomic(EvaluatedMove em) throws EntityNotFoundException{
		sdf.commitMoveAtomic(em);
		Game retGame = sdf.getGameById(em.getGame().getId());
		return retGame;
	}
	private Game commitMove(EvaluatedMove em) throws EntityNotFoundException{
		sdf.commitMove(em);
		Game retGame = sdf.getGameById(em.getGame().getId());
		return retGame;
	}
	private AuthenticatedUserMoveInfo createAUMI(String user, String move, long gameid){
		UserMoveInfo umi = new UserMoveInfo(user,move,gameid);
		return  AuthenticatedUserMoveInfo.overrideToken(umi, user); 
	}
	private Game commitMoveLoop(String user, long id, String fen) throws EntityNotFoundException{
		AuthenticatedUserMoveInfo amui = createAUMI(user,fen,id);
		EvaluatedMove em = sdf.evaluateMove(amui);
		Game game = sdf.commitMoveAtomic(em);
		return sdf.getGameById(id);
	}
	
	public void smoke() throws EntityNotFoundException{
		CreateUser(testUserID_0);
		CreateUser(testUserID_1);
		User u1 = uf.getUser(testUserID_0);
		System.out.println("HERE> "+u1.getId());
		createGame(testUserID_0,testUserID_1);
		long gameID = gameKeys.get(gameKeys.size()-1).getId();
		System.out.println(gameID);
		Game testGame = sdf.getGameById(gameID);
		System.out.println(testGame.getFen());
		ArrayList<String> fens = new ArrayList<String>();
		Game retGame;
		
		retGame= commitMoveLoop(testUserID_0,gameID,"e2e3");
		fens.add(retGame.getFen());
		retGame= commitMoveLoop(testUserID_1,gameID,"a7a6");
		fens.add(retGame.getFen()); 
		retGame= commitMoveLoop(testUserID_0,gameID,"f1c4");
		fens.add(retGame.getFen());
		retGame= commitMoveLoop(testUserID_1,gameID,"a6a5");
		fens.add(retGame.getFen());
		retGame= commitMoveLoop(testUserID_0,gameID,"d1f3");
		fens.add(retGame.getFen());
		retGame= commitMoveLoop(testUserID_1,gameID,"h7h5");
		fens.add(retGame.getFen());
		retGame= commitMoveLoop(testUserID_0,gameID,"f3f7");
		fens.add(retGame.getFen());
		
		for (String s:fens){
			System.out.println( s );
			Board testBoard = new Board();
			testBoard.setFen( s );
			System.out.println( testBoard.isEndGame() );
		}
		
	}
	public static void main(String args[]) throws EntityNotFoundException{
		System.out.println("START MAIN");
		DSSmokeTest st = new DSSmokeTest();
		st.smoke();
		st.cleanUp();
	}
}
