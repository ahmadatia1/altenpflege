package altenpfleger.sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.*;

import altenpfleger.sample.dbservices.*;

import java.io.IOException;


public class MainApp extends Application {
    private static Stage stage;
    
    
	@SuppressWarnings("exports")
	@Override
    public void start( Stage s) throws IOException {
        stage=s;
        setRoot("Start","Start");
    }

    static void setRoot(String fxml) throws IOException {
        setRoot(fxml,stage.getTitle());
    }

    static void setRoot(String fxml, String title) throws IOException {
    	
        		DBManager.connectDB();
			
				Scene  scene = new Scene(loadFXML(fxml));
				
		        stage.setTitle(title);
		        stage.setScene(scene);
		        stage.show();
        
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("/fxml/"+fxml + ".fxml"));
        return fxmlLoader.load();
    }


    public static void main(String[] args) {
    	
    	
        launch(args);
    }

}
