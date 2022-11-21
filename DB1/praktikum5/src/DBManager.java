import java.sql.Connection;
import java.util.ArrayList;
import java.sql.*;


public class DBManager {
	
	
private static final String url ="jdbc:oracle:thin:@172.22.112.100:1521:fbpool";
private static final String username = "C##FBPOOL16";
private static final String password = "oracle";


public static void connect()
{
	String queryString = 
			"select k.idkunde, k.nachname, k.vorname "
			+ "From kunde k, artikel a, warenkorb w "
			+ "where k.idkunde = w.idkunde "
			+ "and a.idartikel = w.idartikel "
			+ "and k.anrede =?";
	queryString = 
			"select k.idkunde, k.nachname, k.vorname "
			+ "From kunde k, artikel a, warenkorb w "
			+ "where k.idkunde = w.idkunde "
			+ "and a.idartikel = w.idartikel "
			+ "and k.anrede ='Herr'";
	
	
	
	try(Connection con = DriverManager.getConnection(url, username, password))
	{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		PreparedStatement stmt = con.prepareStatement(queryString);
		//stmt.setString(1,"Herr");
		ResultSet rs = stmt.executeQuery();
		while(rs.next())
		{
			
			System.out.println("Kundennummer: " + rs.getString(1)+ " " + "Nachname: " + rs.getString(2) + "\t Vorname: " + rs.getString(3));
		}
		
	}	
	catch(Exception e)
	{
		e.printStackTrace();
	}
	
}



}
