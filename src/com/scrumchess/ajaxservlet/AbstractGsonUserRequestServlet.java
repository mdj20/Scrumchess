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
import com.scrumchess.authentication.SimpleAbstractUserCredentialGsonAdapter;

public abstract class AbstractGsonUserRequestServlet extends HttpServlet {
	
	private static final String contentTypeString ="application/json";
	
	protected BufferedReader preProcessRequest(HttpServletRequest req) {
		BufferedReader ret = null;
		try {
			ret = req.getReader();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	protected String getJsonString(BufferedReader bReader) {
		String ret = null;
		try {
			ret = bReader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	protected String preProcessAndGetString(HttpServletRequest req) {
		return getJsonString(preProcessRequest(req));
	}
	
	/**
	 * returns a custom Gson for the scrumchess userRequest/userResponse type hierarchy.
	 * 
	 * @return
	 */
	
	protected Gson buildScrumchessGson() {
		GsonBuilder gb = new GsonBuilder();
		Gson gson = gb.registerTypeAdapter(AbstractUserCredentials.class, new SimpleAbstractUserCredentialGsonAdapter() ).create();
		return gson;
	}
	
	protected void setResponseContentType(HttpServletResponse resp) {
		resp.setContentType(contentTypeString);
	}
	
	protected void sendRespnseJson(HttpServletResponse resp, String json) {
		setResponseContentType(resp);
		PrintWriter responseWriter = null;
		try {
			responseWriter = resp.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		responseWriter.print(json);
		responseWriter.flush();
	}
	
}
