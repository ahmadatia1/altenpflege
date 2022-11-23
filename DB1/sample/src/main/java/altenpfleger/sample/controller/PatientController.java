package altenpfleger.sample.controller;
/*
Put header here


 */

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

import altenpfleger.sample.dbservices.DBManager;
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
 *  Klasse PatientController kontrolliert der View bzw. Adresse.fxml Datei
 *  Events werden augeführt, Textfeldern werden bearbeiten und neue Werte gespeichert, etc..
 *  
 * @author Ahmad, Akram, Nour 
 *
 */
public class PatientController implements Initializable {
	

	private ArrayList<Patient> p;
	
	
	@FXML private Button einfuegenButton;
	@FXML private Button speichernButton;
  
    
    
    @FXML private TableView<Patient> tabelleData;
    @FXML private TableColumn<Patient, String> id_patient;
    @FXML private TableColumn<Patient, String> anrede;
    @FXML private TableColumn<Patient, String> vorname;
    @FXML private TableColumn<Patient, String> KVnummer;
    @FXML private TableColumn<Patient, String> Geburtsdatum;
    @FXML private TableColumn<Patient, String> nachname;
 
    
    /**
     *  die Methode editButton wird aufgerufen, wenn der Edit icon geklickt wird
     *  wird EditEvent für die Textfeldern in der Tabelle angehört 
     *  und bei neuen Wertern sowohl in db als auch in der List Patient aktualisiert 
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
    	anrede.setCellValueFactory(new PropertyValueFactory<Patient, String>("anrede"));
    	
    	anrede.setOnEditCommit(new EventHandler<CellEditEvent<Patient, String>>(){

			@Override
			public void handle(CellEditEvent<Patient, String> event) {
				
				Patient p = event.getRowValue();
				Alert alert = new Alert(AlertType.CONFIRMATION);
		    	alert.setTitle("Update");
		    	alert.setHeaderText("Anrede ändern und aktualisieren");
		    	alert.setContentText("Bitte bstätigen?");

		    	Optional<ButtonType> result = alert.showAndWait();
		    	
		    	
		    	if (result.get() == ButtonType.OK){
		    		
		    		try {
		    			String querey = "update patient"
		    					+ " Set anrede='" + event.getNewValue() + "'" 
		    					+ " where id_patient='" + event.getRowValue().getId_patient()+"'";
		    			DBManager.sendQuery(querey);
						p.setAnrede(event.getNewValue());
						anrede.setEditable(false);
					} catch (SQLException e) {
						
						String err = DBManager.printSQLException(e);
						alert = new Alert(AlertType.ERROR);
						alert.setContentText(err);
						result = alert.showAndWait();
						p.setAnrede(event.getOldValue());
						anrede.setEditable(true);
					}
		    		
					
					
					tabelleData.refresh();
		    	} 
		    	else 
		    	{
		    		
		    		anrede.setEditable(true);
		    		p.setAnrede(event.getOldValue());
		    	   	tabelleData.refresh();
		    	   
		    	}
				
				
				
				
			}
			
    		
    	});
    	
    	
    	// Nachname Listener für Aktualisieren 
    	nachname.setEditable(true);
    	nachname.setCellFactory(TextFieldTableCell.forTableColumn());
    	nachname.setCellValueFactory(new PropertyValueFactory<Patient, String>("nachname"));
    	
    	nachname.setOnEditCommit(new EventHandler<CellEditEvent<Patient, String>>(){

			@Override
			public void handle(CellEditEvent<Patient, String> event) {
				
				Patient p = event.getRowValue();
				
				Alert alert = new Alert(AlertType.CONFIRMATION);
		    	alert.setTitle("Update");
		    	alert.setHeaderText("Nachname ändern und aktualisieren");
		    	alert.setContentText("Bitte bstätigen?");

		    	Optional<ButtonType> result = alert.showAndWait();
		    	if (result.get() == ButtonType.OK){
		    		
		    		try {
		    			String querey = "update patient"
		    					+ " Set nachname='" + event.getNewValue() + "'" 
		    					+ " where id_patient='" + event.getRowValue().getId_patient()+"'";
		    			DBManager.sendQuery(querey);
						p.setNachname(event.getNewValue());
						nachname.setEditable(false);
					} 
		    		catch (SQLException e) 
		    		{
		    			String err = DBManager.printSQLException(e);
						alert = new Alert(AlertType.ERROR);
						alert.setContentText(err);
						result = alert.showAndWait();
						nachname.setEditable(true);
					}
		    		
					tabelleData.refresh();
					
		    	} 
		    	else 
		    	{
		    		nachname.setEditable(true);
		    		
		    		p.setAnrede(event.getOldValue());
		    	   	tabelleData.refresh();
		    	   
		    	}
		    	
				
				
			}
    		
    	});
    	
    	
    	// vorname Listener für Aktualisieren 
    	vorname.setEditable(true);
    	vorname.setCellFactory(TextFieldTableCell.forTableColumn());
    	vorname.setCellValueFactory(new PropertyValueFactory<Patient, String>("vorname"));
    	
    	vorname.setOnEditCommit(new EventHandler<CellEditEvent<Patient, String>>(){

			@Override
			public void handle(CellEditEvent<Patient, String> event) {
				
				Patient p = event.getRowValue();
				
				Alert alert = new Alert(AlertType.CONFIRMATION);
		    	alert.setTitle("Update");
		    	alert.setHeaderText("vorname ändern und aktualisieren");
		    	alert.setContentText("Bitte bstätigen?");

		    	Optional<ButtonType> result = alert.showAndWait();
		    	if (result.get() == ButtonType.OK){
		    		
		    		try {
		    			String querey = "update patient"
		    					+ " Set vorname='" + event.getNewValue() + "'" 
		    					+ " where id_patient='" + event.getRowValue().getId_patient()+"'";
		    			DBManager.sendQuery(querey);
		    			p.setVorname(event.getNewValue());
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
		    	   p.setVorname(event.getOldValue());
		    	   tabelleData.refresh();
		    	   
		    	}
				
				
				
			}
    		
    	});
    	
    	
    	// KVnummer Listener für Aktualisieren 
    	KVnummer.setEditable(true);
    	KVnummer.setCellFactory(TextFieldTableCell.forTableColumn());
    	KVnummer.setCellValueFactory(new PropertyValueFactory<Patient, String>("KVnummer"));
    	
    	KVnummer.setOnEditCommit(new EventHandler<CellEditEvent<Patient, String>>(){

			@Override
			public void handle(CellEditEvent<Patient, String> event) {
				
				Patient p = event.getRowValue();
				
				Alert alert = new Alert(AlertType.CONFIRMATION);
		    	alert.setTitle("Update");
		    	alert.setHeaderText("KVnummer ändern und aktualisieren");
		    	alert.setContentText("Bitte bstätigen?");

		    	Optional<ButtonType> result = alert.showAndWait();
		    	if (result.get() == ButtonType.OK){
		    		
		    		try {
		    			String querey = "update patient"
		    					+ " Set KVnummer='" + event.getNewValue() + "'" 
		    					+ " where id_patient='" + event.getRowValue().getId_patient()+"'";
		    			DBManager.sendQuery(querey);
		    			p.setKVnummer(event.getNewValue());
		    			KVnummer.setEditable(false);
					} catch (SQLException e) {
						String err = DBManager.printSQLException(e);
						alert = new Alert(AlertType.ERROR);
						alert.setContentText(err);
						result = alert.showAndWait();
						KVnummer.setEditable(true);
					}
		    		
				
					
					tabelleData.refresh();
		    	} else {
		    		KVnummer.setEditable(true);
		    	   p.setKVnummer(event.getOldValue());
		    	   tabelleData.refresh();
		    	   
		    	}
				
				
				
			}
    		
    	});
    	
    	
    	// Geburtsdatum Listener für Aktualisieren 
    	Geburtsdatum.setEditable(true);
    	Geburtsdatum.setCellFactory(TextFieldTableCell.forTableColumn());
    	Geburtsdatum.setCellValueFactory(new PropertyValueFactory<Patient, String>("Geburtsdatum"));
    	
    	Geburtsdatum.setOnEditCommit(new EventHandler<CellEditEvent<Patient, String>>(){

			@Override
			public void handle(CellEditEvent<Patient, String> event) {
				
				Patient p = event.getRowValue();
				
				Alert alert = new Alert(AlertType.CONFIRMATION);
		    	alert.setTitle("Update");
		    	alert.setHeaderText("Geburtsdatum ändern und aktualisieren");
		    	alert.setContentText("Bitte bstätigen?");

		    	Optional<ButtonType> result = alert.showAndWait();
		    	if (result.get() == ButtonType.OK){
		    		
		    		try {
		    			String querey = "update patient"
		    					+ " Set Geburtsdatum='" + event.getNewValue() + "'" 
		    					+ " where id_patient='" + event.getRowValue().getId_patient()+"'";
		    			DBManager.sendQuery(querey);
		    			p.setGeburtsdatum(event.getNewValue());
		    			Geburtsdatum.setEditable(false);
					} 
		    		catch (SQLException e) 
		    		{
		    			String err = DBManager.printSQLException(e);
						alert = new Alert(AlertType.ERROR);
						alert.setContentText(err);
						result = alert.showAndWait();
						Geburtsdatum.setEditable(true);
					}
		    		
					
					
					tabelleData.refresh();
		    	} else {
		    		Geburtsdatum.setEditable(true);
		    	   p.setGeburtsdatum(event.getOldValue());
		    	   tabelleData.refresh();
		    	   
		    	}
				
				
				
			}
    		
    	});
    	
    	
    	
    	
   	
    }
    
    /**
     *  die Methode deleteButton wird aufgerufen, wenn der delete icon geklickt wird
     *  wird DeleteEvent angehört, um einer Patient in db und auch in der Liste zulöschen
     *  
     * @param event verantwotlich für Anhörung einer Aktion
     * @author Ahmad, Akram, Nour 
     *
     */
    @FXML
    private void deleteButton(ActionEvent event) throws IOException
    {
    	
    	
    	Patient p = tabelleData.getSelectionModel().getSelectedItem();
    	
    	if(p!=null)
    	{
			Alert alert = new Alert(AlertType.CONFIRMATION);
	    	alert.setTitle("Patient löschen");
	    	alert.setContentText("Wollen Sie es sicher löschen?");

	    	Optional<ButtonType> result = alert.showAndWait();
	    	if (result.get() == ButtonType.OK){
	    		String querey = "delete from patient where id_patient=" + p.getId_patient();
	    		
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
	    		
	    		boolean status = tabelleData.getItems().remove(p);
	    		System.out.print(status);
	    	    
	    	} 
	    	else 
	    	{
	    		
	    		System.out.print("kein Datensatz ausgewählt !!!");
	    	    
	    	}
	    	
			
    	}
    	
    	
    	
    }
    
    
    
    /**
     *  die Methode einfuegenEvent wird aufgerufen, wenn der plus icon geklickt wird
     *  wird einfuegenEvent für die Textfeldern in der Tabelle angehört. 
     *  zuerst wird default werte in der Textfeldern eingesetzt 
     *  und nach Berarbeitung werden die Werte in der Liste Patient 
     *  und in der Tabelle gespeichert
     *  
     * @param event verantwotlich für Anhörung einer Aktion
     * @author Ahmad, Akram, Nour 
     *
     */
    @FXML
    private void einfuegenEvent(ActionEvent event) throws IOException
    {
    	
    	patient.add(new Patient("hier ändern", "hier ändern", "hier ändern", "hier ändern", "hier ändern", "hier ändern"));
    	
    	// anrede Listener für Aktualisieren 
    	id_patient.setEditable(true);
    	id_patient.setCellFactory(TextFieldTableCell.forTableColumn());
    	id_patient.setCellValueFactory(new PropertyValueFactory<Patient, String>("id_patient"));
    	
    	id_patient.setOnEditCommit(new EventHandler<CellEditEvent<Patient, String>>(){

			@Override
			public void handle(CellEditEvent<Patient, String> event) {
				
				Patient p = event.getRowValue();
				p.setId_patient(event.getNewValue());	
				
				tabelleData.refresh();
				

			}
			
    		
    	});
    	
    	
    	// anrede Listener für Aktualisieren 
    	anrede.setEditable(true);
    	anrede.setCellFactory(TextFieldTableCell.forTableColumn());
    	anrede.setCellValueFactory(new PropertyValueFactory<Patient, String>("anrede"));
    	
    	anrede.setOnEditCommit(new EventHandler<CellEditEvent<Patient, String>>(){

			@Override
			public void handle(CellEditEvent<Patient, String> event) {
				
				Patient p = event.getRowValue();
				p.setAnrede(event.getNewValue());	
				
				tabelleData.refresh();
				

			}
			
    		
    	});
    	
    	// anrede Listener für Aktualisieren 
    	nachname.setEditable(true);
    	nachname.setCellFactory(TextFieldTableCell.forTableColumn());
    	nachname.setCellValueFactory(new PropertyValueFactory<Patient, String>("nachname"));
    	
    	nachname.setOnEditCommit(new EventHandler<CellEditEvent<Patient, String>>(){

			@Override
			public void handle(CellEditEvent<Patient, String> event) {
				
				Patient p = event.getRowValue();
				p.setNachname(event.getNewValue());	
				
				tabelleData.refresh();
				

			}
			
    		
    	});
    	
    	// anrede Listener für Aktualisieren 
    	vorname.setEditable(true);
    	vorname.setCellFactory(TextFieldTableCell.forTableColumn());
    	vorname.setCellValueFactory(new PropertyValueFactory<Patient, String>("vorname"));
    	
    	vorname.setOnEditCommit(new EventHandler<CellEditEvent<Patient, String>>(){

			@Override
			public void handle(CellEditEvent<Patient, String> event) {
				
				Patient p = event.getRowValue();
				p.setVorname(event.getNewValue());	
				
				tabelleData.refresh();
				

			}
			
    		
    	});
    	
    	// anrede Listener für Aktualisieren 
    	KVnummer.setEditable(true);
    	KVnummer.setCellFactory(TextFieldTableCell.forTableColumn());
    	KVnummer.setCellValueFactory(new PropertyValueFactory<Patient, String>("KVnummer"));
    	
    	KVnummer.setOnEditCommit(new EventHandler<CellEditEvent<Patient, String>>(){

			@Override
			public void handle(CellEditEvent<Patient, String> event) {
				
				Patient p = event.getRowValue();
				p.setKVnummer(event.getNewValue());	
				
				tabelleData.refresh();
				

			}
			
    		
    	});
    	
    	// anrede Listener für Aktualisieren 
    	Geburtsdatum.setEditable(true);
    	Geburtsdatum.setCellFactory(TextFieldTableCell.forTableColumn());
    	Geburtsdatum.setCellValueFactory(new PropertyValueFactory<Patient, String>("Geburtsdatum"));
    	
    	Geburtsdatum.setOnEditCommit(new EventHandler<CellEditEvent<Patient, String>>(){

			@Override
			public void handle(CellEditEvent<Patient, String> event) {
				
				Patient p = event.getRowValue();
				p.setGeburtsdatum(event.getNewValue());	
				
				tabelleData.refresh();
				

			}
			
    		
    	});
    	
    	id_patient.setEditable(false);
		nachname.setEditable(false);
		vorname.setEditable(false);
		Geburtsdatum.setEditable(false);
		KVnummer.setEditable(false);
		anrede.setEditable(false);
    	
    	this.speichernButton.setVisible(true);
    	this.einfuegenButton.setVisible(false);	
    	
    }
    
    
    /**
     *  die Methode einfuegenEvent wird aufgerufen, wenn der check icon geklickt wird
     *  wird ein SQL_Abfrage für Insert der Daten in der Tabelle Patient in DB erstellt 
     *  und bei Aufruf der Methode sendQuery von Klasse DBManager gesendet
     *  
     * @param event verantwotlich für Anhörung einer Aktion
     * @author Ahmad, Akram, Nour 
     *
     */
    @FXML
    public void speichernEvent(ActionEvent event)
    {
    	
    	Patient p = patient.get(patient.size()-1);
    	
    	
    	if(!p.getId_patient().isEmpty())
    	{
    		
    		String queryString = "INSERT INTO Patient (id_patient, anrede, nachname, vorname, KVnummer, Geburtsdatum)"
    		+ "VALUES (" + p.getId_patient()+ ", '" + p.getAnrede() + "', '" + p.getNachname() + "', '" + p.getVorname()  + "', '" + p.getKVnummer() + "', '" + p.getGeburtsdatum() + "')";
    		
        	try {
        		DBManager.sendQuery(queryString);
    			
    			
    		} catch (SQLException e) {
    			String err = DBManager.printSQLException(e);
				Alert alert = new Alert(AlertType.ERROR);
				alert.setContentText(err);
			 	Optional<ButtonType> result = alert.showAndWait();
    		}	
    		

    	}
    	else	
    	{
    		System.out.print("Error");
    	}
    	
    	
    	
    	this.speichernButton.setVisible(false);
    	this.einfuegenButton.setVisible(true);
    	
    	
    }
    
    /**
     *  in der Methode initialize wird eine SQL_Abfrage für Holen der Patient Daten 
     *  anhand der Methode getAlleDatenPatientin der Klasse Patient in der Patient Liste gespeichert.
     *  Auch werde all Daten von Patient Liste werden in der Liste vom Typ ObservableList gespeichert,
     *  die es Zuhörern ermöglicht, Änderungen zu verfolgen, wenn sie auftreten.
     * @param url ist nicht benutzt
     * @param rb ist nicht benutzt
     * @author Ahmad, Akram, Nour 
     *
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	
    
    	
    	String queryString = "select id_patient, Anrede, Nachname, Vorname, KVnummer, TO_CHAR(Geburtsdatum,'DD.MM.YYYY') From patient";
    	
		this.p = Patient.getAlleDatenPatient(queryString);
	
			
		
	
		patient.addAll(p);
    	
    	id_patient.setCellValueFactory(new PropertyValueFactory<>("id_patient"));
		nachname.setCellValueFactory(new PropertyValueFactory<>("nachname"));
		vorname.setCellValueFactory(new PropertyValueFactory<>("vorname"));
		Geburtsdatum.setCellValueFactory(new PropertyValueFactory<>("Geburtsdatum"));
		KVnummer.setCellValueFactory(new PropertyValueFactory<>("KVnummer"));
		anrede.setCellValueFactory(new PropertyValueFactory<>("anrede"));
		
		
		tabelleData.setItems(patient);
		
    	
    }    
    
   private ObservableList<Patient> patient = FXCollections.observableArrayList();
   
   
    
    
}
