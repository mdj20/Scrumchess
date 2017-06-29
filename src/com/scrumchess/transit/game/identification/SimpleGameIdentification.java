package com.scrumchess.transit.game.identification;

public class SimpleGameIdentification implements GameIdentification {
	private long gameID;
	public SimpleGameIdentification(long id){
		this.gameID = id;
	}
	/* (non-Javadoc)
	 * @see com.mdj20.scrumchessswing.tansit.data.GameIdentification#getGameID()
	 */
	@Override
	public long getGameID(){
		return gameID;
	}
}
