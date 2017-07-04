package com.scrumchess.transit;

import com.scrumchess.transit.game.playerconfiguration.PlayerConfiguration;
import com.scrumchess.transit.user.MultiUser;

public class SimpleMultiUserConfiguration implements MultiUserConfiguration {
	
	
	MultiUserConfiguration(PlayerConfiguration.Config config){
		
	}
	
	private PlayerConfiguration playerConfiguration;
	private MultiUser multiUser;

	@Override
	public String getPseudonym(int index) {
		return multiUser.getPseudonym(index);
	}

	@Override
	public boolean hasPseudonym(int index) {
		return multiUser.hasPseudonym(index);
	}

	@Override
	public String getId(int index) {
		return multiUser.getId(index);
	}

	@Override
	public String getId() {
		return multiUser.getId();
	}

	@Override
	public String getPseudonym() {
		return multiUser.getPseudonym();
	}

	@Override
	public boolean hasPseudonym() {
		return multiUser.hasPseudonym();
	}

	@Override
	public Config getConfigurationValue() {
		return playerConfiguration.getConfigurationValue();
	}

}
