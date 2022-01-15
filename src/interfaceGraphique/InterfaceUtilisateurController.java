package interfaceGraphique;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class InterfaceUtilisateurController implements Initializable {
	
	private double x,y;
	
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
	
	private VBox conges(String nom, String dateDebut, String dateFin) {
		
		Label label1 = new Label(nom);
		label1.setPrefWidth(130);
		label1.setFont(new Font("System Bold",16));
		HBox.setMargin(label1, new Insets(0,5,0,0));
		
		FontAwesomeIcon icon1 = new FontAwesomeIcon();
		icon1.setGlyphName("COG");
		icon1.setSize("1.4em");
		HBox.setMargin(icon1, new Insets(0,5,0,0));
		
		FontAwesomeIcon icon2 = new FontAwesomeIcon();
		icon2.setGlyphName("CLOSE");
		icon2.setSize("1.4em");
		HBox.setMargin(icon2, new Insets(0,5,0,0));
		
		HBox h = new HBox(label1,icon1,icon2);
		h.setAlignment(Pos.CENTER_RIGHT);
		h.setMaxHeight(30);
		h.setPrefHeight(25);
		h.setPrefWidth(200);
		h.setStyle("-fx-background-color: #0000FF;");
		
		Label label2 = new Label("Debut : "+dateDebut);
		label2.setPrefWidth(TextField.USE_COMPUTED_SIZE);
		label2.setFont(new Font("System Bold",16));
		VBox.setMargin(label2, new Insets(10,0,0,0));
		
		Label label3 = new Label("Fin : "+dateFin);
		label3.setPrefWidth(TextField.USE_COMPUTED_SIZE);
		label3.setFont(new Font("System Bold",16));
		VBox.setMargin(label3, new Insets(5,0,0,0));
		
		VBox v = new VBox(h,label2,label3);
		v.setAlignment(Pos.TOP_CENTER);
		v.setMaxWidth(180);
		v.setPrefHeight(100);
		v.setPrefWidth(180);
		v.setStyle("-fx-background-color: #2080FF;");
		VBox.setMargin(v, new Insets(10,0,0,0));
		
		return v;
	}
	
	@FXML
	private void prendreConges(ActionEvent event) throws IOException {
		
		ObjectAjoutConges l = DisplayInterface.displayAjoutConges();
		
		if(l.isCongesValide()) {
			
			/*
				Ajout dans la bdd de la demande de congés
			*/
			
			//sous reserve de l'ajout de la demande dans la bdd
			demande.getChildren().add(conges(l.getNomConges(),l.getDebutConges().toString(),l.getFinConges().toString()));
			System.out.print("Conges ajoute");
		}else {
			System.out.print("Conges pas ajoute");
		}
		
	}
	
	public void addConges(String nom, String dateDebut, String dateFin) {
		demande.getChildren().add(conges(nom,dateDebut,dateFin));
	}
	
}
