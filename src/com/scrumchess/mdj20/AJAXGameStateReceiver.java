/*
 *  This is a debug class will simply add a fen notation state to the datastore.
 */

package com.scrumchess.mdj20;

import java.awt.List;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.scrumchess.data.TestData;

@SuppressWarnings("serial")
public class AJAXGameStateReceiver extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String type = req.getParameter("type");
		String fen =  req.getParameter("fen");
		String id =  req.getParameter("id");
		if ( type.equals("set")  ){
			TestData.add(Integer.parseInt(id), fen);
		}
		else if ( type.equals("get") ){
			PrintWriter ps = resp.getWriter();
			ArrayList<String> data = TestData.getAllWithID(Integer.parseInt(id));
			for (String s : data){
				ps.append(s+" \n");
			}
			
		}
	}
	
}
