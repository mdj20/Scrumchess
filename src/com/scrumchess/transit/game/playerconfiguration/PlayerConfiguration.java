package com.scrumchess.transit.game.playerconfiguration;

public interface PlayerConfiguration {
	public  static final int  NONE =0;
	public static final int WHITE = 1;
	public static final int  BLACK = 2;
	public static final int BOTH = 3;
	public int getConfigurationValue();
}
