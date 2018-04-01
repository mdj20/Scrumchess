package com.scrumchess.data;

import java.util.Date;

public class GameMessage {
	private  String message;
	private  long gameId;
	private  String user;
	private Date date;
	
	public String getMessage() {
		return message;
	}
	public long getGameId() {
		return gameId;
	}
	public Date getDate() {
		return this.date;
	}
	void setMessage(String message) {
		this.message = message;
	}
	void setGameId(long gameId) {
		this.gameId = gameId;
	}
	void setUser(String user) {
		this.user = user;
	}
	void setDate(Date date) {
		this.date = date;
	}
	public String getUser() {
		return user;
	}
	GameMessage(long gameId, String user, String message){
		this(gameId,user,message,new Date());
	}
	GameMessage(long gameId, String user, String message, Date date){
		this.gameId = gameId;
		this.user = user;
		this.message = message;
		this.date = date;
	}
}
