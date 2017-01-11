package com.scrumchess.data;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.scrumchess.ajaxendpoint.UserMoveInfo;
import com.scrumchess.gamelogic.MoveValidator;
import com.scrumchess.mdj20.GoogleAuthHelper;

public class ScrumchessDatastoreFacade {
	private DatastoreService dss;
	private GameFacade gf;
	private ScrumchessDatastoreFacade( DatastoreService dss ){
		this.dss = dss;
		gf = new GameFacade(dss);
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
	
	public Game appendMove(UserMoveInfo umi){
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
				}
				else {
					 // invalid move
				}
			
			}
		}
		return game;
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
