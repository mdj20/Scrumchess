package com.scrumchess.authentication;

/**Concrete class that implements AuthenticableUserRequest
 * 
 * @author Matthew D. Jeffreys
 *
 * @param <T> type of internal identifier for the user i.e. type of user primary key.
 */

public class SimpleUserAuthenticationInfo<T> implements AuthenticableUserRequest<T>{
	private AbstractUserCredentials userCredentials;
	private T userIdentifier;
	private boolean isAuthenticated = false;
	
	public SimpleUserAuthenticationInfo(AbstractUserCredentials userCredentials){
		this.userCredentials = userCredentials;
	}

	@Override
	public AbstractUserCredentials getUserCredentials() {
		return userCredentials;
	}

	@Override
	public boolean isAuthenticated() {
		return isAuthenticated;
	}

	@Override
	public void setIsAuthenticated(boolean value) {
		isAuthenticated = value;
		
	}

	@Override
	public T getUserIdentifier() {
		return userIdentifier;
	}

	@Override
	public void setUserIdentifier(T identifier) {
		userIdentifier = identifier;
	}

}
