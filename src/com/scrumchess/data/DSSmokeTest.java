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
import com.scrumchess.data.ScrumchessDatastoreFacade;
import com.scrumchess.data.UserFacade;

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
	
	public void smoke() throws EntityNotFoundException{
		CreateUser(testUserID_1);
		CreateUser(testUserID_2);
		User u1 = uf.getUser(testUserID_1);
		System.out.println("HERE> "+u1.getId());
		createGame(testUserID_1,testUserID_2);

		
		long gameID = gameKeys.get(gameKeys.size()-1).getId();
		System.out.println(gameID);
		
		Game testGame = sdf.getGameById(gameID);
		
		
		
		
		
		
		
		
		
	}
	
	public static void main(String args[]) throws EntityNotFoundException{
		System.out.println("START MAIN");
		DSSmokeTest st = new DSSmokeTest();
		st.smoke();
		st.cleanUp();
	}
	
	
	

}
