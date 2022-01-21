package interfaceGraphique;

import javafx.scene.*;
import Client.Client;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Interface extends Application {
	
	public static 	Client client =  new Client();

	public static void main(String[] args) {
		launch(args);
	}
	
	public void start(Stage primaryStage) {
		try {
			
	     	 FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Connexion.fxml"));
			Parent root =fxmlLoader.load();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("HolyGL - Logiciel de gestion de congés");
			primaryStage.initStyle(StageStyle.TRANSPARENT);
			primaryStage.setResizable(false);			
			InterfaceConnexionController controller = fxmlLoader.getController();
			controller.setClient(client);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
