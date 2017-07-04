package com.scrumchess.transit.request;

import java.util.Date;

import com.scrumchess.transit.MultiUserConfiguration;
import com.scrumchess.transit.auth.pre.UserPreAuthentication;
import com.scrumchess.transit.game.playerconfiguration.PlayerConfiguration;
import com.scrumchess.transit.game.playerconfiguration.SimplePlayerConfiguration;

public class NewGameRequest extends AbstractAuthenticableClientRequest implements MultiUserConfiguration{
	MultiUserConfiguration multiUserConfiguration; 
	
	
	NewGameRequest(UserPreAuthentication upaType, MultiUserConfiguration multiUserConfiguration) {
		super(AbstractClientRequest.NEW_GAME_REQUEST, new Date(), upaType);
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
