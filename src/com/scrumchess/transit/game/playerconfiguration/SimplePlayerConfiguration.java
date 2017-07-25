package com.scrumchess.transit.game.playerconfiguration;
import static com.scrumchess.transit.game.playerconfiguration.PlayerConfigurationStaticTypes.Config;

public class SimplePlayerConfiguration  implements PlayerConfiguration {
	private Config value;
	
	public SimplePlayerConfiguration(Config setValue) {
		value = setValue;
	}
	
	public SimplePlayerConfiguration(int configIntRepresentation){
		this(Config.getConfigByInt(configIntRepresentation));
	}

	@Override
	public Config getConfigurationValue() {
		return value;
	}
}
