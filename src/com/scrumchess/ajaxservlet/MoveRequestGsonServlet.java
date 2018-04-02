package com.scrumchess.ajaxservlet;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.scrumchess.data.ScrumchessUserRequestHandler;
import com.scrumchess.userrequests.MoveRequest;
import com.scrumchess.userrequests.MoveRequestResponse;
import com.scrumchess.userrequests.NewGameRequest;
import com.scrumchess.userrequests.NewGameResponse;

public class MoveRequestGsonServlet extends AbstractGsonUserRequestServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		Gson gson = buildScrumchessGson();
		String jSon = preProcessAndGetString(req);
		MoveRequest moveRequest = gson.fromJson(jSon,MoveRequest.class);
		ScrumchessUserRequestHandler scurh = ScrumchessUserRequestHandler.getInstance();
		MoveRequestResponse response  = scurh.tryMoveRequest(moveRequest);
		gson = new Gson();
		String json = gson.toJson(response);
		sendRespnseJson(resp,json);
	}
}
