package com.scrumchess.userrequests;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.scrumchess.authentication.AbstractUserCredentials;
import com.scrumchess.authentication.SimpleAbstractUserCredentialGsonAdapter;

public class ScrumChessGsonBuilder<pSimpleAbstractUserCredentialGsonAdapter>{
	public static SimpleAbstractUserCredentialGsonAdapter userCredentialadpater = new SimpleAbstractUserCredentialGsonAdapter();
	public static Gson getSCGson(){
		Gson ret = null;
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter( AbstractUserCredentials.class,userCredentialadpater);
		ret = gsonBuilder.create();
		return ret;
	}
}
