package com.scrumchess.mdj20;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.scrumchess.data.ScrumchessDatastore;
import com.scrumchess.data.UserProxy;

public class UserInfoTestServlet  extends HttpServlet{

	private static final String _idtoken = "idtoken";
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp){
		System.out.println("POST!!");
		String idToken = (String) req.getParameter(_idtoken);
		String subject = GoogleAuthHelper.getSubjectFromEndpoint(idToken);
		System.out.println("Google Subject:" + subject);	
		ScrumchessDatastore.userSignIn(subject);

		ScrumchessDatastore.userNewGame(subject);
	
	}
	
	@Override 
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		resp.setContentType("text/plain");
		resp.getWriter().println("Hello, world");
		System.out.println("GET!!!");
	}
	
}
