package com.scrumchess.transit.user;

import com.scrumchess.transit.user.pseudonym.SimpleUserPseudonym;
import com.scrumchess.transit.user.pseudonym.UserPseudonym;
import com.scrumchess.transit.user.uniqueidentification.AbstractUserIdentificationString;

public class SimpleCompositeUserIdetification extends AbstractUserIdentificationString implements CompositeUserIdentification{
	
	private UserPseudonym userPseudonym;
	
	public SimpleCompositeUserIdetification(String id){
		super(id);
		userPseudonym = new SimpleUserPseudonym();
	}
	public SimpleCompositeUserIdetification(CompositeUserIdentification compositeUserIdetification){
		super(compositeUserIdetification.getId());
		if(compositeUserIdetification.hasPseudonym()){
			userPseudonym = new SimpleUserPseudonym(compositeUserIdetification.getPseudonym());
		}
		else {
			userPseudonym = new SimpleUserPseudonym();
		}
	}
	
	public SimpleCompositeUserIdetification(String id, String pseudo){
		super(id);
		userPseudonym = new SimpleUserPseudonym(pseudo);
	}

	@Override
	public String getPseudonym() {
		return userPseudonym.getPseudonym();
	}

	@Override
	public boolean hasPseudonym() {
		return userPseudonym.hasPseudonym();
	}

}
