package com.scrumchess.transit.request;

import java.util.Date;

import com.scrumchess.authentication.StringBaseUserAuthenticationObject;
import com.scrumchess.authentication.pre.UserCredentials;
import com.scrumchess.transit.MultiUserConfiguration;
import com.scrumchess.transit.game.playerconfiguration.PlayerConfiguration;
import com.scrumchess.transit.game.playerconfiguration.SimplePlayerConfiguration;
import static com.scrumchess.transit.game.playerconfiguration.PlayerConfigurationStaticTypes.Config;

public class NewGameRequest extends AbstractAuthenticableClientRequest implements MultiUserConfiguration{
	MultiUserConfiguration multiUserConfiguration; 
	
	public NewGameRequest(StringBaseUserAuthenticationObject upaType, MultiUserConfiguration multiUserConfiguration) {
		super(RequestType.NEW_GAME_REQUEST, new Date(), upaType);
		this.multiUserConfiguration = multiUserConfiguration;
	}

	@Override
	public Config getConfigurationValue() {
		return multiUserConfiguration.getConfigurationValue();
	}

	@Override
	public String getPseudonym(int index) {
		return multiUserConfiguration.getPseudonym(index);
	}

	@Override
	public boolean hasPseudonym(int index) {
		return multiUserConfiguration.hasPseudonym(index);
	}

	@Override
	public String getId(int index) {
		return multiUserConfiguration.getId(index);
	}

	@Override
	public String getId() {
		return multiUserConfiguration.getId();
	}

	@Override
	public String getPseudonym() {
		return multiUserConfiguration.getPseudonym();
	}

	@Override
	public boolean hasPseudonym() {
		return multiUserConfiguration.hasPseudonym();
	}
}
