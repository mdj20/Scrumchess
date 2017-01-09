package com.scrumchess.data;

import java.util.Date;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;

public class ScrumchessDatastore {
	
	
	public static void userNewGameWhite(String id){
		DatastoreService dss = DatastoreServiceFactory.getDatastoreService();
		GameProxy gp = new GameProxy(dss);
		Key key = gp.newGameToUserWhite(id);
	}
	
	public static void userSignIn(String id){
		DatastoreService dss = DatastoreServiceFactory.getDatastoreService();
		UserProxy up = new UserProxy(dss);
		try {
			up.updateLastLogin(id);
		} catch (EntityNotFoundException e) {
			up.createNewUser(id);
			System.out.println("New User");
		}
		System.out.println("Google ID/name"+up.getKey(id));
	}
	
	public static void updateName(String id, String name) throws EntityNotFoundException{
		DatastoreService dss = DatastoreServiceFactory.getDatastoreService();
		UserProxy up = new UserProxy(dss);
		up.updateName(id, name);
	}
	
	public static Date getDateJoined(String id) throws EntityNotFoundException{
		DatastoreService dss = DatastoreServiceFactory.getDatastoreService();
		UserProxy up = new UserProxy(dss);
		Date date = up.getDateJoined(id);
		return date;
	}
	
}
