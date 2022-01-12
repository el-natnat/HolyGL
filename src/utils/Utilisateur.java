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
	private String soldeCongé;
	private String soldeRTT;
	
	public Utilisateur(String id, String nom, String prenom, String statut, String superieur, String soldeCongé,
			String soldeRTT) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.statut = statut;
		this.superieur = superieur;
		this.soldeCongé = soldeCongé;
		this.soldeRTT = soldeRTT;
	}

	@Override
	public String toString() {
		return "Uilisateur [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", statut=" + statut + ", superieur="
				+ superieur + ", soldeCongé=" + soldeCongé + ", soldeRTT=" + soldeRTT + "]";
	}
	

}
