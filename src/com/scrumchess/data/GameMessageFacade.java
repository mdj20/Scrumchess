package com.scrumchess.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class GameMessageFacade implements ObjectEntityAdapter<GameMessage>{
	
	final static private String _kind = "GameMessage";
	final static private String _text = "text";
	final static private String _from = "from";
	final static private String _gameId = "gameId"; 
	final static private String _date = "date";
	private DatastoreService dss;
	
	protected GameMessageFacade(DatastoreService dstore){
		this.dss = dstore;
	}
	
	public Key sendGameMessage(Key gameKey, GameMessage message) {
		Entity entity = toChildEntity(gameKey,message);
		return dss.put(entity);
	}
	
	public List<GameMessage> getGameMessages(Key gameKey){
		List<GameMessage> ret = new ArrayList<GameMessage>();
		Query q = new Query(_kind).setAncestor(gameKey);
		PreparedQuery pq = dss.prepare(q);
		ret = toObject(pq.asIterable());
		return ret;
	}
	
	public GameMessage toObject(Entity entity) {
		GameMessage gameMessage = new GameMessage(
				(long) entity.getProperty(_gameId),
				(String) entity.getProperty(_from),
				(String) entity.getProperty(_kind),
				(Date) entity.getProperty(_date));
		return gameMessage;
	}
	
	public List<GameMessage> toObject(Iterable<Entity> entities) {
		ArrayList<GameMessage> ret = new ArrayList<GameMessage>();
		for(Entity e: entities) {
			ret.add(toObject(e));
		}
		return ret;
	}
	
	public Entity toEntity(GameMessage gameMessage) {
		Entity entity = new Entity(_kind);
		entity.setProperty(_text, gameMessage.getMessage());
		entity.setProperty(_gameId, gameMessage.getGameId());
		entity.setProperty(_from, gameMessage.getUser());
		entity.setProperty(_date , gameMessage.getDate());
		return entity;
	}
	
	public Entity toChildEntity(Key parent, GameMessage gameMessage) {
		Entity entity = new Entity(_kind, parent);
		entity.setProperty(_text, gameMessage.getMessage());
		entity.setProperty(_gameId, gameMessage.getGameId());
		entity.setProperty(_from, gameMessage.getUser());
		entity.setProperty(_date , gameMessage.getDate());
		return entity;
	}


	@Override
	public List<Entity> toEntity(List<GameMessage> object) {
		ArrayList<Entity> ret = new ArrayList<Entity>();
		for(GameMessage gm: object) {
			ret.add(toEntity(gm));
		}
		return ret;
	}
}
