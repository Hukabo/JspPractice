package com.newlecture.web;

import java.io.IOException;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/add")
public class Add extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		Cookie[] cookies = request.getCookies();
		
		String value = request.getParameter("value");
		String op = request.getParameter("operator");
		String dot = request.getParameter("dot");
		
		String exp = "";
		 if(cookies != null)
			 for(Cookie c : cookies) {
				 if(c.getName().equals("exp")) {
					 exp = c.getValue();
					  break;
				 }
			 }
		 
		 if(op != null && op.equals("=")) {
			 
			 ScriptEngine engine = new ScriptEngineManager().getEngineByName("graal.js");
			 
			 try {
				exp = String.valueOf(engine.eval(exp));
			} catch (ScriptException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
		 
		 else if(op != null && op.equals("C")) {
			 exp = "0";
		 }
		 
		 else {
			 exp += value == null ? "" : value;
			 exp += op == null ? "" : op;
			 exp += dot == null ? "" : dot;
		 }
		 
		
		 
		
		Cookie expCookie = new Cookie("exp", exp);
		if(op != null && op.equals("C"))
			expCookie.setMaxAge(0); 
		
		response.addCookie(expCookie);
		response.sendRedirect("/calcpage");
	}
}
