package interfaceGraphique;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;
import Message.*;
import interfaceGraphique.InterfaceRHController.ListeCongé;
import Client.Client;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
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
	private TableViewSelectionModel<ListeCongé> selectionModel;
	@FXML
	private TableColumn<ListeCongé, String> nom;
	@FXML
	private TableColumn<ListeCongé, String> dateD;
	@FXML
	private TableColumn<ListeCongé, String> dateF;

	@FXML
	private VBox box;

	@FXML
	private Button button_refuser;
	@FXML
	private Button button_valider;
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		nom.setCellValueFactory(new PropertyValueFactory<ListeCongé, String>("salarié"));
		dateD.setCellValueFactory(new PropertyValueFactory<ListeCongé, String>("debut"));
		dateF.setCellValueFactory(new PropertyValueFactory<ListeCongé, String>("fin"));

		selectionModel = tableCongé.getSelectionModel();
		// set selection mode to only 1 row
		selectionModel.setSelectionMode(SelectionMode.SINGLE);

		selectionModel.getSelectedItems().addListener(new ListChangeListener<ListeCongé>() {
			@Override
			public void onChanged(Change<? extends ListeCongé> change) {
				//System.out.println("Selection changed: " + change.getList().get(0).getDebut());
				//box.setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
				button_valider.setVisible(true);
				button_refuser.setVisible(true);
			}
		});

		//box.setBackground(new Background(new BackgroundFill(null, null, null)));
		button_valider.setVisible(false);
		button_refuser.setVisible(false);

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
					CongéData.add(new ListeCongé(conge.getUser().getNom(), d1, d2, conge.getId(),conge.getUser().getId()));
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
		System.exit(1);
	}

	public class ListeCongé {

		private String salarié;
		private String id_salarié;
		private String debut;
		private String fin;
		private String id;

		public ListeCongé(String salarié, String debut, String fin, String id,String id_salarié) {
			super();
			this.salarié = salarié;
			this.debut = debut;
			this.fin = fin;
			this.id = id;
			this.id_salarié=id_salarié;
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

		/**
		 * @return the id
		 */
		public String getId() {
			return id;
		}

		/**
		 * @return the id_salarié
		 */
		public String getId_salarié() {
			return id_salarié;
		}

	}

	@FXML
	private void valider(ActionEvent event) {
		ListeCongé congé= selectionModel.getSelectedItems().get(0);
		client.Envoie(new Accepte_Refuse_ddC(null, congé.getId(), this.utilisateur.getId(), congé.getId_salarié(), true, "Bonne Vacance"));
		System.out.println("Valide le congé" );
		
		
		tableCongé.getItems().removeAll(CongéData);
		CongéData.remove(congé);
		tableCongé.getItems().setAll(CongéData);
		
		selectionModel.clearSelection();
		//box.setBackground(new Background(new BackgroundFill(null, null, null)));
		button_valider.setVisible(false);
		button_refuser.setVisible(false);

	}

	@FXML
	private void refuser(ActionEvent event) {
		ListeCongé congé= selectionModel.getSelectedItems().get(0);
		client.Envoie(new Accepte_Refuse_ddC(null, congé.getId(), this.utilisateur.getId(), congé.getId_salarié(), false, "Non va travailler"));
		System.out.println("Refuse le congé");
		
		tableCongé.getItems().removeAll(CongéData);
		CongéData.remove(congé);
		tableCongé.getItems().setAll(CongéData);
		
		selectionModel.clearSelection();
		//box.setBackground(new Background(new BackgroundFill(null, null, null)));
		button_valider.setVisible(false);
		button_refuser.setVisible(false);
	}
	
	@FXML
	private void add_employé(ActionEvent event) throws IOException {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.initStyle(StageStyle.TRANSPARENT);
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("InterfaceAjoutUtilisateur.fxml"));
		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root);
		InterfaceAjoutUtilisateurController controller = fxmlLoader.getController();
		controller.setClient(client);
		controller.setUtilisateur(utilisateur);
		
		window.setScene(scene);
		window.showAndWait();
	
	}
}
