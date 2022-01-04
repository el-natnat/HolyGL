package interfaceGraphique;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
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
	private VBox demande;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
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
	
	private VBox conges() {
		
		Label label1 = new Label("Vacances");
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
		
		Label label2 = new Label("Debut : **/**/****");
		label2.setPrefWidth(130);
		label2.setFont(new Font("System Bold",16));
		VBox.setMargin(label2, new Insets(10,0,0,0));
		
		Label label3 = new Label("Fin : **/**/****");
		label3.setPrefWidth(130);
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
	private void addConges(ActionEvent event) throws IOException {
		
		System.out.print("Conges ajoute");
		demande.getChildren().add(conges());
		
	}
	
}
