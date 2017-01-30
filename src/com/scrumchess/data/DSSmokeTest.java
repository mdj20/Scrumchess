package com.scrumchess.data;

// class will smoke test the datastore, this will be removed after datastore is operational

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.scrumchess.ajaxendpoint.AuthenticatedUserMoveInfo;
import com.scrumchess.ajaxendpoint.EvaluatedMove;
import com.scrumchess.ajaxendpoint.UserMoveInfo;
import com.scrumchess.data.ScrumchessDatastoreFacade;
import com.scrumchess.data.UserFacade;
import com.scrumchess.gamelogic.MoveValidator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;

import org.junit.Test;

public class DSSmokeTest {
	
	private final LocalServiceTestHelper helper =
		      new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig().setDefaultHighRepJobPolicyUnappliedJobPercentage(100));
	
	private ScrumchessDatastoreFacade sdf;
	private UserFacade uf;
	private DatastoreService dss;
	private GameFacade gf;
	private static String testUserID_1 = "00001";
	private static String testUserID_2 = "00002";
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

	private Game commitMove(EvaluatedMove em) throws EntityNotFoundException{
		sdf.commitMoveAtomic(em);
		Game retGame = sdf.getGameById(em.getGame().getId());
		return retGame;
	}
	
	public void smoke() throws EntityNotFoundException{
		CreateUser(testUserID_1);
		CreateUser(testUserID_2);
		User u1 = uf.getUser(testUserID_1);
		System.out.println("HERE> "+u1.getId());
		createGame(testUserID_1,testUserID_2);

		long gameID = gameKeys.get(gameKeys.size()-1).getId();
		System.out.println(gameID);
		Game testGame = sdf.getGameById(gameID);
		System.out.println(testGame.getFen());
		
		UserMoveInfo umi1 = new UserMoveInfo(testUserID_1,"e2e3",gameID);
		AuthenticatedUserMoveInfo aumi = AuthenticatedUserMoveInfo.overrideToken(umi1, testUserID_1);
		EvaluatedMove em1 = sdf.evaluateMove(aumi);
		
		System.out.println(em1.getUpdateFen());
		Game retGame = commitMove(em1);
		System.out.println(retGame.getFen());
		

		UserMoveInfo umi2 = new UserMoveInfo(testUserID_2,"d7d5",gameID);
		AuthenticatedUserMoveInfo aumi2 = AuthenticatedUserMoveInfo.overrideToken(umi2, testUserID_2);
		EvaluatedMove em2 = sdf.evaluateMove(aumi2);
		
		System.out.println(em2.getUpdateFen());
		retGame = commitMove(em2);
		System.out.println(retGame.getFen());


		
	}
	
	public static void main(String args[]) throws EntityNotFoundException{
		System.out.println("START MAIN");
		DSSmokeTest st = new DSSmokeTest();
		st.smoke();
		st.cleanUp();
	}
	
	

}
