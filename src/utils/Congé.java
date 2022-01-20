package utils;

import java.io.Serializable;

public class Congé implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7394976055673042633L;
	public String id;
	public String utilisateur;
	public String nomDemande;
	public String typeCongé;
	public String dateDébut;
	public String nbjour;
	public String dateDemade;
	public String statut;
	public String emetteurReponse;
	public String justification;
	@Override
	public String toString() {
		return "Congé [id=" + id + ", utilisateur=" + utilisateur + ", nomDemande=" + nomDemande + ", typeCongé="
				+ typeCongé + ", dateDébut=" + dateDébut + ", nbjour=" + nbjour + ", dateDemade=" + dateDemade
				+ ", statut=" + statut + ", emetteurReponse=" + emetteurReponse + ", justification=" + justification
				+ "]";
	}
	public Congé(String id, String utilisateur, String nomDemande, String typeCongé, String dateDébut, String nbjour) {
		super();
		this.id = id;
		this.utilisateur = utilisateur;
		this.nomDemande = nomDemande;
		this.typeCongé = typeCongé;
		this.dateDébut = dateDébut;
		this.nbjour = nbjour;
		
	}
	
	
	public Congé(String id, String utilisateur, String nomDemande, String typeCongé, String dateDébut, String nbjour,
			String dateDemade, String statut, String emetteurReponse, String justification) {
		super();
		this.id = id;
		this.utilisateur = utilisateur;
		this.nomDemande = nomDemande;
		this.typeCongé = typeCongé;
		this.dateDébut = dateDébut;
		this.nbjour = nbjour;
		this.dateDemade = dateDemade;
		this.statut = statut;
		this.emetteurReponse = emetteurReponse;
		this.justification = justification;
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @return the utilisateur
	 */
	public String getUtilisateur() {
		return utilisateur;
	}
	/**
	 * @return the nomDemande
	 */
	public String getNomDemande() {
		return nomDemande;
	}
	/**
	 * @return the typeCongé
	 */
	public String getTypeCongé() {
		return typeCongé;
	}
	/**
	 * @return the dateDébut
	 */
	public String getDateDébut() {
		return dateDébut;
	}
	/**
	 * @return the nbjour
	 */
	public String getNbjour() {
		return nbjour;
	}
	/**
	 * @return the dateDemade
	 */
	public String getDateDemade() {
		return dateDemade;
	}
	/**
	 * @return the statut
	 */
	public String getStatut() {
		return statut;
	}
	/**
	 * @return the emetteurReponse
	 */
	public String getEmetteurReponse() {
		return emetteurReponse;
	}
	/**
	 * @return the justification
	 */
	public String getJustification() {
		return justification;
	}
	
	
	
}
