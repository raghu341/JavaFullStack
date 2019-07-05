package com.example.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("account")
public class Technologies {

    // JDBC URL, username and password of MySQL server
    private static final String url = "jdbc:mysql://localhost:3306/test";
    private static final String user = "root";
    private static final String password = "root";

    // JDBC variables for opening and managing connection
    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;
    @RequestMapping(value="account/user", method = RequestMethod.GET)
	public String index1(ModelMap modelMap1) {
		return "account/user";
	}
   public static void main(String args[]) {
        String query = "select * from Technologies";

        try {
            con = DriverManager.getConnection(url, user, password);

            
            stmt = con.createStatement();

            
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                
            	System.out.println("Id " + rs.getInt("id") + " TrainerName" + rs.getString("trainername")+rs.getString("technology"));
            }

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
        	
            try { con.close(); } catch(SQLException se) { }
            try { stmt.close(); } catch(SQLException se) {  }
            try { rs.close(); } catch(SQLException se) {  }
        }
    }

}
