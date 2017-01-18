package com.scrumchess.data;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Transaction;
import com.scrumchess.ajaxendpoint.UserMoveInfo;
import com.scrumchess.ajaxendpoint.EvaluatedMove;
import com.scrumchess.gamelogic.MoveValidator;
import com.scrumchess.mdj20.GoogleAuthHelper;

public class ScrumchessDatastoreFacade {
	private DatastoreService dss;
	private GameFacade gf;
	private MoveFacade mf;
	private ScrumchessDatastoreFacade( DatastoreService dss ){
		this.dss = dss;
		gf = new GameFacade(dss);
		mf = new MoveFacade(dss);
	}
	public static ScrumchessDatastoreFacade getInstance(){ // Factory method constuctor
		return new ScrumchessDatastoreFacade(
				DatastoreServiceFactory.getDatastoreService()
				);
	}

	public Game getGameById(long id) throws EntityNotFoundException{
		Game ret;
		ret = gf.getGame(id);
		return ret;
	}
	
	public Game commitMoveAtomic(EvaluatedMove em){
		Game ret = null;
		int moveNum = em.getGame().getMoveNum(); // get move number of current game from evaluated move
		Transaction txn = dss.beginTransaction();
		// need to use a transaction to make sure this conforms to ACID...
		try {
			Game current = gf.getGameTransaction(txn,em.getGame().getId());
			if( current.getFen().equals(em.getGame().getFen() ) && moveNum == current.getMoveNum() ){ // check if move is correct
				current.setFen(em.getUpdateFen());
				current.setMoveNum(moveNum+1);
				Key gameKey = gf.updateGameTransaction(txn, current);
				Move disjoint = mf.createDisjointMove(moveNum+1, em.getUserMoveInfo().getMoveAlgebraic());
				mf.moveToGameTransaction(txn, gameKey, disjoint);
				txn.commit();
			}
			
		} catch (EntityNotFoundException e) {
			// wrong 
			e.printStackTrace();
		}
		finally {
			
		}
		
		return ret;
	}
	
	public EvaluatedMove evalidateMove(UserMoveInfo umi){
		EvaluatedMove ret=null;
		Game game;
		String userID = null;
		try {
			game=gf.getGame(umi.getGame());
		} catch (EntityNotFoundException e) {
			game = null;
			// TODO Auto-generated catch block
			// TODO place error message, for record 
			e.printStackTrace();
		}
		userID = GoogleAuthHelper.getSubjectFromEndpoint(umi.getUserToken());
		if (userID != null && game != null){  // checks if userID and Game exist
			MoveValidator mv = MoveValidator.createWithFen(game.getFen());
			if ( isPlayerTurn(userID,game,mv.isWhiteTurn()) ){  // check for correct color/ turn
				if (mv.setMove(umi.getMoveAlgebraic())){  // adds move and if valid
					String newFen = mv.doMove();
					ret = EvaluatedMove.createValid(game,newFen,umi);
				}
				else {
					 ret = EvaluatedMove.createInvalid(game,"", umi);
				}
			}
		}
		return ret;
	}
	
	// returns true if it is the user's turn 
	private boolean isPlayerTurn(String id, Game game, boolean white){
		boolean ret = false;
		if (white) {
			if (game.isWhite() && id.equals(game.getWhite()) ) // check if game.isWhite is true, then check for equality 
				ret = true;
		}
		else {
			if (game.isBlack() && id.equals(game.getBlack()))  // same for black
				ret = true;
		}
		return ret;
	}
}
