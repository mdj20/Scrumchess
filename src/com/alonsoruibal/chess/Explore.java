package com.alonsoruibal.chess;

public class Explore {
	public static void main(String args[]){
		Board board = new Board();
		board.startPosition();
		pf(board);
		int move = Move.getFromString(board, "e2e4", true);
		pm(move);
		board.doMove(move);
		pf(board);
		move = Move.getFromString(board, "e2e4", true);
		pm(move);
		board.doMove(move);
		pf(board);
		board.getLegalMove(move);
		
		
	}
	public static void pm(int move){
		System.out.println(move);
	}
	public static void pf(Board board){
		System.out.println(board.getFen());
	}
	public static void pb(Board board){
		System.out.println(board.toString());
	}
}
