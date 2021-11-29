package interfaceGraphique;
import javafx.scene.layout.*;
import javafx.scene.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

public class Connexion extends Application {

	public static void main(String[] args) {
		launch(args);
	}
	
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
