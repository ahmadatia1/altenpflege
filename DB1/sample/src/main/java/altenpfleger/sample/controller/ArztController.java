package altenpfleger.sample.controller;
/*
Put header here


 */

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

import altenpfleger.sample.dbservices.DBManager;
import altenpfleger.sample.model.Arzt;
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


public class ArztController implements Initializable {
	

	private ArrayList<Arzt> a;
	
	
	@FXML private Button einfuegenButton;
	@FXML private Button speichernButton;
  
	
    
    @FXML private TableView<Arzt> tabelleData;
    @FXML private TableColumn<Arzt, String> id_arzt;
    @FXML private TableColumn<Arzt, String> anrede;
    @FXML private TableColumn<Arzt, String> nachname;
    @FXML private TableColumn<Arzt, String> vorname;
    @FXML private TableColumn<Arzt, String> mail;
    @FXML private TableColumn<Arzt, String> tel;

    @FXML private TableColumn<Arzt, String> spezialtaet;
    @FXML private TableColumn<Arzt, String> vertretung_id_arzt;
 
    
   
    @FXML
    private void editButton(ActionEvent event) throws IOException {
    	
    	
    	
    	// anrede Listener für Aktualisieren 
    	anrede.setEditable(true);
    	anrede.setCellFactory(TextFieldTableCell.forTableColumn());
    	anrede.setCellValueFactory(new PropertyValueFactory<Arzt, String>("anrede"));
    	
    	anrede.setOnEditCommit(new EventHandler<CellEditEvent<Arzt, String>>(){

			@Override
			public void handle(CellEditEvent<Arzt, String> event) {
				
				Arzt a = event.getRowValue();
				Alert alert = new Alert(AlertType.CONFIRMATION);
		    	alert.setTitle("Update");
		    	alert.setHeaderText("Anrede ändern und aktualisieren");
		    	alert.setContentText("Bitte bstätigen?");

		    	Optional<ButtonType> result = alert.showAndWait();
		    	
		    	
		    	if (result.get() == ButtonType.OK){
		    		
		    		try {
		    			String querey = "update Artz"
		    					+ " Set anrede='" + event.getNewValue() + "'" 
		    					+ " where id_arzt='" + event.getRowValue().getId_arzt()+"'";
		    			DBManager.sendQuery(querey);
						a.setAnrede(event.getNewValue());
					} catch (SQLException e) {
						
						DBManager.printSQLException(e);
						a.setAnrede(event.getOldValue());
					}
		    		
					
					
					tabelleData.refresh();
		    	} else {
		    		
		    		 alert = new Alert(AlertType.ERROR);
		    		 alert.setContentText("Bitte geben Sie entweder Herr odr Frau ein!!!");
			    	 result = alert.showAndWait();
			    	 tabelleData.refresh();
			    	 a.setAnrede(event.getOldValue());
			    	 tabelleData.refresh();
		    	   
		    	}
				
				
				
				
			}
			
    		
    	});
    	
    	
    	// Nachname Listener für Aktualisieren 
    	nachname.setEditable(true);
    	nachname.setCellFactory(TextFieldTableCell.forTableColumn());
    	nachname.setCellValueFactory(new PropertyValueFactory<Arzt, String>("nachname"));
    	
    	nachname.setOnEditCommit(new EventHandler<CellEditEvent<Arzt, String>>(){

			@Override
			public void handle(CellEditEvent<Arzt, String> event) {
				
				Arzt a = event.getRowValue();
				
				Alert alert = new Alert(AlertType.CONFIRMATION);
		    	alert.setTitle("Update");
		    	alert.setHeaderText("Nachname ändern und aktualisieren");
		    	alert.setContentText("Bitte bstätigen?");

		    	Optional<ButtonType> result = alert.showAndWait();
		    	if (result.get() == ButtonType.OK){
		    		
		    		try {
		    			String querey = "update Artz"
		    					+ " Set nachname='" + event.getNewValue() + "'" 
		    					+ " where id_arzt='" + event.getRowValue().getId_arzt()+"'";
		    			DBManager.sendQuery(querey);
						a.setNachname(event.getNewValue());
					} 
		    		catch (SQLException e) 
		    		{
						DBManager.printSQLException(e);
					}
		    		
					tabelleData.refresh();
					
		    	} 
		    	else 
		    	{
		    	   a.setNachname(event.getOldValue());
		    	   tabelleData.refresh();
		    	   
		    	}
				
				
				
			}
    		
    	});
    	
    	
    	// vorname Listener für Aktualisieren 
    	vorname.setEditable(true);
    	vorname.setCellFactory(TextFieldTableCell.forTableColumn());
    	vorname.setCellValueFactory(new PropertyValueFactory<Arzt, String>("vorname"));
    	
    	vorname.setOnEditCommit(new EventHandler<CellEditEvent<Arzt, String>>(){

			@Override
			public void handle(CellEditEvent<Arzt, String> event) {
				
				Arzt a = event.getRowValue();
				
				Alert alert = new Alert(AlertType.CONFIRMATION);
		    	alert.setTitle("Update");
		    	alert.setHeaderText("vorname ändern und aktualisieren");
		    	alert.setContentText("Bitte bstätigen?");

		    	Optional<ButtonType> result = alert.showAndWait();
		    	if (result.get() == ButtonType.OK){
		    		
		    		try {
		    			String querey = "update Artz"
		    					+ " Set vorname='" + event.getNewValue() + "'" 
		    					+ " where id_arzt='" + event.getRowValue().getId_arzt()+"'";
		    			DBManager.sendQuery(querey);
					} catch (SQLException e) 
		    		{
						DBManager.printSQLException(e);
					}
		    		
					a.setVorname(event.getNewValue());
					
					tabelleData.refresh();
		    	} 
		    	else 
		    	{
		    	   a.setVorname(event.getOldValue());
		    	   tabelleData.refresh();
		    	   
		    	}
				
				
				
			}
    		
    	});
    	
    	
    	// KVnummer Listener für Aktualisieren 
    	spezialtaet.setEditable(true);
    	mail.setCellFactory(TextFieldTableCell.forTableColumn());
    	mail.setCellValueFactory(new PropertyValueFactory<Arzt, String>("spezialtaet"));
    	
    	mail.setOnEditCommit(new EventHandler<CellEditEvent<Arzt, String>>(){

			@Override
			public void handle(CellEditEvent<Arzt, String> event) {
				
				Arzt a = event.getRowValue();
				
				Alert alert = new Alert(AlertType.CONFIRMATION);
		    	alert.setTitle("Update");
		    	alert.setHeaderText("setMail ändern und aktualisieren");
		    	alert.setContentText("Bitte bstätigen?");

		    	Optional<ButtonType> result = alert.showAndWait();
		    	if (result.get() == ButtonType.OK){
		    		
		    		try {
		    			String querey = "update Artz"
		    					+ " Set mail='" + event.getNewValue() + "'" 
		    					+ " where id_arzt='" + event.getRowValue().getId_arzt()+"'";
		    			DBManager.sendQuery(querey);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		    		
					a.setMail(event.getNewValue() );
					
					tabelleData.refresh();
		    	} else {
		    	   a.setMail(event.getOldValue());
		    	   tabelleData.refresh();
		    	   
		    	}
				
				
				
			}
    		
    	});
    	
    	
    	// Geburtsdatum Listener für Aktualisieren 
    	tel.setEditable(true);
    	tel.setCellFactory(TextFieldTableCell.forTableColumn());
    	tel.setCellValueFactory(new PropertyValueFactory<Arzt, String>("tel"));
    	
    	tel.setOnEditCommit(new EventHandler<CellEditEvent<Arzt, String>>(){

			@Override
			public void handle(CellEditEvent<Arzt, String> event) {
				
				Arzt a = event.getRowValue();
				
				Alert alert = new Alert(AlertType.CONFIRMATION);
		    	alert.setTitle("Update");
		    	alert.setHeaderText("tel ändern und aktualisieren");
		    	alert.setContentText("Bitte bstätigen?");

		    	Optional<ButtonType> result = alert.showAndWait();
		    	if (result.get() == ButtonType.OK){
		    		
		    		try {
		    			String querey = "update Artz"
		    					+ " Set tel='" + event.getNewValue() + "'" 
		    					+ " where id_arzt='" + event.getRowValue().getId_arzt()+"'";
		    			DBManager.sendQuery(querey);
					} 
		    		catch (SQLException e) 
		    		{
						DBManager.printSQLException(e);
					}
		    		
					a.setMail(event.getNewValue() );
					
					tabelleData.refresh();
		    	} else {
		    	   a.setMail(event.getOldValue());
		    	   tabelleData.refresh();
		    	   
		    	}
				
				
				
			}
    		
    	});
    	
    	
     	// KVnummer Listener für Aktualisieren 
    	spezialtaet.setEditable(true);
    	spezialtaet.setCellFactory(TextFieldTableCell.forTableColumn());
    	spezialtaet.setCellValueFactory(new PropertyValueFactory<Arzt, String>("spezialtaet"));
    	
    	spezialtaet.setOnEditCommit(new EventHandler<CellEditEvent<Arzt, String>>(){

			@Override
			public void handle(CellEditEvent<Arzt, String> event) {
				
				Arzt a = event.getRowValue();
				
				Alert alert = new Alert(AlertType.CONFIRMATION);
		    	alert.setTitle("Update");
		    	alert.setHeaderText("setMail ändern und aktualisieren");
		    	alert.setContentText("Bitte bstätigen?");

		    	Optional<ButtonType> result = alert.showAndWait();
		    	if (result.get() == ButtonType.OK){
		    		
		    		try {
		    			String querey = "update Artz"
		    					+ " Set spezialtaet='" + event.getNewValue() + "'" 
		    					+ " where id_arzt='" + event.getRowValue().getId_arzt()+"'";
		    			DBManager.sendQuery(querey);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		    		
					a.setSpezialtaet(event.getNewValue() );
					
					tabelleData.refresh();
		    	} else {
		    	   a.setSpezialtaet(event.getOldValue());
		    	   tabelleData.refresh();
		    	   
		    	}
				
				
				
			}
    		
    	});
    	
    	
    	// Geburtsdatum Listener für Aktualisieren 
    	vertretung_id_arzt.setEditable(true);
    	vertretung_id_arzt.setCellFactory(TextFieldTableCell.forTableColumn());
    	vertretung_id_arzt.setCellValueFactory(new PropertyValueFactory<Arzt, String>("vertretung_id_arzt"));
    	
    	vertretung_id_arzt.setOnEditCommit(new EventHandler<CellEditEvent<Arzt, String>>(){

			@Override
			public void handle(CellEditEvent<Arzt, String> event) {
				
				Arzt a = event.getRowValue();
				
				Alert alert = new Alert(AlertType.CONFIRMATION);
		    	alert.setTitle("Update");
		    	alert.setHeaderText("tel ändern und aktualisieren");
		    	alert.setContentText("Bitte bstätigen?");

		    	Optional<ButtonType> result = alert.showAndWait();
		    	if (result.get() == ButtonType.OK){
		    		
		    		try {
		    			String querey = "update Artz"
		    					+ " Set vertretung_id_arzt='" + event.getNewValue() + "'" 
		    					+ " where id_arzt='" + event.getRowValue().getId_arzt()+"'";
		    			DBManager.sendQuery(querey);
					} 
		    		catch (SQLException e) 
		    		{
						DBManager.printSQLException(e);
					}
		    		
					a.setVertretung_id_arzt(event.getNewValue() );
					
					tabelleData.refresh();
		    	} else {
		    	   a.setVertretung_id_arzt(event.getOldValue());
		    	   tabelleData.refresh();
		    	   
		    	}
				
				
				
			}
    		
    	});
    	
    	
    	
    	
   	
    }
    
    
    @FXML
    private void deleteButton(ActionEvent event) throws IOException
    {
    	
    	
    	Arzt a = tabelleData.getSelectionModel().getSelectedItem();
    	
    	if(a!=null)
    	{
			Alert alert = new Alert(AlertType.CONFIRMATION);
	    	alert.setTitle("Confirmation Dialog");
	    	alert.setHeaderText("Look, a Confirmation Dialog");
	    	alert.setContentText("Are you ok with this?");

	    	Optional<ButtonType> result = alert.showAndWait();
	    	if (result.get() == ButtonType.OK){
	    		String querey = "delete from Artz where id_arzt=" + a.getId_arzt();
	    		
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
    	
    	arzt.add(new Arzt("hier ändern", "hier ändern", "hier ändern", "hier ändern", "hier ändern", "hier ändern", "hier ändern", "hier ändern"));
    	
    	// anrede Listener für Aktualisieren 
    	id_arzt.setEditable(true);
    	id_arzt.setCellFactory(TextFieldTableCell.forTableColumn());
    	id_arzt.setCellValueFactory(new PropertyValueFactory<Arzt, String>("id_arzt"));
    	
    	id_arzt.setOnEditCommit(new EventHandler<CellEditEvent<Arzt, String>>(){

			@Override
			public void handle(CellEditEvent<Arzt, String> event) {
				
				Arzt a = event.getRowValue();
				a.setId_arzt(event.getNewValue());	
				//id_arzt.setEditable(false);
				tabelleData.refresh();
				

			}
			
    		
    	});
    	
    	
    	// anrede Listener für Aktualisieren 
    	anrede.setEditable(true);
    	anrede.setCellFactory(TextFieldTableCell.forTableColumn());
    	anrede.setCellValueFactory(new PropertyValueFactory<Arzt, String>("anrede"));
    	
    	anrede.setOnEditCommit(new EventHandler<CellEditEvent<Arzt, String>>(){

			@Override
			public void handle(CellEditEvent<Arzt, String> event) {
				
				Arzt a = event.getRowValue();
				a.setAnrede(event.getNewValue());	
				//anrede.setEditable(false);
				tabelleData.refresh();
				

			}
			
    		
    	});
    	
    	// anrede Listener für Aktualisieren 
    	nachname.setEditable(true);
    	nachname.setCellFactory(TextFieldTableCell.forTableColumn());
    	nachname.setCellValueFactory(new PropertyValueFactory<Arzt, String>("nachname"));
    	
    	nachname.setOnEditCommit(new EventHandler<CellEditEvent<Arzt, String>>(){

			@Override
			public void handle(CellEditEvent<Arzt, String> event) {
				
				Arzt a = event.getRowValue();
				a.setNachname(event.getNewValue());	
				//nachname.setEditable(false);
				tabelleData.refresh();
				

			}
			
    		
    	});
    	
    	// anrede Listener für Aktualisieren 
    	vorname.setEditable(true);
    	vorname.setCellFactory(TextFieldTableCell.forTableColumn());
    	vorname.setCellValueFactory(new PropertyValueFactory<Arzt, String>("vorname"));
    	
    	vorname.setOnEditCommit(new EventHandler<CellEditEvent<Arzt, String>>(){

			@Override
			public void handle(CellEditEvent<Arzt, String> event) {
				
				Arzt a = event.getRowValue();
				a.setVorname(event.getNewValue());	
				//vorname.setEditable(false);
				tabelleData.refresh();
				

			}
			
    		
    	});
    	
    	// anrede Listener für Aktualisieren 
    	mail.setEditable(true);
    	mail.setCellFactory(TextFieldTableCell.forTableColumn());
    	mail.setCellValueFactory(new PropertyValueFactory<Arzt, String>("mail"));
    	
    	mail.setOnEditCommit(new EventHandler<CellEditEvent<Arzt, String>>(){

			@Override
			public void handle(CellEditEvent<Arzt, String> event) {
				
				Arzt a = event.getRowValue();
				a.setMail(event.getNewValue());;
				//mail.setEditable(false);
				tabelleData.refresh();
				

			}
			
    		
    	});
    	
    	// anrede Listener für Aktualisieren 
    	tel.setEditable(true);
    	tel.setCellFactory(TextFieldTableCell.forTableColumn());
    	tel.setCellValueFactory(new PropertyValueFactory<Arzt, String>("tel"));
    	
    	tel.setOnEditCommit(new EventHandler<CellEditEvent<Arzt, String>>(){

			@Override
			public void handle(CellEditEvent<Arzt, String> event) {
				
				Arzt a = event.getRowValue();
				a.setTel(event.getNewValue());
				//tel.setEditable(false);
				tabelleData.refresh();
				

			}
			
    		
    	});
    	
    	// anrede Listener für Aktualisieren 
    	spezialtaet.setEditable(true);
    	spezialtaet.setCellFactory(TextFieldTableCell.forTableColumn());
    	spezialtaet.setCellValueFactory(new PropertyValueFactory<Arzt, String>("spezialtaet"));
    	
    	spezialtaet.setOnEditCommit(new EventHandler<CellEditEvent<Arzt, String>>(){

			@Override
			public void handle(CellEditEvent<Arzt, String> event) {
				
				Arzt a = event.getRowValue();
				a.setSpezialtaet(event.getNewValue());
				//spezialtaet.setEditable(false);
				tabelleData.refresh();
				

			}
			
    		
    	});
    	
    	
    	// anrede Listener für Aktualisieren 
    	vertretung_id_arzt.setEditable(true);
    	vertretung_id_arzt.setCellFactory(TextFieldTableCell.forTableColumn());
    	vertretung_id_arzt.setCellValueFactory(new PropertyValueFactory<Arzt, String>("vertretung_id_arzt"));
    	
    	vertretung_id_arzt.setOnEditCommit(new EventHandler<CellEditEvent<Arzt, String>>(){

			@Override
			public void handle(CellEditEvent<Arzt, String> event) {
				
				Arzt a = event.getRowValue();
				a.setVertretung_id_arzt(event.getNewValue());
				//vertretung_id_arzt.setEditable(false);
				tabelleData.refresh();
				

			}
			
    		
    	});
    	
    	this.speichernButton.setVisible(true);
    	this.einfuegenButton.setVisible(false);	
    	
    }
    
    
    
    @FXML
    public void speichernEvent(ActionEvent event)
    {
    	
    	Arzt a = arzt.get(arzt.size()-1);
    	
    	
    	if(!a.getId_arzt().isEmpty())
    	{
    		
    		String queryString = "INSERT INTO Arzt (id_arzt, anrede, nachname, vorname, mail, tel, spezialtaet, vertretung_id_arzt)"
    		+ " VALUES (" + a.getId_arzt()+ ", '" + a.getAnrede() + "', '" + a.getNachname() + "', '" + a.getVorname()  + "', '" + a.getMail() + "', " + a.getTel() + ", '" + a.getSpezialtaet() + "', " + a.getVertretung_id_arzt() + ")";
    		
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
    	
    
    	
    	String queryString = "select id_arzt, anrede, nachname, vorname, mail, tel, spezialtaet, vertretung_id_arzt From Arzt";
    	try {
			a = Arzt.getAlleDatenArzt(queryString);
			
			
		} catch (SQLException e) {
			
			DBManager.printSQLException(e);
		}	
		
    	arzt.addAll(a);
    	
    	id_arzt.setCellValueFactory(new PropertyValueFactory<>("id_arzt"));
    	anrede.setCellValueFactory(new PropertyValueFactory<>("anrede"));
		nachname.setCellValueFactory(new PropertyValueFactory<>("nachname"));
		vorname.setCellValueFactory(new PropertyValueFactory<>("vorname"));
		mail.setCellValueFactory(new PropertyValueFactory<>("mail"));
		tel.setCellValueFactory(new PropertyValueFactory<>("tel"));
		spezialtaet.setCellValueFactory(new PropertyValueFactory<>("spezialtaet"));
		vertretung_id_arzt.setCellValueFactory(new PropertyValueFactory<>("vertretung_id_arzt"));
		
		
		tabelleData.setItems(arzt);
		
    	
    }    
    
   private ObservableList<Arzt> arzt = FXCollections.observableArrayList();
   
   
    
    
}
