package com.scrumchess.authentication;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class SimpleUserCredentials extends AbstractUserCredentials{
	private String userToken;
	private ScrumchessAuthenticationType scrumchessAuthenticationType;
	
	public SimpleUserCredentials(ScrumchessAuthenticationType type, String token){
		scrumchessAuthenticationType = type;
		userToken = token;
	}
	
	@Override
	String getUserToken() {
		return userToken;
	}

	@Override
	ScrumchessAuthenticationType getAuthenticationType() {
		return scrumchessAuthenticationType;
	}
	
	
	static class AbstractUserCredentialDeserializer implements JsonDeserializer<AbstractUserCredentials> {

		@Override
		public AbstractUserCredentials deserialize(JsonElement json, Type arg1, JsonDeserializationContext arg2)
				throws JsonParseException {
			JsonObject jsonObject = json.getAsJsonObject();
			ScrumchessAuthenticationType authType = ScrumchessAuthenticationType.valueOf(jsonObject.get("scrumchessAuthenticationType").getAsString());
			String userToken = jsonObject.get("userToken").getAsString();
			SimpleUserCredentials ret = new SimpleUserCredentials(authType,userToken);
			return ret;
		}
		
	}

}
