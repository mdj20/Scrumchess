package com.scrumchess.userrequests;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.InstanceCreator;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.scrumchess.authentication.AbstractUserCredentials;
import com.scrumchess.authentication.ScrumchessAuthenticationType;
import com.scrumchess.authentication.SimpleUserAuthenticationInfo;
import com.scrumchess.authentication.SimpleUserCredentials;



public class NewGameRequest extends AbstractUserRequest{
	private NewGameConfig newGameConfig;
	private String otherPlayerId;
	public NewGameRequest(SimpleUserAuthenticationInfo<String> userAuthenticationInfo, NewGameConfig newGameConfig) {
		this(userAuthenticationInfo,newGameConfig,null);
	}
	
	public NewGameRequest(SimpleUserAuthenticationInfo<String> userAuthenticationInfo, NewGameConfig newGameConfig, String otherPlayerId){
		super(userAuthenticationInfo);
		this.newGameConfig=newGameConfig;
		this.otherPlayerId=otherPlayerId;
	}
	
	public NewGameConfig getNewGameConfig() {
		return newGameConfig;
	}
	
	public String getOtherPlayerId(){
		return otherPlayerId;
	}
	
	public enum NewGameConfig{
		WHITE, // SINGLE PLAYER
		BLACK,
		BLACK2, // 2 PLAYER
		WHITE2;
	}
	
	
	public static void main(String args[]) {
		SimpleUserAuthenticationInfo<String> userInfo = new SimpleUserAuthenticationInfo<String>(new SimpleUserCredentials(ScrumchessAuthenticationType.DEBUG, "userID"));
		NewGameRequest ngr = new NewGameRequest(userInfo,NewGameConfig.WHITE);
		Gson gson = new Gson();
		String json = gson.toJson(ngr);
		System.out.println(json);
		GsonBuilder gb = new GsonBuilder();
		gson = gb.registerTypeAdapter(AbstractUserCredentials.class, new AbstractUserCredentialDeserializer() ).create();
		NewGameRequest ngr2 = gson.fromJson(json, NewGameRequest.class);
		System.out.println(ngr2.isAuthenticated());
		System.out.println(ngr2.getOtherPlayerId());
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
