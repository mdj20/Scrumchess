package com.scrumchess.data;
import java.util.ArrayList;
import java.util.Date;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.QueryResultList;
public class GameProxy {
	protected final static String _kind = "game";
	protected final static String _fen = "fen";
	protected final static String _moveNum = "moveNum"; 
	protected final static String _started = "started";
	protected final static String _startFen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 1 1";
	private DatastoreService dss;
	
	protected GameProxy(DatastoreService dstore){
		this.dss = dstore;
	}
	
	protected Key newGameToUser(Key parent){
		Key key;
		Date date = new Date();
		Game game = new Game(_startFen,0,date);
		Entity entity = toEntity(game,parent);
		key = dss.put(entity);
		return key;
	}
	
	protected ArrayList<Game> getGames(Key userKey){
		Query query = new Query(_kind).setAncestor(userKey);
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
	protected Entity toEntity(Game game, Key parent){
		Entity entity = new Entity(_kind,parent);
		entity.setProperty(_fen, game.getFen());
		entity.setProperty(_moveNum, game.getMoveNum());
		entity.setProperty(_started, game.getStarted());
		return entity;		
	}
	protected Entity toEntity(Game game){
		Entity entity = new Entity(_kind);
		entity.setProperty(_fen, game.getFen());
		entity.setProperty(_moveNum, game.getMoveNum());
		entity.setProperty(_started, game.getStarted());
		return entity;
	}
}
