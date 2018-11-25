package com.cst.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class RegisterUserDao extends GenericServlet {

	/**
	 * @author mr Rajesh Pothumani
	 */
	private static final long serialVersionUID = 1L;
	// database connection settings
	private String dbURL = "jdbc:mysql://localhost:3306/cst";
	private String dbUser = "root";
	private String dbPass = "root";
	Connection con;
	PreparedStatement ps;
	@Override
	public void service(ServletRequest req, ServletResponse resp) throws ServletException, IOException {
        
		System.out.println("iam in RegisterUserDao");
		
		String message ="";
		System.out.println("iam in RegisterUserDao1");
		String id = req.getParameter("id");
		int lid=Integer.parseInt(id);
		System.out.println("lid==="+lid);
		String username = req.getParameter("name");
		String password = req.getParameter("pass");

		try {

			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(dbURL, dbUser, dbPass);
			
            
			String sql = "INSERT INTO cst.login (id,username,password) values (?, ?, ?)";
			 ps = con.prepareStatement(sql);
			 ps.setInt(1, lid);
	            ps.setString(2, username);
	            ps.setString(3, password);
	            
            int row = ps.executeUpdate();
            
            if (row > 0) {
            message = "login data saved sucessfully into database";
            }


		} catch (Exception e) {

			e.printStackTrace();
		}
		finally {
			try {
				ps.close();
				con.close();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

}
