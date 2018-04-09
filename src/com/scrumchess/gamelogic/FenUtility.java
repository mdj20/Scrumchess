package com.scrumchess.gamelogic;

import java.util.ArrayList;

import com.mdj20.scrumchessswing.RankAndFile;

public class FenUtility {
	
	public static final String STARTING_FEN_SHORT = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR";
	public static final String STARTING_FEN_LONG = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
	public static final char[] fileChars = {'a','b','c','d','e','f','g','h'};
	
	// parses fen and return true if fen is valid, returns false if it is not.
	public static boolean checkFEN(String fen){  
		boolean ret = true;
		int i = 0;
		int l = 0;
		int slash = 0;
		for(char c :fen.toCharArray()){
			if(Character.isDigit(c)){
				i+=Character.getNumericValue(c);
			}
			else if (Character.isLetter(c)){
				l++;
			}
			else if (c == '/'){
				slash++;
			}
		}
		if(slash != 7){
			ret = false;
		}
		if(l+i != 64){
			ret = false;
		}
		return ret;
	}
	
	public static String getBoardFenSection(String fen){
		String split[] = fen.split(" ");
		if(split[0]!=null && checkFEN(split[0])){
			return split[0];
		}
		else
			return null;
	}
	
	public static StringBuilder square(int file, int rank, StringBuilder sb){
		if (file > 7 || file < 0 || rank >7 || rank < 0 ){
			throw new IllegalArgumentException("Both rank and file must be values x : -1< x < 8");
		}
		sb.append(fileChars[file]);
		sb.append(Integer.toString(rank+1));
		return sb;
	}
	
	public static String square(int file, int rank){
		return square(file,rank,new StringBuilder()).toString();
	}

	public static String move(int file0, int rank0, int file1, int rank1){
		StringBuilder sb = new StringBuilder();
		sb = square(file0,rank0,sb);
		return square(file1,rank1,sb).toString();
	}
	
	public static String move(RankAndFile from, RankAndFile to) {
		return move(from.getFile(),from.getRank(),to.getFile(),to.getRank());
	}
	
	//Smoketest
	public static void main(String args[]){
		ArrayList<String> strings = new ArrayList<String>();
		for (int i = 0 ; i<8 ; i++){
			for (int j = 0 ; j<8 ; j++){
				strings.add(square(i,j));
				strings.add(square(7-i,7-j));
				strings.add(move(i, j, 7-i, 7-j));
			}
		}
		for(String s: strings){
			System.out.println(s);
		}
	}
	
}
