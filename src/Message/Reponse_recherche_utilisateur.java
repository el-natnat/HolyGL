package Message;

import java.io.Serializable;
import utils.Utilisateur;

public class Reponse_recherche_utilisateur extends Message implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3846186170616790834L;
	private boolean utilisateur_existe;
	private Utilisateur user;
	
	public Reponse_recherche_utilisateur(String message, boolean utilisateur_existe, Utilisateur utilisateur) {
		super(message);
		this.utilisateur_existe = utilisateur_existe;
		this.user = utilisateur;
	}

	@Override
	public String toString() {
		if (!this.utilisateur_existe ) {
			return "Reponse_recherche_utilisateur [utilisateur n'existe pas ]";

		} else {
			return "Reponse_recherche_utilisateur [utilisateur_existe=" + utilisateur_existe +" , "+ user.toString() + "]";
		}
	}

	/**
	 * @return the utilisateur_existe
	 */
	public boolean isUtilisateur_existe() {
		return utilisateur_existe;
	}

	/**
	 * @return the user
	 */
	public Utilisateur getUser() {
		return user;
	}
	
	

}
