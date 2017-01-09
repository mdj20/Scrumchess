package com.scrumchess.data;

import com.google.appengine.api.datastore.Key;

/*
 *  This class will store the User to Game relation
 */

public class Player {
	private Key user;
	private Key Game;
	private boolean white;
	
	public Key getUser() {
		return user;
	}
	public Key getGame() {
		return Game;
	}
	protected void setUser(Key user) {
		this.user = user;
	}
	protected void setGame(Key game) {
		Game = game;
	}
	Player(Key user, Key game, boolean white){
		this.user = user;
		this.Game = game;
		this.white = white;
	}
}
