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
import com.scrumchess.userrequests.MoveRequest;
import com.scrumchess.userrequests.MoveRequestResponse;
import com.scrumchess.userrequests.NewGameRequest;
import com.scrumchess.userrequests.NewGameResponse;
import com.scrumchess.userrequests.NewGameRequest.NewGameConfig;

@SuppressWarnings("serial")
public class MoveRequestServlet extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String userToken = req.getParameter("userToken");
		String authenticationTypeString = req.getParameter("authenticationType"); 
		ScrumchessAuthenticationType scrumchessAuthnticationType = ScrumchessAuthenticationType.valueOf(authenticationTypeString);
		SimpleUserAuthenticationInfo<String> userAuthenticationInfo = new SimpleUserAuthenticationInfo<String>(new SimpleUserCredentials(scrumchessAuthnticationType,userToken));
		long gameId = Long.parseLong(req.getParameter("gameId"));
		System.out.println("gameid: "+gameId);
		String alegbraicNotation = req.getParameter("algebraicNotation");
		MoveRequest moveRequest = new MoveRequest(userAuthenticationInfo,gameId,alegbraicNotation);
		System.out.println("gameidMR: "+moveRequest.getGameID());
		ScrumchessUserRequestHandler scurh = ScrumchessUserRequestHandler.getInstance();
		MoveRequestResponse moveRequestResponse  = scurh.tryMoveRequest(moveRequest);
		System.out.println("ID:"+moveRequestResponse.getResponseObject().getId());
		Gson gson = new Gson();
		String json = gson.toJson(moveRequestResponse);
		resp.setContentType("application/json");
		PrintWriter responseWriter = resp.getWriter();
		responseWriter.print(json);
		responseWriter.flush();
	}
}
