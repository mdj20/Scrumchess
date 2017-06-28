package com.scrumchess.transit.user;

import com.scrumchess.transit.user.pseudonym.UserPseudonym;
import com.scrumchess.transit.user.uniqueidentification.UserIdentification;
public interface CompositeUserIdentification extends UserIdentification<String>, UserPseudonym {
	
}
