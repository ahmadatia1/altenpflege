package altenpfleger.sample;
/*
Put header here


 */

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

import altenpfleger.sample.dbservices.DBManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.util.*;


public class StartController implements Initializable {
	
    

    

    
    
    @FXML
    private void startButton(ActionEvent event) throws IOException, SQLException {
    	
    	MainApp.setRoot("Dienstplan", "Dienstplan");

    }
 
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
    }    
    

    
    
}
