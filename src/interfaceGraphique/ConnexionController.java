package interfaceGraphique;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ConnexionController implements Initializable {
	
	double x,y;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	@FXML
	void dragged(MouseEvent event) {
		
		Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		stage.setX(event.getScreenX()-x);
		stage.setY(event.getScreenY()-y);	
	}
	
	@FXML
	void pressed(MouseEvent event) {
		
		x = event.getSceneX();
		y = event.getSceneY();
		
	}
	
	@FXML
	void min(MouseEvent event) {
		
		Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		stage.setIconified(true);
	}
	
	@FXML
	void close(MouseEvent event) {
		
		Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		stage.close();
	}
	
}
