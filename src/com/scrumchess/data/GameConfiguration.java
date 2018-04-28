package com.scrumchess.data;

import java.util.HashMap;
import java.util.Map;

public enum GameConfiguration{
	WHITE(0), // SINGLE PLAYER
	BLACK(1),
	BLACK2(2), // 2 PLAYER
	WHITE2(3);
	
	private int value;
	
	static Map<Integer,GameConfiguration> map = new HashMap<Integer,GameConfiguration>();

	static {
		for(GameConfiguration gc: GameConfiguration.values()){
			map.put(gc.getIntegerValue(), gc);
		}
	}
	
	GameConfiguration(final int value){
		this.value = value;
	}
	
	public int getIntegerValue(){
		return value;
	}
	
	public static GameConfiguration valueOf(Integer i){
		return map.get(i);
	}
}