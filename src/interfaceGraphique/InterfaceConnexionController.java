package interfaceGraphique;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import Message.*;
import Client.Client;
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
import utils.Utilisateur;

public class InterfaceConnexionController implements Initializable {

	private double x, y;

	@FXML
	private Client client;

	@FXML
	private Utilisateur utilisateur;

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

	public void setClient(Client client) {
		this.client = client;
	}

	@FXML
	private void dragged(MouseEvent event) {

		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setX(event.getScreenX() - x);
		stage.setY(event.getScreenY() - y);
	}

	@FXML
	private void pressed(MouseEvent event) {

		x = event.getSceneX();
		y = event.getSceneY();

	}

	@FXML
	private void min(MouseEvent event) {

		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setIconified(true);
	}

	@FXML
	private void close(MouseEvent event) {

		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.close();
	}

	@FXML
	private void connect(ActionEvent event) throws Exception {

		String username = userName.getText();
		String pass = password.getText();
		client.Envoie(new Demande_identification(null, username, pass));

		Message message = client.Recoit_Message();
		message.toString();

		if ((message instanceof Reponse_recherche_utilisateur)
				&& ((Reponse_recherche_utilisateur) message).isUtilisateur_existe()) {
			System.out.println("User connected");
			this.utilisateur = ((Reponse_recherche_utilisateur) message).getUser();
			if (this.utilisateur.getStatut().equals("Classic")) {
				switchToUserInterface(event);
			}
			if (this.utilisateur.getStatut().equals("RH")) {
				switchToRHInterface(event);
			}

		} else {
			password.setText("");
			incorrect.setVisible(true);
		}

	}

	@FXML
	public void switchToUserInterface(Event event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("InterfaceUtilisateur.fxml"));
		Parent root = fxmlLoader.load();
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);

		InterfaceUtilisateurController controller = fxmlLoader.getController();
		controller.setClient(client);
		controller.setUtilisateur(utilisateur);
		stage.show();
	}
	
	@FXML
	public void switchToRHInterface(Event event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("InterfaceRH.fxml"));
		Parent root = fxmlLoader.load();
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);

		InterfaceRHController controller = fxmlLoader.getController();
		controller.setClient(client);
		controller.setUtilisateur(utilisateur);
		stage.show();
	}

	public void login(ActionEvent event) {

	}
}
