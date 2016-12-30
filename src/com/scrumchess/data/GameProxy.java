package com.scrumchess.data;
import java.util.ArrayList;
import java.util.Date;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.CompositeFilter;
import com.google.appengine.api.datastore.Query.CompositeFilterOperator;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.appengine.api.datastore.QueryResultList;
public class GameProxy {
	protected final static String _kind = "game";
	protected final static String _fen = "fen";
	protected final static String _moveNum = "moveNum"; 
	protected final static String _started = "started";
	protected final static String _startFen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 1 1";
	protected final static String _black = "black";
	protected final static String _white = "white";
	protected final static String _isWhite = "isWhite"; 
	protected final static String _isBlack = "isBlack";
	private DatastoreService dss;
	
	protected GameProxy(DatastoreService dstore){
		this.dss = dstore;
	}
	protected Key newGameToUserWhite(int user){
		Key key;
		Date date = new Date();
		Game game = new Game(_startFen,0,date);
		game.setWhite(user);
		Entity entity = toEntity(game);
		key = dss.put(entity);
		return key;
	}
	protected Key newGameToUserBlack(int user){
		Key key;
		Date date = new Date();
		Game game = new Game(_startFen,0,date);
		game.setBlack(user);
		Entity entity = toEntity(game);
		key = dss.put(entity);
		return key;
	}
	protected Key newGameToUsers(int white,int black){
		Key key;
		Date date = new Date();
		Game game = new Game(_startFen,0,date);
		game.setWhite(white);
		game.setBlack(black);
		Entity entity = toEntity(game);
		key = dss.put(entity);
		return key;
	}
	protected ArrayList<Game> getGames(int userID){
		Query query = getBlackOrWhiteQuery(userID);
		PreparedQuery pq = dss.prepare(query);
		Iterable<Entity> results;
		results = pq.asIterable();
		return toGame(results);
	}
	protected Game toGame(Entity entity){
		Game game = new Game( (String) entity.getProperty(_fen),
				(Integer) entity.getProperty(_moveNum),
				(Date) entity.getProperty(_started));
		return game;
	}
	protected ArrayList<Game> toGame(Iterable<Entity> entities ){
		ArrayList<Game> ret = new ArrayList<Game>();
		for(Entity e:entities){
			ret.add(toGame(e));
		}
		return ret;
	}	

	protected Entity toEntity(Game game){
		Entity entity = new Entity(_kind);
		entity.setProperty(_fen, game.getFen());
		entity.setProperty(_moveNum, game.getMoveNum());
		entity.setProperty(_started, game.getStarted());
		if (game.isWhite()){
			entity.setProperty(_white, game.getWhite());
			entity.setProperty(_isWhite, true);
		}
		else {
			entity.setProperty(_isWhite, false);
		}
		if (game.isBlack()){
			entity.setProperty(_white, game.getBlack());
			entity.setProperty(_isBlack, true);
		}
		else {
			entity.setProperty(_isBlack,false);
		}
		return entity;
	}
	
	private Query getBlackOrWhiteQuery(int user){
		ArrayList<Filter> filterList = new ArrayList<Filter>();
		filterList.add(getEqualFilter(_black,user));   // calls local method that creates user filter
		filterList.add(getEqualFilter(_white,user));
		CompositeFilter cf = new CompositeFilter(CompositeFilterOperator.OR,filterList);
		Query q = new Query(_kind).setFilter(cf);
		return q;
	}
	private Filter getEqualFilter(String color,int user){
		Filter filter = new FilterPredicate(color, FilterOperator.EQUAL,user);
		return filter;
	}
}
