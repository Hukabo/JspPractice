package com.newlecture.web.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.newlecture.web.entity.Notice;

@WebServlet("/html/notice/list")
public class NoticeListContorller extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String dbDriver = "org.postgresql.Driver";
		String dbUrl = "jdbc:postgresql://localhost:5432/jdbc";
		String dbUser = "hukabo";
		String dbPassword = "hukabo";
		String sql = "SELECT * FROM notice";
		
		List<Notice> list = new ArrayList<>();
		
		try {
			
			Class.forName(dbDriver);
			Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String writerId = rs.getString("writer_id");
				Date regdate = rs.getDate("regDate");
				int hit = rs.getInt("hit");
				String files = rs.getString("files");
				String content = rs.getString("content");
				
				Notice notice = new Notice(id, title, writerId, regdate, hit,files,content);
				list.add(notice);
			}
			
		    rs.close();
		    st.close();
		    con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("list",list);
		 
		request.getRequestDispatcher("/WEB-INF/view/notice/list.jsp").forward(request, response);
	}
  }
