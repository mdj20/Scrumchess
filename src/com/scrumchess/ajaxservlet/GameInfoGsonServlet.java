package com.scrumchess.ajaxservlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.scrumchess.authentication.AbstractUserCredentials;
import com.scrumchess.authentication.SimpleAbstractUserCredentialGsonAdapter;
import com.scrumchess.data.ScrumchessUserRequestHandler;
import com.scrumchess.userrequests.GameInfoRequest;
import com.scrumchess.userrequests.GameInfoResponse;

@SuppressWarnings("serial")
public class GameInfoGsonServlet extends AbstractGsonUserRequestServlet{

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		Gson gson = buildScrumchessGson();
		String jSon = preProcessAndGetString(req);
		GameInfoRequest gameInfoRequest = gson.fromJson(jSon,GameInfoRequest.class);
		ScrumchessUserRequestHandler scurh = ScrumchessUserRequestHandler.getInstance();
		GameInfoResponse gameInfoResponse  = scurh.tryGameInfoRequest(gameInfoRequest);
		Gson gsonResp = new Gson();
		String json = gsonResp.toJson(gameInfoResponse);
		sendRespnseJson(resp,json);
	}
	
}
