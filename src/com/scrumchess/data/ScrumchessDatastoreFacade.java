package com.scrumchess.data;

import java.util.List;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Transaction;
import com.scrumchess.gamelogic.MoveValidator;
import com.scrumchess.userrequests.AuthenticatedUserMoveInfo;
import com.scrumchess.userrequests.EvaluatedMove;

public class ScrumchessDatastoreFacade {
	private DatastoreService dss;
	private GameFacade gf;
	private MoveFacade mf;
	private UserFacade uf;
	
	private ScrumchessDatastoreFacade( DatastoreService dss ){
		this.dss = dss;
		gf = new GameFacade(dss);
		mf = new MoveFacade(dss);
		uf = new UserFacade(dss);
	}
	
	public static ScrumchessDatastoreFacade getInstance(){ // Factory method constructor
		return new ScrumchessDatastoreFacade(
				DatastoreServiceFactory.getDatastoreService()
				);
	}
	public static ScrumchessDatastoreFacade getInstance(DatastoreService dss){
		return new  ScrumchessDatastoreFacade(dss);
	}
	
	
	// returns game object 
	public Game getGameById(long id) throws EntityNotFoundException{
		Game ret;
		ret = gf.getGame(id);
		return ret;
	}
	
	// these methods are very inefficient, and need optimized, as they access the data store twice for each method.
	public Game newGameWhite(String id) throws EntityNotFoundException {
		Key gameKey = gf.newGameToUserWhite(id);
		return gf.getGame(gameKey);
	}
	public Game newGameBlack(String id) throws EntityNotFoundException {
		Key gameKey = gf.newGameToUserBlack(id);
		return gf.getGame(gameKey);
	}
	public Game newTwoPlayerGame(String white, String black) throws EntityNotFoundException {
		Key gameKey = gf.newGameToUsers(white, black);
		return gf.getGame(gameKey);
	}
	
	public Game commitMove(EvaluatedMove em){
		Game ret = null;
		int moveNum = em.getGame().getMoveNum();
		System.out.println("move: "+moveNum);
		try {
			Game current = gf.getGame(em.getGame().getId());
			if( current.getFen().equals(em.getGame().getFen() ) && moveNum == current.getMoveNum() ){
				current.setFen(em.getUpdateFen());
				current.setMoveNum(moveNum+1);
				Key gameKey = gf.updateGame( current, current.getId());
				Move disjoint = mf.createDisjointMove(moveNum+1, em.getUserMoveInfo().getMoveAlgebraic());
				mf.moveToParent( gameKey, disjoint);
			}
		}
		catch ( EntityNotFoundException e){
			// Must some type of transaction report...
			e.printStackTrace();
		}
		return ret;
	}
	public boolean commitMoveAtomic(EvaluatedMove em){
		boolean ret = false;
		int moveNum = em.getGame().getMoveNum(); // get move number of current game from evaluated move
		Transaction txn = dss.beginTransaction();
		// need to use a transaction to make sure this conforms to ACID...
		try {
			Game current = gf.getGameTransaction(txn,em.getGame().getId());
			if( current.getFen().equals(em.getGame().getFen() ) && moveNum == current.getMoveNum() ){ // check if move is correct
				current.setFen(em.getUpdateFen());
				current.setMoveNum(moveNum+1);
				Key gameKey = gf.updateGameTransaction(txn, current, current.getId());
				Move disjoint = mf.createDisjointMove(moveNum+1, em.getUserMoveInfo().getMoveAlgebraic());
				mf.moveToParentTransaction(txn, gameKey, disjoint);
				txn.commit();
				ret = true;
			}	
		} catch (EntityNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		finally {
			if(txn.isActive()){
				txn.rollback();
				ret = false;
			}
		}	
		return ret;
	}
	
	public EvaluatedMove evaluateMove(AuthenticatedUserMoveInfo aumi){
		EvaluatedMove ret=null;
		Game game;
		String userID = aumi.getUserToken();
		try {
			game=gf.getGame(aumi.getGameID());
		} catch (EntityNotFoundException e) {
			game = null;
			e.printStackTrace();
		}
		if (game != null){  // checks if userID and Game exist
			MoveValidator mv = MoveValidator.createWithFen(game.getFen());
			if ( isPlayerTurn(userID,game,mv.isWhiteTurn()) ){  // check for correct color/ turn
				if (mv.setMove(aumi.getMoveAlgebraic()) && mv.doMove()){  // adds move and if valid
					String newFen = mv.getFen();
					ret = EvaluatedMove.createValid(game,newFen,aumi);
				}
				else {
					 ret = EvaluatedMove.createInvalid(game,"", aumi);
				}
			}
			else {
				// wrong user....
				ret = EvaluatedMove.createInvalid(null,"", aumi);
			}
		}
		else{
			ret = EvaluatedMove.createInvalid(null,"", aumi);
		}
		return ret;
	}
	
	public GameMovelistComposite getFullGameInfo(long id) throws EntityNotFoundException{
		GameMovelistComposite ret = null;
		Key key = gf.getKeyFromID(id);
		Game game = gf.getGame(key);
		List<Move> moves = mf.getMoves(key);
		ret = new GameMovelistComposite(game,moves);
		return ret;
	}
	
	// returns true if it is the user's turn 
	private boolean isPlayerTurn(String id, Game game, boolean white){
		boolean ret = false;
		if (white) {
			if ( game.isWhite() && id.equals(game.getWhite()) ) // check if game.isWhite is true, then check for equality 
				ret = true;
		}
		else {
			if ( game.isBlack() && id.equals(game.getBlack()) )  // same for black
				ret = true;
		}
		return ret;
	}	
	
	public User changePseudonym(String userId, String pseudonym) throws EntityNotFoundException{
		uf.updateName(userId, pseudonym);
		return null;
	}
}
