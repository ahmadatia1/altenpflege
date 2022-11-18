module altenpfleger {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
	requires java.sql;
	requires javafx.graphics;
	requires javafx.base;
    opens altenpfleger.sample to javafx.fxml;
    exports altenpfleger.sample;
}