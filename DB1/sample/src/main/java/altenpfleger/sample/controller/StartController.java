package altenpfleger.sample.controller;
/*
Put header here


 */

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

import altenpfleger.sample.*;
import altenpfleger.sample.dbservices.DBManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.util.*;


public class StartController implements Initializable {
	
    

    

    @FXML private TextField benutzername;
    @FXML private PasswordField passwort;
    
    
    
    
    
    @FXML
    private void anmeldenButton(ActionEvent event) throws IOException, SQLException {
    	
    	DBManager.connectDB(benutzername.getText(), passwort.getText());
    	
    	if(DBManager.connectionStatus)
    	{
    		MainApp.setRoot("Dienst", "Dienst");
    		
    	}
    	else
    	{
    		
   		 	Alert alert = new Alert(AlertType.ERROR);
	    	alert.setContentText("Anmeldedaten sind nicht korrekt !!!");
	    	Optional<ButtonType> result = alert.showAndWait();
    	}
    	

    }
 
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
    }    
    

    
    
}
