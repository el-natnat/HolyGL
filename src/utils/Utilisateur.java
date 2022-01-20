package utils;

import java.io.Serializable;

public class Utilisateur  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8404321026785406329L;
	private String id;
	private String nom;
	private String prenom;
	private String statut;
	private String superieur;
	private String dateEmbauche;
	private String quotite;
	private String nbr_CongéDispo;
	
	public Utilisateur(String id, String nom, String prenom, String statut, String superieur, String dateEmbauche,
			String quotite,String nbr_CongéDispo) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.statut = statut;
		this.superieur = superieur;
		this.dateEmbauche = dateEmbauche;
		this.quotite = quotite;
		this.nbr_CongéDispo=nbr_CongéDispo;
	}
	@Override
	public String toString() {
		return "Utilisateur [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", statut=" + statut + ", superieur="
				+ superieur + ", dateEmbauche=" + dateEmbauche + ", quotite=" + quotite + ", nombre de Congé dispo="+nbr_CongéDispo+"]";
	}
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
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
	 * @return the statut
	 */
	public String getStatut() {
		return statut;
	}
	/**
	 * @return the superieur
	 */
	public String getSuperieur() {
		return superieur;
	}
	/**
	 * @return the dateEmbauche
	 */
	public String getDateEmbauche() {
		return dateEmbauche;
	}
	/**
	 * @return the quotite
	 */
	public String getQuotite() {
		return quotite;
	}
	/**
	 * @return the nbr_CongéDispo
	 */
	public String getNbr_CongéDispo() {
		return nbr_CongéDispo;
	}
	/**
	 * @param nbr_CongéDispo the nbr_CongéDispo to set
	 */
	public void setNbr_CongéDispo(String nbr_CongéDispo) {
		this.nbr_CongéDispo = nbr_CongéDispo;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	/**
	 * @param prenom the prenom to set
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	/**
	 * @param statut the statut to set
	 */
	public void setStatut(String statut) {
		this.statut = statut;
	}
	/**
	 * @param superieur the superieur to set
	 */
	public void setSuperieur(String superieur) {
		this.superieur = superieur;
	}
	/**
	 * @param dateEmbauche the dateEmbauche to set
	 */
	public void setDateEmbauche(String dateEmbauche) {
		this.dateEmbauche = dateEmbauche;
	}
	/**
	 * @param quotite the quotite to set
	 */
	public void setQuotite(String quotite) {
		this.quotite = quotite;
	}
	
	
	

}
