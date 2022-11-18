package altenpfleger.sample;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import altenpfleger.sample.dbservices.DBManager;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Patient {
	
	
	private SimpleStringProperty  id, nachname, vorname;
	private SimpleIntegerProperty checkbox;
	
	
	
	public Patient(String id, String nachname, String vorname, int checkbox)
	{
		this.id = new SimpleStringProperty(id);
		
		this.nachname = new SimpleStringProperty(nachname);
		this.vorname = new SimpleStringProperty(vorname);
		this.checkbox = new SimpleIntegerProperty(checkbox);
		
	}






	



	public String getId() {
		return id.getValue();
	}










	public void setId(String id) {
		this.id.set(id);;
	}










	public String getNachname() {
		return nachname.get();
	}










	public void setNachname(String nachname) {
		this.nachname.set(nachname);;
	}










	public String getVorname() {
		return vorname.get();
	}










	public void setVorname(String vorname) {
		this.vorname.set(vorname);;
	}










	public int getCheckbox() {
		return checkbox.get();
	}










	public void setCheckbox(int checkbox) {
		this.checkbox.set(checkbox);
	}










	public static ArrayList<Patient> sendQuery(String querey) throws SQLException
	{
		ArrayList<Patient> data = new ArrayList<Patient>();
		
		try
		{
			PreparedStatement stmt = DBManager.con.prepareStatement(querey);
			ResultSet rs = stmt.executeQuery();
			while(rs.next())
			{

				
				data.add(new Patient(rs.getString(1), rs.getString(2), rs.getString(3), 0));
			}
			
		}	
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return data;
	}
	
	public static void updatePatient(String querey) throws SQLException
	{
		PreparedStatement stmt = DBManager.con.prepareStatement(querey);
		ResultSet rs = stmt.executeQuery();
		int del = stmt.executeUpdate();
		System.out.println("Number of updated records: " + del);
	}
	
	public static void removePatient(String querey) throws SQLException
	{
		
		PreparedStatement stmt = DBManager.con.prepareStatement(querey);
		ResultSet rs = stmt.executeQuery();
		int del = stmt.executeUpdate();
		System.out.println("Number of deleted records: " + del);
		
	}
	
	public static void insertPatient()
	{
		
	}






}
