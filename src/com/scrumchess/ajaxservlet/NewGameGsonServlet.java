package com.scrumchess.ajaxservlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.scrumchess.authentication.AbstractUserCredentials;
import com.scrumchess.authentication.ScrumchessAuthenticationType;
import com.scrumchess.authentication.SimpleAbstractUserCredentialGsonAdapter;
import com.scrumchess.authentication.SimpleUserAuthenticationInfo;
import com.scrumchess.authentication.SimpleUserCredentials;
import com.scrumchess.data.ScrumchessUserRequestHandler;
import com.scrumchess.userrequests.GameInfoRequest;
import com.scrumchess.userrequests.NewGameRequest;
import com.scrumchess.userrequests.NewGameResponse;
import com.scrumchess.userrequests.NewGameRequest.NewGameConfig;

public class NewGameGsonServlet extends AbstractGsonUserRequestServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		Gson gson = buildScrumchessGson();
		String jSon = preProcessAndGetString(req);
		NewGameRequest newGameRequest = gson.fromJson(jSon,NewGameRequest.class);
		ScrumchessUserRequestHandler scurh = ScrumchessUserRequestHandler.getInstance();
		NewGameResponse newGameResponse  = scurh.tryNewGameRequest(newGameRequest);
		gson = new Gson();
		String json = gson.toJson(newGameResponse);
		sendRespnseJson(resp,json);
	}
}
