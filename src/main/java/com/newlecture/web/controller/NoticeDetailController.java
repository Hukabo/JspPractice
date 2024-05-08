package com.newlecture.web.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.newlecture.web.entity.Notice;

@WebServlet("/html/notice/detail")
public class NoticeDetailController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));

	 	String dbDriver = "org.postgresql.Driver";
		String dbUrl = "jdbc:postgresql://localhost:5432/jdbc";
		String dbUser = "hukabo";
		String dbPassword = "hukabo";
		String sql = "SELECT * FROM notice WHERE id=?";
		
		try {
			Class.forName(dbDriver);
			Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			rs.next();
			
			String title = rs.getString("title");
		 	String content = rs.getString("content");
		 	String writerId = rs.getString("writer_id");
			int hit = rs.getInt("hit");
			String files = rs.getString("files");
		 	Date regdate = rs.getDate("regDate");
		 	
		 	Notice notice = new Notice(id, title, writerId, regdate, hit,files,content);
		 	
		 	request.setAttribute("notice", notice);
		 	
		    rs.close();
		    st.close();
		    con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 
		request.getRequestDispatcher("/WEB-INF/view/notice/detail.jsp").forward(request, response);
	}
}
