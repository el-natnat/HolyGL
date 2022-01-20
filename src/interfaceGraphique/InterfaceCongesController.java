package interfaceGraphique;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class InterfaceCongesController implements Initializable{
	
	private int n;
	
	@FXML
	private Label nom;
	
	@FXML
	private Label dateDebut;
	
	@FXML
	private Label dateFin;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	}
	
	@FXML
	private void modifier() {
		System.out.println("Modifier conges numero : "+n);
	}
	
	@FXML
	private void supprimer() {
		System.out.println("Supprimer conges numero : "+n);
	}

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}
	
	public void setTextNom(String str) {
		nom.setText(str);
	}
	
	public void setTextDateDebut(String str) {
		dateDebut.setText("Debut : "+str);
	}
	
	public void setTextDateFin(String str) {
		dateFin.setText("Fin : "+str);
	}
}
