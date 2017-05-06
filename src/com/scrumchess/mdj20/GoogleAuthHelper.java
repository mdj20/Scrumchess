	package com.scrumchess.mdj20;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


// this class will help with googles sign in authentication

public class GoogleAuthHelper {
	
	
	private static final String endpointURL = "https://www.googleapis.com/oauth2/v3/tokeninfo?id_token=";
	private static final String _sub = "sub";
	
	//836532323858-mpraf2crpo7vu2fqd1v414abgb190so7.apps.googleusercontent.com

	public static String getSubjectFromEndpoint(String idToken){
		String ret = null ;
		JsonObject userObject = parseObjectViaEndpoint(idToken); // returns user token claim JsonObject
		if (userObject != null){
			ret = userObject.get(_sub).toString(); // gets user Subject
		}
		return ret;
	}
	
	/*
	public static int getSubject(String idToken){
		int ret = -1;
		
		JacksonFactory jacksonFactory = new JacksonFactory();
		
		GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder
				(UrlFetchTransport.getDefaultInstance(),jacksonFactory)
		
		
		
		return ret;
	}
	*/
	
	// takes idToken and returns JsonObject that represents Google user ID Token claims
	private static JsonObject parseObjectViaEndpoint(String idToken) {
		JsonObject ret = null;
		String responseString = null;
		try {
			responseString = getAuthResponse(idToken);
		} catch (Exception e) {
			// issue with connection
			System.out.println("getAuthResponse EXCEPTION");
			e.printStackTrace();
		}
		if(responseString != null){
			JsonElement jElement = new JsonParser().parse(responseString);
			ret = jElement.getAsJsonObject();
		}
		return ret;
	}
	
	// takes a tokenID then sends Authentication request and returns string representation of input stream.
	private static String getAuthResponse(String tokenID) throws Exception{
		URL endPointCall = new URL(endpointURL+tokenID);
		HttpURLConnection conn = (HttpURLConnection) endPointCall.openConnection(); 
		conn.setRequestMethod("GET");
		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		StringBuilder sb = new StringBuilder();
		while (br.ready()){
			sb.append(br.readLine());
		}
		return sb.toString();
	}
	
}
