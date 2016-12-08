package com.scrumchess.data;
import java.util.Date;
import com.google.appengine.api.datastore.*;
public class Game {
	
	protected Game(String fen ,Integer moveNum,Date date){
		this.fen = fen;
		this.moveNum = moveNum;
		this.started = date;
	}
	
	public int getId() {
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
	protected void setId(int id) {
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
	private int id;
	private String fen;
	private int moveNum;
	private Date started;
	
}
