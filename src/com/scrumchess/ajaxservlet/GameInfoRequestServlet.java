/*
 *  This is a debug class will simply add a fen notation state to the datastore.
 */

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
import com.scrumchess.data.ScrumchessDatastoreFacade;
import com.scrumchess.data.ScrumchessUserRequestHandler;
import com.scrumchess.userrequests.GameInfoRequest;
import com.scrumchess.userrequests.GameInfoResponse;


@SuppressWarnings("serial")
public class GameInfoRequestServlet extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String userToken = req.getParameter("userToken");
		String authenticationTypeString = req.getParameter("authenticationType"); 
		ScrumchessAuthenticationType scrumchessAuthnticationType = ScrumchessAuthenticationType.valueOf(authenticationTypeString);
		SimpleUserAuthenticationInfo<String> userAuthenticationInfo = new SimpleUserAuthenticationInfo<String>(new SimpleUserCredentials(scrumchessAuthnticationType,userToken));
		long gameId = Long.parseLong(req.getParameter("gameId"));
		GameInfoRequest gameInfoRequest = new GameInfoRequest(userAuthenticationInfo,gameId);
		ScrumchessUserRequestHandler scurh = ScrumchessUserRequestHandler.getInstance();
		GameInfoResponse gameInfoResponse  = scurh.tryGameInfoRequest(gameInfoRequest);
		Gson gson = new Gson();
		String json = gson.toJson(gameInfoResponse);
		resp.setContentType("application/json");
		PrintWriter responseWriter = resp.getWriter();
		responseWriter.print(json);
		responseWriter.flush();
	}
	
}
