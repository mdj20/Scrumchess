package com.scrumchess.transit.auth.pre.type;

public interface AuthenticationType {
	public static final int AUTH_TYPE_GOOGLE = 1;
	public static final int NONE = -1;
	public int getAuthenticationType();
}
