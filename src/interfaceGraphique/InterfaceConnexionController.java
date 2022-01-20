package interfaceGraphique;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	public static String utilisateur;
	
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
    private void connect(ActionEvent event) throws IOException, SQLException {
        
        Connection con;
        PreparedStatement pst;
        ResultSet rs;
        
        String username= userName.getText();
        String pass= password.getText();
        
        try {
            // Charger le driver MYSQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Pour se connecter à la ("jdbc:mysql://localhost/nom de la base", "User du serveur", "Mot de passe ")
            con = DriverManager.getConnection("jdbc:mysql://localhost/holidays", "root", "0000");
            // Ecrire requete
            String requete1="select * from authentification where login=? and mdp1=?";
            // Entrer la requete SQL
            pst=con.prepareStatement(requete1);
            
            pst.setString(1,username );
            pst.setString(2,"gVELY4fim7kubu+HuShFCYO7ZUk9DCq82V3CypglMm0=$O/0yfKIzwvDoRsz85EI/mnebSjaE5OC+pT2ThhWE8Is=" );

            
            // exécuté requete SQL quand on fait un SELECT 
            rs= pst.executeQuery();
            

        if(rs.next()) {
            System.out.println("User connected");
            utilisateur = username;
            switchToUserInterface(event);
        }
        else {
            password.setText("");
            incorrect.setVisible(true);
        }
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
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
