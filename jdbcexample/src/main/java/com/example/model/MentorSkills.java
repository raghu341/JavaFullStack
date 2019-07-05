package com.example.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class MentorSkills {
	private static final String url = "jdbc:mysql://localhost:3306/test";
    private static final String user = "root";
    private static final String password = "root";

    
    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;
    @RequestMapping(value="account/user", method = RequestMethod.GET)
	public String index1(ModelMap modelMap1) {
		return "account/user";
	}
    public void index2() {
    String query = "select * from MentorSkills";

    try {
   
        con = DriverManager.getConnection(url, user, password);

        
        stmt = con.createStatement();

        
        rs = stmt.executeQuery(query);

        while (rs.next()) {
            
        	System.out.println("Id " + rs.getInt("id") + " MentorSkills" + rs.getString("mentorname")+rs.getString("skills"));
        }

    } catch (SQLException sqlEx) {
        sqlEx.printStackTrace();
    } finally {
        
        try { con.close(); } catch(SQLException se) {  }
        try { stmt.close(); } catch(SQLException se) { }
        try { rs.close(); } catch(SQLException se) {  }
    }
}
}
