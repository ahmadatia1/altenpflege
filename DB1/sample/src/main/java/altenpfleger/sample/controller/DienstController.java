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


public class DienstController implements Initializable {
	

	private ArrayList<Dienst> d;
	
	
	
	@FXML private Button einfuegenButton;
	@FXML private Button speichernButton;

		
		
    
    
    @FXML private TableView<Dienst> tabelleData;
    @FXML private TableColumn<Dienst, String> ALTENPFLEGE_ID_ALTENPFLEGE;
    @FXML private TableColumn<Dienst, String> PATIENT_ID_PATIENT;
    @FXML private TableColumn<Dienst, String> ARBEITSBEGINN;
    @FXML private TableColumn<Dienst, String> ARBEITSENDE;

 
    
   
    @FXML
    private void editButton(ActionEvent event) throws IOException {
    	
    	
    	// anrede Listener für Aktualisieren 
    	ARBEITSBEGINN.setEditable(true);
    	ARBEITSBEGINN.setCellFactory(TextFieldTableCell.forTableColumn());
    	ARBEITSBEGINN.setCellValueFactory(new PropertyValueFactory<Dienst, String>("ARBEITSBEGINN"));
    	
    	ARBEITSBEGINN.setOnEditCommit(new EventHandler<CellEditEvent<Dienst, String>>(){

			@Override
			public void handle(CellEditEvent<Dienst, String> event) {
				
				Dienst d = event.getRowValue();
				Alert alert = new Alert(AlertType.CONFIRMATION);
		    	alert.setTitle("Update");
		    	alert.setHeaderText("ARBEITSBEGINN ändern und aktualisieren");
		    	alert.setContentText("Bitte bstätigen?");

		    	Optional<ButtonType> result = alert.showAndWait();
		    	
		    	
		    	if (result.get() == ButtonType.OK){
		    		
		    		try {
		    			String querey = "update Dienst"
		    					+ " Set ARBEITSBEGINN='" + event.getNewValue() + "'" 
		    					+ " where ALTENPFLEGE_ID_ALTENPFLEGE='" + event.getRowValue().getALTENPFLEGE_ID_ALTENPFLEGE() +"' and " 
		    					+ " PATIENT_ID_PATIENT='" + event.getRowValue().getPATIENT_ID_PATIENT()+"'";
		    			DBManager.sendQuery(querey);
						d.setARBEITSBEGINN(event.getNewValue());
						
						tabelleData.refresh();
					} catch (SQLException e) {
						DBManager.printSQLException(e);
						d.setARBEITSBEGINN(event.getOldValue());
					}
		    		
		    		ARBEITSBEGINN.setEditable(false);
					ARBEITSENDE.setEditable(false);
		    		
					
		    	} else {
		    		
		    		alert = new Alert(AlertType.ERROR);
			    	result = alert.showAndWait();
			    	tabelleData.refresh();
			    	d.setARBEITSBEGINN(event.getOldValue());
		    	   	tabelleData.refresh();
		    	   
		    	}
				
				
				
				
			}
			
    		
    	});
    	
    	
    	// ARBEITSENDE Listener für Aktualisieren 
    	ARBEITSENDE.setEditable(true);
    	ARBEITSENDE.setCellFactory(TextFieldTableCell.forTableColumn());
    	ARBEITSENDE.setCellValueFactory(new PropertyValueFactory<Dienst, String>("ARBEITSENDE"));
    	
    	ARBEITSENDE.setOnEditCommit(new EventHandler<CellEditEvent<Dienst, String>>(){

			@Override
			public void handle(CellEditEvent<Dienst, String> event) {
				
				Dienst d = event.getRowValue();
				
				Alert alert = new Alert(AlertType.CONFIRMATION);
		    	alert.setTitle("Update");
		    	alert.setHeaderText("ARBEITSENDE ändern und aktualisieren");
		    	alert.setContentText("Bitte bstätigen?");

		    	Optional<ButtonType> result = alert.showAndWait();
		    	if (result.get() == ButtonType.OK){
		    		
		    		try {
		    			String querey = "update Dienst"
		    					+ " Set ARBEITSENDE='" + event.getNewValue() + "'" 
		    					+ " where ALTENPFLEGE_ID_ALTENPFLEGE='" + event.getRowValue().getALTENPFLEGE_ID_ALTENPFLEGE() +"' and " 
		    					+ " PATIENT_ID_PATIENT='" + event.getRowValue().getPATIENT_ID_PATIENT()+"'";
		    			DBManager.sendQuery(querey);
						d.setARBEITSENDE(event.getNewValue());
					
						tabelleData.refresh();
					} 
		    		catch (SQLException e) 
		    		{
						DBManager.printSQLException(e);
						d.setARBEITSENDE(event.getOldValue());
					}
		    		
		    		ARBEITSBEGINN.setEditable(false);
					ARBEITSENDE.setEditable(false);
					
		    	} 
		    	else 
		    	{
		    	   d.setARBEITSENDE(event.getOldValue());
		    	   tabelleData.refresh();
		    	   
		    	}
				
				
				
			}
    		
    	});
    
    	
   	
    }
    
    
    @FXML
    private void deleteButton(ActionEvent event) throws IOException
    {
    	
    	
    	Dienst d = tabelleData.getSelectionModel().getSelectedItem();
    	
    	if(d!=null)
    	{
			Alert alert = new Alert(AlertType.CONFIRMATION);
	    	alert.setTitle("Confirmation Dialog");
	    	alert.setHeaderText("Look, a Confirmation Dialog");
	    	alert.setContentText("Are you ok with this?");

	    	Optional<ButtonType> result = alert.showAndWait();
	    	if (result.get() == ButtonType.OK){
	    		String querey = "delete from dienst where ALTENPFLEGE_ID_ALTENPFLEGE='" + d.getALTENPFLEGE_ID_ALTENPFLEGE() +"'" 
	    				+ "and PATIENT_ID_PATIENT='" + d.getPATIENT_ID_PATIENT()+"'";
	    		
	    		try {
	    			DBManager.sendQuery(querey);
				} 
	    		catch (SQLException e) 
	    		{
					DBManager.printSQLException(e);
				}
	    		
	    		
	    		
	    		boolean status = tabelleData.getItems().remove(d);
	    		System.out.print(status);
	    	    
	    	} else {
	    		
	    		System.out.print("kein Datensatz ausgewählt !!!");
	    	    
	    	}
	    	
			
    	}
    	
    	
    	
    }
    
    
    
    
    @FXML
    private void einfuegenEvent(ActionEvent event) throws IOException
    {
    	
    	dienst.add(new Dienst("hier ändern", "hier ändern", "hier ändern", "hier ändern"));
    	
    	
    	
    	// anrede Listener für Aktualisieren 
    	ALTENPFLEGE_ID_ALTENPFLEGE.setEditable(true);
    	ALTENPFLEGE_ID_ALTENPFLEGE.setCellFactory(TextFieldTableCell.forTableColumn());
    	ALTENPFLEGE_ID_ALTENPFLEGE.setCellValueFactory(new PropertyValueFactory<Dienst, String>("ALTENPFLEGE_ID_ALTENPFLEGE"));
    	
    	ALTENPFLEGE_ID_ALTENPFLEGE.setOnEditCommit(new EventHandler<CellEditEvent<Dienst, String>>(){

			@Override
			public void handle(CellEditEvent<Dienst, String> event) {
				
				Dienst d = event.getRowValue();
				d.setALTENPFLEGE_ID_ALTENPFLEGE(event.getNewValue());	
				ALTENPFLEGE_ID_ALTENPFLEGE.setEditable(false);
				tabelleData.refresh();
				

			}
			
    		
    	});
    	
    	// anrede Listener für Aktualisieren 
    	PATIENT_ID_PATIENT.setEditable(true);
    	PATIENT_ID_PATIENT.setCellFactory(TextFieldTableCell.forTableColumn());
    	PATIENT_ID_PATIENT.setCellValueFactory(new PropertyValueFactory<Dienst, String>("PATIENT_ID_PATIENT"));
    	
    	PATIENT_ID_PATIENT.setOnEditCommit(new EventHandler<CellEditEvent<Dienst, String>>(){

			@Override
			public void handle(CellEditEvent<Dienst, String> event) {
				
				Dienst d = event.getRowValue();
		    	d.setPATIENT_ID_PATIENT(event.getNewValue());
		    	
		    	PATIENT_ID_PATIENT.setEditable(false);
				tabelleData.refresh();
		    
				
				
				
			}
			
    		
    	});
    	
    	// anrede Listener für Aktualisieren 
    	ARBEITSBEGINN.setEditable(true);
    	ARBEITSBEGINN.setCellFactory(TextFieldTableCell.forTableColumn());
    	ARBEITSBEGINN.setCellValueFactory(new PropertyValueFactory<Dienst, String>("ARBEITSBEGINN"));
    	
    	ARBEITSBEGINN.setOnEditCommit(new EventHandler<CellEditEvent<Dienst, String>>(){

			@Override
			public void handle(CellEditEvent<Dienst, String> event) {
				
				Dienst d = event.getRowValue();
				d.setARBEITSBEGINN(event.getNewValue());
				ARBEITSBEGINN.setEditable(false);
				tabelleData.refresh();

			}
			
    		
    	});
    	
    	
    	// ARBEITSENDE Listener für Aktualisieren 
    	ARBEITSENDE.setEditable(true);
    	ARBEITSENDE.setCellFactory(TextFieldTableCell.forTableColumn());
    	ARBEITSENDE.setCellValueFactory(new PropertyValueFactory<Dienst, String>("ARBEITSENDE"));
    	
    	ARBEITSENDE.setOnEditCommit(new EventHandler<CellEditEvent<Dienst, String>>(){

			@Override
			public void handle(CellEditEvent<Dienst, String> event) {
				
				Dienst d = event.getRowValue();
				d.setARBEITSENDE(event.getNewValue());
				ARBEITSENDE.setEditable(false);
				tabelleData.refresh();

			}
    		
    	});
    	
    	
    	this.speichernButton.setVisible(true);
    	this.einfuegenButton.setVisible(false);
    	

    }
    
    
    
    @FXML
    public void speichernEvent(ActionEvent event)
    {
    	
    	Dienst d = dienst.get(dienst.size()-1);
    	System.out.print(d.getALTENPFLEGE_ID_ALTENPFLEGE());
    	
    	if(!d.getALTENPFLEGE_ID_ALTENPFLEGE().isEmpty() && !d.getPATIENT_ID_PATIENT().isEmpty())
    	{
    		
    		String queryString = "INSERT INTO Dienst (ALTENPFLEGE_ID_ALTENPFLEGE, PATIENT_ID_PATIENT, ARBEITSBEGINN, ARBEITSENDE) "
    		+ "VALUES (" + d.getALTENPFLEGE_ID_ALTENPFLEGE() + ", " + d.getPATIENT_ID_PATIENT() + ", '" + d.getARBEITSBEGINN() + "'," + "'" + d.getARBEITSENDE() + "')";
    		
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
    	
    	
    	String queryString = "select ALTENPFLEGE_ID_ALTENPFLEGE, PATIENT_ID_PATIENT,TO_CHAR(ARBEITSBEGINN,'DD.MM.YYYY HH:MM:SS'),  TO_CHAR(ARBEITSENDE,'DD.MM.YYYY HH:MM:SS') from dienst";
    	try {
			d = Dienst.getAlleDatenDienst(queryString);
			System.out.print(d);
			
		} catch (SQLException e) {
			DBManager.printSQLException(e);
		}	
		
		dienst.addAll(d);
    	
		ALTENPFLEGE_ID_ALTENPFLEGE.setCellValueFactory(new PropertyValueFactory<>("ALTENPFLEGE_ID_ALTENPFLEGE"));
		PATIENT_ID_PATIENT.setCellValueFactory(new PropertyValueFactory<>("PATIENT_ID_PATIENT"));
		ARBEITSBEGINN.setCellValueFactory(new PropertyValueFactory<>("ARBEITSBEGINN"));
		ARBEITSENDE.setCellValueFactory(new PropertyValueFactory<>("ARBEITSENDE"));

		
		
		tabelleData.setItems(dienst);
		
    	
    }    
    
   private ObservableList<Dienst> dienst = FXCollections.observableArrayList();
   
   
    
    
}
