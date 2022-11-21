package altenpfleger.sample.dbservices;
import java.util.*;
import java.sql.*;


public class DBManager {
	
	
private static final String url ="jdbc:oracle:thin:@172.22.112.100:1521:fbpool";
public static boolean connectionStatus = false;

public static Connection con;


public static void connectDB(String username, String password)
{
	try
	{
		con = DriverManager.getConnection(url, username, password);
		Class.forName("oracle.jdbc.driver.OracleDriver");
		connectionStatus = true;
		System.out.print("Connceted");
		
	}	
	catch(Exception e)
	{
		connectionStatus = false;
		e.printStackTrace();
	}

}

public static void printSQLException(SQLException ex) {
	for (Throwable e : ex) {
		if (e instanceof SQLException) {
			System. out.println ("\n---SQLException---\n");
			SQLException sqlex = (SQLException) e;
			System.err.println("Message: " +sqlex.getMessage());
			
			System.err.println ("SQLState: "+sqlex.getSQLState ());
			System.err.println ("Error Code: " +sqlex.getErrorCode ());
			
			System. out.println ("");
		}
		
		if (e instanceof SQLWarning) {
			System. out.println ("\n---SQLWarning---\n");
			SQLException sqlw = (SQLWarning) e;
			System.err.println("Message: " +sqlw.getMessage());
			
			System. out.println ("SQLState: " + sqlw.getSQLState ());
			System.out.print ("Vendor error code: " + sqlw.getErrorCode ());
			
			System. out.println("");
			//warning = warning.getNextWarning();
	
		}
	
	}
	
	ex.printStackTrace(System.err);
}

public static void sendQuery(String querey) throws SQLException
{
	Statement stmt = DBManager.con.createStatement();
	ResultSet rs = stmt.executeQuery(querey);
	
	System.out.println("Number of records: " + rs.getWarnings());
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
