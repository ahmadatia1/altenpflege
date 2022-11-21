package altenpfleger.sample.controller;
/*
Put header here


 */

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import altenpfleger.sample.dbservices.DBManager;
import altenpfleger.sample.model.Adresse;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import java.util.*;


public class AdresseController implements Initializable {
	
	
	private ArrayList<Adresse> a;
	
	
	@FXML private Button einfuegenButton;
	@FXML private Button speichernButton;
  
	
    
    @FXML private TableView<Adresse> tabelleData;
    @FXML private TableColumn<Adresse, String> id_adresse;
    @FXML private TableColumn<Adresse, String> id_patient;
    @FXML private TableColumn<Adresse, String> strasse;
    @FXML private TableColumn<Adresse, String> hausnummer;
    @FXML private TableColumn<Adresse, String> ort;
    @FXML private TableColumn<Adresse, String> plz;

 
    
   
    @FXML
    private void editButton(ActionEvent event) throws IOException {
    	
    	
    	
    	// anrede Listener für Aktualisieren 
    	strasse.setEditable(true);
    	strasse.setCellFactory(TextFieldTableCell.forTableColumn());
    	strasse.setCellValueFactory(new PropertyValueFactory<Adresse, String>("strasse"));
    	
    	strasse.setOnEditCommit(new EventHandler<CellEditEvent<Adresse, String>>(){

			@Override
			public void handle(CellEditEvent<Adresse, String> event) {
				
				Adresse a = event.getRowValue();
				Alert alert = new Alert(AlertType.CONFIRMATION);
		    	alert.setTitle("Update");
		    	alert.setHeaderText("strasse ändern und aktualisieren");
		    	alert.setContentText("Bitte bstätigen?");

		    	Optional<ButtonType> result = alert.showAndWait();
		    	
		    	
		    	if (result.get() == ButtonType.OK){
		    		
		    		try {
		    			String querey = "update Adresse"
		    					+ " Set strasse='" + event.getNewValue() + "'" 
		    					+ " where id_adresse='" + event.getRowValue().getId_adresse()+"'";
		    			DBManager.sendQuery(querey);
						a.setStrasse(event.getNewValue());
					} catch (SQLException e) {
						
						DBManager.printSQLException(e);
						a.setStrasse(event.getOldValue());
					}

					tabelleData.refresh();
		    	} else {
		    		
		    		 alert = new Alert(AlertType.ERROR);
		    		 alert.setContentText("Bitte geben Sie entweder Herr odr Frau ein!!!");
			    	 result = alert.showAndWait();
			    	 
			    	 a.setStrasse(event.getOldValue());
			    	 tabelleData.refresh();
		    	   
		    	}
				
				
				
				
			}
			
    		
    	});
    	
    	
    	// anrede Listener für Aktualisieren 
    	hausnummer.setEditable(true);
    	hausnummer.setCellFactory(TextFieldTableCell.forTableColumn());
    	hausnummer.setCellValueFactory(new PropertyValueFactory<Adresse, String>("hausnummer"));
    	
    	hausnummer.setOnEditCommit(new EventHandler<CellEditEvent<Adresse, String>>(){

			@Override
			public void handle(CellEditEvent<Adresse, String> event) {
				
				Adresse a = event.getRowValue();
				Alert alert = new Alert(AlertType.CONFIRMATION);
		    	alert.setTitle("Update");
		    	alert.setHeaderText("hausnummer ändern und aktualisieren");
		    	alert.setContentText("Bitte bstätigen?");

		    	Optional<ButtonType> result = alert.showAndWait();
		    	
		    	
		    	if (result.get() == ButtonType.OK){
		    		
		    		try {
		    			String querey = "update Adresse"
		    					+ " Set strasse='" + event.getNewValue() + "'" 
		    					+ " where id_adresse='" + event.getRowValue().getId_adresse()+"'";
		    			DBManager.sendQuery(querey);
						a.setHausnummer(event.getNewValue());
					} catch (SQLException e) {
						
						DBManager.printSQLException(e);
						a.setHausnummer(event.getOldValue());
					}
		    		
					
					
					tabelleData.refresh();
		    	} else {
		    		
		    		 alert = new Alert(AlertType.ERROR);
		    		 alert.setContentText("Bitte geben Sie entweder Herr odr Frau ein!!!");
			    	 result = alert.showAndWait();
			    	 
			    	 a.setHausnummer(event.getOldValue());
			    	 tabelleData.refresh();
		    	   
		    	}
				
				
				
				
			}
			
    		
    	});
    	
    
    	// anrede Listener für Aktualisieren 
    	ort.setEditable(true);
    	ort.setCellFactory(TextFieldTableCell.forTableColumn());
    	ort.setCellValueFactory(new PropertyValueFactory<Adresse, String>("ort"));
    	
    	ort.setOnEditCommit(new EventHandler<CellEditEvent<Adresse, String>>(){

			@Override
			public void handle(CellEditEvent<Adresse, String> event) {
				
				Adresse a = event.getRowValue();
				Alert alert = new Alert(AlertType.CONFIRMATION);
		    	alert.setTitle("Update");
		    	alert.setHeaderText("ort ändern und aktualisieren");
		    	alert.setContentText("Bitte bstätigen?");

		    	Optional<ButtonType> result = alert.showAndWait();
		    	
		    	
		    	if (result.get() == ButtonType.OK){
		    		
		    		try {
		    			String querey = "update Adresse"
		    					+ " Set ort='" + event.getNewValue() + "'" 
		    					+ " where id_adresse='" + event.getRowValue().getId_adresse()+"'";
		    			DBManager.sendQuery(querey);
						a.setOrt(event.getNewValue());
					} catch (SQLException e) {
						
						DBManager.printSQLException(e);
						a.setOrt(event.getOldValue());
					}
		    		
					
					
					tabelleData.refresh();
		    	} else {
		    		
		    		 alert = new Alert(AlertType.ERROR);
		    		 alert.setContentText("Bitte geben Sie entweder Herr odr Frau ein!!!");
			    	 result = alert.showAndWait();
			    	
			    	 a.setOrt(event.getOldValue());
			    	 tabelleData.refresh();
		    	   
		    	}
				
				
				
				
			}
			
    		
    	});
    	
    	
    	// anrede Listener für Aktualisieren 
    	plz.setEditable(true);
    	plz.setCellFactory(TextFieldTableCell.forTableColumn());
    	plz.setCellValueFactory(new PropertyValueFactory<Adresse, String>("plz"));
    	
    	plz.setOnEditCommit(new EventHandler<CellEditEvent<Adresse, String>>(){

			@Override
			public void handle(CellEditEvent<Adresse, String> event) {
				
				Adresse a = event.getRowValue();
				Alert alert = new Alert(AlertType.CONFIRMATION);
		    	alert.setTitle("Update");
		    	alert.setHeaderText("plz ändern und aktualisieren");
		    	alert.setContentText("Bitte bstätigen?");

		    	Optional<ButtonType> result = alert.showAndWait();
		    	
		    	
		    	if (result.get() == ButtonType.OK){
		    		
		    		try {
		    			String querey = "update Adresse"
		    					+ " Set plz='" + event.getNewValue() + "'" 
		    					+ " where id_adresse='" + event.getRowValue().getId_adresse()+"'";
		    			DBManager.sendQuery(querey);
						a.setPlz(event.getNewValue());
					} catch (SQLException e) {
						
						DBManager.printSQLException(e);
						a.setPlz(event.getOldValue());
					}
		    		
					
					
					tabelleData.refresh();
		    	} else {
		    		
		    		 alert = new Alert(AlertType.ERROR);
		    		 alert.setContentText("Bitte geben Sie entweder Herr odr Frau ein!!!");
			    	 result = alert.showAndWait();
			    	
			    	 a.setPlz(event.getOldValue());
			    	 tabelleData.refresh();
		    	   
		    	}
				
				
				
				
			}
			
    		
    	});
    	
    	
    	
    	
    	
    	
   	
    }
    
    
    @FXML
    private void deleteButton(ActionEvent event) throws IOException
    {
    	
    	
    	Adresse a = tabelleData.getSelectionModel().getSelectedItem();

    	if(a!=null)
    	{
			Alert alert = new Alert(AlertType.CONFIRMATION);
	    	alert.setTitle("Confirmation Dialog");
	    	alert.setHeaderText("Look, a Confirmation Dialog");
	    	alert.setContentText("Are you ok with this?");

	    	Optional<ButtonType> result = alert.showAndWait();
	    	if (result.get() == ButtonType.OK){
	    		String querey = "delete from Adresse where id_arzt=" + a.getId_adresse();
	    		
	    		try {
	    			DBManager.sendQuery(querey);
				} 
	    		catch (SQLException e) 
	    		{
					DBManager.printSQLException(e);
				}
	    		
	    		boolean status = tabelleData.getItems().remove(a);
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
    	
    	adresse.add(new Adresse("hier ändern", "hier ändern", "hier ändern", "hier ändern", "hier ändern", "hier ändern"));
    	
    	
    	// anrede Listener für Aktualisieren 
    	id_adresse.setEditable(true);
    	id_adresse.setCellFactory(TextFieldTableCell.forTableColumn());
    	id_adresse.setCellValueFactory(new PropertyValueFactory<Adresse, String>("id_adresse"));
    	
    	id_adresse.setOnEditCommit(new EventHandler<CellEditEvent<Adresse, String>>(){

			@Override
			public void handle(CellEditEvent<Adresse, String> event) {
				
				Adresse a = event.getRowValue();
				a.setId_adresse(event.getNewValue());
				System.out.print(a.getId_adresse());
				//id_arzt.setEditable(false);
				tabelleData.refresh();			

			}
			
    		
    	});
    	
    	
    	// anrede Listener für Aktualisieren 
    	id_patient.setEditable(true);
    	id_patient.setCellFactory(TextFieldTableCell.forTableColumn());
    	id_patient.setCellValueFactory(new PropertyValueFactory<Adresse, String>("id_patient"));
    	
    	id_patient.setOnEditCommit(new EventHandler<CellEditEvent<Adresse, String>>(){

			@Override
			public void handle(CellEditEvent<Adresse, String> event) {
				
				Adresse a = event.getRowValue();
				a.setId_patient(event.getNewValue());	
				//id_arzt.setEditable(false);
				tabelleData.refresh();
				

			}
			
    		
    	});
    	
    	// anrede Listener für Aktualisieren   
    	strasse.setEditable(true);
    	strasse.setCellFactory(TextFieldTableCell.forTableColumn());
    	strasse.setCellValueFactory(new PropertyValueFactory<Adresse, String>("strasse"));
    	
    	strasse.setOnEditCommit(new EventHandler<CellEditEvent<Adresse, String>>(){

			@Override
			public void handle(CellEditEvent<Adresse, String> event) {
				
				Adresse a = event.getRowValue();
				a.setStrasse(event.getNewValue());	
				//id_arzt.setEditable(false);
				tabelleData.refresh();
				

			}
			
    		
    	});
    	
    	// anrede Listener für Aktualisieren   
    	hausnummer.setEditable(true);
    	hausnummer.setCellFactory(TextFieldTableCell.forTableColumn());
    	hausnummer.setCellValueFactory(new PropertyValueFactory<Adresse, String>("hausnummer"));
    	
    	hausnummer.setOnEditCommit(new EventHandler<CellEditEvent<Adresse, String>>(){

			@Override
			public void handle(CellEditEvent<Adresse, String> event) {
				
				Adresse a = event.getRowValue();
				a.setHausnummer(event.getNewValue());	
				//id_arzt.setEditable(false);
				tabelleData.refresh();
				

			}
			
    		
    	});
    	
    	// anrede Listener für Aktualisieren  
    	ort.setEditable(true);
    	ort.setCellFactory(TextFieldTableCell.forTableColumn());
    	ort.setCellValueFactory(new PropertyValueFactory<Adresse, String>("ort"));
    	
    	ort.setOnEditCommit(new EventHandler<CellEditEvent<Adresse, String>>(){

			@Override
			public void handle(CellEditEvent<Adresse, String> event) {
				
				Adresse a = event.getRowValue();
				a.setOrt(event.getNewValue());	
				//id_arzt.setEditable(false);
				tabelleData.refresh();
				

			}
			
    		
    	});
    	
    	// anrede Listener für Aktualisieren 
    	plz.setEditable(true);
    	plz.setCellFactory(TextFieldTableCell.forTableColumn());
    	plz.setCellValueFactory(new PropertyValueFactory<Adresse, String>("plz"));
    	
    	plz.setOnEditCommit(new EventHandler<CellEditEvent<Adresse, String>>(){

			@Override
			public void handle(CellEditEvent<Adresse, String> event) {
				
				Adresse a = event.getRowValue();
				a.setPlz(event.getNewValue());	
				//id_arzt.setEditable(false);
				tabelleData.refresh();
				

			}
			
    		
    	});
    	
    	
    	
    	this.speichernButton.setVisible(true);
    	this.einfuegenButton.setVisible(false);	
    	
    }
    
    
    
    @FXML
    public void speichernEvent(ActionEvent event)
    {
    	
    	Adresse a = adresse.get(adresse.size()-1);
    	
    	
    	if(!a.getId_adresse().isEmpty())
    	{
    		
    		String queryString = "INSERT INTO Adresse (id_adresse, id_patient, strasse, hausnummer, ort, plz)"
    		+ " VALUES (" + a.getId_adresse()+ ", " + a.getId_patient() + ", '" + a.getStrasse() + "', " + a.getHausnummer()  + ", '" + a.getOrt() + "', " + a.getPlz() + ")";
    		
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
    	
    
    	
    	String queryString = "select id_adresse, id_patient, strasse, hausnummer, ort, plz From Adresse";
    	try {
			a = Adresse.getAlleDatenAdresse(queryString);
			
			
		} catch (SQLException e) {
			
			DBManager.printSQLException(e);
		}	
		
    	adresse.addAll(a);
    	
    	id_adresse.setCellValueFactory(new PropertyValueFactory<>("id_adresse"));
    	id_patient.setCellValueFactory(new PropertyValueFactory<>("id_patient"));
    	strasse.setCellValueFactory(new PropertyValueFactory<>("strasse"));
    	hausnummer.setCellValueFactory(new PropertyValueFactory<>("hausnummer"));
    	ort.setCellValueFactory(new PropertyValueFactory<>("ort"));
    	plz.setCellValueFactory(new PropertyValueFactory<>("plz"));

		
		
		tabelleData.setItems(adresse);
		
    	
    }    
    
   private ObservableList<Adresse> adresse = FXCollections.observableArrayList();
   
   
    
    
}
