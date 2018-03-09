package com.scrumchess.data;
import java.util.Date;
import com.google.appengine.api.datastore.*;
public class Game {
	
	// class that will describe a game object in the data store....
	protected Game(String fen ,int moveNum, Date date){
		this.fen = fen;
		this.halfMoveNumber = moveNum;
		this.dateStarted = date;
		this.isBlack = false;
		this.isWhite = false;
		this.hasID = false;
	}
	protected Game(String fen, long moveNum, Date date){
		this(fen, (int) moveNum, date);
		if (moveNum > Integer.MAX_VALUE){
			throw new IllegalArgumentException();  // halfMoveNumber is larger than int MaxValue
		}
	}
	public long getId() {
		return gameId;
	}
	public String getFen() {
		return fen;
	}
	public int getMoveNum() {
		return halfMoveNumber;
	}
	public Date getStarted() {
		return dateStarted;
	}
	protected void setId(long id) {
		this.hasID = true;
		this.gameId = id;
	}
	protected void setFen(String fen) {
		this.fen = fen;
	}
	protected void setMoveNum(int moveNum) {
		this.halfMoveNumber = moveNum;
	}
	protected void setStarted(Date started) {
		this.dateStarted = started;
	}
	protected void setIsWhite(boolean isWhite) {
		this.isWhite = isWhite;
	}
	protected void setIsBlack(boolean isBlack) {
		this.isBlack = isBlack;
	}
	protected void setWhite(String white) {
		this.userIdWhite = white;
		this.isWhite = true;
	}
	protected void setBlack(String black) {
		this.userIdBlack = black;
		this.isBlack = true;
	}
	public boolean isWhite() {
		return isWhite;
	}
	public boolean isBlack() {
		return isBlack;
	}
	public String getWhite() {
		return userIdWhite;
	}
	public String getBlack() {
		return userIdBlack;
	}
	private long gameId;
	private boolean hasID;
	private String fen;
	private int halfMoveNumber;
	private Date dateStarted;
	private boolean isWhite;
	private boolean isBlack;
	private String userIdWhite;
	private String userIdBlack;	
}
