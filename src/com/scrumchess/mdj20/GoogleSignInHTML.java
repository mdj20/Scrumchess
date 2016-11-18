package com.scrumchess.mdj20;



public class GoogleSignInHTML {

	//<script src="https://apis.google.com/js/platform.js" async defer></script>
	private static final String signInScript = 
			"<script src=\"https://apis.google.com/js/platform.js\" async defer></script>";
	
	// 836532323858-mpraf2crpo7vu2fqd1v414abgb190so7.apps.googleusercontent.com
	private static final String clientID = 
			"<meta name=\"google-signin-client_id\" content=\"836532323858-mpraf2crpo7vu2fqd1v414abgb190so7.apps.googleusercontent.com\">";
	
	//<div class="g-signin2" data-onsuccess="onSignIn"></div>
	private static final String signInDiv = "<div class=\"g-signin2\" data-onsuccess=\"onSignIn\">";
	private static final String closeDiv = "</div>";

	public static String getSigninScript() {
		return signInScript;
	}

	public static String getClientID() {
		return clientID;
	}

	public static String getSignInDiv() {
		return getSignInDiv("");
	}
	public static String getSignInDiv(String innerHTML){
		return signInDiv+innerHTML+closeDiv;
	}
			
		
			
	
}
