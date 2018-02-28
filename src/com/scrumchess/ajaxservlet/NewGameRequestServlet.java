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
import com.scrumchess.data.ScrumchessUserRequestHandler;
import com.scrumchess.userrequests.GameInfoRequest;
import com.scrumchess.userrequests.GameInfoResponse;
import com.scrumchess.userrequests.NewGameRequest;
import com.scrumchess.userrequests.NewGameRequest.NewGameConfig;
import com.scrumchess.userrequests.NewGameResponse;

@SuppressWarnings("serial")
public class NewGameRequestServlet extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String userToken = req.getParameter("userToken");
		String authenticationTypeString = req.getParameter("authenticationType"); 
		ScrumchessAuthenticationType scrumchessAuthnticationType = ScrumchessAuthenticationType.valueOf(authenticationTypeString);
		SimpleUserAuthenticationInfo<String> userAuthenticationInfo = new SimpleUserAuthenticationInfo<String>(new SimpleUserCredentials(scrumchessAuthnticationType,userToken));
		String newGameConfigString = req.getParameter("newGameConfig");
		NewGameConfig newGameConfig = NewGameRequest.NewGameConfig.valueOf(newGameConfigString);
		NewGameRequest newGameRequest = new NewGameRequest(userAuthenticationInfo,newGameConfig);
		ScrumchessUserRequestHandler scurh = ScrumchessUserRequestHandler.getInstance();
		NewGameResponse newGameResponse  = scurh.tryNewGameRequest(newGameRequest);
		Gson gson = new Gson();
		String json = gson.toJson(newGameResponse);
		resp.setContentType("application/json");
		PrintWriter responseWriter = resp.getWriter();
		responseWriter.print(json);
		responseWriter.flush();
	}
}
