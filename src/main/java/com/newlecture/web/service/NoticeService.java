package com.newlecture.web.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.newlecture.web.entity.Notice;

public class NoticeService {
	
	public List<Notice> getNoticeList(){
		
		return getNoticeList("title", "", 1);
	}
	
	public List<Notice> getNoticeList(int page){
		
		return getNoticeList("title", "", page);
	}
	
	public List<Notice> getNoticeList(String field, String query, int page){
		
		String dbDriver = "org.postgresql.Driver";
		String dbUrl = "jdbc:postgresql://localhost:5432/jdbc";
		String dbUser = "hukabo";
		String dbPassword = "hukabo";
		
		List<Notice> list = new ArrayList<>();
		String sql = "SELECT * FROM (SELECT *, ROW_NUMBER() OVER (ORDER BY regdate DESC) AS rownum FROM notice WHERE "+ field +" LIKE ?) AS sub WHERE rownum BETWEEN ? AND ?";
		
		try {
			
			Class.forName(dbDriver);
			Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
			PreparedStatement st = con.prepareStatement(sql);
			
			st.setString(1, "%"+query+"%");
			st.setInt(2, 1+(page-1)*10);
			st.setInt(3, page*10);
			
			ResultSet rs = st.executeQuery();
			
			
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
		
		
		return list;
	}
	
	public int getNoticeCount(String field, String query){
		
		String sql = "SELECT COUNT(*) FROM notice WHERE " +field+ " LIKE ?";
		
		String dbDriver = "org.postgresql.Driver";
		String dbUrl = "jdbc:postgresql://localhost:5432/jdbc";
		String dbUser = "hukabo";
		String dbPassword = "hukabo";
		
		int count = 0;
		
		try {
			
			Class.forName(dbDriver);
			Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
			PreparedStatement st = con.prepareStatement(sql);
			
			st.setString(1, "%"+query+"%");
			
			ResultSet rs = st.executeQuery();
			
			if(rs.next())
				count = rs.getInt("count");
			
		    rs.close();
		    st.close();
		    con.close();
		    
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return count;
	}
	
	public int getNoticeCount(){
		
		return getNoticeCount("title", "");
	}
	
	public Notice getNotice(int id) {
		
		Notice notice = null;
		
		String sql = "SELECT * FROM notice WHERE ID=?";
		
		String dbDriver = "org.postgresql.Driver";
		String dbUrl = "jdbc:postgresql://localhost:5432/jdbc";
		String dbUser = "hukabo";
		String dbPassword = "hukabo";
				
		try {
			
			Class.forName(dbDriver);
			Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
			PreparedStatement st = con.prepareStatement(sql);
			
			st.setInt(1, id);
			
			ResultSet rs = st.executeQuery();
			
			if(rs.next()) {
				int id_ = rs.getInt("id");
				String title = rs.getString("title");
				String writerId = rs.getString("writer_id");
				String content = rs.getString("content");
				Date regdate = rs.getDate("regdate");
				int hit = rs.getInt("hit");
				String files = rs.getString("files");
				
				notice = new Notice(id, title, writerId, regdate, hit, files,content);
			}
			
		    rs.close();
		    st.close();
		    con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return notice;
	}
	 
	public Notice getNextNotice(int id) {
			
		String sql = "SELECT id FROM notice WHERE regdate > (SELECT regdate FROM notice WHERE id=?) ORDER BY id ASC LIMIT 1;";
		
		Notice notice = null;
		
		String dbDriver = "org.postgresql.Driver";
		String dbUrl = "jdbc:postgresql://localhost:5432/jdbc";
		String dbUser = "hukabo";
		String dbPassword = "hukabo";
				
		try {
			
			Class.forName(dbDriver);
			Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
			PreparedStatement st = con.prepareStatement(sql);
			
			st.setInt(1, id);
			
			ResultSet rs = st.executeQuery();
			
			if(rs.next()) {
				int id_ = rs.getInt("id");
				String title = rs.getString("title");
				String writerId = rs.getString("writer_id");
				String content = rs.getString("content");
				Date regdate = rs.getDate("regdate");
				int hit = rs.getInt("hit");
				String files = rs.getString("files");
				
				notice = new Notice(id, title, writerId, regdate, hit, files,content);
			}
			
		    rs.close();
		    st.close();
		    con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return notice;
	}
	
	public Notice getPrevNotice(int id) {
		
		String sql = "SELECT id FROM notice WHERE regdate < (SELECT regdate FROM notice WHERE id=?) ORDER BY id DESC LIMIT 1;";
		
		Notice notice = null;
		
		String dbDriver = "org.postgresql.Driver";
		String dbUrl = "jdbc:postgresql://localhost:5432/jdbc";
		String dbUser = "hukabo";
		String dbPassword = "hukabo";
				
		try {
			
			Class.forName(dbDriver);
			Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
			PreparedStatement st = con.prepareStatement(sql);
			
			st.setInt(1, id);
			
			ResultSet rs = st.executeQuery();
			
			if(rs.next()) {
				int id_ = rs.getInt("id");
				String title = rs.getString("title");
				String writerId = rs.getString("writer_id");
				String content = rs.getString("content");
				Date regdate = rs.getDate("regdate");
				int hit = rs.getInt("hit");
				String files = rs.getString("files");
				
				notice = new Notice(id, title, writerId, regdate, hit, files,content);
			}
			
		    rs.close();
		    st.close();
		    con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return notice;
	}
}

	