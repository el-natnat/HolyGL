package interfaceGraphique;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class InterfaceConnexionController implements Initializable {
	
	private double x,y;
	
	@FXML
	private Label incorrect;
	
	@FXML
	private TextField userName;
	
	@FXML
	private PasswordField password;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
			ChangeListener<String> change = new ChangeListener<String>() {
				@Override
				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
					incorrect.setVisible(false);
				}
			};
		
			userName.textProperty().addListener(change);
			password.textProperty().addListener(change);
		
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
	
	@FXML
	private void connect(ActionEvent event) throws IOException {
		
		if(userName.getText().equals("User")&&password.getText().equals("azerty")) {
			System.out.println("User connected");
			switchToUserInterface(event);
		}else {
			if(userName.getText().equals("RH")&&password.getText().equals("azerty")) {
				System.out.println("RH connected");
			}else {
				password.setText("");
				incorrect.setVisible(true);
			}
		}
	}
	
	@FXML
	public void switchToUserInterface(Event event) throws IOException {
		
		Parent root = FXMLLoader.load(getClass().getResource("InterfaceUtilisateur.fxml"));
		Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
}
