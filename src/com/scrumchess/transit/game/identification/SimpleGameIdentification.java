package com.scrumchess.transit.game.identification;

public class SimpleGameIdentification implements GameIdentification {

	private String gameID;

	protected SimpleGameIdentification(String id){
		this.gameID = id;
	}
	/* (non-Javadoc)
	 * @see com.mdj20.scrumchessswing.tansit.data.GameIdentification#getGameID()
	 */
	@Override
	public String getGameID(){
		return gameID;
	}
	
}
