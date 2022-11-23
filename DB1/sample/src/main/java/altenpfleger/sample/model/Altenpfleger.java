package altenpfleger.sample.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import altenpfleger.sample.dbservices.DBManager;

/**
 *  Klasse Altenpfleger ist Model
 *  alle Attributen werden gekapselt
 * 
 * @author Ahmad, Akram, Nour 
 *
 */
public class Altenpfleger {
	
	
	private SimpleStringProperty  id_altenpfleger, anrede, nachname, vorname, geburtsdatum, mail, tel;

	
	
	
	public Altenpfleger(String id_altenpfleger, String anrede, String nachname, String vorname, String geburtsdatum, String mail, String tel)
	{
		this.id_altenpfleger = new SimpleStringProperty(id_altenpfleger);
		this.anrede = new SimpleStringProperty(anrede);
		this.nachname = new SimpleStringProperty(nachname);
		this.vorname = new SimpleStringProperty(vorname);
		this.geburtsdatum = new SimpleStringProperty(geburtsdatum);
		this.mail = new SimpleStringProperty(mail);
		this.tel = new SimpleStringProperty(tel);

		
	}






	






















	public String getId_altenpfleger() {
		return id_altenpfleger.get();
	}










	public void setId_altenpfleger(String id_altenpfleger) {
		this.id_altenpfleger.set(id_altenpfleger);
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
		this.nachname.set(nachname);
	}










	public String getVorname() {
		return vorname.get();
	}










	public void setVorname(String vorname) {
		this.vorname.set(vorname);
	}







	public String getGeburtsdatum() {
		return geburtsdatum.get();
	}



	public void setGeburtsdatum(String geburtsdatum) {
		this.geburtsdatum.set(geburtsdatum);
	}



	public String getMail() {
		return mail.get();
	}










	public void setMail(String mail) {
		this.mail.set(mail);
	}










	public String getTel() {
		return tel.get();
	}










	public void setTel(String tel) {
		this.tel.set(tel);
	}












	/**
	 *  Klasse Altenpfleger ist Model
	 *  alle Attributen werden gekapselt
	 * 
	 * @param ist für querey SQL_Abfrage
	 * @return git eine Liste von den erhaltenen Daten 
	 */
	public static ArrayList<Altenpfleger> getAlleDatenAltenpfleger(String querey) 
	{
		ArrayList<Altenpfleger> data = new ArrayList<Altenpfleger>();
		
		try
		{
			PreparedStatement stmt = DBManager.con.prepareStatement(querey);
			ResultSet rs = stmt.executeQuery();
			while(rs.next())
			{

				
				data.add(new Altenpfleger(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)));
			}
			
		}	
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return data;
	}




















	

}
