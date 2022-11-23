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

/**
 *  Klasse DienstController kontrolliert der View bzw. Adresse.fxml Datei
 *  Events werden augeführt, Textfeldern werden bearbeiten und neue Werte gespeichert, etc..
 *  
 * @author Ahmad, Akram, Nour 
 *
 */

public class DienstController implements Initializable {
	

	private ArrayList<Dienst> d;
	
	
	
	@FXML private Button einfuegenButton;
	@FXML private Button speichernButton;

		
		
    
    
    @FXML private TableView<Dienst> tabelleData;
    @FXML private TableColumn<Dienst, String> ALTENPFLEGE_ID_ALTENPFLEGE;
    @FXML private TableColumn<Dienst, String> PATIENT_ID_PATIENT;
    @FXML private TableColumn<Dienst, String> ARBEITSBEGINN;
    @FXML private TableColumn<Dienst, String> ARBEITSENDE;

 
    
    /**
     *  die Methode editButton wird aufgerufen, wenn der Edit icon geklickt wird
     *  wird EditEvent für die Textfeldern in der Tabelle angehört und bei neuen Wertern sowohl in db als auch in der List Dienst aktualisiert 
     *  
     * @param event verantwotlich für Anhörung einer Aktion
     * @author Ahmad, Akram, Nour 
     *
     */
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
		    					+ " where ALTENPFLEGER_ID_ALTENPFLEGER='" + event.getRowValue().getALTENPFLEGE_ID_ALTENPFLEGE() +"' and " 
		    					+ " PATIENT_ID_PATIENT='" + event.getRowValue().getPATIENT_ID_PATIENT()+"'";
		    			DBManager.sendQuery(querey);
						d.setARBEITSBEGINN(event.getNewValue());
						ARBEITSBEGINN.setEditable(false);
					
					} catch (SQLException e) {
						String err = DBManager.printSQLException(e);
						alert = new Alert(AlertType.ERROR);
						alert.setContentText(err);
						result = alert.showAndWait();
						d.setARBEITSBEGINN(event.getOldValue());
						ARBEITSBEGINN.setEditable(true);
					}
		    		
		    		
		    		tabelleData.refresh();
					
		    	} else {
		    		
		    		ARBEITSBEGINN.setEditable(true);
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
		    					+ " where ALTENPFLEGER_ID_ALTENPFLEGER='" + event.getRowValue().getALTENPFLEGE_ID_ALTENPFLEGE() +"' and " 
		    					+ " PATIENT_ID_PATIENT='" + event.getRowValue().getPATIENT_ID_PATIENT()+"'";
		    			DBManager.sendQuery(querey);
						d.setARBEITSENDE(event.getNewValue());
					
						ARBEITSENDE.setEditable(false);
					} 
		    		catch (SQLException e) 
		    		{
		    			String err = DBManager.printSQLException(e);
						alert = new Alert(AlertType.ERROR);
						alert.setContentText(err);
						result = alert.showAndWait();
						d.setARBEITSENDE(event.getOldValue());
						ARBEITSENDE.setEditable(true);
					}
		    		
		    		tabelleData.refresh();
					
		    	} 
		    	else 
		    	{
		    		ARBEITSENDE.setEditable(true);
		    	   d.setARBEITSENDE(event.getOldValue());
		    	   tabelleData.refresh();
		    	   
		    	}
				
				
				
			}
    		
    	});
    
    	
   	
    }
    
    /**
     *  die Methode deleteButton wird aufgerufen, wenn der delete icon geklickt wird
     *  wird DeleteEvent angehört, um einer Dienst in db und auch in der Liste zulöschen
     *  
     * @param event verantwotlich für Anhörung einer Aktion
     * @author Ahmad, Akram, Nour 
     *
     */
    @FXML
    private void deleteButton(ActionEvent event) throws IOException
    {
    	
    	
    	Dienst d = tabelleData.getSelectionModel().getSelectedItem();
    	
    	if(d!=null)
    	{
			Alert alert = new Alert(AlertType.CONFIRMATION);
	    	alert.setTitle("Dienst löschen");
	    	alert.setContentText("Wollen Sie es sicher löschen?");

	    	Optional<ButtonType> result = alert.showAndWait();
	    	if (result.get() == ButtonType.OK){
	    		String querey = "delete from dienst where ALTENPFLEGER_ID_ALTENPFLEGER='" + d.getALTENPFLEGE_ID_ALTENPFLEGE() +"'" 
	    				+ "and PATIENT_ID_PATIENT='" + d.getPATIENT_ID_PATIENT()+"'";
	    		
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
	    		
	    		
	    		
	    		boolean status = tabelleData.getItems().remove(d);
	    		System.out.print(status);
	    	    
	    	} else {
	    		
	    		System.out.print("kein Datensatz ausgewählt !!!");
	    	    
	    	}
	    	
			
    	}
    	
    	
    	
    }
    
    
    
    /**
     *  die Methode einfuegenEvent wird aufgerufen, wenn der plus icon geklickt wird
     *  wird einfuegenEvent für die Textfeldern in der Tabelle angehört. 
     *  zuerst wird default werte in der Textfeldern eingesetzt 
     *  und nach Berarbeitung werden die Werte in der Liste Dienst 
     *  und in der Tabelle gespeichert
     *  
     * @param event verantwotlich für Anhörung einer Aktion
     * @author Ahmad, Akram, Nour 
     *
     */
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
				
				tabelleData.refresh();

			}
    		
    	});
    	
    	
    	this.speichernButton.setVisible(true);
    	this.einfuegenButton.setVisible(false);
    	

    }
    
    
    /**
     *  die Methode einfuegenEvent wird aufgerufen, wenn der check icon geklickt wird
     *  wird ein SQL_Abfrage für Insert der Daten in der Tabelle Dienst in DB erstellt 
     *  und bei Aufruf der Methode sendQuery von Klasse DBManager gesendet
     *  
     * @param event verantwotlich für Anhörung einer Aktion
     * @author Ahmad, Akram, Nour 
     *
     */
    @FXML
    public void speichernEvent(ActionEvent event)
    {
    	
    	Dienst d = dienst.get(dienst.size()-1);
    	System.out.print(d.getALTENPFLEGE_ID_ALTENPFLEGE());
    	
    	if(!d.getALTENPFLEGE_ID_ALTENPFLEGE().isEmpty() && !d.getPATIENT_ID_PATIENT().isEmpty())
    	{
    		
    		String queryString = "INSERT INTO Dienst (ALTENPFLEGER_ID_ALTENPFLEGER, PATIENT_ID_PATIENT, ARBEITSBEGINN, ARBEITSENDE) "
    		+ "VALUES (" + d.getALTENPFLEGE_ID_ALTENPFLEGE() + ", " + d.getPATIENT_ID_PATIENT() + ", '" + d.getARBEITSBEGINN() + "'," + "'" + d.getARBEITSENDE() + "')";
    		
        	try {
    			DBManager.sendQuery(queryString);
    			
    			
    		} catch (SQLException e) {
    			String err = DBManager.printSQLException(e);
				Alert alert = new Alert(AlertType.ERROR);
				alert.setContentText(err);
				Optional <ButtonType>result = alert.showAndWait();
    		}	
    		

    	}
    	else	
    	{
    		System.out.print("Error");
    	}
    	
    	ALTENPFLEGE_ID_ALTENPFLEGE.setEditable(false);
		PATIENT_ID_PATIENT.setEditable(false);
		ARBEITSBEGINN.setEditable(false);
		ARBEITSENDE.setEditable(false);

    	
    	this.speichernButton.setVisible(false);
    	this.einfuegenButton.setVisible(true);
    	
    	
    }
    
    /**
     *  in der Methode initialize wird eine SQL_Abfrage für Holen der Dienst Daten 
     *  anhand der Methode getAlleDatenDienst in der Klasse Adresse in der Dienst Liste gespeichert.
     *  Auch werde all Daten von Dienst Liste werden in der Liste vom Typ ObservableList gespeichert,
     *  die es Zuhörern ermöglicht, Änderungen zu verfolgen, wenn sie auftreten.
     * @param url ist nicht benutzt
     * @param rb ist nicht benutzt
     * @author Ahmad, Akram, Nour 
     *
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	
    	
    	String queryString = "select ALTENPFLEGER_ID_ALTENPFLEGER, PATIENT_ID_PATIENT,TO_CHAR(ARBEITSBEGINN,'DD.MM.YYYY HH:MM:SS'),  TO_CHAR(ARBEITSENDE,'DD.MM.YYYY HH:MM:SS') from dienst";
    	
		d = Dienst.getAlleDatenDienst(queryString);
			
			
		
		dienst.addAll(d);
    	
		ALTENPFLEGE_ID_ALTENPFLEGE.setCellValueFactory(new PropertyValueFactory<>("ALTENPFLEGE_ID_ALTENPFLEGE"));
		PATIENT_ID_PATIENT.setCellValueFactory(new PropertyValueFactory<>("PATIENT_ID_PATIENT"));
		ARBEITSBEGINN.setCellValueFactory(new PropertyValueFactory<>("ARBEITSBEGINN"));
		ARBEITSENDE.setCellValueFactory(new PropertyValueFactory<>("ARBEITSENDE"));

		tabelleData.setItems(dienst);
		
    	
    }    
    
   private ObservableList<Dienst> dienst = FXCollections.observableArrayList();
   
   
    
    
}
