package Message;

import java.io.Serializable;

import interfaceGraphique.Password;

public class Add_Employé extends Message implements Serializable {

	private String nom;
	private String prenom;
	private String login;
	private String mdp;
	private String statut;
	private String date;
	
	private Password password = new Password();
	@Override
	public String toString() {
		return "Add_Employé [nom=" + nom + ", prenom=" + prenom + ", login=" + login + ", mdp=" + mdp + ", statut="
				+ statut + ", date=" + date + "]";
	}
	public Add_Employé(String message, String nom, String prenom, String login, String mdp, String statut,
			String date) throws Exception {
		super(message);
		this.nom = nom;
		this.prenom = prenom;
		this.login = login;
		
		this.mdp = password.getSaltedHash(mdp);
		this.statut = statut;
		this.date = date;
	}
	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}
	/**
	 * @return the prenom
	 */
	public String getPrenom() {
		return prenom;
	}
	/**
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}
	/**
	 * @return the mdp
	 */
	public String getMdp() {
		return mdp;
	}
	/**
	 * @return the statut
	 */
	public String getStatut() {
		return statut;
	}
	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}
	
	
	
}
