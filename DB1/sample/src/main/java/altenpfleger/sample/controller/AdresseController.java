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

/**
 *  Klasse AdresseController kontrolliert der View bzw. Adresse.fxml Datei
 *  Events werden augeführt, Textfeldern werden bearbeiten und neue Werte gespeichert, etc..
 *  
 * @author Ahmad, Akram, Nour 
 *
 */

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

 
    
    /**
     *  die Methode editButton wird aufgerufen, wenn der Edit icon geklickt wird
     *  wird EditEvent für die Textfeldern in der Tabelle angehört 
     *  und bei neuen Wertern sowohl in db als auch in der List Adresse aktualisiert 
     *  
     * @param event verantwotlich für Anhörung einer Aktion
     * @author Ahmad, Akram, Nour 
     *
     */
    @FXML
    private void editButton(ActionEvent event) throws IOException {
    	
    	
    	
    	// anrede Listener für Aktualisieren 
    	strasse.setEditable(true);
    	strasse.setCellFactory(TextFieldTableCell.forTableColumn());
    	strasse.setCellValueFactory(new PropertyValueFactory<Adresse, String>("strasse"));
    	
    	strasse.setOnEditCommit(new EventHandler<CellEditEvent<Adresse, String>>(){

			@Override
			public void handle(CellEditEvent<Adresse, String> event) {
				String err = "";
				Adresse a = event.getRowValue();
				Alert alert = new Alert(AlertType.CONFIRMATION);
		    	alert.setTitle("Update");
		    	alert.setHeaderText("strasse ändern und aktualisieren");
		    	alert.setContentText("Bitte bestätigen?");

		    	Optional<ButtonType> result = alert.showAndWait();
		    	
		    	
		    	if (result.get() == ButtonType.OK){
		    		
		    		try {
		    			String querey = "update Adresse"
		    					+ " Set strasse='" + event.getNewValue() + "'" 
		    					+ " where id_adresse='" + event.getRowValue().getId_adresse()+"'";
		    			DBManager.sendQuery(querey);
						a.setStrasse(event.getNewValue());
						strasse.setEditable(false);
					} catch (SQLException e) {
						
						err = DBManager.printSQLException(e);
						alert = new Alert(AlertType.ERROR);
						alert.setContentText(err);
						result = alert.showAndWait();
						a.setStrasse(event.getOldValue());
						strasse.setEditable(true);
					}

					tabelleData.refresh();
		    	} 
		    	else
		    	{
		    		strasse.setEditable(true);
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
				String err = "";
				Adresse a = event.getRowValue();
				Alert alert = new Alert(AlertType.CONFIRMATION);
		    	alert.setTitle("Update");
		    	alert.setHeaderText("Hausnummer ändern und aktualisieren");
		    	alert.setContentText("Bitte bestätigen?");

		    	Optional<ButtonType> result = alert.showAndWait();
		    	
		    	
		    	if (result.get() == ButtonType.OK){
		    		
		    		try {
		    			String querey = "update Adresse"
		    					+ " Set hausnummer='" + event.getNewValue() + "'" 
		    					+ " where id_adresse='" + event.getRowValue().getId_adresse()+"'";
		    			DBManager.sendQuery(querey);
						a.setHausnummer(event.getNewValue());
						hausnummer.setEditable(false);
					} catch (SQLException e) {
						
						err = DBManager.printSQLException(e);
						alert = new Alert(AlertType.ERROR);
						alert.setContentText(err);
						result = alert.showAndWait();
			    	 
						
						
						a.setHausnummer(event.getOldValue());
						hausnummer.setEditable(true);
						
					}
		    		
					
					
					tabelleData.refresh();
		    	} 
		    	else
		    	{
		    		hausnummer.setEditable(true);
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
				String err = "";
				Adresse a = event.getRowValue();
				Alert alert = new Alert(AlertType.CONFIRMATION);
		    	alert.setTitle("Update");
		    	alert.setHeaderText("ort ändern und aktualisieren");
		    	alert.setContentText("Bitte bestätigen?");

		    	Optional<ButtonType> result = alert.showAndWait();
		    	
		    	
		    	if (result.get() == ButtonType.OK){
		    		
		    		try {
		    			String querey = "update Adresse"
		    					+ " Set ort='" + event.getNewValue() + "'" 
		    					+ " where id_adresse='" + event.getRowValue().getId_adresse()+"'";
		    			DBManager.sendQuery(querey);
						a.setOrt(event.getNewValue());
						ort.setEditable(false);
					} catch (SQLException e) {
						
						err = DBManager.printSQLException(e);
						alert = new Alert(AlertType.ERROR);
						alert.setContentText(err);
						result = alert.showAndWait();
						a.setOrt(event.getOldValue());
						ort.setEditable(true);
					}
		    		
					
					
					tabelleData.refresh();
		    	} 
		    	else
		    	{
		    		ort.setEditable(true);
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
				String err = "";
				Adresse a = event.getRowValue();
				Alert alert = new Alert(AlertType.CONFIRMATION);
		    	alert.setTitle("Update");
		    	alert.setHeaderText("plz ändern und aktualisieren");
		    	alert.setContentText("Bitte bestätigen?");

		    	Optional<ButtonType> result = alert.showAndWait();
		    	
		    	
		    	if (result.get() == ButtonType.OK){
		    		
		    		try {
		    			String querey = "update Adresse"
		    					+ " Set plz='" + event.getNewValue() + "'" 
		    					+ " where id_adresse='" + event.getRowValue().getId_adresse()+"'";
		    			DBManager.sendQuery(querey);
						a.setPlz(event.getNewValue());
						plz.setEditable(false);
					} catch (SQLException e) {
						
						err = DBManager.printSQLException(e);
						alert = new Alert(AlertType.ERROR);
						alert.setContentText(err);
						result = alert.showAndWait();
						a.setPlz(event.getOldValue());
						plz.setEditable(true);
					}
		    		
					
					
					tabelleData.refresh();
		    	} 
		    	else
		    	{
		    		plz.setEditable(true);
		    		a.setPlz(event.getOldValue());
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
    	
    	
    	Adresse a = tabelleData.getSelectionModel().getSelectedItem();

    	if(a!=null)
    	{
			Alert alert = new Alert(AlertType.CONFIRMATION);
	    	alert.setTitle("Adresse löschen");
	    	alert.setContentText("Wollen Sie es sicher löschen?");

	    	Optional<ButtonType> result = alert.showAndWait();
	    	if (result.get() == ButtonType.OK){
	    		String querey = "delete from Adresse where id_adresse=" + a.getId_adresse();
	    		
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
    	
    	
    	
    }
    
    
    /**
     *  die Methode einfuegenEvent wird aufgerufen, wenn der plus icon geklickt wird
     *  wird einfuegenEvent für die Textfeldern in der Tabelle angehört. 
     *  zuerst wird default werte in der Textfeldern eingesetzt 
     *  und nach Berarbeitung werden die Werte in der Liste Adresse 
     *  und in der Tabelle gespeichert
     *  
     * @param event verantwotlich für Anhörung einer Aktion
     * @author Ahmad, Akram, Nour 
     *
     */
    
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
    
    
    /**
     *  die Methode einfuegenEvent wird aufgerufen, wenn der check icon geklickt wird
     *  wird ein SQL_Abfrage für Insert der Daten in der Tabelle Adresse in DB erstellt 
     *  und bei Aufruf der Methode sendQuery von Klasse DBManager gesendet
     *  
     * @param event verantwotlich für Anhörung einer Aktion
     * @author Ahmad, Akram, Nour 
     *
     */
    @FXML
    public void speichernEvent(ActionEvent event)
    {
    	
    	Adresse a = adresse.get(adresse.size()-1);
    	
    	
    	
    		
		String queryString = "INSERT INTO adresse (id_adresse, id_patient, strasse, hausnummer, ort, plz)"
		+ " VALUES (" + a.getId_adresse()+ ", " + a.getId_patient() + ", '" + a.getStrasse() + "', " + a.getHausnummer()  + ", '" + a.getOrt() + "', " + a.getPlz() + ")";
		
    	try {
    		DBManager.sendQuery(queryString);
			
			
		} catch (SQLException e) {
				
			 Alert alert = new Alert(AlertType.ERROR);
    		 alert.setContentText(DBManager.printSQLException(e));
    		 Optional<ButtonType> result = alert.showAndWait();
    		 adresse.remove(a);
    	
	    	 tabelleData.refresh();
	    	 
			
			
		}	
    		
    	id_adresse.setEditable(false);
    	id_patient.setEditable(false);
    	strasse.setEditable(false);
    	hausnummer.setEditable(false);
    	ort.setEditable(false);
    	plz.setEditable(false);
    	
    	this.speichernButton.setVisible(false);
    	this.einfuegenButton.setVisible(true);
    	
    	
    }
    
    
    /**
     *  in der Methode initialize wird eine SQL_Abfrage für Holen der Adresse Daten 
     *  anhand der Methode getAlleDatenAdresse in der Klasse Adresse in der Adresse Liste gespeichert.
     *  Auch werde all Daten von Adresse Liste werden in der Liste vom Typ ObservableList gespeichert,
     *  die es Zuhörern ermöglicht, Änderungen zu verfolgen, wenn sie auftreten.
     * @param url ist nicht benutzt
     * @param rb ist nicht benutzt
     * @author Ahmad, Akram, Nour 
     *
     */
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	
    
    	
    	String queryString = "select id_adresse, id_patient, strasse, hausnummer, ort, plz From adresse";
    	a = Adresse.getAlleDatenAdresse(queryString);	
		
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
