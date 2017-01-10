package com.scrumchess.data;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.scrumchess.ajaxendpoint.UserMoveInfo;

public class ScrumchessDatastoreFacade {
	private DatastoreService dss;
	private GameFacade gf;
	ScrumchessDatastoreFacade( DatastoreService dss ){
		this.dss = dss;
		gf = new GameFacade(dss);
	}
	public static ScrumchessDatastoreFacade getInstance(){
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
		try {
			game =gf.getGame(umi.getGame());
		} catch (EntityNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	

	
	
}
