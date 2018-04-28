package com.scrumchess.data;
import java.util.Date;
import com.google.appengine.api.datastore.*;
public class Game {
	
	// class that will describe a game object in the data store....
	protected Game(String fen ,int moveNum, Date date, GameConfiguration gameConfiguration){
		this.fen = fen;
		this.halfMoveNumber = moveNum;
		this.dateStarted = date;
		this.isBlack = false;
		this.isWhite = false;
		this.hasID = false;
		this.gameConfiguration = gameConfiguration;
	}
	protected Game(String fen, long moveNum, Date date, GameConfiguration gameConfiguration){
		this(fen, (int) moveNum, date,gameConfiguration);
		if (moveNum > Integer.MAX_VALUE){
			throw new IllegalArgumentException();  // halfMoveNumber is larger than int MaxValue
		}
	}
	public boolean isPlayer(String user) {
		boolean ret = false;
		if(userIdBlack!=null) {
			ret = ret || (isBlack&&userIdBlack.equals(user));
		}
		if(userIdWhite!=null) {
			ret = ret || (isWhite&&userIdWhite.equals(user));
		}
		return ret;
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
		if(isBlack()){
			setGameConfiguration(GameConfiguration.WHITE2);
		}
		else{
			setGameConfiguration(GameConfiguration.WHITE);
		}
		this.isWhite = isWhite;
	}
	protected void setIsBlack(boolean isBlack) {
		if(isBlack()){
			setGameConfiguration(GameConfiguration.BLACK2);
		}
		else{
			setGameConfiguration(GameConfiguration.BLACK);
		}
		this.isBlack = isBlack;
	}
	protected void setWhite(String white) {
		this.userIdWhite = white;
		setIsWhite(true);
	}
	protected void setBlack(String black) {
		this.userIdBlack = black;
		setIsBlack(true);
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
	public GameConfiguration getGameConfiguration(){
		return gameConfiguration;
	}
	public void setGameConfiguration(GameConfiguration gameConfiguration){
		this.gameConfiguration = gameConfiguration;
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
	private GameConfiguration gameConfiguration = GameConfiguration.WHITE;  // NOTE: the default value is set to WHITE for refactoring purposes.
	public static int WHITE = 0;
	public static int WHITE2 = 1;
	public static int BLACK = 2;
	public static int BLACK2 = 3;
}
