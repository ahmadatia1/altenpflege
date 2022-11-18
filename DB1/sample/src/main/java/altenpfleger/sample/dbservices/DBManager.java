package altenpfleger.sample.dbservices;
import java.util.*;
import java.sql.*;


public class DBManager {
	
	
private static final String url ="jdbc:oracle:thin:@172.22.112.100:1521:fbpool";
private static final String username = "C##FBPOOL16";
private static final String password = "oracle";

public static Connection con;


public static void connectDB()
{
	try
	{
		con = DriverManager.getConnection(url, username, password);
		Class.forName("oracle.jdbc.driver.OracleDriver");
		System.out.print("Connceted");
		
	}	
	catch(Exception e)
	{
		e.printStackTrace();
	}

}


public static void closeDB() throws SQLException
{
	try
	{
		con.close();
		System.out.print("Disconnected");
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	
}

}
