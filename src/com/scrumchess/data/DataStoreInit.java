package com.scrumchess.data;

import com.google.appengine.api.datastore.*;

public class DataStoreInit {

	
	public static void init(){
		
		final String testEntity = "Test Entity";
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Entity e1 = new Entity(testEntity);
		e1.setProperty("white" , "WHITE");
		datastore.put(e1);
		
	}
	
	
}
