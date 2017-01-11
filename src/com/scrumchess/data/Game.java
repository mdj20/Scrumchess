package com.scrumchess.data;
import java.util.Date;
import com.google.appengine.api.datastore.*;
public class Game {
	
	protected Game(String fen ,Integer moveNum, Date date){
		this.fen = fen;
		this.moveNum = moveNum;
		this.started = date;
		this.isBlack = false;
		this.isWhite = false;
	}
	public long getId() {
		return id;
	}
	public String getFen() {
		return fen;
	}
	public int getMoveNum() {
		return moveNum;
	}
	public Date getStarted() {
		return started;
	}
	protected void setId(long id) {
		this.id = id;
	}
	protected void setFen(String fen) {
		this.fen = fen;
	}
	protected void setMoveNum(int moveNum) {
		this.moveNum = moveNum;
	}
	protected void setStarted(Date started) {
		this.started = started;
	}
	protected void setIsWhite(boolean isWhite) {
		this.isWhite = isWhite;
	}
	protected void setIsBlack(boolean isBlack) {
		this.isBlack = isBlack;
	}
	protected void setWhite(String white) {
		this.white = white;
		this.isWhite = true;
	}
	protected void setBlack(String black) {
		this.black = black;
		this.isBlack = true;
	}
	public boolean isWhite() {
		return isWhite;
	}
	public boolean isBlack() {
		return isBlack;
	}
	public String getWhite() {
		return white;
	}
	public String getBlack() {
		return black;
	}
	private long id;
	private String fen;
	private int moveNum;
	private Date started;
	private boolean isWhite;
	private boolean isBlack;
	private String white;
	private String black;
	
	
	
}
