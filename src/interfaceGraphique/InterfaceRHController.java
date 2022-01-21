package interfaceGraphique;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;
import Message.*;
import Client.Client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utils.Congé;
import utils.Utilisateur;

public class InterfaceRHController implements Initializable {

	private ObservableList<ListeCongé> CongéData = FXCollections.observableArrayList();

	private double x, y;

	private Client client;

	private Utilisateur utilisateur;

	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");

	@FXML
	private TableView<ListeCongé> tableCongé;
	@FXML
	private TableColumn<ListeCongé, String> nom;
	@FXML
	private TableColumn<ListeCongé, String> dateD;
	@FXML
	private TableColumn<ListeCongé, String> dateF;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		nom.setCellValueFactory(new PropertyValueFactory<ListeCongé, String>("salarié"));
        dateD.setCellValueFactory(new PropertyValueFactory<ListeCongé, String>("debut"));
        dateF.setCellValueFactory(new PropertyValueFactory<ListeCongé, String>("fin"));
	}

	public void setClient(Client client) {
		this.client = client;
	}

	/**
	 * @param utilisateur the utilisateur to set
	 */
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;

		client.Envoie(new Demande_Liste_congé_a_valider(null, utilisateur.getId()));
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
					CongéData.add(new ListeCongé(conge.getUser().getNom(), d1, d2));
				}
				tableCongé.getItems().setAll(CongéData);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
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

	public class ListeCongé{
		
		
		private String salarié;
		private String debut;
		private String fin;
		public ListeCongé(String salarié, String debut, String fin) {
			super();
			this.salarié = salarié;
			this.debut = debut;
			this.fin = fin;
		}
		/**
		 * @return the salarié
		 */
		public String getSalarié() {
			return salarié;
		}
		/**
		 * @return the debut
		 */
		public String getDebut() {
			return debut;
		}
		/**
		 * @return the fin
		 */
		public String getFin() {
			return fin;
		}
		
		
		
	}
	
}

