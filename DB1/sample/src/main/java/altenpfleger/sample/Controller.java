package altenpfleger.sample;
/*
Put header here


 */

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

import java.util.*;


public class Controller implements Initializable {
    
    @FXML
    private Label lblOut;
    @FXML
    private Text text;
    @FXML
    private Text text1;
    
    @FXML
    private void startButton(ActionEvent event) throws IOException {
    	
    	
    	 MainApp.setRoot("Dienstplan", "Dienstplan");
    	
    	 
        
    }
    
    @FXML
    private void dienstplanHinzufuegen(ActionEvent event) throws IOException
    {
    	MainApp.setRoot("Formular", "Formular");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	
    }    
}
