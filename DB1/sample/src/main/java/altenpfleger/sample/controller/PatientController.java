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
					} catch (SQLException e) {
						
						DBManager.printSQLException(e);
						 p.setAnrede(event.getOldValue());
					}
		    		
					
					
					tabelleData.refresh();
		    	} else {
		    		
		    		 alert = new Alert(AlertType.ERROR);
		    		 alert.setContentText("Bitte geben Sie entweder Herr odr Frau ein!!!");
			    	 result = alert.showAndWait();
			    	 tabelleData.refresh();
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
					} 
		    		catch (SQLException e) 
		    		{
						DBManager.printSQLException(e);
					}
		    		
					tabelleData.refresh();
					
		    	} 
		    	else 
		    	{
		    	   p.setNachname(event.getOldValue());
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
					} catch (SQLException e) 
		    		{
						DBManager.printSQLException(e);
					}
		    		
					p.setVorname(event.getNewValue());
					
					tabelleData.refresh();
		    	} 
		    	else 
		    	{
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
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		    		
					p.setKVnummer(event.getNewValue());
					
					tabelleData.refresh();
		    	} else {
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
					} 
		    		catch (SQLException e) 
		    		{
						DBManager.printSQLException(e);
					}
		    		
					p.setGeburtsdatum(event.getNewValue());
					
					tabelleData.refresh();
		    	} else {
		    	   p.setGeburtsdatum(event.getOldValue());
		    	   tabelleData.refresh();
		    	   
		    	}
				
				
				
			}
    		
    	});
    	
    	
    	
    	
   	
    }
    
    
    @FXML
    private void deleteButton(ActionEvent event) throws IOException
    {
    	
    	
    	Patient p = tabelleData.getSelectionModel().getSelectedItem();
    	
    	if(p!=null)
    	{
			Alert alert = new Alert(AlertType.CONFIRMATION);
	    	alert.setTitle("Confirmation Dialog");
	    	alert.setHeaderText("Look, a Confirmation Dialog");
	    	alert.setContentText("Are you ok with this?");

	    	Optional<ButtonType> result = alert.showAndWait();
	    	if (result.get() == ButtonType.OK){
	    		String querey = "delete from patient where id_patient=" + p.getId_patient();
	    		
	    		try {
	    			DBManager.sendQuery(querey);
				} 
	    		catch (SQLException e) 
	    		{
					DBManager.printSQLException(e);
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
				id_patient.setEditable(false);
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
				anrede.setEditable(false);
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
				nachname.setEditable(false);
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
				vorname.setEditable(false);
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
				KVnummer.setEditable(false);
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
				Geburtsdatum.setEditable(false);
				tabelleData.refresh();
				

			}
			
    		
    	});
    	
    	this.speichernButton.setVisible(true);
    	this.einfuegenButton.setVisible(false);	
    	
    }
    
    
    
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
    			DBManager.printSQLException(e);
    		}	
    		

    	}
    	else	
    	{
    		System.out.print("Error");
    	}
    	
    	this.speichernButton.setVisible(false);
    	this.einfuegenButton.setVisible(true);
    	
    	
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	
    
    	
    	String queryString = "select id_patient, Anrede, Nachname, Vorname, KVnummer, TO_CHAR(Geburtsdatum,'DD.MM.YYYY') From patient";
    	try {
    		
			this.p = Patient.getAlleDatenPatient(queryString);
			System.out.print("test");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}	
	
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
