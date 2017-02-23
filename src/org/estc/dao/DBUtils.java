package org.estc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {
	
	
	private final static String URL="jdbc:mysql://localhost/studentdb";
	private final static String USER="root";
	private final static String PASSWD="";
	
	private static Connection connection;
	
	public DBUtils(){}
	
	public static synchronized Connection getConnecttion()throws Exception
	{
		if(connection!=null)
		{
			return connection;
		}
		else
		{
			try {
				Class.forName("com.mysql.jdbc.Driver");

				connection = DriverManager.getConnection(URL, USER, PASSWD);
				return connection;
				
			} catch (SQLException e) {
				throw new Exception(e);
			}
			
		}
		
	}
	public static synchronized void closeConnection()throws Exception
	{
		if(connection!=null)
		{
			try {
				connection.close();
			} catch (SQLException e) {
				throw new Exception(e);
			}
			finally
			{
				connection=null;
			}
		}
	}

}
