package interfaceGraphique;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;

public class InterfaceUtilisateurAjoutController implements Initializable {
	
	private double x,y;
	
	public static boolean congesValide;
	public static String nomConges;
	public static LocalDate debutConges,finConges;
	
	@FXML
	private TextField nomDemande;
	
	@FXML
	private DatePicker dateDebutConges;
	
	@FXML
	private DatePicker dateFinConges;
	
	@FXML
	private Button buttonValide;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		congesValide = false;
		
		nomDemande.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
			nomConges = nomDemande.getText();
		});
		
		dateDebutConges.valueProperty().addListener((ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) -> {
			debutConges = dateDebutConges.getValue();
			dateFinConges.setDayCellFactory(getDayCellFactory(dateDebutConges.getValue()));
			dateFinConges.setDisable(false);
			if((dateFinConges.getValue()!=null)&&(dateDebutConges.getValue().isAfter(dateFinConges.getValue()))) {
				System.out.println("Date non valide");
				buttonValide.setDisable(true);
			}
		});
		
		dateFinConges.valueProperty().addListener((ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) -> {
			finConges = dateFinConges.getValue();
			buttonValide.setDisable(false);
		});
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
	private void close(MouseEvent event) {
		
		Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		stage.close();
	}
	
	@FXML
	public void valideConges(ActionEvent event) {
		
		congesValide = true;
		
		Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		stage.close();
	}
	
	private Callback<DatePicker, DateCell> getDayCellFactory(LocalDate date) {

        final Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>() {

            @Override
            public DateCell call(final DatePicker datePicker) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);

                        // Disable Monday, Tuesday, Wednesday.
                        if (item.isBefore(date)) {
                            setDisable(true);
                            setStyle("-fx-background-color: #ffc0cb;");
                        }
                    }
                };
            }
        };
        return dayCellFactory;
    }
	
}
