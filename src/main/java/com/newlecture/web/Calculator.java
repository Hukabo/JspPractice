package com.newlecture.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/calculator")
public class Calculator extends HttpServlet {
	
//	@Override
//	protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//		
//		if(request.getMethod().equals("GET")) {
//			System.out.println("GET요청이 왔습니다.");
//		}
//		else if(request.getMethod().equals("POST")) {
//			System.out.println("POST요청이 왔습니다.");
//		}
//		
//		super.service(request, response);
//	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
		
		expCookie.setPath("/calculator");
		response.addCookie(expCookie);
		response.sendRedirect("/calculator"); // get요청으로 감
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Cookie[] cookies = request.getCookies();
		 response.setCharacterEncoding("UTF-8");
		 response.setContentType("text/html; UTF-8");
		 
		 String exp = "0";
		 
		 if(cookies != null)
			 for(Cookie c : cookies) {
				 if(c.getName().equals("exp")) {
					 exp = c.getValue();
					  break;
				 }
			 }
		 
		  PrintWriter out = response.getWriter();
		  
		  out.write("<!DOCTYPE html>");
		  out.write("<html>");
		  out.write("<head>");
		  out.write("<meta charset=\"UTF-8\">");
		  out.write("<title>Insert title here</title>");
		  out.write("<style>");
		  out.write("input {");
		  out.write("height: 50px;");
		  out.write("width: 50px;");
		  out.write("}");
		  out.write(".output {");
		  out.write("height: 50px;");
		  out.write("background: #e9e9e9;");
		  out.write("font-size: 24px;");
		  out.write("font-weight: bold;");
		  out.write("text-align: end;");
		  out.write("padding-right: 5px;");
		  out.write("}");
		  out.write("</style>");
		  out.write("</head>");
		  out.write("<body>");
		  out.write("<form method=\"post\">");
		  out.write("<table>");
		  out.write("<tr>");
		  out.printf("<td class=\"output\" colspan=\"4\">%s</td>", exp);
		  out.write("</tr>");
		  out.write("<tr>");
		  out.write("<td><input type=\"submit\" name=\"operator\" value=\"CE\" /></td>");
		  out.write("<td><input type=\"submit\" name=\"operator\" value=\"C\" /></td>");
		  out.write("<td><input type=\"submit\" name=\"operator\" value=\"BS\" /></td>");
		  out.write("<td><input type=\"submit\" name=\"operator\" value=\"/\" /></td>");
		  out.write("</tr>");
		  out.write("<tr>");
		  out.write("<td><input type=\"submit\" name=\"value\" value=\"7\" /></td>");
		  out.write("<td><input type=\"submit\" name=\"value\" value=\"8\" /></td>");
		  out.write("<td><input type=\"submit\" name=\"value\" value=\"9\" /></td>");
		  out.write("<td><input type=\"submit\" name=\"operator\" value=\"*\" /></td>");
		  out.write("</tr>");
		  out.write("<tr>");
		  out.write("<td><input type=\"submit\" name=\"value\" value=\"4\" /></td>");
		  out.write("<td><input type=\"submit\" name=\"value\" value=\"5\" /></td>");
		  out.write("<td><input type=\"submit\" name=\"value\" value=\"6\" /></td>");
		  out.write("<td><input type=\"submit\" name=\"operator\" value=\"-\" /></td>");
		  out.write("</tr>");
		  out.write("<tr>");
		  out.write("<td><input type=\"submit\" name=\"value\" value=\"1\" /></td>");
		  out.write("<td><input type=\"submit\" name=\"value\" value=\"2\" /></td>");
		  out.write("<td><input type=\"submit\" name=\"value\" value=\"3\" /></td>");
		  out.write("<td><input type=\"submit\" name=\"operator\" value=\"+\" /></td>");
		  out.write("</tr>");
		  out.write("<tr>");
		  out.write("<td></td>");
		  out.write("<td><input type=\"submit\" name=\"value\" value=\"0\" /></td>");
		  out.write("<td><input type=\"submit\" name=\"dot\" value=\".\" /></td>");
		  out.write("<td><input type=\"submit\" name=\"operator\" value=\"=\" /></td>");
		  out.write("</tr>");
		  out.write("</table>");
		  out.write("</form>");
		  out.write("</body>");
		  out.write("</html>");
	}
}
