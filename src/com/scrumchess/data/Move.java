package com.scrumchess.data;

import java.util.Date;

public class Move implements Comparable<Move>{
	private String moveString;
	private int number;
	private Date date;

	Move(String moveString, int number, Date date){
		this.moveString = moveString;
		this.number = number;
		this.date = date;
	}

	protected void setNumber(int number) {
		this.number = number;
	}
	protected void setMoveString(String moveString) {
		this.moveString = moveString;
	}
	protected void setDate(Date date) {
		this.date = date;
	}
	public int getNumber() {
		return number;
	}
	public String getMoveString() {
		return moveString;
	}
	public Date getDate() {
		return date;
	}

	@Override
	public int compareTo(Move o) {
		return this.number - o.number;
	}	
}
