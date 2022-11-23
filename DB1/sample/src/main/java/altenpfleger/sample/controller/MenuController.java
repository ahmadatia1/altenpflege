package altenpfleger.sample.controller;
/*
Put header here


 */

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

import altenpfleger.sample.MainApp;
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
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.util.*;


public class MenuController implements Initializable {
	

	/**
     *  die Methode setAltenpflegerScene ändert der View zu Altenpfleger, wenn der Taster geklickt wird
     * @param event verantwotlich für Anhörung einer Aktion
     *
     */
	@FXML 
	private void setAltenpflegerScene(ActionEvent e) throws IOException
	{
		MainApp.setRoot("Altenpfleger", "Altenpfleger");
	}
	
	/**
     *  die Methode setPatientScene ändert der View zu Patient, wenn der Taster geklickt wird
     * @param event verantwotlich für Anhörung einer Aktion
     *
     */
	@FXML 
	private void setPatientScene(ActionEvent e) throws IOException
	{
		MainApp.setRoot("Patient", "Patient");
	}
	
	/**
     *  die Methode setAdresseScene ändert der View zu Adresse, wenn der Taster geklickt wird
     * @param event verantwotlich für Anhörung einer Aktion
     *
     */
	@FXML 
	private void setAdresseScene(ActionEvent e) throws IOException
	{
		MainApp.setRoot("Adresse", "Adresse");
	}
	/**
     *  die Methode setArztScene ändert der View zu Arzt, wenn der Taster geklickt wird
     * @param event verantwotlich für Anhörung einer Aktion
     *
     */
	@FXML 
	private void setArztScene(ActionEvent e)throws IOException
	{
		MainApp.setRoot("Arzt", "Arzt");
	}
	
	/**
     *  die Methode setDienstScene ändert der View zu Dienst, wenn der Taster geklickt wird
     * @param event verantwotlich für Anhörung einer Aktion
     *
     */
	@FXML 
	private void setDienstScene(ActionEvent e) throws IOException
	{
		MainApp.setRoot("Dienst", "Dienst");
	}
	
	/**
     *  die Methode setArztHasPatientScene ändert der View zu Arzt_has_Patient, wenn der Taster geklickt wird
     * @param event verantwotlich für Anhörung einer Aktion
     *
     */
	@FXML 
	private void setArztHasPatientScene(ActionEvent e) throws IOException
	{
		MainApp.setRoot("Arzt_has_Patient", "Arzt_has_Patient");
	}
	
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	
    }    
    
 
   
   
    
    
}
