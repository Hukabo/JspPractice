package com.newlecture.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/add")
public class Add extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		String x = request.getParameter("x");
		String y = request.getParameter("y");
		
		if (x == null || x.equals("") || y == null || y.equals("")) {
			out.println("똑바로 입력해라잉");
		} else {
			int x_ = Integer.parseInt(x);
			int y_ = Integer.parseInt(y);
			int sum = x_+y_;
			out.println("결과는 : " + sum);
		}
		
	}
}
