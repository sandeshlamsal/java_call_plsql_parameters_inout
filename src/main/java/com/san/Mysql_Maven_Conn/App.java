package com.san.Mysql_Maven_Conn;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	try{  
			Class.forName("com.mysql.jdbc.Driver");  //or use com.mysql.jc.jdbc.Driver as new driver
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels","test","test");  
			CallableStatement statement = con.prepareCall("{call get_order_by_cust(?,?,?,?,?)}");
			//add_productlines is a storedprocedure created by user test in database named classicmodels in mysql
			
			statement.setInt(1,141); //1 as ? and IN in proc
			statement.registerOutParameter(2, Types.INTEGER); //@shipped, 22
            statement.registerOutParameter(3, Types.INTEGER);//@canceled
            statement.registerOutParameter(4, Types.INTEGER); //@resolved
            statement.registerOutParameter(4, Types.INTEGER); //@disputed
			statement.execute();
			
			Integer shipped=statement.getInt(2);  //2nd ? as OUT in proc
			System.out.println("Shipped count " + shipped);  
			
			Integer canceled=statement.getInt(3);
			System.out.println("Shipped count " + canceled);  
			
			Integer resolved=statement.getInt(4);
			System.out.println("Shipped count " + resolved);  
			
			Integer disputed=statement.getInt(4);
			System.out.println("Shipped count " + disputed);
			
			System.out.println("Stored procedure called successfully!");  
			statement.close();
			}
			catch(Exception e){ 
				System.out.println(e);  
			}  	
    }
}
