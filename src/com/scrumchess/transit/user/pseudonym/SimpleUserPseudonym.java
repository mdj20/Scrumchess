package com.scrumchess.transit.user.pseudonym;

public class SimpleUserPseudonym implements UserPseudonym {

	private String pseudonym;
	
	SimpleUserPseudonym(String set){
		pseudonym = set;
	}

	@Override
	public String getPseudonym() {
		return pseudonym;
	}
}

