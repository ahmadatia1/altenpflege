package altenpfleger.sample.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import altenpfleger.sample.dbservices.DBManager;

public class Dienst {
	
	
	private SimpleStringProperty  ALTENPFLEGE_ID_ALTENPFLEGE, PATIENT_ID_PATIENT, ARBEITSBEGINN, ARBEITSENDE;

	
	
	
	public Dienst(String ALTENPFLEGE_ID_ALTENPFLEGE, String PATIENT_ID_PATIENT, String ARBEITSBEGINN, String ARBEITSENDE)
	{
		this.ALTENPFLEGE_ID_ALTENPFLEGE = new SimpleStringProperty(ALTENPFLEGE_ID_ALTENPFLEGE);
		this.PATIENT_ID_PATIENT = new SimpleStringProperty(PATIENT_ID_PATIENT);
		
		this.ARBEITSBEGINN = new SimpleStringProperty(ARBEITSBEGINN);
		this.ARBEITSENDE = new SimpleStringProperty(ARBEITSENDE);

		
	}






	






	public String getALTENPFLEGE_ID_ALTENPFLEGE() {
		return ALTENPFLEGE_ID_ALTENPFLEGE.get();
	}













	public void setALTENPFLEGE_ID_ALTENPFLEGE(String aLTENPFLEGE_ID_ALTENPFLEGE) {
		ALTENPFLEGE_ID_ALTENPFLEGE.set(aLTENPFLEGE_ID_ALTENPFLEGE);
	}













	public String getPATIENT_ID_PATIENT() {
		return PATIENT_ID_PATIENT.get();
	}













	public void setPATIENT_ID_PATIENT(String pATIENT_ID_PATIENT) {
		PATIENT_ID_PATIENT.set(pATIENT_ID_PATIENT);
	}













	













	public String getARBEITSENDE() {
		return ARBEITSENDE.get();
	}













	public void setARBEITSENDE(String aRBEITSENDE) {
		ARBEITSENDE.set(aRBEITSENDE);
	}













	public String getARBEITSBEGINN() {
		return ARBEITSBEGINN.get();
	}













	public void setARBEITSBEGINN(String aRBEITSBEGINN) {
		ARBEITSBEGINN.set(aRBEITSBEGINN);
	}













	public static ArrayList<Dienst> sendQuery(String querey) throws SQLException
	{
		ArrayList<Dienst> data = new ArrayList<Dienst>();
		
		try
		{
			PreparedStatement stmt = DBManager.con.prepareStatement(querey);
			ResultSet rs = stmt.executeQuery();
			while(rs.next())
			{

				
				data.add(new Dienst(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
			}
			
		}	
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return data;
	}
	
	public static void updateDienst(String querey) throws SQLException
	{
		PreparedStatement stmt = DBManager.con.prepareStatement(querey);
		ResultSet rs = stmt.executeQuery();
		int del = stmt.executeUpdate();
		System.out.println("Number of updated records: " + del);
	}
	
	public static void removeDienst(String querey) throws SQLException
	{
		
		PreparedStatement stmt = DBManager.con.prepareStatement(querey);
		ResultSet rs = stmt.executeQuery();
		int del = stmt.executeUpdate();
		System.out.println("Number of deleted records: " + del);
		
	}
	
	public static void insertDienst(String querey) throws SQLException
	{
		PreparedStatement stmt = DBManager.con.prepareStatement(querey);
		ResultSet rs = stmt.executeQuery();
		int insert = stmt.executeUpdate();
		System.out.println("Number of inserted records: " + insert);
	}






}
