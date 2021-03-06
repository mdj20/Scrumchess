package com.scrumchess.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Transaction;

public class MoveFacade  {
	
	// Field identifiers for Google datastore
	protected static final String _kind = "move";
	protected static final String _moveString = "moveString";
	protected static final String _number = "number";
	protected static final String _date = "date";	
	private DatastoreService dss;
	
	protected MoveFacade(DatastoreService dss){
		this.dss = dss;
	}
	
	protected Key moveToParent(Key game, String string, int num ){
		Key ret;
		Date date = new Date();
		Move move = new Move(string,num, date);
		Entity entity = toChildEntity(move,game);
		ret = dss.put(entity);
		return ret;
	}
	
	protected Key moveToParent(Key game,Move move){
		Entity entity = toChildEntity(move,game);
		return dss.put(entity);
	}
	
	protected Key moveToParentTransaction(Transaction txn, Key game, Move move){
		Entity entity = toChildEntity(move,game);
		return dss.put(txn,entity);
	}
	
	protected ArrayList<Move> getMoves(Key game){
		ArrayList<Move> ret = new ArrayList<Move>();
		Query q = new Query(_kind).setAncestor(game);
		PreparedQuery pq = dss.prepare(q);
		ret = toMove(pq.asIterable());
		return ret;
	}
	
	protected ArrayList<Move> getSortedMoves(Key game){
		ArrayList<Move> moves = getMoves(game);
		Collections.sort(moves);
		return moves;
	}
	
	protected ArrayList<Move> toMove(Iterable<Entity> entities){
		ArrayList<Move> moves = new ArrayList<Move>();
		for(Entity e:entities){
			moves.add(toMove(e));
		}
		return moves;
	}
 	protected Move toMove(Entity entity){
		Move move = new Move( (String) entity.getProperty(_moveString),
				(int) (long) entity.getProperty(_number),
				(Date)entity.getProperty(_date)
				);
		return move;
	}
	
	protected Entity toChildEntity(Move move, Key game){
		Entity entity = new Entity(_kind,game);
		entity.setProperty(_moveString, move.getMoveString());
		entity.setProperty(_number,move.getNumber());
		entity.setProperty(_date,move.getDate());
		return entity;
	}
	
	protected Move createDisjointMove(int num, String move){
		return new Move(move,num,new Date());
	}
	
}
