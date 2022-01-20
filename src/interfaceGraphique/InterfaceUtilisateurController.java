package interfaceGraphique;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class InterfaceUtilisateurController implements Initializable {
	
	private double x,y;
	
	private int n = 0;
	
	@FXML
	private VBox accepte;
	
	@FXML
	public VBox demande;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		/*
			Charger les différents congés acceptés et en cours de validation depuis la bdd
			Vous pouvez créer une liste de ObjectAjoutConges avec le boolean de l'objet pour indiquer si le conges a été validé (je ferai l'achiffage de cette liste)
			Vous pouvez réquperer l'utilisateur actuel avec InterfaceConnexionController.utilisateur
		*/
		
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
	private void min(MouseEvent event) {
		
		Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		stage.setIconified(true);
	}
	
	@FXML
	private void close(MouseEvent event) {
		
		Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		stage.close();
	}
	
	private Parent conges(String nom, String dateDebut, String dateFin) throws IOException {
		
		FXMLLoader loader = new FXMLLoader(Interface.class.getResource("InterfaceConges.fxml"));
		Parent root = loader.load();
		InterfaceCongesController controller = loader.getController();
		controller.setN(n);
		controller.setTextNom(nom);
		controller.setTextDateDebut(dateDebut);
		controller.setTextDateFin(dateFin);
		
		n++;
		
		VBox.setMargin(root, new Insets(10,0,0,0));
		
		return root;
	}
	
	@FXML
	private void prendreConges(ActionEvent event) throws IOException {
		
		ObjectAjoutConges l = DisplayInterface.displayAjoutConges();
		
		if(l.isCongesValide()) {
			
			/*
				Ajout dans la bdd de la demande de congés
			*/
			
			//sous reserve de l'ajout de la demande dans la bdd
			Parent root = conges(l.getNomConges(),l.getDebutConges().toString(),l.getFinConges().toString());
			demande.getChildren().add(root);
			//VBox.setMargin(root, new Insets(10,0,0,0));
			DisplayInterface.displayInfo("La demande de congés a bien été envoyée.");
			System.out.println("Conges ajoute");
		}else {
			System.out.println("Conges pas ajoute");
		}
		
	}
	
	public void addConges(String nom, String dateDebut, String dateFin) throws IOException {
		demande.getChildren().add(conges(nom,dateDebut,dateFin));
	}
	
}
