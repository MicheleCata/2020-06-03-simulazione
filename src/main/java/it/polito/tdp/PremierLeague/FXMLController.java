/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.PremierLeague;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.PremierLeague.model.Avversari;
import it.polito.tdp.PremierLeague.model.Model;
import it.polito.tdp.PremierLeague.model.TopPlayer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnCreaGrafo"
    private Button btnCreaGrafo; // Value injected by FXMLLoader

    @FXML // fx:id="btnTopPlayer"
    private Button btnTopPlayer; // Value injected by FXMLLoader

    @FXML // fx:id="btnDreamTeam"
    private Button btnDreamTeam; // Value injected by FXMLLoader

    @FXML // fx:id="txtK"
    private TextField txtK; // Value injected by FXMLLoader

    @FXML // fx:id="txtGoals"
    private TextField txtGoals; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doCreaGrafo(ActionEvent event) {

    	txtResult.clear();
    	String goals = txtGoals.getText();
    	double minimo;
    	if (goals==null) {
    		txtResult.appendText("Inserisci i valori di input!");
    		return;
    	}
    	try {
    		minimo= Double.parseDouble(goals);
    		
    	}catch (NumberFormatException e) {
    		txtResult.setText("Inserisci un valore numerico");
    		return;
    	}
    	
    	model.creaGrafo(minimo);
    	
    	txtResult.appendText("Grafo creato\n #VERTICI: "+ model.getNumVertici()+ " #ARCHI: "+ model.getNArchi()+"\n");
    }

    @FXML
    void doDreamTeam(ActionEvent event) {

    }

    @FXML
    void doTopPlayer(ActionEvent event) {
    	
    	 txtResult.clear();
    	 
     	TopPlayer top = model.getTopPlayer();
     	if(top == null) {
    		txtResult.appendText("Crea il grafo!");
    		return;
    	}
     	txtResult.appendText("TOP PLAYER:"+ top.getP().getName()+"\n");
     	txtResult.appendText("\n\nAVVERSARI BATTUTI:\n");
     	for (Avversari a: top.getAvversari()) {
     		txtResult.appendText(a.toString());
     	}
    	 
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnCreaGrafo != null : "fx:id=\"btnCreaGrafo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnTopPlayer != null : "fx:id=\"btnTopPlayer\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnDreamTeam != null : "fx:id=\"btnDreamTeam\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtK != null : "fx:id=\"txtK\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtGoals != null : "fx:id=\"txtGoals\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    }
}
