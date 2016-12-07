package com.scrumchess.data;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;

public class UserProxy {
	protected static final String _kind = "user";
	protected static final String _id = "id";
	protected static final String _name = "name";
	protected static final String _joined = "joined";
	protected static final String _lastLogin = "lastLogin";
	
	private DatastoreService dss;
	
	UserProxy(DatastoreService datastore){
		this.dss = datastore;
	}
	
	protected static User createNewUser(String id){
		User user = new User(id);
		return user;
	}
	
	protected User getUser(String key){
		Entity entity = dss.get(key);
	}
	
	protected boolean insertUser(User user){
		boolean ret = false;
		Entity entity = new Entity(_kind,user.getId());
		entity.setProperty(_name,user.getName());
		entity.setProperty(_joined, user.getJoined());
		entity.setProperty(_lastLogin, user.getLastLogin());
		dss.put(entity);
		return ret;
	}
	
	
	
	
}
