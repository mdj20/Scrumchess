/*
 *  This is a debug class will simply add a fen notation state to the datastore.
 */

package com.scrumchess.mdj20;

import java.io.IOException;

import 

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@SuppressWarnings("serial")
public class AJAXGameStateReceiver extends HttpServlet {
	
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String type = (String) req.getAttribute(type);
		String fen = (String) req.getAttribute(fen);
		
		if (  )
		
		if ( fen != null ){
			TestData.
		}
	}
	
}
