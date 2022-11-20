module altenpfleger {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
	requires java.sql;
	requires javafx.graphics;
	requires javafx.base;
    opens altenpfleger.sample to javafx.fxml;
    opens altenpfleger.sample.controller to javafx.fxml;
    opens altenpfleger.sample.model to javafx.base;
    
    exports altenpfleger.sample;
}