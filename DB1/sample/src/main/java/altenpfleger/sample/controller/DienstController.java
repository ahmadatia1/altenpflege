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
				
				System.out.println( event.getNewValue());
				
					
				
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
						Dienst.updateDienst(querey);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		    		
					d.setARBEITSBEGINN(event.getNewValue());
					
					tabelleData.refresh();
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
						Dienst.updateDienst(querey);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		    		
					d.setARBEITSENDE(event.getNewValue());
					
					tabelleData.refresh();
		    	} else {
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
	    		String querey = "delete from dienst where ALTENPFLEGE_ID_ALTENPFLEGE='" 
	    				+ d.getALTENPFLEGE_ID_ALTENPFLEGE() +"'" 
	    				+ "and PATIENT_ID_PATIENT='" + d.getPATIENT_ID_PATIENT()+"'";
	    		
	    		try {
					Dienst.removeDienst(querey);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    		
	    		boolean status = tabelleData.getItems().remove(d);
	    		System.out.print(status);
	    	    
	    	} else {
	    		
	    		System.out.print("kein Datensatz ausgewählt !!!");
	    	    
	    	}
	    	
			
    	}
    	
    	
    	
    }
    
    
    
    
    @FXML
    private void dienstplanHinzufuegen(ActionEvent event) throws IOException
    {
    	
    	dienst.add(new Dienst( (dienst.size()+1)+ "", "", "", ""));
    	System.out.println(dienst.size());
    	try {
			String querey = "insert into patient values (5, '', '', '', 'test', '')";
					
			Patient.insertPatient(querey);
		} catch (SQLException e) {
			
			e.getMessage();
		}
    	
    	this.editButton(event);
    	
    	
    	
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	
    	
    	String queryString = "select ALTENPFLEGE_ID_ALTENPFLEGE, PATIENT_ID_PATIENT,TO_CHAR(ARBEITSBEGINN,'DD.MM.YYYY HH:MM:SS'),  TO_CHAR(ARBEITSENDE,'DD.MM.YYYY HH:MM:SS') from dienst";
    	try {
			d = Dienst.sendQuery(queryString);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
