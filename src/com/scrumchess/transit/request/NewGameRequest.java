package com.scrumchess.transit.request;

import java.util.Date;

import com.scrumchess.transit.auth.pre.UserPreAuthentication;
import com.scrumchess.transit.game.playerconfiguration.PlayerConfiguration;
import com.scrumchess.transit.game.playerconfiguration.SimplePlayerConfiguration;

public class NewGameRequest extends AbstractClientReqPreAuthComp  implements PlayerConfiguration{
	PlayerConfiguration playerConfiguration; 
	NewGameRequest(UserPreAuthentication upaType, int playerConfiguration) {
		super(AbstractClientRequest.NEW_GAME_REQUEST, new Date(), upaType);
		this.playerConfiguration = new SimplePlayerConfiguration(playerConfiguration);
	}

	@Override
	public int getConfigurationValue() {
		return playerConfiguration.getConfigurationValue();
	}
}
