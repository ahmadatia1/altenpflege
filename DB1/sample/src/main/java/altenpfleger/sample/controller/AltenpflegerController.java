package altenpfleger.sample.controller;
/*
Put header here


 */

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

import altenpfleger.sample.dbservices.DBManager;
import altenpfleger.sample.model.Altenpfleger;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.util.*;


public class AltenpflegerController implements Initializable {
	

	private ArrayList<Altenpfleger> a;
	
	@FXML private AnchorPane anchor;
	@FXML private Button einfuegenButton;
	@FXML private Button speichernButton;
  
	
    
    @FXML private TableView<Altenpfleger> tabelleData;
    @FXML private TableColumn<Altenpfleger, String> id_altenpfleger;
    @FXML private TableColumn<Altenpfleger, String> anrede;
    @FXML private TableColumn<Altenpfleger, String> nachname;
    @FXML private TableColumn<Altenpfleger, String> vorname;
    @FXML private TableColumn<Altenpfleger, String> mail;
    @FXML private TableColumn<Altenpfleger, String> tel;

    @FXML private TableColumn<Altenpfleger, String> geburtsdatum;
   
 
    
   
    @FXML
    private void editButton(ActionEvent event) throws IOException {
    	
    	
    	
    	// anrede Listener für Aktualisieren 
    	anrede.setEditable(true);
    	anrede.setCellFactory(TextFieldTableCell.forTableColumn());
    	anrede.setCellValueFactory(new PropertyValueFactory<Altenpfleger, String>("anrede"));
    	
    	anrede.setOnEditCommit(new EventHandler<CellEditEvent<Altenpfleger, String>>(){

			@Override
			public void handle(CellEditEvent<Altenpfleger, String> event) {
				
				Altenpfleger a = event.getRowValue();
				Alert alert = new Alert(AlertType.CONFIRMATION);
		    	alert.setTitle("Update");
		    	alert.setHeaderText("Anrede ändern und aktualisieren");
		    	alert.setContentText("Bitte bstätigen?");

		    	Optional<ButtonType> result = alert.showAndWait();
		    	
		    	
		    	if (result.get() == ButtonType.OK){
		    		
		    		try {
		    			String querey = "update Altenpfleger"
		    					+ " Set anrede='" + event.getNewValue() + "'" 
		    					+ " where id_altenpfleger='" + event.getRowValue().getId_altenpfleger()+"'";
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
    	nachname.setCellValueFactory(new PropertyValueFactory<Altenpfleger, String>("nachname"));
    	
    	nachname.setOnEditCommit(new EventHandler<CellEditEvent<Altenpfleger, String>>(){

			@Override
			public void handle(CellEditEvent<Altenpfleger, String> event) {
				
				Altenpfleger a = event.getRowValue();
				
				Alert alert = new Alert(AlertType.CONFIRMATION);
		    	alert.setTitle("Update");
		    	alert.setHeaderText("Nachname ändern und aktualisieren");
		    	alert.setContentText("Bitte bstätigen?");

		    	Optional<ButtonType> result = alert.showAndWait();
		    	if (result.get() == ButtonType.OK){
		    		
		    		try {
		    			String querey = "update Altenpfleger"
		    					+ " Set nachname='" + event.getNewValue() + "'" 
		    					+ " where id_altenpfleger='" + event.getRowValue().getId_altenpfleger()+"'";
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
    	vorname.setCellValueFactory(new PropertyValueFactory<Altenpfleger, String>("vorname"));
    	
    	vorname.setOnEditCommit(new EventHandler<CellEditEvent<Altenpfleger, String>>(){

			@Override
			public void handle(CellEditEvent<Altenpfleger, String> event) {
				
				Altenpfleger a = event.getRowValue();
				
				Alert alert = new Alert(AlertType.CONFIRMATION);
		    	alert.setTitle("Update");
		    	alert.setHeaderText("vorname ändern und aktualisieren");
		    	alert.setContentText("Bitte bstätigen?");

		    	Optional<ButtonType> result = alert.showAndWait();
		    	if (result.get() == ButtonType.OK){
		    		
		    		try {
		    			String querey = "update Altenpfleger"
		    					+ " Set vorname='" + event.getNewValue() + "'" 
		    					+ " where id_altenpfleger='" + event.getRowValue().getId_altenpfleger()+"'";
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
    	geburtsdatum.setEditable(true);
    	geburtsdatum.setCellFactory(TextFieldTableCell.forTableColumn());
    	geburtsdatum.setCellValueFactory(new PropertyValueFactory<Altenpfleger, String>("geburtsdatum"));
    	
    	geburtsdatum.setOnEditCommit(new EventHandler<CellEditEvent<Altenpfleger, String>>(){

			@Override
			public void handle(CellEditEvent<Altenpfleger, String> event) {
				
				Altenpfleger a = event.getRowValue();
				
				Alert alert = new Alert(AlertType.CONFIRMATION);
		    	alert.setTitle("Update");
		    	alert.setHeaderText("setMail ändern und aktualisieren");
		    	alert.setContentText("Bitte bstätigen?");

		    	Optional<ButtonType> result = alert.showAndWait();
		    	if (result.get() == ButtonType.OK){
		    		
		    		try {
		    			String querey = "update Altenpfleger"
		    					+ " Set geburtsdatum='" + event.getNewValue() + "'" 
		    					+ " where id_altenpfleger='" + event.getRowValue().getId_altenpfleger() +"'";
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
    	tel.setCellValueFactory(new PropertyValueFactory<Altenpfleger, String>("tel"));
    	
    	tel.setOnEditCommit(new EventHandler<CellEditEvent<Altenpfleger, String>>(){

			@Override
			public void handle(CellEditEvent<Altenpfleger, String> event) {
				
				Altenpfleger a = event.getRowValue();
				
				Alert alert = new Alert(AlertType.CONFIRMATION);
		    	alert.setTitle("Update");
		    	alert.setHeaderText("tel ändern und aktualisieren");
		    	alert.setContentText("Bitte bstätigen?");

		    	Optional<ButtonType> result = alert.showAndWait();
		    	if (result.get() == ButtonType.OK){
		    		
		    		try {
		    			String querey = "update Altenpfleger"
		    					+ " Set tel='" + event.getNewValue() + "'" 
		    					+ " where id_arzt='" + event.getRowValue().getId_altenpfleger()+"'";
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
    	mail.setEditable(true);
    	mail.setCellFactory(TextFieldTableCell.forTableColumn());
    	mail.setCellValueFactory(new PropertyValueFactory<Altenpfleger, String>("mail"));
    	
    	mail.setOnEditCommit(new EventHandler<CellEditEvent<Altenpfleger, String>>(){

			@Override
			public void handle(CellEditEvent<Altenpfleger, String> event) {
				
				Altenpfleger a = event.getRowValue();
				
				Alert alert = new Alert(AlertType.CONFIRMATION);
		    	alert.setTitle("Update");
		    	alert.setHeaderText("setMail ändern und aktualisieren");
		    	alert.setContentText("Bitte bstätigen?");

		    	Optional<ButtonType> result = alert.showAndWait();
		    	if (result.get() == ButtonType.OK){
		    		
		    		try {
		    			String querey = "update Altenpfleger"
		    					+ " Set mail='" + event.getNewValue() + "'" 
		    					+ " where id_altenpfleger='" + event.getRowValue().getId_altenpfleger() +"'";
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
    	
    	
    	
    	
    	
    	
   	
    }
    
    
    @FXML
    private void deleteButton(ActionEvent event) throws IOException
    {
    	
    	
    	Altenpfleger a = tabelleData.getSelectionModel().getSelectedItem();
    	
    	if(a!=null)
    	{
			Alert alert = new Alert(AlertType.CONFIRMATION);
	    	alert.setTitle("Confirmation Dialog");
	    	alert.setHeaderText("Look, a Confirmation Dialog");
	    	alert.setContentText("Are you ok with this?");

	    	Optional<ButtonType> result = alert.showAndWait();
	    	if (result.get() == ButtonType.OK){
	    		String querey = "delete from Altenpfleger where id_altenpfleger=" + a.getId_altenpfleger();
	    		
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
    	
    	altenpfleger.add(new Altenpfleger("hier ändern", "hier ändern", "hier ändern", "hier ändern", "hier ändern", "hier ändern", "hier ändern"));
    	
    	// anrede Listener für Aktualisieren 
    	id_altenpfleger.setEditable(true);
    	id_altenpfleger.setCellFactory(TextFieldTableCell.forTableColumn());
    	id_altenpfleger.setCellValueFactory(new PropertyValueFactory<Altenpfleger, String>("id_altenpfleger"));
    	
    	id_altenpfleger.setOnEditCommit(new EventHandler<CellEditEvent<Altenpfleger, String>>(){

			@Override
			public void handle(CellEditEvent<Altenpfleger, String> event) {
				
				Altenpfleger a = event.getRowValue();
				a.setId_altenpfleger(event.getNewValue());	
				//id_arzt.setEditable(false);
				tabelleData.refresh();
				

			}
			
    		
    	});
    	
    	
    	// anrede Listener für Aktualisieren 
    	anrede.setEditable(true);
    	anrede.setCellFactory(TextFieldTableCell.forTableColumn());
    	anrede.setCellValueFactory(new PropertyValueFactory<Altenpfleger, String>("anrede"));
    	
    	anrede.setOnEditCommit(new EventHandler<CellEditEvent<Altenpfleger, String>>(){

			@Override
			public void handle(CellEditEvent<Altenpfleger, String> event) {
				
				Altenpfleger a = event.getRowValue();
				a.setAnrede(event.getNewValue());	
				//anrede.setEditable(false);
				tabelleData.refresh();
				

			}
			
    		
    	});
    	
    	// anrede Listener für Aktualisieren 
    	nachname.setEditable(true);
    	nachname.setCellFactory(TextFieldTableCell.forTableColumn());
    	nachname.setCellValueFactory(new PropertyValueFactory<Altenpfleger, String>("nachname"));
    	
    	nachname.setOnEditCommit(new EventHandler<CellEditEvent<Altenpfleger, String>>(){

			@Override
			public void handle(CellEditEvent<Altenpfleger, String> event) {
				
				Altenpfleger a = event.getRowValue();
				a.setNachname(event.getNewValue());	
				//nachname.setEditable(false);
				tabelleData.refresh();
				

			}
			
    		
    	});
    	
    	// anrede Listener für Aktualisieren 
    	vorname.setEditable(true);
    	vorname.setCellFactory(TextFieldTableCell.forTableColumn());
    	vorname.setCellValueFactory(new PropertyValueFactory<Altenpfleger, String>("vorname"));
    	
    	vorname.setOnEditCommit(new EventHandler<CellEditEvent<Altenpfleger, String>>(){

			@Override
			public void handle(CellEditEvent<Altenpfleger, String> event) {
				
				Altenpfleger a = event.getRowValue();
				a.setVorname(event.getNewValue());	
				//vorname.setEditable(false);
				tabelleData.refresh();
				

			}
			
    		
    	});
    	
    	// anrede Listener für Aktualisieren 
    	mail.setEditable(true);
    	mail.setCellFactory(TextFieldTableCell.forTableColumn());
    	mail.setCellValueFactory(new PropertyValueFactory<Altenpfleger, String>("mail"));
    	
    	mail.setOnEditCommit(new EventHandler<CellEditEvent<Altenpfleger, String>>(){

			@Override
			public void handle(CellEditEvent<Altenpfleger, String> event) {
				
				Altenpfleger a = event.getRowValue();
				a.setMail(event.getNewValue());;
				//mail.setEditable(false);
				tabelleData.refresh();
				

			}
			
    		
    	});
    	
    	// anrede Listener für Aktualisieren 
    	tel.setEditable(true);
    	tel.setCellFactory(TextFieldTableCell.forTableColumn());
    	tel.setCellValueFactory(new PropertyValueFactory<Altenpfleger, String>("tel"));
    	
    	tel.setOnEditCommit(new EventHandler<CellEditEvent<Altenpfleger, String>>(){

			@Override
			public void handle(CellEditEvent<Altenpfleger, String> event) {
				
				Altenpfleger a = event.getRowValue();
				a.setTel(event.getNewValue());
				//tel.setEditable(false);
				tabelleData.refresh();
				

			}
			
    		
    	});
    	
    	
    	
    	// anrede Listener für Aktualisieren 
    	geburtsdatum.setEditable(true);
    	geburtsdatum.setCellFactory(TextFieldTableCell.forTableColumn());
    	geburtsdatum.setCellValueFactory(new PropertyValueFactory<Altenpfleger, String>("geburtsdatum"));
    	
    	geburtsdatum.setOnEditCommit(new EventHandler<CellEditEvent<Altenpfleger, String>>(){

			@Override
			public void handle(CellEditEvent<Altenpfleger, String> event) {
				
				Altenpfleger a = event.getRowValue();
				a.setGeburtsdatum(event.getNewValue());
				//spezialtaet.setEditable(false);
				tabelleData.refresh();
				

			}
			
    		
    	});
    	
    	
    	
    	this.speichernButton.setVisible(true);
    	this.einfuegenButton.setVisible(false);	
    	
    }
    
    
    
    @FXML
    public void speichernEvent(ActionEvent event)
    {
    	
    	Altenpfleger a = altenpfleger.get(altenpfleger.size()-1);
    	
    	
    	if(!a.getId_altenpfleger().isEmpty())
    	{
    	
    		String queryString = "INSERT INTO Altenpfleger (id_altenpfleger, anrede, nachname, vorname, TO_CHAR(geburtsdatum,'DD.MM.YYYY'), mail, tel)"
    		+ " VALUES ("+ a.getId_altenpfleger()+ ",'" + a.getAnrede() + "','" + a.getNachname() + "','" + a.getVorname() + "','" + a.getGeburtsdatum()  + "','" + a.getMail() + "'," + a.getTel() + ")";
    		System.out.print(queryString);
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
    	
    	
    	
    	String queryString = "select id_altenpfleger, anrede, nachname, vorname, TO_CHAR(geburtsdatum,'DD.MM.YYYY'), mail, tel From Altenpfleger";
    	try {
			a = Altenpfleger.getAlleDatenAltenpfleger(queryString);
			
			
		} catch (SQLException e) {
			
			DBManager.printSQLException(e);
		}	
		
    	altenpfleger.addAll(a);
    	
    	id_altenpfleger.setCellValueFactory(new PropertyValueFactory<>("id_altenpfleger"));
    	anrede.setCellValueFactory(new PropertyValueFactory<>("anrede"));
		nachname.setCellValueFactory(new PropertyValueFactory<>("nachname"));
		vorname.setCellValueFactory(new PropertyValueFactory<>("vorname"));
		mail.setCellValueFactory(new PropertyValueFactory<>("mail"));
		tel.setCellValueFactory(new PropertyValueFactory<>("tel"));
		geburtsdatum.setCellValueFactory(new PropertyValueFactory<>("geburtsdatum"));
		
		
	
		tabelleData.setItems(altenpfleger);
		
    	
    }    
    
   private ObservableList<Altenpfleger> altenpfleger = FXCollections.observableArrayList();
   
   
    
    
}
