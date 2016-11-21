package com.scrumchess.mdj20;

package com.scrumchess.mdj20;

import java.awt.List;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;

import com.scrumchess.data.TestData;

@SuppressWarnings("serial")
public class AJAXUserSignInServlet extends HttpServlet {
	
	private static final String tokenId = "token_id";
	private static final String postVerifyURL = "https://www.googleapis.com/oauth2/v3/tokeninfo";
	private static final String getVerifyURL = "https://www.googleapis.com/oauth2/v3/tokeninfo?id_token=";
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String  token =  req.getParameter(tokenId);
		

			
	}
	
	
	
	private GoogleAuth postVerify(String token){
		URL url = new URL(postVerifyURL);
		HttpURLConnection uc = (HttpURLConnection)url.openConnection();
		uc.setRequestMethod("POST");
		uc.setDoOutput(true);
		
	}
	
	private GoogleAuth getVerify(String token){
		URL url = new URL(getVerifyURL+token);
		HttpURLConnection uc = (HttpURLConnection)url.openConnection();
		InputStream is = uc.getInputStream();	
		
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
	






