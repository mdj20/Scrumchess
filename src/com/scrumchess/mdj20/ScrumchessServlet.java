package com.scrumchess.mdj20;

import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.http.*;

import com.google.gson.Gson;

@SuppressWarnings("serial")
public class ScrumchessServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		BufferedReader input = req.getReader();
		
		Gson gson = new Gson();
		
		System.out.println("test");
		int i = 0;
		while(input.ready()){
			System.out.println(input.readLine());
		}
		
		
		resp.setContentType("text/plain");
		resp.getWriter().println("Hello, world again!");
		
	}
}
