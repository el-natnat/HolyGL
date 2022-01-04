package interfaceGraphique;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
		
		FontAwesomeIcon icon1 = new FontAwesomeIcon();
		icon1.setGlyphName("COG");
		icon1.setSize("1.4em");
		
		FontAwesomeIcon icon2 = new FontAwesomeIcon();
		icon2.setGlyphName("CLOSE");
		icon2.setSize("1.4em");
		
		HBox h = new HBox(label1,icon1,icon2);
		h.setAlignment(Pos.CENTER_RIGHT);
		h.setMaxHeight(30);
		h.setPrefHeight(25);
		h.setPrefWidth(200);
		h.setStyle("-fx-background-color: #0000FF;");
		
		VBox v = new VBox();
		v.setAlignment(Pos.TOP_CENTER);
		v.setMaxWidth(180);
		v.setPrefHeight(100);
		v.setPrefWidth(180);
		v.setStyle("-fx-background-color: #0000FF;");
		
		
		
		return v;
	}
}
