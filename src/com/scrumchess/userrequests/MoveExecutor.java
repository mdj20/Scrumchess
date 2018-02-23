package com.scrumchess.userrequests;

import com.google.appengine.api.datastore.EntityNotFoundException;
import com.scrumchess.ajaxservlet.GoogleAuthHelper;
import com.scrumchess.data.Game;
import com.scrumchess.data.ScrumchessDatastoreFacade;
import com.scrumchess.gamelogic.GameValidator;
import com.scrumchess.gamelogic.MoveValidator;

// class takes move information and checks validity and adds move to correct game
public class MoveExecutor {
	
	public static Game appendMove(UserMoveInfo umi){
		String userID = GoogleAuthHelper.getSubjectFromEndpoint(umi.getUserToken());
		ScrumchessDatastoreFacade sdf = ScrumchessDatastoreFacade.getInstance();		
		Game game;
		try {
			game = sdf.getGameById(umi.getGameID());
		} catch (EntityNotFoundException e) {
			game = null;
			// this will collect errors at some point 
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (userID != null && game != null){
			MoveValidator gv = MoveValidator.createWithFen(game.getFen());
			gv.setMove(umi.getMoveAlgebraic());
		}
		return null;
	}
}
