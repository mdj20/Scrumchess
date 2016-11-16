package com.scrumchess.data;
import com.google.appengine.api.datastore.*;

public class Game {
	
	final static String _kind = "game";
	final static String _currentFen = "current_fen";
	final static String _player1 = "player1";
	final static String _player2 = "player2";
	final static String _p1white = "p1white";
	final static String _p2human = "p2human";
	final static String _move = "move"; 
	final static String _startFen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 1 1";
	
	private int id;
	private String fen;
	
	public static Key create(Key player1, Key player2){
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		Entity game = new Entity(_kind,player1);
		game.setProperty(_currentFen,_startFen);
		game.setIndexedProperty(_player2,player2);
		game.setProperty(_p1white, true);
		game.setProperty(_p2human, true);
		game.setPropety()
	}
	
}
