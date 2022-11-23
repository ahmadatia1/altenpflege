package altenpfleger.sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.*;

import altenpfleger.sample.dbservices.*;

import java.io.IOException;
import java.sql.SQLException;


/**
 * in Klasse MainApp werden die Views bzw. FXML dateien aufgerufen und kontrolliert 
 * 
 * @author Ahmad, Akram, Nour 
 *
 */

public class MainApp extends Application {
	
    private static Stage stage;
    
    /**
     * die Methode start ist veranwortlich für Starten das Pragramm und Ändern der angezeigte View bzw. FXML Datei. 
     * 
     * @param s hilft, damit die stage variable gekapselt wird
     * 
     * @author Ahmad, Akram, Nour 
     * 
     *
     */
	@Override
    public void start( Stage s) throws IOException, SQLException {
		
		
        stage=s;
        setRoot("Start","Start");
		
    }
	
	/**
	 * die statische  Mathode setRoot setzt ein neue View ein
	 * @param fxml Name der View (FXML Datei)
	 * @throws IOException bei falsche Name der View wird der Exception geworfen und die Fehler angezeigt
	 */

    public static void setRoot(String fxml, String title) throws IOException {
    	
    	
			
				Scene  scene = new Scene(loadFXML(fxml));
				
				
				
		        stage.setTitle(title);
		        stage.setScene(scene);
		        stage.show();
        
    }
    
    /**
	 * die statische  Mathode loadFXML lädt der View also FXML Datei und gibt das Ergebnis zurück
	 * @param fxml Name der View (FXML Datei)
	 * @return der geladene View zurückgegeben
	 * @throws IOException bei falsche Name der View wird der Exception geworfen und die Fehler angezeigt
	 */

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("/fxml/"+fxml + ".fxml"));
        
   
        return fxmlLoader.load();
    }


    public static void main(String[] args) {
    	
    	
        launch(args);
    }

}
