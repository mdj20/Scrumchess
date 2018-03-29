package com.scrumchess.userrequests;

import com.google.gson.Gson;
import com.scrumchess.data.Game;

public class NewGameResponse extends AbstractUserResponse<Game> {
	public NewGameResponse(boolean success, UniversalFailureReason reason){
		super(success,reason);
	}
	public NewGameResponse(boolean success,Game game){
		super(success,game);
	}
	
	public static void main(String args[]) {
		NewGameResponse ngr = new NewGameResponse(false, UniversalFailureReason.USER_NOT_OWNER);
		Gson gson = new Gson();
		String json = gson.toJson(ngr);
		System.out.println(json);
		NewGameResponse ngr2 = gson.fromJson(json, NewGameResponse.class);
		System.out.println();
	}
}
