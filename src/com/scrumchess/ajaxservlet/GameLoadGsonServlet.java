package com.scrumchess.ajaxservlet;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.scrumchess.data.ScrumchessUserRequestHandler;
import com.scrumchess.userrequests.GameLoadRequest;
import com.scrumchess.userrequests.GameLoadResponse;
import com.scrumchess.userrequests.NewGameRequest;
import com.scrumchess.userrequests.NewGameResponse;

public class GameLoadGsonServlet extends AbstractGsonUserRequestServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		Gson gson = buildScrumchessGson();
		String jSon = preProcessAndGetString(req);
		GameLoadRequest gameLoadRequest = gson.fromJson(jSon,GameLoadRequest.class);
		ScrumchessUserRequestHandler scurh = ScrumchessUserRequestHandler.getInstance();
		GameLoadResponse gameLoadResponse  = scurh.tryGameLoadRequest(gameLoadRequest);
		gson = new Gson();
		String json = gson.toJson(gameLoadResponse);
		sendRespnseJson(resp,json);
	}
}
