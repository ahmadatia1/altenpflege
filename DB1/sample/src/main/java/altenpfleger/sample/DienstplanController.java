package altenpfleger.sample;
/*
Put header here


 */

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

import altenpfleger.sample.dbservices.DBManager;
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


public class DienstplanController implements Initializable {
	

	private ArrayList<Patient> p;
	
	
	
  
    
    
    @FXML private TableView<Patient> tabelleData;
    @FXML private TableColumn<Patient, String> id;
    @FXML private TableColumn<Patient, String> nachname;
    @FXML private TableColumn<Patient, String> vorname;
    @FXML private TableColumn<Patient, Integer> checkbox;
  
    
   
    @FXML
    private void dienstplanEditButton(ActionEvent event) throws IOException {
    	
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
		    			String querey = "update kunde"
		    					+ " Set nachname='" + event.getNewValue() + "'" 
		    					+ " where idkunde='" + event.getRowValue().getId() +"'";
						Patient.updatePatient(querey);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		    		
					p.setNachname(event.getNewValue());
					tabelleData.refresh();
		    	} else {
		    	   p.setNachname(event.getOldValue());
		    	   tabelleData.refresh();
		    	   
		    	}
				
				
				
			}
    		
    	});
    	
    	
    	
    	
    	
    	/*
    	String querey = "update kunde"
				+ "set nachname=" 
				+ "where idkunde=";
				
		
			try {
				Patient.updatePatient(querey);
				
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}	*/
    	
    

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
	    		String querey = "delete from kunde where idkunde=" + p.getId();
	    		
	    		try {
					Patient.removePatient(querey);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    		
	    		boolean status = tabelleData.getItems().remove(p);
	    		System.out.print(status);
	    	    
	    	} else {
	    		
	    		System.out.print("kein Datensatz ausgewählt !!!");
	    	    
	    	}
	    	
			
    	}
    	
    	
    	
    }
    
    
    
    
    @FXML
    private void dienstplanHinzufuegen(ActionEvent event) throws IOException
    {
    	MainApp.setRoot("Formular", "Formular");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	
    	
    	String queryString = "select idkunde, nachname, vorname From kunde";
    	try {
			p = Patient.sendQuery(queryString);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		patient.addAll(p);
    	
    	id.setCellValueFactory(new PropertyValueFactory<>("id"));
		nachname.setCellValueFactory(new PropertyValueFactory<>("nachname"));
		vorname.setCellValueFactory(new PropertyValueFactory<>("vorname"));
		
		
		
		tabelleData.setItems(patient);
		
    	
    }    
    
   private ObservableList<Patient> patient = FXCollections.observableArrayList();
   
   
    
    
}
