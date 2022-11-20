package altenpfleger.sample.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import altenpfleger.sample.dbservices.DBManager;

public class Patient {
	
	
	private SimpleStringProperty  id_patient, anrede, nachname, vorname, KVnummer, Geburtsdatum;

	
	
	
	public Patient(String id_patient, String anrede, String nachname, String vorname, String KVnummer, String Geburtsdatum)
	{
		this.id_patient = new SimpleStringProperty(id_patient);
		this.anrede = new SimpleStringProperty(anrede);
		
		this.nachname = new SimpleStringProperty(nachname);
		this.vorname = new SimpleStringProperty(vorname);
		this.KVnummer = new SimpleStringProperty(KVnummer);
		this.Geburtsdatum = new SimpleStringProperty(Geburtsdatum);
		
	}






	



	public String getId_patient() {
		return id_patient.get();
	}










	public void setId_patient(String id_patient) {
		this.id_patient.set(id_patient); 
	}










	public String getAnrede() {
		return anrede.get();
	}










	public void setAnrede(String anrede) {
		this.anrede.set(anrede); 
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
		this.vorname.set(vorname); 
	}










	public String getKVnummer() {
		return KVnummer.get();
	}










	public void setKVnummer(String kVnummer) {
		KVnummer.set(kVnummer);;
	}










	public String getGeburtsdatum() {
		return Geburtsdatum.get();
	}










	public void setGeburtsdatum(String geburtsdatum) {
		Geburtsdatum.set(geburtsdatum);;
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

				
				data.add(new Patient(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));
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
	
	public static void insertPatient(String querey) throws SQLException
	{
		PreparedStatement stmt = DBManager.con.prepareStatement(querey);
		ResultSet rs = stmt.executeQuery();
		int insert = stmt.executeUpdate();
		System.out.println("Number of inserted records: " + insert);
	}






}
