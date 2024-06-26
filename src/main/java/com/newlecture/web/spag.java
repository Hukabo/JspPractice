package com.newlecture.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/spag")
public class spag extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		int num = 0;

		String num_ = request.getParameter("n");
		
		if(num_ != null && !num_.equals("")) {
			num = Integer.parseInt(num_);
		}
		
		String result;
		
		if(num % 2 == 0) {
			result = "짝수";
		} else {
			result = "홀수";
		}
		String[] names = {"newlec", "dragon"};
		Map<String, Object> notice = new HashMap<>();
		notice.put("id", 1);
		notice.put("title", "jsp연습중");
		
		request.setAttribute("result", result);
		request.setAttribute("names", names);
		request.setAttribute("notice", notice);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("spag.jsp");
		dispatcher.forward(request, response);
	}
}
