package com.scrumchess.transit.game.playerconfiguration;

public class SimplePlayerConfiguration  implements PlayerConfiguration {
	private Config value;
	
	public SimplePlayerConfiguration(Config setValue) {
		value = setValue;
	}
	
	public SimplePlayerConfiguration(int configIntRepresentation){
		this(PlayerConfiguration.Config.getConfigByInt(configIntRepresentation));
	}

	@Override
	public Config getConfigurationValue() {
		return value;
	}
}
