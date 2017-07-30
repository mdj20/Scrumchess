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
	
	

	NewGameRequest(StringBaseUserAuthenticationObject upaType, MultiUserConfiguration multiUserConfiguration) {

		super(RequestType.NEW_GAME_REQUEST, new Date(), upaType);
		this.multiUserConfiguration = multiUserConfiguration;
	}

	@Override
	public Config getConfigurationValue() {
		return multiUserConfiguration.getConfigurationValue();
	}

	@Override
	public String getPseudonym(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasPseudonym(int index) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getId(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPseudonym() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasPseudonym() {
		// TODO Auto-generated method stub
		return false;
	}
}
