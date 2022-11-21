package altenpfleger.sample.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import altenpfleger.sample.dbservices.DBManager;

public class Arzt_has_Patient {
	
	
	private SimpleStringProperty  arzt_id_arzt, patient_id_patient, krankheit, wunschtermin;

	
	
	
	public Arzt_has_Patient(String arzt_id_arzt, String patient_id_patient, String krankheit, String wunschtermin)
	{
		this.arzt_id_arzt = new SimpleStringProperty(arzt_id_arzt);
		this.patient_id_patient = new SimpleStringProperty(patient_id_patient);
		
		this.krankheit = new SimpleStringProperty(krankheit);
		this.wunschtermin = new SimpleStringProperty(wunschtermin);

		
	}






	





	public String getArzt_id_arzt() {
		return arzt_id_arzt.get();
	}












	public void setArzt_id_arzt(String arzt_id_arzt) {
		this.arzt_id_arzt.set(arzt_id_arzt);
	}












	public String getPatient_id_patient() {
		return patient_id_patient.get();
	}












	public void setPatient_id_patient(String patient_id_patient) {
		this.patient_id_patient.set(patient_id_patient);
	}












	public String getKrankheit() {
		return krankheit.get();
	}












	public void setKrankheit(String krankheit) {
		this.krankheit.set(krankheit);
	}












	public String getWunschtermin() {
		return wunschtermin.get();
	}












	public void setWunschtermin(String wunschtermin) {
		this.wunschtermin.set(wunschtermin);
	}












	public static ArrayList<Arzt_has_Patient> getAlleDatenArzt_has_Patient(String querey) throws SQLException
	{
		ArrayList<Arzt_has_Patient> data = new ArrayList<Arzt_has_Patient>();
		
		try
		{
			PreparedStatement stmt = DBManager.con.prepareStatement(querey);
			ResultSet rs = stmt.executeQuery();
			while(rs.next())
			{

				
				data.add(new Arzt_has_Patient(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
			}
			
		}	
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return data;
	}
	
	

}
