package com.scrumchess.data;
import java.util.ArrayList;
import java.util.Date;


import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.CompositeFilter;
import com.google.appengine.api.datastore.Query.CompositeFilterOperator;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.appengine.api.datastore.QueryResultList;
import com.google.appengine.api.datastore.Transaction;
public class GameFacade {
	
	
	protected final static String _kind = "game";
	protected final static String _fen = "fen";
	protected final static String _moveNum = "moveNum"; 
	protected final static String _started = "started";
	protected final static String _startFen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 1 1";
	protected final static String _black = "black";
	protected final static String _white = "white";
	protected final static String _isWhite = "isWhite"; 
	protected final static String _isBlack = "isBlack";
	protected final static String _gameConfiguration = "gameConfiguration";
	private DatastoreService dss;
	
	protected GameFacade(DatastoreService dstore){
		this.dss = dstore;
	}
	protected Key newGameToUserWhite(String user){
		Key key;
		Date date = new Date();
		Game game = new Game(_startFen,1,date, GameConfiguration.WHITE);
		game.setWhite(user);
		Entity entity = toEntity(game);
		key = dss.put(entity);
		return key;
	}
	protected Key newGameToUserBlack(String user){
		Key key;
		Date date = new Date();
		Game game = new Game(_startFen,1,date,GameConfiguration.BLACK);
		game.setBlack(user);
		Entity entity = toEntity(game);
		key = dss.put(entity);
		return key;
	}
	
	// why is this such a hard situation to be in?
	protected Key newGameToUsers(String white,String black){
		Key key;
		Date date = new Date();
		Game game = new Game(_startFen,1,date,GameConfiguration.WHITE2);
		game.setWhite(white);
		game.setBlack(black);
		Entity entity = toEntity(game);
		key = dss.put(entity);
		return key;
	}
	protected ArrayList<Game> getGames(String userID){
		Query query = getBlackOrWhiteQuery(userID);
		PreparedQuery pq = dss.prepare(query);
		Iterable<Entity> results;
		results = pq.asIterable();
		return toGame(results);
	}
	protected Key updateGame(Game game, long id) throws EntityNotFoundException{
		Key gameKey = KeyFactory.createKey(_kind, id);
		return updateGame(game,gameKey);
	}
	protected Key updateGame(Game game, Key key) throws EntityNotFoundException{
		Entity entity = dss.get(key);
		entity = updateFen(entity,game);
		entity = updateMoveNum(entity,game);
		return dss.put(entity);
	}
	protected Key updateGameTransaction(Transaction txn, Game game, Key key) throws EntityNotFoundException{
		Entity entity = dss.get(txn, key);
		entity = updateFen(entity,game);
		entity = updateMoveNum(entity,game);
		return dss.put(txn,entity);
	}
	protected Key updateGameTransaction(Transaction txn, Game game, long id) throws EntityNotFoundException{
		Key gameKey = KeyFactory.createKey(_kind,id);
		return updateGameTransaction(txn,game,gameKey);
	}
	protected Game getGameTransaction(Transaction txn, long id) throws EntityNotFoundException{
		Key gameKey = KeyFactory.createKey(_kind,id);
		Game game = toGame(dss.get(txn,gameKey));
		return game;
	}
	protected Game getGame(long id) throws EntityNotFoundException{
		Key gameKey = KeyFactory.createKey(_kind,id);
		Game game = toGame(dss.get(gameKey));
		return game;
	}
	protected Game getGame(Key key) throws EntityNotFoundException{
		return toGame(dss.get(key));
	}
	protected Game toGame(Entity entity){
		Game game = new Game( (String) entity.getProperty(_fen),
				(long) entity.getProperty(_moveNum),  // cast to long, then cast to int (data is stored as long but returns as)
				(Date) entity.getProperty(_started),
				GameConfiguration.valueOf(((Long)entity.getProperty(_gameConfiguration)).intValue()));
		Key key = entity.getKey();
		if (key.isComplete()){	
			game.setId(entity.getKey().getId());
		}
		boolean temp = (Boolean) entity.getProperty(_isBlack);
		if (temp){
			game.setBlack( (String) entity.getProperty(_black));
		}
		temp = (Boolean) entity.getProperty(_isWhite);
		if (temp){
			game.setWhite( (String) entity.getProperty(_white));
		}
		return game;
	}
	protected ArrayList<Game> toGame(Iterable<Entity> entities ){
		ArrayList<Game> ret = new ArrayList<Game>();
		for(Entity e:entities){
			ret.add(toGame(e));
		}
		return ret;
	}	
	protected Entity updateFen(Entity entity, Game game){
		entity.setProperty(_fen,game.getFen());
		return entity;
	}
	protected Entity updateMoveNum(Entity entity, Game game){
		entity.setProperty(_moveNum, game.getMoveNum());
		return entity;
	}
	protected Entity update(Entity entity, Game game){
		entity.setProperty(_moveNum, game.getMoveNum());
		return entity;
	}
	
	// creates new entity object from game
	protected Entity toEntity(Game game){
		Entity entity = new Entity(_kind);
		entity.setProperty( _fen, game.getFen());
		entity.setProperty( _moveNum, game.getMoveNum());
		entity.setProperty( _started, game.getStarted());
		entity.setProperty(_gameConfiguration, game.getGameConfiguration().getIntegerValue());
		if (game.isWhite()){
			entity.setProperty( _white, game.getWhite());
			entity.setProperty( _isWhite, true);
		}
		else {
			entity.setProperty( _isWhite, false);
		}
		if (game.isBlack()){
			entity.setProperty( _black, game.getBlack());
			entity.setProperty(_isBlack, true);
		}
		else {
			entity.setProperty(_isBlack,false);
		}
		return entity;
	}
	
	public Key getKeyFromID(long id){
		Key key = KeyFactory.createKey(_kind, id);
		return key;
	}
	
	private Query getBlackOrWhiteQuery(String user){
		ArrayList<Filter> filterList = new ArrayList<Filter>();
		filterList.add(getEqualFilter(_black,user));   // calls local method that creates user filter
		filterList.add(getEqualFilter(_white,user));
		CompositeFilter cf = new CompositeFilter(CompositeFilterOperator.OR,filterList);
		Query q = new Query(_kind).setFilter(cf);
		return q;
	}
	private Filter getEqualFilter(String color,String user){
		Filter filter = new FilterPredicate(color, FilterOperator.EQUAL,user);
		return filter;
	}
	
}
