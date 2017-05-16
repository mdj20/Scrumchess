package com.scrumchess.transit.game.identification;

public class SimpleGameIndentificationInteger extends SimpleGameIdentification implements GameIdentificationInteger{
	public static final String DIGIT_REGEX = "\\d+"; // used to check if the game Identification is a string integer.
	public SimpleGameIndentificationInteger(String id) {
		super(id);
	}

	@Override
	public Integer getGameInteger() {
		if (!super.getGameID().matches(DIGIT_REGEX))
			throw new UnsupportedOperationException("Game Id is not a string representation of Integer");
		else 
			return Integer.parseInt(super.getGameID());
	}
}
