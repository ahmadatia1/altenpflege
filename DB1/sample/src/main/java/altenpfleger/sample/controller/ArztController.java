package altenpfleger.sample.controller;
/*
Put header here


 */

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

import altenpfleger.sample.dbservices.DBManager;
import altenpfleger.sample.model.Arzt;
import altenpfleger.sample.model.Dienst;
import altenpfleger.sample.model.Patient;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener.Change;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.text.Text;

import java.util.*;

/**
 *  Klasse ArztController kontrolliert der View bzw. ArztController.fxml Datei
 *  Events werden augeführt, Textfeldern werden bearbeiten und neue Werte gespeichert, etc..
 *  
 * @author Ahmad, Akram, Nour 
 *
 */

public class ArztController implements Initializable {
	

	private ArrayList<Arzt> a;
	
	
	@FXML private Button einfuegenButton;
	@FXML private Button speichernButton;
  
	
    
    @FXML private TableView<Arzt> tabelleData;
    @FXML private TableColumn<Arzt, String> id_arzt;
    @FXML private TableColumn<Arzt, String> anrede;
    @FXML private TableColumn<Arzt, String> nachname;
    @FXML private TableColumn<Arzt, String> vorname;
    @FXML private TableColumn<Arzt, String> mail;
    @FXML private TableColumn<Arzt, String> tel;

    @FXML private TableColumn<Arzt, String> spezialtaet;
    @FXML private TableColumn<Arzt, String> vertretung_id_arzt;
 
    
    /**
     *  die Methode editButton wird aufgerufen, wenn der Edit icon geklickt wird
     *  wird EditEvent für die Textfeldern in der Tabelle angehört und bei neuen Wertern sowohl in db als auch in der List Arzt aktualisiert 
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
    	anrede.setCellValueFactory(new PropertyValueFactory<Arzt, String>("anrede"));
    	
    	anrede.setOnEditCommit(new EventHandler<CellEditEvent<Arzt, String>>(){

			@Override
			public void handle(CellEditEvent<Arzt, String> event) {
				
				Arzt a = event.getRowValue();
				Alert alert = new Alert(AlertType.CONFIRMATION);
		    	alert.setTitle("Update");
		    	alert.setHeaderText("Anrede ändern und aktualisieren");
		    	alert.setContentText("Bitte bstätigen?");

		    	Optional<ButtonType> result = alert.showAndWait();
		    	
		    	
		    	if (result.get() == ButtonType.OK){
		    		
		    		try {
		    			String querey = "update arzt"
		    					+ " Set anrede='" + event.getNewValue() + "'" 
		    					+ " where id_arzt='" + event.getRowValue().getId_arzt()+"'";
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
		    	} else {
		    		
		    		anrede.setEditable(true);
			    	 a.setAnrede(event.getOldValue());
			    	 tabelleData.refresh();
		    	   
		    	}
				
				
				
				
			}
			
    		
    	});
    	
    	
    	// Nachname Listener für Aktualisieren 
    	nachname.setEditable(true);
    	nachname.setCellFactory(TextFieldTableCell.forTableColumn());
    	nachname.setCellValueFactory(new PropertyValueFactory<Arzt, String>("nachname"));
    	
    	nachname.setOnEditCommit(new EventHandler<CellEditEvent<Arzt, String>>(){

			@Override
			public void handle(CellEditEvent<Arzt, String> event) {
				
				Arzt a = event.getRowValue();
				
				Alert alert = new Alert(AlertType.CONFIRMATION);
		    	alert.setTitle("Update");
		    	alert.setHeaderText("Nachname ändern und aktualisieren");
		    	alert.setContentText("Bitte bstätigen?");

		    	Optional<ButtonType> result = alert.showAndWait();
		    	if (result.get() == ButtonType.OK){
		    		
		    		try {
		    			String querey = "update arzt"
		    					+ " Set nachname='" + event.getNewValue() + "'" 
		    					+ " where id_arzt='" + event.getRowValue().getId_arzt()+"'";
		    			DBManager.sendQuery(querey);
						a.setNachname(event.getNewValue());
					} 
		    		catch (SQLException e) 
		    		{
		    			String err = DBManager.printSQLException(e);
						alert = new Alert(AlertType.ERROR);
						alert.setContentText(err);
						result = alert.showAndWait();
					}
		    		
					tabelleData.refresh();
					
		    	} 
		    	else 
		    	{
		    	   a.setNachname(event.getOldValue());
		    	   tabelleData.refresh();
		    	   
		    	}
				
				
				
			}
    		
    	});
    	
    	
    	// vorname Listener für Aktualisieren 
    	vorname.setEditable(true);
    	vorname.setCellFactory(TextFieldTableCell.forTableColumn());
    	vorname.setCellValueFactory(new PropertyValueFactory<Arzt, String>("vorname"));
    	
    	vorname.setOnEditCommit(new EventHandler<CellEditEvent<Arzt, String>>(){

			@Override
			public void handle(CellEditEvent<Arzt, String> event) {
				
				Arzt a = event.getRowValue();
				
				Alert alert = new Alert(AlertType.CONFIRMATION);
		    	alert.setTitle("Update");
		    	alert.setHeaderText("vorname ändern und aktualisieren");
		    	alert.setContentText("Bitte bstätigen?");

		    	Optional<ButtonType> result = alert.showAndWait();
		    	if (result.get() == ButtonType.OK){
		    		
		    		try {
		    			String querey = "update arzt"
		    					+ " Set vorname='" + event.getNewValue() + "'" 
		    					+ " where id_arzt='" + event.getRowValue().getId_arzt()+"'";
		    			DBManager.sendQuery(querey);
		    			a.setVorname(event.getNewValue());
		    			vorname.setEditable(false);
					} catch (SQLException e) 
		    		{
						String err = DBManager.printSQLException(e);
						alert = new Alert(AlertType.ERROR);
						alert.setContentText(err);
						result = alert.showAndWait();
						vorname.setEditable(true);
					}
		    		
					
					
					tabelleData.refresh();
		    	} 
		    	else 
		    	{
		    		vorname.setEditable(true);
		    	   a.setVorname(event.getOldValue());
		    	   tabelleData.refresh();
		    	   
		    	}
				
				
				
			}
    		
    	});
    	
    	
    	// KVnummer Listener für Aktualisieren 
    	mail.setEditable(true);
    	mail.setCellFactory(TextFieldTableCell.forTableColumn());
    	mail.setCellValueFactory(new PropertyValueFactory<Arzt, String>("spezialtaet"));
    	
    	mail.setOnEditCommit(new EventHandler<CellEditEvent<Arzt, String>>(){

			@Override
			public void handle(CellEditEvent<Arzt, String> event) {
				
				Arzt a = event.getRowValue();
				
				Alert alert = new Alert(AlertType.CONFIRMATION);
		    	alert.setTitle("Update");
		    	alert.setHeaderText("setMail ändern und aktualisieren");
		    	alert.setContentText("Bitte bstätigen?");

		    	Optional<ButtonType> result = alert.showAndWait();
		    	if (result.get() == ButtonType.OK){
		    		
		    		try {
		    			String querey = "update arzt"
		    					+ " Set mail='" + event.getNewValue() + "'" 
		    					+ " where id_arzt='" + event.getRowValue().getId_arzt()+"'";
		    			DBManager.sendQuery(querey);
		    			a.setMail(event.getNewValue() );
		    			mail.setEditable(false);
					} catch (SQLException e) {
						String err = DBManager.printSQLException(e);
						alert = new Alert(AlertType.ERROR);
						alert.setContentText(err);
						result = alert.showAndWait();
						mail.setEditable(true);
					}
		    		
					
					
					tabelleData.refresh();
		    	} else {
		    		mail.setEditable(true);
		    	   a.setMail(event.getOldValue());
		    	   tabelleData.refresh();
		    	   
		    	}
				
				
				
			}
    		
    	});
    	
    	
    	// Geburtsdatum Listener für Aktualisieren 
    	tel.setEditable(true);
    	tel.setCellFactory(TextFieldTableCell.forTableColumn());
    	tel.setCellValueFactory(new PropertyValueFactory<Arzt, String>("tel"));
    	
    	tel.setOnEditCommit(new EventHandler<CellEditEvent<Arzt, String>>(){

			@Override
			public void handle(CellEditEvent<Arzt, String> event) {
				
				Arzt a = event.getRowValue();
				
				Alert alert = new Alert(AlertType.CONFIRMATION);
		    	alert.setTitle("Update");
		    	alert.setHeaderText("tel ändern und aktualisieren");
		    	alert.setContentText("Bitte bstätigen?");

		    	Optional<ButtonType> result = alert.showAndWait();
		    	if (result.get() == ButtonType.OK){
		    		
		    		try {
		    			String querey = "update arzt"
		    					+ " Set tel='" + event.getNewValue() + "'" 
		    					+ " where id_arzt='" + event.getRowValue().getId_arzt()+"'";
		    			DBManager.sendQuery(querey);
		    			a.setMail(event.getNewValue());
		    			tel.setEditable(false);
					} 
		    		catch (SQLException e) 
		    		{
		    			String err = DBManager.printSQLException(e);
						alert = new Alert(AlertType.ERROR);
						alert.setContentText(err);
						result = alert.showAndWait();
						tel.setEditable(true);
					}
		    		
					
					
					tabelleData.refresh();
		    	} else {
		    		tel.setEditable(true);
		    	   a.setMail(event.getOldValue());
		    	   tabelleData.refresh();
		    	   
		    	}
				
				
				
			}
    		
    	});
    	
    	
     	// KVnummer Listener für Aktualisieren 
    	spezialtaet.setEditable(true);
    	spezialtaet.setCellFactory(TextFieldTableCell.forTableColumn());
    	spezialtaet.setCellValueFactory(new PropertyValueFactory<Arzt, String>("spezialtaet"));
    	
    	spezialtaet.setOnEditCommit(new EventHandler<CellEditEvent<Arzt, String>>(){

			@Override
			public void handle(CellEditEvent<Arzt, String> event) {
				
				Arzt a = event.getRowValue();
				
				Alert alert = new Alert(AlertType.CONFIRMATION);
		    	alert.setTitle("Update");
		    	alert.setHeaderText("setMail ändern und aktualisieren");
		    	alert.setContentText("Bitte bstätigen?");

		    	Optional<ButtonType> result = alert.showAndWait();
		    	if (result.get() == ButtonType.OK){
		    		
		    		try {
		    			String querey = "update arzt"
		    					+ " Set spezialtaet='" + event.getNewValue() + "'" 
		    					+ " where id_arzt='" + event.getRowValue().getId_arzt()+"'";
		    			DBManager.sendQuery(querey);
		    			a.setSpezialtaet(event.getNewValue() );
		    			spezialtaet.setEditable(false);
					} catch (SQLException e) {
						String err = DBManager.printSQLException(e);
						alert = new Alert(AlertType.ERROR);
						alert.setContentText(err);
						result = alert.showAndWait();
						spezialtaet.setEditable(true);
					}
		    		
					
					
					tabelleData.refresh();
		    	} else {
		    		spezialtaet.setEditable(true);
		    	   a.setSpezialtaet(event.getOldValue());
		    	   tabelleData.refresh();
		    	   
		    	}
				
				
				
			}
    		
    	});
    	
    	
    	// Geburtsdatum Listener für Aktualisieren 
    	vertretung_id_arzt.setEditable(true);
    	vertretung_id_arzt.setCellFactory(TextFieldTableCell.forTableColumn());
    	vertretung_id_arzt.setCellValueFactory(new PropertyValueFactory<Arzt, String>("vertretung_id_arzt"));
    	
    	vertretung_id_arzt.setOnEditCommit(new EventHandler<CellEditEvent<Arzt, String>>(){

			@Override
			public void handle(CellEditEvent<Arzt, String> event) {
				
				Arzt a = event.getRowValue();
				
				Alert alert = new Alert(AlertType.CONFIRMATION);
		    	alert.setTitle("Update");
		    	alert.setHeaderText("tel ändern und aktualisieren");
		    	alert.setContentText("Bitte bstätigen?");

		    	Optional<ButtonType> result = alert.showAndWait();
		    	if (result.get() == ButtonType.OK){
		    		
		    		try {
		    			String querey = "update arzt"
		    					+ " Set vertretung_id_arzt='" + event.getNewValue() + "'" 
		    					+ " where id_arzt='" + event.getRowValue().getId_arzt()+"'";
		    			DBManager.sendQuery(querey);
		    			a.setVertretung_id_arzt(event.getNewValue() );
		    			vertretung_id_arzt.setEditable(false);
					} 
		    		catch (SQLException e) 
		    		{
		    			String err = DBManager.printSQLException(e);
						alert = new Alert(AlertType.ERROR);
						alert.setContentText(err);
						result = alert.showAndWait();
						vertretung_id_arzt.setEditable(true);
					}
		    		
					
					
					tabelleData.refresh();
		    	} else {
		    		vertretung_id_arzt.setEditable(true);
		    	   a.setVertretung_id_arzt(event.getOldValue());
		    	   tabelleData.refresh();
		    	   
		    	}
				
				
				
			}
    		
    	});
    	
    	
    	
    	
   	
    }
    
    /**
     *  die Methode deleteButton wird aufgerufen, wenn der delete icon geklickt wird
     *  wird DeleteEvent angehört, um einer Adresse in db und auch in der Liste zulöschen
     *  
     * @param event verantwotlich für Anhörung einer Aktion
     * @author Ahmad, Akram, Nour 
     *
     */
    @FXML
    private void deleteButton(ActionEvent event) throws IOException
    {
    	
    	
    	Arzt a = tabelleData.getSelectionModel().getSelectedItem();
    
			Alert alert = new Alert(AlertType.CONFIRMATION);
	    	alert.setTitle("Arzt löschen");
	    	alert.setContentText("Wollen Sie es sicher löschen?");

	    	Optional<ButtonType> result = alert.showAndWait();
	    	if (result.get() == ButtonType.OK){
	    		String querey = "delete from arzt where id_arzt=" + a.getId_arzt();
	    		
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
	    	else 
	    	{
	    		
	    		System.out.print("kein Datensatz ausgewählt !!!");
	    	    
	    	}
	    	
			
  
    	
    	
    	
    }
    
    
    
    /**
     *  die Methode einfuegenEvent wird aufgerufen, wenn der plus icon geklickt wird
     *  wird einfuegenEvent für die Textfeldern in der Tabelle angehört. 
     *  zuerst wird default werte in der Textfeldern eingesetzt und nach Berarbeitung werden die Werte in der Liste Arzt und in der Tabelle gespeichert
     *  
     * @param event verantwotlich für Anhörung einer Aktion
     * @author Ahmad, Akram, Nour 
     *
     */
    @FXML
    private void einfuegenEvent(ActionEvent event) throws IOException
    {
    	
    	arzt.add(new Arzt("hier ändern", "hier ändern", "hier ändern", "hier ändern", "hier ändern", "hier ändern", "hier ändern", "hier ändern"));
    	
    	// anrede Listener für Aktualisieren 
    	id_arzt.setEditable(true);
    	id_arzt.setCellFactory(TextFieldTableCell.forTableColumn());
    	id_arzt.setCellValueFactory(new PropertyValueFactory<Arzt, String>("id_arzt"));
    	
    	id_arzt.setOnEditCommit(new EventHandler<CellEditEvent<Arzt, String>>(){

			@Override
			public void handle(CellEditEvent<Arzt, String> event) {
				
				Arzt a = event.getRowValue();
				a.setId_arzt(event.getNewValue());	
				//id_arzt.setEditable(false);
				tabelleData.refresh();
				

			}
			
    		
    	});
    	
    	
    	// anrede Listener für Aktualisieren 
    	anrede.setEditable(true);
    	anrede.setCellFactory(TextFieldTableCell.forTableColumn());
    	anrede.setCellValueFactory(new PropertyValueFactory<Arzt, String>("anrede"));
    	
    	anrede.setOnEditCommit(new EventHandler<CellEditEvent<Arzt, String>>(){

			@Override
			public void handle(CellEditEvent<Arzt, String> event) {
				
				Arzt a = event.getRowValue();
				a.setAnrede(event.getNewValue());	
				//anrede.setEditable(false);
				tabelleData.refresh();
				

			}
			
    		
    	});
    	
    	// anrede Listener für Aktualisieren 
    	nachname.setEditable(true);
    	nachname.setCellFactory(TextFieldTableCell.forTableColumn());
    	nachname.setCellValueFactory(new PropertyValueFactory<Arzt, String>("nachname"));
    	
    	nachname.setOnEditCommit(new EventHandler<CellEditEvent<Arzt, String>>(){

			@Override
			public void handle(CellEditEvent<Arzt, String> event) {
				
				Arzt a = event.getRowValue();
				a.setNachname(event.getNewValue());	
				//nachname.setEditable(false);
				tabelleData.refresh();
				

			}
			
    		
    	});
    	
    	// anrede Listener für Aktualisieren 
    	vorname.setEditable(true);
    	vorname.setCellFactory(TextFieldTableCell.forTableColumn());
    	vorname.setCellValueFactory(new PropertyValueFactory<Arzt, String>("vorname"));
    	
    	vorname.setOnEditCommit(new EventHandler<CellEditEvent<Arzt, String>>(){

			@Override
			public void handle(CellEditEvent<Arzt, String> event) {
				
				Arzt a = event.getRowValue();
				a.setVorname(event.getNewValue());	
				//vorname.setEditable(false);
				tabelleData.refresh();
				

			}
			
    		
    	});
    	
    	// anrede Listener für Aktualisieren 
    	mail.setEditable(true);
    	mail.setCellFactory(TextFieldTableCell.forTableColumn());
    	mail.setCellValueFactory(new PropertyValueFactory<Arzt, String>("mail"));
    	
    	mail.setOnEditCommit(new EventHandler<CellEditEvent<Arzt, String>>(){

			@Override
			public void handle(CellEditEvent<Arzt, String> event) {
				
				Arzt a = event.getRowValue();
				a.setMail(event.getNewValue());;
				//mail.setEditable(false);
				tabelleData.refresh();
				

			}
			
    		
    	});
    	
    	// anrede Listener für Aktualisieren 
    	tel.setEditable(true);
    	tel.setCellFactory(TextFieldTableCell.forTableColumn());
    	tel.setCellValueFactory(new PropertyValueFactory<Arzt, String>("tel"));
    	
    	tel.setOnEditCommit(new EventHandler<CellEditEvent<Arzt, String>>(){

			@Override
			public void handle(CellEditEvent<Arzt, String> event) {
				
				Arzt a = event.getRowValue();
				a.setTel(event.getNewValue());
				//tel.setEditable(false);
				tabelleData.refresh();
				

			}
			
    		
    	});
    	
    	// anrede Listener für Aktualisieren 
    	spezialtaet.setEditable(true);
    	spezialtaet.setCellFactory(TextFieldTableCell.forTableColumn());
    	spezialtaet.setCellValueFactory(new PropertyValueFactory<Arzt, String>("spezialtaet"));
    	
    	spezialtaet.setOnEditCommit(new EventHandler<CellEditEvent<Arzt, String>>(){

			@Override
			public void handle(CellEditEvent<Arzt, String> event) {
				
				Arzt a = event.getRowValue();
				a.setSpezialtaet(event.getNewValue());
				//spezialtaet.setEditable(false);
				tabelleData.refresh();
				

			}
			
    		
    	});
    	
    	
    	// anrede Listener für Aktualisieren 
    	vertretung_id_arzt.setEditable(true);
    	vertretung_id_arzt.setCellFactory(TextFieldTableCell.forTableColumn());
    	vertretung_id_arzt.setCellValueFactory(new PropertyValueFactory<Arzt, String>("vertretung_id_arzt"));
    	
    	vertretung_id_arzt.setOnEditCommit(new EventHandler<CellEditEvent<Arzt, String>>(){

			@Override
			public void handle(CellEditEvent<Arzt, String> event) {
				
				Arzt a = event.getRowValue();
				a.setVertretung_id_arzt(event.getNewValue());
				//vertretung_id_arzt.setEditable(false);
				tabelleData.refresh();
				

			}
			
    		
    	});
    	
    	this.speichernButton.setVisible(true);
    	this.einfuegenButton.setVisible(false);	
    	
    }
    
    
    /**
     *  die Methode einfuegenEvent wird aufgerufen, wenn der check icon geklickt wird
     *  wird ein SQL_Abfrage für Insert der Daten in der Tabelle Arzt in DB erstellt 
     *  und bei Aufruf der Methode sendQuery von Klasse DBManager gesendet
     *  
     * @param event verantwotlich für Anhörung einer Aktion
     * @author Ahmad, Akram, Nour 
     *
     */
    @FXML
    public void speichernEvent(ActionEvent event)
    {
    	
    	Arzt a = arzt.get(arzt.size()-1);
    	
    	
    	if(!a.getId_arzt().isEmpty())
    	{
    		
    		String queryString = "INSERT INTO arzt (id_arzt, anrede, nachname, vorname, mail, tel, spezialitaet, vertretung_id_arzt)"
    		+ " VALUES (" + a.getId_arzt()+ ", '" + a.getAnrede() + "', '" + a.getNachname() + "', '" + a.getVorname()  + "', '" + a.getMail() + "', " + a.getTel() + ", '" + a.getSpezialtaet() + "', " + a.getVertretung_id_arzt() + ")";
    		
        	try {
        		DBManager.sendQuery(queryString);
    			
    			
    		} catch (SQLException e) {
    			String err = DBManager.printSQLException(e);
				Alert alert = new Alert(AlertType.ERROR);
				alert.setContentText(err);
				Optional <ButtonType> result = alert.showAndWait();
    		}	
    		

    	}
    	else	
    	{
    		System.out.print("Error");
    	}
    	
    	id_arzt.setEditable(false);
    	anrede.setEditable(false);
		nachname.setEditable(false);
		vorname.setEditable(false);
		mail.setEditable(false);
		tel.setEditable(false);
		spezialtaet.setEditable(false);
		vertretung_id_arzt.setEditable(false);
    	
    	this.speichernButton.setVisible(false);
    	this.einfuegenButton.setVisible(true);
    	
    	
    }
    
    /**
     *  in der Methode initialize wird eine SQL_Abfrage für Holen der Arzt Daten 
     *  anhand der Methode getAlleDatenAdresse in der Klasse Arzt in der Arzt Liste gespeichert.
     *  Auch werde all Daten von Arzt Liste werden in der Liste vom Typ ObservableList gespeichert,
     *  die es Zuhörern ermöglicht, Änderungen zu verfolgen, wenn sie auftreten.
     * @param url ist nicht benutzt
     * @param rb ist nicht benutzt
     * @author Ahmad, Akram, Nour 
     *
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	
    
    	
    	String queryString = "select id_arzt, anrede, nachname, vorname, mail, tel, spezialitaet, vertretung_id_arzt From arzt";
    	
		a = Arzt.getAlleDatenArzt(queryString);
			
	
    	arzt.addAll(a);
    	
    	id_arzt.setCellValueFactory(new PropertyValueFactory<>("id_arzt"));
    	anrede.setCellValueFactory(new PropertyValueFactory<>("anrede"));
		nachname.setCellValueFactory(new PropertyValueFactory<>("nachname"));
		vorname.setCellValueFactory(new PropertyValueFactory<>("vorname"));
		mail.setCellValueFactory(new PropertyValueFactory<>("mail"));
		tel.setCellValueFactory(new PropertyValueFactory<>("tel"));
		spezialtaet.setCellValueFactory(new PropertyValueFactory<>("spezialtaet"));
		vertretung_id_arzt.setCellValueFactory(new PropertyValueFactory<>("vertretung_id_arzt"));
		
		
		tabelleData.setItems(arzt);
		
    	
    }    
    
   private ObservableList<Arzt> arzt = FXCollections.observableArrayList();
   
   
    
    
}
