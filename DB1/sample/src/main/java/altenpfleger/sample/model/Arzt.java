package altenpfleger.sample.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import altenpfleger.sample.dbservices.DBManager;

public class Arzt {
	
	
	private SimpleStringProperty  id_arzt, anrede, nachname, vorname, mail, tel, spezialtaet, vertretung_id_arzt;

	
	
	
	public Arzt(String id_arzt, String anrede, String nachname, String vorname, String mail, String tel, String spezialtaet, String vertretung_id_arzt)
	{
		this.id_arzt = new SimpleStringProperty(id_arzt);
		this.anrede = new SimpleStringProperty(anrede);
		
		this.nachname = new SimpleStringProperty(nachname);
		this.vorname = new SimpleStringProperty(vorname);
		this.mail = new SimpleStringProperty(mail);
		this.tel = new SimpleStringProperty(tel);
		this.spezialtaet = new SimpleStringProperty(spezialtaet);
		this.vertretung_id_arzt = new SimpleStringProperty(vertretung_id_arzt);
		
	}






	



	public String getVertretung_id_arzt() {
		return vertretung_id_arzt.get();
	}










	public void setVertretung_id_arzt(String vertretung_id_artz) {
		this.vertretung_id_arzt.set(vertretung_id_artz);
	}










	public String getId_arzt() {
		return id_arzt.get();
	}










	public void setId_arzt(String id_artz) {
		this.id_arzt.set(id_artz);
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










	public String getSpezialtaet() {
		return spezialtaet.get();
	}



	public void setSpezialtaet(String spezialtaet) {
		this.spezialtaet.set(spezialtaet);
	}



	public static ArrayList<Arzt> getAlleDatenArzt(String querey) throws SQLException
	{
		ArrayList<Arzt> data = new ArrayList<Arzt>();
		
		try
		{
			PreparedStatement stmt = DBManager.con.prepareStatement(querey);
			ResultSet rs = stmt.executeQuery();
			while(rs.next())
			{

				
				data.add(new Arzt(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)));
			}
			
		}	
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return data;
	}




















	

}
