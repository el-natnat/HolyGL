package Message;

import java.io.Serializable;

public class Demande_historique_Congé extends Message implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4098458124246224163L;
	private String id_utilisateur;

	public Demande_historique_Congé(String message, String id_utilisateur) {
		super(message);
		this.id_utilisateur = id_utilisateur;
	}

	@Override
	public String toString() {
		return "Demande_historique_Congé [id_utilisateur=" + id_utilisateur + "]";
	}

	/**
	 * @return the id_utilisateur
	 */
	public String getId_utilisateur() {
		return id_utilisateur;
	}
	
	

}
