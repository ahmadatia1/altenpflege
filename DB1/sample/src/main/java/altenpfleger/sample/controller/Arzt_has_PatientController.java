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


public class Arzt_has_PatientController implements Initializable {
	

	private ArrayList<Arzt_has_Patient> a;
	
	
	
	@FXML private Button einfuegenButton;
	@FXML private Button speichernButton;

		
		
    
    
    @FXML private TableView<Arzt_has_Patient> tabelleData;
    @FXML private TableColumn<Arzt_has_Patient, String> arzt_id_arzt;
    @FXML private TableColumn<Arzt_has_Patient, String> patient_id_patient;
    @FXML private TableColumn<Arzt_has_Patient, String> krankheit;
    @FXML private TableColumn<Arzt_has_Patient, String> wunschtermin;

   
    
   
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
						
						tabelleData.refresh();
					} catch (SQLException e) {
						DBManager.printSQLException(e);
						a.setKrankheit(event.getOldValue());
					}
		    		
		    		
		    		
					
		    	} else {
		    		
		    		alert = new Alert(AlertType.ERROR);
			    	result = alert.showAndWait();
			    	tabelleData.refresh();
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
						
						tabelleData.refresh();
					} catch (SQLException e) {
						DBManager.printSQLException(e);
						a.setWunschtermin(event.getOldValue());
					}
		    		
		    		
		    		
					
		    	} else {
		    		
		    		alert = new Alert(AlertType.ERROR);
			    	result = alert.showAndWait();
			    	tabelleData.refresh();
			    	a.setWunschtermin(event.getOldValue());
		    	   	tabelleData.refresh();
		    	   
		    	}
				
				
				
				
			}
			
    		
    	});
    	
    
    	
   	
    }
    
    
    @FXML
    private void deleteButton(ActionEvent event) throws IOException
    {
    	
    	
    	Arzt_has_Patient a = tabelleData.getSelectionModel().getSelectedItem();
    	
    	if(a!=null)
    	{
			Alert alert = new Alert(AlertType.CONFIRMATION);
	    	alert.setTitle("Confirmation Dialog");
	    	alert.setHeaderText("Look, a Confirmation Dialog");
	    	alert.setContentText("Are you ok with this?");

	    	Optional<ButtonType> result = alert.showAndWait();
	    	if (result.get() == ButtonType.OK){
	    		String querey = "delete from Arzt_has_Patient where arzt_id_arzt='" + a.getArzt_id_arzt() +"'" 
	    				+ "and patient_id_patient='" + a.getPatient_id_patient()+"'";
	    		
	    		try {
	    			DBManager.sendQuery(querey);
				} 
	    		catch (SQLException e) 
	    		{
					DBManager.printSQLException(e);
				}
	    		
	    		
	    		
	    		boolean status = tabelleData.getItems().remove(a);
	    		System.out.print(status);
	    	    
	    	} else {
	    		
	    		System.out.print("kein Datensatz ausgewählt !!!");
	    	    
	    	}
	    	
			
    	}
    	
    	
    	
    }
    
    
    
    
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
    	
    	
    	String queryString = "select arzt_id_arzt, patient_id_patient, krankheit,  TO_CHAR(wunschtermin,'DD.MM.YYYY HH:MM:SS') from arzt_has_patient";
    	try {
    		
			a = Arzt_has_Patient.getAlleDatenArzt_has_Patient(queryString);
			
			
		} catch (SQLException e) {
			DBManager.printSQLException(e);
		}	
		
    	arzt_has_patient.addAll(a);
    	
    	arzt_id_arzt.setCellValueFactory(new PropertyValueFactory<>("arzt_id_arzt"));
    	patient_id_patient.setCellValueFactory(new PropertyValueFactory<>("patient_id_patient"));
    	krankheit.setCellValueFactory(new PropertyValueFactory<>("krankheit"));
    	wunschtermin.setCellValueFactory(new PropertyValueFactory<>("wunschtermin"));

		
		
		tabelleData.setItems(arzt_has_patient);
		
    }    
    
   private ObservableList<Arzt_has_Patient> arzt_has_patient = FXCollections.observableArrayList();
   
   
    
    
}
