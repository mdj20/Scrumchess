package com.scrumchess.data;

import java.util.ArrayList;
import java.util.Date;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class MoveProxy {
	protected static final String _kind = "move";
	protected static final String _moveString = "moveString";
	protected static final String _number = "number";
	protected static final String _to = "to";
	protected static final String _from = "from";
	protected static final String _date = "date";	
	private DatastoreService dss;
	protected MoveProxy(DatastoreService dss){
		this.dss = dss;
	}
	
	protected Key moveToGame(Key game, String string, int num, int from, int to){
		Key ret;
		Date date =new Date();
		Move move = new Move(string,num,from,to, date);
		Entity entity = toEntity(move,game);
		ret = dss.put(entity);
		return ret;
	}
	
	protected ArrayList<Move> getMoves(Key game){
		ArrayList<Move> ret = new ArrayList<Move>();
		Query q = new Query().setAncestor(game);
		PreparedQuery pq = dss.prepare(q);
		return ret;
	}
	
	protected Move toMove(Entity entity){
		Move move = new Move( (String) entity.getProperty(_moveString),
				(int) entity.getProperty(_number),
				(int) entity.getProperty(_from),
				(int) entity.getProperty(_to),
				(Date)entity.getProperty(_date)
				);
		return move;
	}
	
	protected Entity toEntity(Move move, Key game){
		Entity entity = new Entity(_kind,game);
		entity.setProperty(_moveString, move.getMoveString());
		entity.setProperty(_number,move.getNumber());
		entity.setProperty(_from,move.getFrom());
		entity.setProperty(_to,move.getTo());
		entity.setProperty(_date,move.getDate());
		return entity;
	}
	
}
