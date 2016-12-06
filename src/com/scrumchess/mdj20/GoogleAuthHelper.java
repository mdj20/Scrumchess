package com.scrumchess.mdj20;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import com.google.api.client.extensions.appengine.http.UrlFetchTransport;
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.appengine.repackaged.com.google.gson.Gson;

// this class will help with googles sign in authentication

public class GoogleAuthHelper {
	
	
	private static final String endpointURL = "https://www.googleapis.com/oauth2/v3/tokeninfo?id_token=";
	

	public static int getSubjectFromEndpoint(String idToken){
		int ret = -1;
		String getURL = endpointURL+idToken;
		URL url = new URL(getURL);
		
		
		
		return ret;
	}
	
	
	public static int getSubject(String idToken){
		int ret = -1;
		
		JacksonFactory jacksonFactory = new JacksonFactory();
		
		GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder
				(UrlFetchTransport.getDefaultInstance(),jacksonFactory)
		
		
		
		return ret;
	}
	
	
	private static int  parseEndpoint(String urlString) throws Exception{
		int ret = -1;
		URL endPointCall = new URL(urlString);
		HttpURLConnection conn = (HttpURLConnection) endPointCall.openConnection(); 
		conn.setRequestMethod("GET");
		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		StringBuilder sb = new StringBuilder();
		while (br.ready()){
			sb.append(br.readLine());
		}
		
		
		
		return ret;
	}
	
	
	private static HashMap<String,String> parseJson(String request){
		HashMap<String,String> ret = new HashMap<String,String>();
		Gson gson = new Gson();
		
		
		
		return ret;
		
	}
	
	
}
