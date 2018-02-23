package com.scrumchess.ajaxservlet;


import java.awt.List;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.api.client.extensions.appengine.http.UrlFetchTransport;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier.Builder;
import com.google.api.client.json.jackson.JacksonFactory;


@SuppressWarnings("serial")
public class AjaxUserSignInServlet extends HttpServlet {
	
	private static final String tokenId = "token_id";
	private static final String postVerifyURL = "https://www.googleapis.com/oauth2/v3/tokeninfo";
	private static final String getVerifyURL = "https://www.googleapis.com/oauth2/v3/tokeninfo?id_token=";
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String  token =  req.getParameter(tokenId);		
		String userID = null;
		
			try {
				userID = verifyGoogle(token);
			} catch (GeneralSecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	/*
	private GoogleAuth postVerify(String token) throws IOException{
		URL url = new URL(postVerifyURL);
		HttpURLConnection uc = (HttpURLConnection)url.openConnection();
		uc.setRequestMethod("POST");
		uc.setDoOutput(true);
	}
	
	private GoogleAuth getVerify(String token) throws IOException{
		URL url = new URL(getVerifyURL+token);
		HttpURLConnection uc = (HttpURLConnection)url.openConnection();
		InputStream is = uc.getInputStream();	
	}
	
	*/
	private static String verifyGoogle(String token) throws GeneralSecurityException, IOException{
		
		String ret = null;
		JacksonFactory jf = new JacksonFactory();
		GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(UrlFetchTransport.getDefaultInstance(),jf)
				.setAudience(Arrays.asList(GoogleSignInHTML.getClientIDString()))
				.setIssuer("accounts.google.com")
				.build();
		
		GoogleIdToken idToken =verifier.verify(token);	
		if (idToken != null){
			Payload payload = idToken.getPayload();
			String userID = payload.getSubject();
			ret = userID;
		}
		return ret;
	}
	
		// sub class that contains authentication information
		class GoogleAuth{
			String iss;
			String sub;
			String azp;
			String aud;
			String iat;
			String exp;
			GoogleAuth(String iss,String sub,String azp,String aud,String iat,String exp){
				this.iss =iss;
				this.sub = sub;
				this.azp =azp;
				this.aud = aud;
				this.iat = iat;
				this.exp = exp;
			}
		}
}
	






