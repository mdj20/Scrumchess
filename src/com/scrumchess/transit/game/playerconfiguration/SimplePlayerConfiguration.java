package com.scrumchess.transit.game.playerconfiguration;

public class SimplePlayerConfiguration extends AbstractPlayerConfiguration implements PlayerConfiguration {

	private int value;
	
	SimplePlayerConfiguration(int setValue) {
		super();
		value = setValue;
	}

	@Override
	public int getConfigurationValue() {
		return value;
	}

}
