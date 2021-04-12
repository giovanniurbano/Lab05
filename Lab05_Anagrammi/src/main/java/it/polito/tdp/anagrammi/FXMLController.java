package it.polito.tdp.anagrammi;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtParola;

    @FXML
    private TextArea txtCorretti;

    @FXML
    private TextArea txtErrati;

	private Model model;

    @FXML
    void doCalcolaAnagrammi(ActionEvent event) {
    	String parola = txtParola.getText();
    	List<List<String>> soluzione = new ArrayList<List<String>>(this.model.anagrammi(parola));
    	txtCorretti.clear();
    	txtErrati.clear();
    	for(String a : soluzione.get(0))
    		txtCorretti.appendText(a+"\n");
    	for(String a : soluzione.get(1))
    		txtErrati.appendText(a+"\n");
    }

    @FXML
    void doReset(ActionEvent event) {
    	txtParola.clear();
    	txtCorretti.clear();
    	txtErrati.clear();
    }

    public void setModel(Model m) {
    	this.model = m;
    }
    
    @FXML
    void initialize() {
        assert txtParola != null : "fx:id=\"txtParola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtCorretti != null : "fx:id=\"txtCorretti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtErrati != null : "fx:id=\"txtErrati\" was not injected: check your FXML file 'Scene.fxml'.";

    }
}
