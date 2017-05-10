package com.scrumchess.transit;

public class SendMove extends UserGame{
	public static final String DIGIT_REGEX = "\\d+";
	private String userToken;
	private String gameid;
	private String algebraicNotation;
	public String getUserToken(){
		return userToken;
	}
	public String getGameid(){
		return gameid;
	}
	public Integer getGameID(){
		if (gameid.matches(DIGIT_REGEX))
			return Integer.parseInt(gameid);
		else 
			return null;
	}
	public String getAlgebraicNotation(){
		return algebraicNotation;
	}
	
	
	//below is smoke
	public static void main(String args[]){
		smokeTest();
	}
	public static void smokeTest(){
		String n = "163734848";
		String s = "kljahdfkjhad";
		System.out.println(n.matches(DIGIT_REGEX));
		System.out.println(s.matches(DIGIT_REGEX));
	}
}
