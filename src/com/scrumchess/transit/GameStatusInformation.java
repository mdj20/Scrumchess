package com.scrumchess.transit;

import com.scrumchess.transit.game.identification.SimpleGameIdentification;

public class GameStatusInformation extends SimpleGameIdentification{

	public static final int USER_WHITE = 1;
	public static final int USER_BLACK = 2;
	public static final int USER_BOTH = 3;
	public static final int USER_NONE = 0;
	
	private String fen;
	private int move;
 		
	GameStatusInformation(String id, String inFen) {
		super(id);
		this.fen = inFen;
		// TODO Auto-generated constructor stub
	}

	
	
}
