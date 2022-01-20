package interfaceGraphique;

import java.io.IOException;
import java.time.LocalDate;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public interface DisplayInterface {
	
	public static ObjectAjoutConges displayAjoutConges() throws IOException {
		
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.initStyle(StageStyle.TRANSPARENT);
		Parent root = FXMLLoader.load(Interface.class.getResource("InterfaceUtilisateurDate.fxml"));
		Scene scene = new Scene(root);
		
		window.setScene(scene);
		window.showAndWait();
		
		ObjectAjoutConges l = new ObjectAjoutConges(InterfaceUtilisateurAjoutController.congesValide,
													InterfaceUtilisateurAjoutController.nomConges,
													InterfaceUtilisateurAjoutController.debutConges,
													InterfaceUtilisateurAjoutController.finConges);
		return l;
	}
	
	public static void displayInfo(String message) throws IOException {
		
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.initStyle(StageStyle.TRANSPARENT);
		FXMLLoader loader = new FXMLLoader(Interface.class.getResource("InterfaceInfo.fxml"));
		Parent root = loader.load();
		InterfaceInfoController controller = loader.getController();
		controller.updateText(message);
		Scene scene = new Scene(root);
		window.setScene(scene);
		window.showAndWait();
	}
}

class ObjectAjoutConges{
	
	private boolean congesValide;
	private String nomConges;
	private LocalDate debutConges,finConges;
	
	public ObjectAjoutConges(boolean congesValide, String nomConges, LocalDate debutConges, LocalDate finConges) {
		this.congesValide = congesValide;
		this.nomConges = nomConges;
		this.debutConges = debutConges;
		this.finConges = finConges;
	}
	
	public boolean isCongesValide() {
		return congesValide;
	}
	public void setCongesValide(boolean congesValide) {
		this.congesValide = congesValide;
	}
	public String getNomConges() {
		return nomConges;
	}
	public void setNomConges(String nomConges) {
		this.nomConges = nomConges;
	}
	public LocalDate getDebutConges() {
		return debutConges;
	}
	public void setDebutConges(LocalDate debutConges) {
		this.debutConges = debutConges;
	}
	public LocalDate getFinConges() {
		return finConges;
	}
	public void setFinConges(LocalDate finConges) {
		this.finConges = finConges;
	}
}
