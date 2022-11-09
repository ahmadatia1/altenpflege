import java.sql.Connection;
import java.sql.*;


public class DBManager {
	
	
private static final String url ="jdbc:oracle:thin:@172.22.112.100:1521:fbpool";
private static final String username = "C##FBPOOL16";
private static final String password = "oracle";

public static void connect()
{
	String queryString = "select kundennummer, vorname from kunde";
	S
	try(Connection con = DriverManager.getConnection(url, username, password))
	{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		PreparedStatement stmt = con.prepareStatement(queryString);
		ResultSet rs = stmt.executeQuery();
		while(rs.next())
		{
			System.out.print("Kundenummer: " + rs.getString(0) + "Vorname: " + rs.getString(1));
		}
		
	}	
	catch(Exception e)
	{
		e.printStackTrace();
	}
	
}



}
