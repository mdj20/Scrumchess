package com.scrumchess.transit.user;

public interface MultiUser extends CompositeUserIdentification{
	public String getPseudonym(int index);
	public boolean hasPseudonym(int index);
	public String getId(int index);
}
