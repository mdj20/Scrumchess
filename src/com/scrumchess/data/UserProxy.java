package com.scrumchess.data;

import java.util.Date;

import com.google.api.client.util.store.DataStoreFactory;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

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

	protected void addGameToUser(String id, Key gameKey){
		
	}
	
	protected User createNewUser(String id){
		User user = new User(id);
		Date date = new Date();
		user.setJoined(date);
		user.setLastLogin(date);
		user.setName("");
		Entity userEntity = toEntity(user);
		insertUserEntity(userEntity);
		return user;
	}
	protected User updateName(String id, String name) throws EntityNotFoundException{
		Entity entity = getEntity(id);
		entity.setProperty(_name, name);
		User ret = toUser(entity);
		insertUserEntity(entity);
		return ret;
	}
	protected User updateLastLogin(String id) throws EntityNotFoundException{
		Entity entity = getEntity(id);
		Date date = new Date();
		entity.setProperty(_lastLogin, date);
		User ret = toUser(entity);
		insertUserEntity(entity);
		return ret;
	}
	protected Entity getEntity(String id) throws EntityNotFoundException{
		Key userKey = KeyFactory.createKey(_kind, id);
		Entity entity = dss.get(userKey);
		return entity;
	}
	protected User getUser(String id) throws EntityNotFoundException{
		Entity entity = getEntity(id);
		User user = toUser(entity);
		return user;	
	}
	protected Date getDateJoined(String id) throws EntityNotFoundException{
		User user = getUser(id);
		return user.getJoined();
	}
	protected boolean insertUserEntity(Entity user){
		boolean ret = false;
		dss.put(user);
		return ret;
	}
	protected static Entity toEntity(User user){
		Entity entity = new Entity(_kind,user.getId());
		entity.setProperty(_name,user.getName());
		entity.setProperty(_joined, user.getJoined());
		entity.setProperty(_lastLogin, user.getLastLogin());
		return entity;
	}
	protected static User toUser(Entity entity){
		User user = new User((String) entity.getProperty(_id),
				(String) entity.getProperty(_name),
				(Date) entity.getProperty(_joined),
				(Date) entity.getProperty(_lastLogin));
		return user;
	}
}
