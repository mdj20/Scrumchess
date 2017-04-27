package com.scrumchess.gamelogic;

public class AlgebraicNotation {
	
	/*
	 *  Utility class will return used for converting coordinates to algebraic notation.
	 */
	
	private static final int a = 97;  // unicode digit for 'a'
	private static final int eight = 56; // unicode digit for '8'
	
	public static String createFromCoordinates(int file, int rank, int file2, int rank2){
		String a = cellString(file,rank);
		String b = cellString(file2,rank2);
		String ret = new StringBuilder().append(a).append(b).toString();
		return ret;
	}
	// returns on square in algebraic notation from x y coordinates.
	public static String cellString(int file, int rank){
		if(rank < 0 || rank > 7 || file < 0 || file > 7){
			throw new IllegalArgumentException();
		}
		char fileChar = (char) ( a + file);
		char rankChar = (char) (eight - rank); 
		String ret = new StringBuilder().append(fileChar).append(rankChar).toString();
		return ret;
	}
	
	// testing methods below.
	public static void main(String args[]){
		smokeTest();
	}
	
	private static void smokeTest(){
		for(int i=0; i<8 ;i++){
			for (int j=0; j<8 ;j++){
				System.out.println(cellString(j,i	));		
			}
		}		
	}
}
