package interfaceGraphique;

import java.net.URL;
import java.util.ResourceBundle;


import Client.Client;
import Message.Add_Employé;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utils.Utilisateur;

public class InterfaceAjoutUtilisateurController implements Initializable {

	
	private double x,y;
	
	private Client client;

	private Utilisateur utilisateur;
	
	@FXML
	private TextField nom;
	
	@FXML
	private TextField prenom;
	
	@FXML
	private TextField nomUtilisateur;
	@FXML
	private PasswordField password;
	
	@FXML
	private ChoiceBox<String> status;
	
	@FXML DatePicker dateEmbauche;
	
	
	/**
	 * @param client the client to set
	 */
	public void setClient(Client client) {
		this.client = client;
	}


	/**
	 * @param utilisateur the utilisateur to set
	 */
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		ObservableList<String> list = FXCollections.observableArrayList("Classique","Gestionnaire des ressources humaines");
		status.setItems(list);
	}
		
	
	@FXML
	private void dragged(MouseEvent event) {
		
		Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		stage.setX(event.getScreenX()-x);
		stage.setY(event.getScreenY()-y);
	}
	
	@FXML
	private void pressed(MouseEvent event) {
		
		x = event.getSceneX();
		y = event.getSceneY();
	}
	
	@FXML
	private void close(Event event) {
		
		Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		stage.close();
	}
	
	@FXML
	private void valider(ActionEvent event) throws Exception {
		client.Envoie(new Add_Employé(null, nom.getText(), prenom.getText(), nomUtilisateur.getText(),password.getText(), status.getValue(), dateEmbauche.getValue().toString()));
		Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		stage.close();
	}
	@FXML
	private void annuler (ActionEvent event) {
		Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		stage.close();
	}
}
