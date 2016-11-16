package com.scrumchess.data;

import com.google.appengine.api.datastore.*;


public class User {
	private static final String _kind = "user";
	private static final String _name = "_name";
	private static final String _joined = "joined";
	
	
	public static void create(String name){
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		Entity user = new Entity(_kind);
		user.setProperty(_name,name);
		
	}

}
