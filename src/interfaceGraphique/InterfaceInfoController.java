package interfaceGraphique;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class InterfaceInfoController implements Initializable {
	
	private double x,y;
	
	@FXML
	private Label text;
	
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
	private void close(Event event) {
		
		Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		stage.close();
	}
	
	public void updateText(String message) {
		
		text.setText(message);
	}
}
