package interfaceGraphique;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

import Client.Client;
import Message.*;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import utils.Congé;
import utils.Utilisateur;

public class InterfaceUtilisateurController implements Initializable {

	private double x, y;

	private Client client;

	private Utilisateur utilisateur;

	@FXML
	private Label bonjour;
	@FXML
	private Label nombreConge;

	@FXML
	private VBox accepte;

	@FXML
	private VBox demande;

	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

	/**
	 * @param client the client to set
	 */
	public void setClient(Client client) {
		this.client = client;
	}

	/**
	 * @param utilisateur the utilisateur to set
	 */
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
		bonjour.setText("Bonjour, Mr " + utilisateur.getNom() + " " + utilisateur.getPrenom() + ", il vous avez :");
		nombreConge.setText(utilisateur.getNbr_CongéDispo() + " jours de congés disponibles");

		client.Envoie(new Demande_historique_Congé(null, utilisateur.getId()));
		Message message;
		try {
			message = client.Recoit_Message();
			message.toString();
			if (message instanceof Reponse_Liste_Congé) {
				ArrayList<Congé> conges = ((Reponse_Liste_Congé) message).getConges();
				for (Congé conge : conges) {

					Calendar cal = Calendar.getInstance();
					cal.setTime(sdf1.parse(conge.getDateDébut()));
					String d1 = sdf2.format(cal.getTime());

					// Nombre de jours à ajouter
					cal.add(Calendar.DAY_OF_MONTH, Integer.valueOf(conge.getNbjour()));
					String d2 = sdf2.format(cal.getTime());

					if (conge.getStatut().equals("T")) {
						demande.getChildren().add(conges(conge.getId(), conge.getNomDemande(), d1, d2));
					} else if (conge.getStatut().equals("A")) {
						accepte.getChildren().add(conges(conge.getId(), conge.getNomDemande(), d1, d2));

					}
				}
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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


	private Parent conges(String id, String nom, String dateDebut, String dateFin) throws IOException {

		FXMLLoader loader = new FXMLLoader(Interface.class.getResource("InterfaceConges.fxml"));
		Parent root = loader.load();
		InterfaceCongesController controller = loader.getController();
		controller.setN(Integer.valueOf(id));
		controller.setTextNom(nom);
		controller.setTextDateDebut(dateDebut);
		controller.setTextDateFin(dateFin);

		VBox.setMargin(root, new Insets(10, 0, 0, 0));

		return root;
	}

	@FXML
	private void prendreConges(ActionEvent event) throws IOException, ParseException {

		ObjectAjoutConges l = DisplayInterface.displayAjoutConges();

		if (l.isCongesValide()) {

			/*
			 * Ajout dans la bdd de la demande de congés
			 */

			// sous reserve de l'ajout de la demande dans la bdd
			Calendar cal = Calendar.getInstance();
			cal.setTime(sdf1.parse(l.getDebutConges().toString()));
			String d1 = sdf2.format(cal.getTime());
			cal.setTime(sdf1.parse(l.getFinConges().toString()));
			String d2 = sdf2.format(cal.getTime());
			
			Parent root = conges("0", l.getNomConges(),d1,d2);
			demande.getChildren().add(root);
			// VBox.setMargin(root, new Insets(10,0,0,0));
			DisplayInterface.displayInfo("La demande de congés a bien été envoyée.");
			System.out.println("1 " + l.getDebutConges().toString());
			long differenceInDays = ChronoUnit.DAYS.between(l.getDebutConges(), l.getFinConges()) + 1;
			System.out.println(differenceInDays);
			
			/// envoie au serveur
			 client.Envoie(new Demande_ddc(null,new Congé(null, utilisateur.getId(),l.getNomConges(), "RTT",l.getDebutConges().toString(), String.valueOf(differenceInDays))));
			
			//envoie par mail
			// Mail.Envoie("afaugere@enssat.fr", "Demande de congé par "+utilisateur.getNom(), utilisateur.getNom()+" a poster une demande de congé connectez vous vite sur l'application Holidays");

			System.out.println("Conges ajoute");
		} else {
			System.out.println("Conges pas ajoute");
		}

	}

	

}
