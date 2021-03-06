package com.scrumchess.ajaxservlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.scrumchess.authentication.ScrumchessAuthenticationType;
import com.scrumchess.authentication.SimpleUserAuthenticationInfo;
import com.scrumchess.authentication.SimpleUserCredentials;
import com.scrumchess.data.GameConfiguration;
import com.scrumchess.data.ScrumchessUserRequestHandler;
import com.scrumchess.userrequests.NewGameRequest;
import com.scrumchess.userrequests.NewGameResponse;

@SuppressWarnings("serial")
public class NewGameRequestServlet extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String userToken = req.getParameter("userToken");
		System.out.println(userToken);
		String authenticationTypeString = req.getParameter("authenticationType");
		System.out.println(authenticationTypeString);
		ScrumchessAuthenticationType scrumchessAuthnticationType = ScrumchessAuthenticationType.valueOf(authenticationTypeString);
		SimpleUserAuthenticationInfo<String> userAuthenticationInfo = new SimpleUserAuthenticationInfo<String>(new SimpleUserCredentials(scrumchessAuthnticationType,userToken));
		String newGameConfigString = req.getParameter("newGameConfig");
		String opponentId = req.getParameter("opponent");
		GameConfiguration gameConfiguration = GameConfiguration.valueOf(newGameConfigString);
		NewGameRequest newGameRequest = new NewGameRequest(userAuthenticationInfo,gameConfiguration,opponentId);
		ScrumchessUserRequestHandler scurh = ScrumchessUserRequestHandler.getInstance();
		NewGameResponse newGameResponse  = scurh.tryNewGameRequest(newGameRequest);
		System.out.println("Success"+newGameResponse.isSuccessful());
		System.out.println(newGameResponse.getResponseObject().getBlack());
		Gson gson = new Gson();
		String json = gson.toJson(newGameResponse);
		System.out.println("JSON:"+json); 
		resp.setContentType("application/json");
		PrintWriter responseWriter = resp.getWriter();
		responseWriter.print(json);
		responseWriter.flush();
	}
}
