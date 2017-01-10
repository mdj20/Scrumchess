package com.scrumchess.data;

import java.util.ArrayList;

public class GameMovelistComposite {

	private Game game;
	private ArrayList<Move> moves;
	
	public Game getGame(){
		return game;
	}
	public ArrayList<Move> getMoves(){
		return moves;
	}
	
	GameMovelistComposite(Game game, Iterable<Move> moves){
		this.moves = new ArrayList<Move>();
		for (Move m:moves){
			this.moves.add(m);
		}
		this.game=game;
	}
}
