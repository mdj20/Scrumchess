package com.scrumchess.transit.user.pseudonym;

public class SimpleUserPseudonym implements UserPseudonym {

	private String pseudonym;
	private boolean has;
	
	public SimpleUserPseudonym(){
		has = false;
	}
	
	public SimpleUserPseudonym(String set){
		pseudonym = set;
		has = true;
	}

	@Override
	public String getPseudonym() {
		if (has)
			return pseudonym;
		else 
			return null;
	}

	@Override
	public boolean hasPseudonym() {
		return has;
	}
}

