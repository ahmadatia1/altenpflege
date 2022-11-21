package altenpfleger.sample.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import altenpfleger.sample.dbservices.DBManager;

public class Adresse {
	
	
	private SimpleStringProperty  id_adresse, id_patient, strasse, hausnummer, ort, plz;

	
	
	
	public Adresse(String id_adresse, String id_patient, String strasse, String hausnummer, String ort, String plz)
	{
		this.id_adresse = new SimpleStringProperty(id_adresse);
		this.id_patient = new SimpleStringProperty(id_patient);
		
		this.strasse = new SimpleStringProperty(strasse);
		this.hausnummer = new SimpleStringProperty(hausnummer);
		this.ort = new SimpleStringProperty(ort);
		this.plz = new SimpleStringProperty(plz);
		
		
	}






	



	public String getId_adresse() {
		return id_adresse.get();
	}










	public void setId_adresse(String id_adresse) {
		this.id_adresse.set(id_adresse);
	}







	 


	public String getId_patient() {
		return id_patient.get();
	}










	public void setId_patient(String id_patient) {
		this.id_patient.set(id_patient);
	}








	

	public String getStrasse() {
		return strasse.get();
	}










	public void setStrasse(String anrede) {
		this.strasse.set(anrede);
	}








	

	public String getHausnummer() {
		return hausnummer.get();
	}









	
	public void setHausnummer(String hausnummer) {
		this.hausnummer.set(hausnummer);
	}









	 
	public String getOrt() {
		return ort.get();
	}










	public void setOrt(String ort) {
		this.ort.set(ort);
	}










	public String getPlz() {
		return plz.get();
	}










	public void setPlz(String plz) {
		this.plz.set(plz);
	}





















	public static ArrayList<Adresse> getAlleDatenAdresse(String querey) throws SQLException
	{
		ArrayList<Adresse> data = new ArrayList<Adresse>();
		
		try
		{
			PreparedStatement stmt = DBManager.con.prepareStatement(querey);
			ResultSet rs = stmt.executeQuery();
			while(rs.next())
			{

				
				data.add(new Adresse(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));
			}
			
		}	
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return data;
	}










	

}
