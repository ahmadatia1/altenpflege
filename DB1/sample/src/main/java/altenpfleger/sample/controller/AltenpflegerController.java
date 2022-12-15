package altenpfleger.sample.controller;
/*
Put header here


 */

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

import altenpfleger.sample.dbservices.DBManager;
import altenpfleger.sample.model.Altenpfleger;
import altenpfleger.sample.model.Dienst;
import altenpfleger.sample.model.Patient;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener.Change;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.util.*;

/**
 *  Klasse AltenpflegerController kontrolliert der View bzw. Altenpfleger.fxml Datei
 *  Events werden augeführt, Textfeldern werden bearbeiten und neue Werte gespeichert, etc..
 *  
 * @author Ahmad, Akram, Nour 
 *
 */
public class AltenpflegerController implements Initializable {
	

	private ArrayList<Altenpfleger> a;
	
	@FXML private AnchorPane anchor;
	@FXML private Button einfuegenButton;
	@FXML private Button speichernButton;
  
	
    
    @FXML private TableView<Altenpfleger> tabelleData;
    @FXML private TableColumn<Altenpfleger, String> id_altenpfleger;
    @FXML private TableColumn<Altenpfleger, String> anrede;
    @FXML private TableColumn<Altenpfleger, String> nachname;
    @FXML private TableColumn<Altenpfleger, String> vorname;
    @FXML private TableColumn<Altenpfleger, String> mail;
    @FXML private TableColumn<Altenpfleger, String> tel;

    @FXML private TableColumn<Altenpfleger, String> geburtsdatum;
   
 
    /**
     *  die Methode editButton wird aufgerufen, wenn der Edit icon geklickt wird
     *  wird EditEvent für die Textfeldern in der Tabelle angehört und bei neuen Wertern sowohl in db als auch in der List Altenpfleger aktualisiert 
     *  
     * @param event verantwotlich für Anhörung einer Aktion
     * @author Ahmad, Akram, Nour 
     *
     */
   
    @FXML
    private void editButton(ActionEvent event) throws IOException {
    	
    	
    	
    	// anrede Listener für Aktualisieren 
    	anrede.setEditable(true);
    	anrede.setCellFactory(TextFieldTableCell.forTableColumn());
    	anrede.setCellValueFactory(new PropertyValueFactory<Altenpfleger, String>("anrede"));
    	
    	anrede.setOnEditCommit(new EventHandler<CellEditEvent<Altenpfleger, String>>(){

			@Override
			public void handle(CellEditEvent<Altenpfleger, String> event) {
				
				Altenpfleger a = event.getRowValue();
				Alert alert = new Alert(AlertType.CONFIRMATION);
		    	alert.setTitle("Update");
		    	alert.setHeaderText("anrede ändern und aktualisieren");
		    	alert.setContentText("Bitte bestätigen?");

		    	Optional<ButtonType> result = alert.showAndWait();
		    	
		    	
		    	if (result.get() == ButtonType.OK){
		    		
		    		try {
		    			String querey = "update Altenpfleger"
		    					+ " Set anrede='" + event.getNewValue() + "'" 
		    					+ " where id_altenpfleger='" + event.getRowValue().getId_altenpfleger()+"'";
		    			DBManager.sendQuery(querey);
						a.setAnrede(event.getNewValue());
						anrede.setEditable(false);
					} catch (SQLException e) {
						
						String err = DBManager.printSQLException(e);
						alert = new Alert(AlertType.ERROR);
						alert.setContentText(err);
						result = alert.showAndWait();
						a.setAnrede(event.getOldValue());
						anrede.setEditable(true);
					}
		    		
					
					
					tabelleData.refresh();
		    	} 
		    	else
		    	{
		    		anrede.setEditable(true);
		    		a.setAnrede(event.getOldValue());
		    		tabelleData.refresh();
		    	}
				
				
				
				
			}
			
    		
    	});
    	
    	
    	// Nachname Listener für Aktualisieren 
    	nachname.setEditable(true);
    	nachname.setCellFactory(TextFieldTableCell.forTableColumn());
    	nachname.setCellValueFactory(new PropertyValueFactory<Altenpfleger, String>("nachname"));
    	
    	nachname.setOnEditCommit(new EventHandler<CellEditEvent<Altenpfleger, String>>(){

			@Override
			public void handle(CellEditEvent<Altenpfleger, String> event) {
				
				Altenpfleger a = event.getRowValue();
				
				Alert alert = new Alert(AlertType.CONFIRMATION);
		    	alert.setTitle("Update");
		    	alert.setHeaderText("Nachname ändern und aktualisieren");
		    	alert.setContentText("Bitte bstätigen?");

		    	Optional<ButtonType> result = alert.showAndWait();
		    	if (result.get() == ButtonType.OK){
		    		
		    		try {
		    			String querey = "update Altenpfleger"
		    					+ " Set nachname='" + event.getNewValue() + "'" 
		    					+ " where id_altenpfleger='" + event.getRowValue().getId_altenpfleger()+"'";
		    			DBManager.sendQuery(querey);
						a.setNachname(event.getNewValue());
						nachname.setEditable(false);
					} 
		    		catch (SQLException e) 
		    		{
						String err = DBManager.printSQLException(e);
						alert = new Alert(AlertType.ERROR);
						alert.setContentText(err);
						result = alert.showAndWait();
						a.setNachname(event.getOldValue());
						nachname.setEditable(true);
					}
		    		
					tabelleData.refresh();
					
		    	}
		    	else
		    	{
		    		a.setNachname(event.getOldValue());
		    		nachname.setEditable(true);
		    		tabelleData.refresh();
		    		
		    	}
		    	
				
				
				
			}
    		
    	});
    	
    	
    	// vorname Listener für Aktualisieren 
    	vorname.setEditable(true);
    	vorname.setCellFactory(TextFieldTableCell.forTableColumn());
    	vorname.setCellValueFactory(new PropertyValueFactory<Altenpfleger, String>("vorname"));
    	
    	vorname.setOnEditCommit(new EventHandler<CellEditEvent<Altenpfleger, String>>(){

			@Override
			public void handle(CellEditEvent<Altenpfleger, String> event) {
				
				Altenpfleger a = event.getRowValue();
				
				Alert alert = new Alert(AlertType.CONFIRMATION);
		    	alert.setTitle("Update");
		    	alert.setHeaderText("vorname ändern und aktualisieren");
		    	alert.setContentText("Bitte bstätigen?");

		    	Optional<ButtonType> result = alert.showAndWait();
		    	if (result.get() == ButtonType.OK){
		    		
		    		try {
		    			String querey = "update Altenpfleger"
		    					+ " Set vorname='" + event.getNewValue() + "'" 
		    					+ " where id_altenpfleger='" + event.getRowValue().getId_altenpfleger()+"'";
		    			DBManager.sendQuery(querey);
		    			a.setVorname(event.getNewValue());
		    			vorname.setEditable(false);
					} catch (SQLException e) 
		    		{
						String err = DBManager.printSQLException(e);
						alert = new Alert(AlertType.ERROR);
						alert.setContentText(err);
						result = alert.showAndWait();
						a.setVorname(event.getOldValue());
						vorname.setEditable(true);

					}
		    		
					
					
					tabelleData.refresh();
		    	} 
		    	else
		    	{
		    		a.setVorname(event.getOldValue());
		    		vorname.setEditable(true);
		    		tabelleData.refresh();
		    	}
		    	
				
				
				
			}
    		
    	});
    	
    	
    	// KVnummer Listener für Aktualisieren 
    	geburtsdatum.setEditable(true);
    	geburtsdatum.setCellFactory(TextFieldTableCell.forTableColumn());
    	geburtsdatum.setCellValueFactory(new PropertyValueFactory<Altenpfleger, String>("geburtsdatum"));
    	
    	geburtsdatum.setOnEditCommit(new EventHandler<CellEditEvent<Altenpfleger, String>>(){

			@Override
			public void handle(CellEditEvent<Altenpfleger, String> event) {
				
				Altenpfleger a = event.getRowValue();
				
				Alert alert = new Alert(AlertType.CONFIRMATION);
		    	alert.setTitle("Update");
		    	alert.setHeaderText("setMail ändern und aktualisieren");
		    	alert.setContentText("Bitte bstätigen?");

		    	Optional<ButtonType> result = alert.showAndWait();
		    	if (result.get() == ButtonType.OK){
		    		
		    		try {
		    			String querey = "update Altenpfleger"
		    					+ " Set geburtsdatum='" + event.getNewValue() + "'" 
		    					+ " where id_altenpfleger='" + event.getRowValue().getId_altenpfleger() +"'";
		    			DBManager.sendQuery(querey);
		    			a.setGeburtsdatum(event.getNewValue());
		    			geburtsdatum.setEditable(false);
					} catch (SQLException e) {
						String err = DBManager.printSQLException(e);
						alert = new Alert(AlertType.ERROR);
						alert.setContentText(err);
						result = alert.showAndWait();
						a.setGeburtsdatum(event.getOldValue());
						geburtsdatum.setEditable(true);

					}
		    		
				
					
					tabelleData.refresh();
		    	}
		    	else
		    	{
		    		geburtsdatum.setEditable(true);
		    		a.setGeburtsdatum(event.getOldValue());
		    		tabelleData.refresh();
		    	}
				
				
				
			}
    		
    	});
    	
    	
    	// Geburtsdatum Listener für Aktualisieren 
    	tel.setEditable(true);
    	tel.setCellFactory(TextFieldTableCell.forTableColumn());
    	tel.setCellValueFactory(new PropertyValueFactory<Altenpfleger, String>("tel"));
    	
    	tel.setOnEditCommit(new EventHandler<CellEditEvent<Altenpfleger, String>>(){

			@Override
			public void handle(CellEditEvent<Altenpfleger, String> event) {
				
				Altenpfleger a = event.getRowValue();
				
				Alert alert = new Alert(AlertType.CONFIRMATION);
		    	alert.setTitle("Update");
		    	alert.setHeaderText("tel ändern und aktualisieren");
		    	alert.setContentText("Bitte bstätigen?");

		    	Optional<ButtonType> result = alert.showAndWait();
		    	if (result.get() == ButtonType.OK){
		    		
		    		try {
		    			String querey = "update Altenpfleger"
		    					+ " Set tel='" + event.getNewValue() + "'" 
		    					+ " where id_arzt='" + event.getRowValue().getId_altenpfleger()+"'";
		    			DBManager.sendQuery(querey);
		    			a.setTel(event.getNewValue());
		    			tel.setEditable(false);
					} 
		    		catch (SQLException e) 
		    		{
						String err = DBManager.printSQLException(e);
						alert = new Alert(AlertType.ERROR);
						alert.setContentText(err);
						result = alert.showAndWait();
						a.setTel(event.getOldValue());
						tel.setEditable(true);

					}
		    		
			
					
					tabelleData.refresh();
		    	}
		    	else
		    	{
		    		tel.setEditable(true);
		    		a.setTel(event.getOldValue());
		    		tabelleData.refresh();
		    	}
				
				
				
			}
    		
    	});
    	
    	
     	// KVnummer Listener für Aktualisieren 
    	mail.setEditable(true);
    	mail.setCellFactory(TextFieldTableCell.forTableColumn());
    	mail.setCellValueFactory(new PropertyValueFactory<Altenpfleger, String>("mail"));
    	
    	mail.setOnEditCommit(new EventHandler<CellEditEvent<Altenpfleger, String>>(){

			@Override
			public void handle(CellEditEvent<Altenpfleger, String> event) {
				
				Altenpfleger a = event.getRowValue();
				
				Alert alert = new Alert(AlertType.CONFIRMATION);
		    	alert.setTitle("Update");
		    	alert.setHeaderText("setMail ändern und aktualisieren");
		    	alert.setContentText("Bitte bstätigen?");

		    	Optional<ButtonType> result = alert.showAndWait();
		    	if (result.get() == ButtonType.OK){
		    		
		    		try {
		    			String querey = "update Altenpfleger"
		    					+ " Set mail='" + event.getNewValue() + "'" 
		    					+ " where id_altenpfleger='" + event.getRowValue().getId_altenpfleger() +"'";
		    			DBManager.sendQuery(querey);
		    			a.setMail(event.getNewValue());
		    			mail.setEditable(false);
					} catch (SQLException e) {
						String err = DBManager.printSQLException(e);
						alert = new Alert(AlertType.ERROR);
						alert.setContentText(err);
						result = alert.showAndWait();
						a.setMail(event.getOldValue());
						mail.setEditable(true);

					}
		    		
					
					
					tabelleData.refresh();
		    	}
		    	else
		    	{
		    		mail.setEditable(true);
		    		a.setMail(event.getOldValue());
		    		tabelleData.refresh();
		    	}
				
				
				
			}
    		
    	});
    	
    	
    	
    	
   	
    }
    
    /**
     *  die Methode deleteButton wird aufgerufen, wenn der delete icon geklickt wird
     *  wird DeleteEvent angehört, um ein Altenpfleger in db und auch in der Liste zulöschen
     *  
     * @param event verantwotlich für Anhörung einer Aktion
     * @author Ahmad, Akram, Nour 
     *
     */
    @FXML
    private void deleteButton(ActionEvent event) throws IOException
    {
    	
    	
    	Altenpfleger a = tabelleData.getSelectionModel().getSelectedItem();
    	
    	
			Alert alert = new Alert(AlertType.CONFIRMATION);
	    	alert.setTitle("Altenpfleger löschen");
	    	alert.setContentText("Wollen Sie es sicher löschen?");

	    	Optional<ButtonType> result = alert.showAndWait();
	    	if (result.get() == ButtonType.OK){
	    		String querey = "delete from Altenpfleger where id_altenpfleger=" + a.getId_altenpfleger();
	    		
	    		try {
	    			DBManager.sendQuery(querey);
				} 
	    		catch (SQLException e) 
	    		{
	    			String err = DBManager.printSQLException(e);
					alert = new Alert(AlertType.ERROR);
					alert.setContentText(err);
					result = alert.showAndWait();
				}
	    		
	    		boolean status = tabelleData.getItems().remove(a);
	    		System.out.print(status);
	    	    
	    	
			
	    	}
    	
    	
    	
    }
    
    
    
    /**
     *  die Methode einfuegenEvent wird aufgerufen, wenn der plus icon geklickt wird
     *  wird einfuegenEvent für die Textfeldern in der Tabelle angehört. 
     *  zuerst wird default werte in der Textfeldern eingesetzt und nach Berarbeitung werden die Werte in der Liste Altenpfleger und in der Tabelle gespeichert
     *  
     * @param event verantwotlich für Anhörung einer Aktion
     * @author Ahmad, Akram, Nour 
     *
     */
    @FXML
    private void einfuegenEvent(ActionEvent event) throws IOException
    {
    	
    	altenpfleger.add(new Altenpfleger("hier ändern", "hier ändern", "hier ändern", "hier ändern", "hier ändern", "hier ändern", "hier ändern"));
    	
    	// anrede Listener für Aktualisieren 
    	id_altenpfleger.setEditable(true);
    	id_altenpfleger.setCellFactory(TextFieldTableCell.forTableColumn());
    	id_altenpfleger.setCellValueFactory(new PropertyValueFactory<Altenpfleger, String>("id_altenpfleger"));
    	
    	id_altenpfleger.setOnEditCommit(new EventHandler<CellEditEvent<Altenpfleger, String>>(){

			@Override
			public void handle(CellEditEvent<Altenpfleger, String> event) {
				
				Altenpfleger a = event.getRowValue();
				a.setId_altenpfleger(event.getNewValue());	
				//id_arzt.setEditable(false);
				tabelleData.refresh();
				

			}
			
    		
    	});
    	
    	
    	// anrede Listener für Aktualisieren 
    	anrede.setEditable(true);
    	anrede.setCellFactory(TextFieldTableCell.forTableColumn());
    	anrede.setCellValueFactory(new PropertyValueFactory<Altenpfleger, String>("anrede"));
    	
    	anrede.setOnEditCommit(new EventHandler<CellEditEvent<Altenpfleger, String>>(){

			@Override
			public void handle(CellEditEvent<Altenpfleger, String> event) {
				
				Altenpfleger a = event.getRowValue();
				a.setAnrede(event.getNewValue());	
				
				tabelleData.refresh();
				
				
			}
			
    		
    	});
    	
    	// anrede Listener für Aktualisieren 
    	nachname.setEditable(true);
    	nachname.setCellFactory(TextFieldTableCell.forTableColumn());
    	nachname.setCellValueFactory(new PropertyValueFactory<Altenpfleger, String>("nachname"));
    	
    	nachname.setOnEditCommit(new EventHandler<CellEditEvent<Altenpfleger, String>>(){

			@Override
			public void handle(CellEditEvent<Altenpfleger, String> event) {
				
				Altenpfleger a = event.getRowValue();
				a.setNachname(event.getNewValue());	
				//nachname.setEditable(false);
				tabelleData.refresh();
				

			}
			
    		
    	});
    	
    	// anrede Listener für Aktualisieren 
    	vorname.setEditable(true);
    	vorname.setCellFactory(TextFieldTableCell.forTableColumn());
    	vorname.setCellValueFactory(new PropertyValueFactory<Altenpfleger, String>("vorname"));
    	
    	vorname.setOnEditCommit(new EventHandler<CellEditEvent<Altenpfleger, String>>(){

			@Override
			public void handle(CellEditEvent<Altenpfleger, String> event) {
				
				Altenpfleger a = event.getRowValue();
				a.setVorname(event.getNewValue());	
				//vorname.setEditable(false);
				tabelleData.refresh();
				

			}
			
    		
    	});
    	
    	// anrede Listener für Aktualisieren 
    	mail.setEditable(true);
    	mail.setCellFactory(TextFieldTableCell.forTableColumn());
    	mail.setCellValueFactory(new PropertyValueFactory<Altenpfleger, String>("mail"));
    	
    	mail.setOnEditCommit(new EventHandler<CellEditEvent<Altenpfleger, String>>(){

			@Override
			public void handle(CellEditEvent<Altenpfleger, String> event) {
				
				Altenpfleger a = event.getRowValue();
				a.setMail(event.getNewValue());;
				//mail.setEditable(false);
				tabelleData.refresh();
				

			}
			
    		
    	});
    	
    	// anrede Listener für Aktualisieren 
    	tel.setEditable(true);
    	tel.setCellFactory(TextFieldTableCell.forTableColumn());
    	tel.setCellValueFactory(new PropertyValueFactory<Altenpfleger, String>("tel"));
    	
    	tel.setOnEditCommit(new EventHandler<CellEditEvent<Altenpfleger, String>>(){

			@Override
			public void handle(CellEditEvent<Altenpfleger, String> event) {
				
				Altenpfleger a = event.getRowValue();
				a.setTel(event.getNewValue());
				//tel.setEditable(false);
				tabelleData.refresh();
				

			}
			
    		
    	});
    	
    	
    	
    	// anrede Listener für Aktualisieren 
    	geburtsdatum.setEditable(true);
    	geburtsdatum.setCellFactory(TextFieldTableCell.forTableColumn());
    	geburtsdatum.setCellValueFactory(new PropertyValueFactory<Altenpfleger, String>("geburtsdatum"));
    	
    	geburtsdatum.setOnEditCommit(new EventHandler<CellEditEvent<Altenpfleger, String>>(){

			@Override
			public void handle(CellEditEvent<Altenpfleger, String> event) {
				
				Altenpfleger a = event.getRowValue();
				a.setGeburtsdatum(event.getNewValue());
				//spezialtaet.setEditable(false);
				tabelleData.refresh();
				

			}
			
    		
    	});
    	
    	
    	this.speichernButton.setVisible(true);
    	this.einfuegenButton.setVisible(false);	
    	
    }
    
    /**
     *  die Methode einfuegenEvent wird aufgerufen, wenn der check icon geklickt wird
     *  wird ein SQL_Abfrage für Insert der Daten in der Tabelle Altenpfleger in DB erstellt 
     *  und bei Aufruf der Methode sendQuery von Klasse DBManager gesendet
     *  
     * @param event verantwotlich für Anhörung einer Aktion
     * @author Ahmad, Akram, Nour 
     *
     */
    
    @FXML
    public void speichernEvent(ActionEvent event)
    {
    	
    	Altenpfleger a = altenpfleger.get(altenpfleger.size()-1);
    	
    	
    	
		String queryString = "INSERT INTO altenpfleger (id_altenpfleger, anrede, nachname, vorname, geburtsdatum, mail, tel) "
		+ " VALUES ("+ a.getId_altenpfleger()+ ",'" + a.getAnrede() + "','" + a.getNachname() + "','" + a.getVorname() + "','" + a.getGeburtsdatum()  + "','" + a.getMail() + "', " + a.getTel() + ")";
		System.out.print(queryString);
    	try {
    		
    		DBManager.sendQuery(queryString);
			
			
		} catch (SQLException e) {
			 Alert alert = new Alert(AlertType.ERROR);
    		 alert.setContentText(DBManager.printSQLException(e));
    		 Optional<ButtonType> result = alert.showAndWait();
    		 altenpfleger.remove(a);
    		 
	    	 tabelleData.refresh();
	    	 
		}	
    		

    	id_altenpfleger.setEditable(false);
    	anrede.setEditable(false);
    	nachname.setEditable(false);
    	vorname.setEditable(false);
    	mail.setEditable(false);
    	tel.setEditable(false);
		geburtsdatum.setEditable(false);
		
    	this.speichernButton.setVisible(false);
    	this.einfuegenButton.setVisible(true);
    	
    	
    }
    
    /**
     *  in der Methode initialize wird eine SQL_Abfrage für Holen der Adresse Daten 
     *  anhand der Methode getAlleDatenAltenpflegerin der Klasse Altenpfleger in der Altenpfleger Liste gespeichert.
     *  Auch werde all Daten von Altenpfleger Liste werden in der Liste vom Typ ObservableList gespeichert,
     *  die es Zuhörern ermöglicht, Änderungen zu verfolgen, wenn sie auftreten.
     * @param url ist nicht benutzt
     * @param rb ist nicht benutzt
     * @author Ahmad, Akram, Nour 
     *
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	
    	
    	
    	String queryString = "select id_altenpfleger, anrede, nachname, vorname, TO_CHAR(geburtsdatum,'DD.MM.YYYY'), mail, tel From altenpfleger";
    	
		a = Altenpfleger.getAlleDatenAltenpfleger(queryString);
			
			
		
		
    	altenpfleger.addAll(a);
    	
    	id_altenpfleger.setCellValueFactory(new PropertyValueFactory<>("id_altenpfleger"));
    	anrede.setCellValueFactory(new PropertyValueFactory<>("anrede"));
		nachname.setCellValueFactory(new PropertyValueFactory<>("nachname"));
		vorname.setCellValueFactory(new PropertyValueFactory<>("vorname"));
		mail.setCellValueFactory(new PropertyValueFactory<>("mail"));
		tel.setCellValueFactory(new PropertyValueFactory<>("tel"));
		geburtsdatum.setCellValueFactory(new PropertyValueFactory<>("geburtsdatum"));
		
		
	
		tabelleData.setItems(altenpfleger);
		
    	
    }    
    
   private ObservableList<Altenpfleger> altenpfleger = FXCollections.observableArrayList();
   
   
    
    
}
