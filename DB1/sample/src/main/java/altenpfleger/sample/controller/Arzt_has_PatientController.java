package altenpfleger.sample.controller;
/*
Put header here


 */

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

import altenpfleger.sample.dbservices.DBManager;
import altenpfleger.sample.model.Arzt_has_Patient;

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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
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
 *  Klasse Arzt_has_PatientController kontrolliert der View bzw. Arzt_has_Patient.fxml Datei
 *  Events werden augeführt, Textfeldern werden bearbeiten und neue Werte gespeichert, etc..
 *  
 * @author Ahmad, Akram, Nour 
 *
 */

public class Arzt_has_PatientController implements Initializable {
	

	private ArrayList<Arzt_has_Patient> a;
	
	
	
	@FXML private Button einfuegenButton;
	@FXML private Button speichernButton;

		
		
    
    
    @FXML private TableView<Arzt_has_Patient> tabelleData;
    @FXML private TableColumn<Arzt_has_Patient, String> arzt_id_arzt;
    @FXML private TableColumn<Arzt_has_Patient, String> patient_id_patient;
    @FXML private TableColumn<Arzt_has_Patient, String> krankheit;
    @FXML private TableColumn<Arzt_has_Patient, String> wunschtermin;

    
    
    /**
     *  die Methode editButton wird aufgerufen, wenn der Edit icon geklickt wird
     *  wird EditEvent für die Textfeldern in der Tabelle angehört und bei neuen Wertern sowohl in db als auch in der List Arzt_has_Patient aktualisiert 
     *  
     * @param event verantwotlich für Anhörung einer Aktion
     * @author Ahmad, Akram, Nour 
     *
     */
    @FXML
    private void editButton(ActionEvent event) throws IOException {
    	
    	
    	// anrede Listener für Aktualisieren 
    	krankheit.setEditable(true);
    	krankheit.setCellFactory(TextFieldTableCell.forTableColumn());
    	krankheit.setCellValueFactory(new PropertyValueFactory<Arzt_has_Patient, String>("krankheit"));
    	
    	krankheit.setOnEditCommit(new EventHandler<CellEditEvent<Arzt_has_Patient, String>>(){

			@Override
			public void handle(CellEditEvent<Arzt_has_Patient, String> event) {
				
				Arzt_has_Patient a = event.getRowValue();
				Alert alert = new Alert(AlertType.CONFIRMATION);
		    	alert.setTitle("Update");
		    	alert.setHeaderText("krankheit ändern und aktualisieren");
		    	alert.setContentText("Bitte bstätigen?");

		    	Optional<ButtonType> result = alert.showAndWait();
		    	
		    	
		    	if (result.get() == ButtonType.OK){
		    		
		    		try {
		    			String querey = "update Arzt_has_Patient"
		    					+ " Set krankheit='" + event.getNewValue() + "'" 
		    					+ " where arzt_id_arzt='" + event.getRowValue().getArzt_id_arzt() +"' and " 
		    					+ " patient_id_patient='" + event.getRowValue().getPatient_id_patient()+"'";
		    			DBManager.sendQuery(querey);
						a.setKrankheit(event.getNewValue());
						krankheit.setEditable(false);
						
					} catch (SQLException e) {
						String err = DBManager.printSQLException(e);
						alert = new Alert(AlertType.ERROR);
						alert.setContentText(err);
						result = alert.showAndWait();
						a.setKrankheit(event.getOldValue());
						krankheit.setEditable(true);
					}
		    		
		    		tabelleData.refresh();
		    		
					
		    	} else {
		    		
		    		krankheit.setEditable(true);
			    	a.setKrankheit(event.getOldValue());
		    	   	tabelleData.refresh();
		    	   
		    	}
				
				
				
				
			}
			
    		
    	});
    	
    	// anrede Listener für Aktualisieren 
    	wunschtermin.setEditable(true);
    	wunschtermin.setCellFactory(TextFieldTableCell.forTableColumn());
    	wunschtermin.setCellValueFactory(new PropertyValueFactory<Arzt_has_Patient, String>("wunschtermin"));
    	
    	wunschtermin.setOnEditCommit(new EventHandler<CellEditEvent<Arzt_has_Patient, String>>(){

			@Override
			public void handle(CellEditEvent<Arzt_has_Patient, String> event) {
				
				Arzt_has_Patient a = event.getRowValue();
				Alert alert = new Alert(AlertType.CONFIRMATION);
		    	alert.setTitle("Update");
		    	alert.setHeaderText("wunschtermin ändern und aktualisieren");
		    	alert.setContentText("Bitte bstätigen?");

		    	Optional<ButtonType> result = alert.showAndWait();
		    	
		    	
		    	if (result.get() == ButtonType.OK){
		    		
		    		try {
		    			String querey = "update Arzt_has_Patient"
		    					+ " Set wunschtermin='" + event.getNewValue() + "'" 
		    					+ " where arzt_id_arzt='" + event.getRowValue().getArzt_id_arzt() +"' and " 
		    					+ " patient_id_patient='" + event.getRowValue().getPatient_id_patient()+"'";
		    			DBManager.sendQuery(querey);
						a.setWunschtermin(event.getNewValue());
						wunschtermin.setEditable(false);
						
					} catch (SQLException e) {
						String err = DBManager.printSQLException(e);
						alert = new Alert(AlertType.ERROR);
						alert.setContentText(err);
						result = alert.showAndWait();
						a.setWunschtermin(event.getOldValue());
						wunschtermin.setEditable(true);
					}
		    		
		    		
		    		tabelleData.refresh();
					
		    	} else {
		    		
		    		wunschtermin.setEditable(true);
			    	a.setWunschtermin(event.getOldValue());
		    	   	tabelleData.refresh();
		    	   
		    	}
				
				
				
				
			}
			
    		
    	});
    	
    
    	
   	
    }
    
    /**
     *  die Methode deleteButton wird aufgerufen, wenn der delete icon geklickt wird
     *  wird DeleteEvent angehört, um einer Arzt_has_Patient in db und auch in der Liste zulöschen
     *  
     * @param event verantwotlich für Anhörung einer Aktion
     * @author Ahmad, Akram, Nour 
     *
     */
    @FXML
    private void deleteButton(ActionEvent event) throws IOException
    {
    	
    	
    	Arzt_has_Patient a = tabelleData.getSelectionModel().getSelectedItem();
    	
    	if(a!=null)
    	{
			Alert alert = new Alert(AlertType.CONFIRMATION);
	    	alert.setTitle("Arzt_has_Patient löschen");
	    	alert.setContentText("Wollen Sie es sicher löschen?");

	    	Optional<ButtonType> result = alert.showAndWait();
	    	if (result.get() == ButtonType.OK){
	    		String querey = "delete from Arzt_has_Patient where arzt_id_arzt='" + a.getArzt_id_arzt() +"'" 
	    				+ "and patient_id_patient='" + a.getPatient_id_patient()+"'";
	    		
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
	    	    
	    	} else {
	    		
	    		System.out.print("kein Datensatz ausgewählt !!!");
	    	    
	    	}
	    	
			
    	}
    	
    	
    	
    }
    
    
    
    /**
     *  die Methode einfuegenEvent wird aufgerufen, wenn der plus icon geklickt wird
     *  wird einfuegenEvent für die Textfeldern in der Tabelle angehört. 
     *  zuerst wird default werte in der Textfeldern eingesetzt und nach Berarbeitung werden die Werte in der Liste Arzt_has_Patient und in der Tabelle gespeichert
     *  
     * @param event verantwotlich für Anhörung einer Aktion
     * @author Ahmad, Akram, Nour 
     *
     */
    @FXML
    private void einfuegenEvent(ActionEvent event) throws IOException
    {
    	
    	arzt_has_patient.add(new Arzt_has_Patient("hier ändern", "hier ändern", "hier ändern", "hier ändern"));
    	
    	
    	
    	// anrede Listener für Aktualisieren 
    	arzt_id_arzt.setEditable(true);
    	arzt_id_arzt.setCellFactory(TextFieldTableCell.forTableColumn());
    	arzt_id_arzt.setCellValueFactory(new PropertyValueFactory<Arzt_has_Patient, String>("arzt_id_arzt"));
    	
    	arzt_id_arzt.setOnEditCommit(new EventHandler<CellEditEvent<Arzt_has_Patient, String>>(){

			@Override
			public void handle(CellEditEvent<Arzt_has_Patient, String> event) {
				
				Arzt_has_Patient a = event.getRowValue();
				a.setArzt_id_arzt(event.getNewValue());	
				arzt_id_arzt.setEditable(false);
				tabelleData.refresh();
				

			}
			
    		
    	});
    	
    	// anrede Listener für Aktualisieren 
    	patient_id_patient.setEditable(true);
    	patient_id_patient.setCellFactory(TextFieldTableCell.forTableColumn());
    	patient_id_patient.setCellValueFactory(new PropertyValueFactory<Arzt_has_Patient, String>("patient_id_patient"));
    	
    	patient_id_patient.setOnEditCommit(new EventHandler<CellEditEvent<Arzt_has_Patient, String>>(){

			@Override
			public void handle(CellEditEvent<Arzt_has_Patient, String> event) {
				
				Arzt_has_Patient a = event.getRowValue();
		    	a.setPatient_id_patient(event.getNewValue());
		    	
		    	patient_id_patient.setEditable(false);
				tabelleData.refresh();
		    
				
				
				
			}
			
    		
    	});
    	
    	// anrede Listener für Aktualisieren 
    	krankheit.setEditable(true);
    	krankheit.setCellFactory(TextFieldTableCell.forTableColumn());
    	krankheit.setCellValueFactory(new PropertyValueFactory<Arzt_has_Patient, String>("krankheit"));
    	
    	krankheit.setOnEditCommit(new EventHandler<CellEditEvent<Arzt_has_Patient, String>>(){

			@Override
			public void handle(CellEditEvent<Arzt_has_Patient, String> event) {
				
				Arzt_has_Patient a = event.getRowValue();
				a.setKrankheit(event.getNewValue());
				krankheit.setEditable(false);
				tabelleData.refresh();

			}
			
    		
    	});
    	
    	
    	// ARBEITSENDE Listener für Aktualisieren 
    	wunschtermin.setEditable(true);
    	wunschtermin.setCellFactory(TextFieldTableCell.forTableColumn());
    	wunschtermin.setCellValueFactory(new PropertyValueFactory<Arzt_has_Patient, String>("wunschtermin"));
    	
    	wunschtermin.setOnEditCommit(new EventHandler<CellEditEvent<Arzt_has_Patient, String>>(){

			@Override
			public void handle(CellEditEvent<Arzt_has_Patient, String> event) {
				
				Arzt_has_Patient a = event.getRowValue();
				a.setWunschtermin(event.getNewValue());
				wunschtermin.setEditable(false);
				tabelleData.refresh();

			}
    		
    	});
    	
    	
    	this.speichernButton.setVisible(true);
    	this.einfuegenButton.setVisible(false);
    	

    }
    
    
    /**
     *  die Methode einfuegenEvent wird aufgerufen, wenn der check icon geklickt wird
     *  wird ein SQL_Abfrage für Insert der Daten in der Tabelle Arzt_has_Patient in DB erstellt 
     *  und bei Aufruf der Methode sendQuery von Klasse DBManager gesendet
     *  
     * @param event verantwotlich für Anhörung einer Aktion
     * @author Ahmad, Akram, Nour 
     *
     */
    @FXML
    public void speichernEvent(ActionEvent event)
    {
    	
    	Arzt_has_Patient a = arzt_has_patient.get(arzt_has_patient.size()-1);
    
    	
    	if(!a.getArzt_id_arzt().isEmpty() && !a.getPatient_id_patient().isEmpty())
    	{
    		
    		String queryString = "INSERT INTO arzt_has_patient (arzt_id_arzt, patient_id_patient, krankheit, wunschtermin) "
    		+ "VALUES (" + a.getArzt_id_arzt()+ ", " + a.getPatient_id_patient() + ", '" + a.getKrankheit() + "'," + "'" + a.getWunschtermin() + "')";
    		
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
    	arzt_id_arzt.setEditable(false);
    	patient_id_patient.setEditable(false);
    	krankheit.setEditable(false);
    	wunschtermin.setEditable(false);
    	
    	this.speichernButton.setVisible(false);
    	this.einfuegenButton.setVisible(true);
    	
    	
    }
    
    
    
    /**
     *  in der Methode initialize wird eine SQL_Abfrage für Holen der Arzt_has_Patient Daten 
     *  anhand der Methode getAlleDatenArzt_has_Patient in der Klasse Arzt_has_Patient in der Arzt_has_Patient Liste gespeichert.
     *  Auch werde all Daten von Arzt_has_Patient Liste werden in der Liste vom Typ ObservableList gespeichert,
     *  die es Zuhörern ermöglicht, Änderungen zu verfolgen, wenn sie auftreten.
     * @param url ist nicht benutzt
     * @param rb ist nicht benutzt
     * @author Ahmad, Akram, Nour 
     *
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	
    	
    	String queryString = "select arzt_id_arzt, patient_id_patient, krankheit,  TO_CHAR(wunschtermin,'DD.MM.YYYY HH:MM:SS') from arzt_has_patient";
    	
    		
		a = Arzt_has_Patient.getAlleDatenArzt_has_Patient(queryString);
			
			
		
    	arzt_has_patient.addAll(a);
    	
    	arzt_id_arzt.setCellValueFactory(new PropertyValueFactory<>("arzt_id_arzt"));
    	patient_id_patient.setCellValueFactory(new PropertyValueFactory<>("patient_id_patient"));
    	krankheit.setCellValueFactory(new PropertyValueFactory<>("krankheit"));
    	wunschtermin.setCellValueFactory(new PropertyValueFactory<>("wunschtermin"));

		
		
		tabelleData.setItems(arzt_has_patient);
		
    }    
    
   private ObservableList<Arzt_has_Patient> arzt_has_patient = FXCollections.observableArrayList();
   
   
    
    
}
