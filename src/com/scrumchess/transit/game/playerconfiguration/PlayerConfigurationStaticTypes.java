package com.scrumchess.transit.game.playerconfiguration;

public class PlayerConfigurationStaticTypes {

	public static enum Config {
		NONE	(0),
		WHITE	(1),
		BLACK 	(2),
		BOTH 	(3);
		
		private int INT_VALUE;
		
		Config(int set){
			this.INT_VALUE = set;
		}
		
		public int getIntegerRepresentation(){
			return INT_VALUE;
		}
		
		public static Config getConfigByInt(int value){
			Config ret = null;
			for(Config c:Config.values()){
				if (c.getIntegerRepresentation()==value){
					ret = c;
					break;
				}
			}
			if(ret==null){
				//TODO thow exception of indexout of bounds
			}
			return ret;
		}
	}
}
