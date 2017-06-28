package com.scrumchess.transit.user.pseudonym;

public class SimpleUserPseudonym implements UserPseudonym {

	private String pseudonym;
	private boolean has = false;
	
	public SimpleUserPseudonym(){
		has = false;
	}
	
	public SimpleUserPseudonym(String set){
		if(set!=null){
			pseudonym = set;
			has = true;
		}
		else{
			has = false;
		}
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

