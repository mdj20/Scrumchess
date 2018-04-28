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
import com.scrumchess.authentication.SimpleAbstractUserCredentialGsonAdapter;
import com.scrumchess.authentication.SimpleUserAuthenticationInfo;
import com.scrumchess.authentication.SimpleUserCredentials;
import com.scrumchess.data.GameConfiguration;



public class NewGameRequest extends AbstractUserRequest{
	private GameConfiguration gameConfiguration;
	private String otherPlayerId;
	public NewGameRequest(SimpleUserAuthenticationInfo<String> userAuthenticationInfo, GameConfiguration gameConfiguration) {
		this(userAuthenticationInfo,gameConfiguration,null);
	}
	
	public NewGameRequest() {}
	
	public NewGameRequest(SimpleUserAuthenticationInfo<String> userAuthenticationInfo, GameConfiguration gameConfiguration, String otherPlayerId){
		super(userAuthenticationInfo);
		this.gameConfiguration=gameConfiguration;
		this.otherPlayerId=otherPlayerId;
	}
	
	public GameConfiguration getNewGameConfig() {
		return gameConfiguration;
	}
	
	public String getOtherPlayerId(){
		return otherPlayerId;
	}
	
	public static void main(String args[]) {
		SimpleUserAuthenticationInfo<String> userInfo = new SimpleUserAuthenticationInfo<String>(new SimpleUserCredentials(ScrumchessAuthenticationType.DEBUG, "userID"));
		NewGameRequest ngr = new NewGameRequest(userInfo,GameConfiguration.WHITE);
		Gson gson = new Gson();
		String json = gson.toJson(ngr);
		System.out.println(json);
		GsonBuilder gb = new GsonBuilder();
		gson = gb.registerTypeAdapter(AbstractUserCredentials.class, new SimpleAbstractUserCredentialGsonAdapter() ).create();
		NewGameRequest ngr2 = gson.fromJson(json, NewGameRequest.class);
		System.out.println(ngr2.isAuthenticated());
		System.out.println(ngr2.getOtherPlayerId());
		System.out.println(ngr2.getNewGameConfig());
	}



	/*
	
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

*/
	
}
