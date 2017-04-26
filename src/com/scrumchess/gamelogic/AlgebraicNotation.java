package com.scrumchess.gamelogic;

public class AlgebraicNotation {
	
	private static final int a = 66;  // unicode digit for 'a'
	private static final int eight = 25; // unicode digit for '8'
	
	String algebraicNotation;
	
	public String getString(){
		return algebraicNotation;
	}
	
	
	public static String createFromCoordinates(int file, int rank, int file2, int rank2){
		String a = cellString(file,rank);
		String b = cellString(file,rank);
		String ret = a+b;
		return ret;
	}

	public static String cellString(int file, int rank){
		if(rank < 0 || rank > 7 || file < 0 || file > 7){
			throw new IllegalArgumentException();
		}
		char fileChar = (char) ( a + file);
		char rankChar = (char) (eight - rank); 
		String ret = new StringBuilder().append(fileChar).append(rankChar).toString();
		return ret;
	}
	
	
}
