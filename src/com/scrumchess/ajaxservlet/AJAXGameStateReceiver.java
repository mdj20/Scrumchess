/*
 *  This is a debug class will simply add a fen notation state to the datastore.
 */

package com.scrumchess.ajaxservlet;

import java.awt.List;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.scrumchess.authentication.ScrumchessAuthenticationType;
import com.scrumchess.authentication.SimpleUserAuthenticationInfo;
import com.scrumchess.authentication.SimpleUserCredentials;
import com.scrumchess.data.ScrumchessDatastoreFacade;
import com.scrumchess.data.ScrumchessUserRequestHandler;
import com.scrumchess.userrequests.GameInfoRequest;


@SuppressWarnings("serial")
public class AJAXGameStateReceiver extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String userToken = req.getParameter("userToken");
		String authenticationTypeString = req.getParameter("authenticationType"); 
		ScrumchessAuthenticationType scrumchessAuthnticationType = ScrumchessAuthenticationType.valueOf(authenticationTypeString);
		SimpleUserAuthenticationInfo<String> userAuthenticationInfo = new SimpleUserAuthenticationInfo<String>(new SimpleUserCredentials(scrumchessAuthnticationType,userToken));
		long gameId = Long.parseLong(req.getParameter("gameId"));
		GameInfoRequest gameInfoRequest = new GameInfoRequest(userAuthenticationInfo,gameId);
		ScrumchessUserRequestHandler scurh = ScrumchessUserRequestHandler.getInstance();
		scurh.tryGameInfoRequest(gameInfoRequest);
	}
	
}
