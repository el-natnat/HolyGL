package utils;

import java.io.Serializable;

public class Congé implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7394976055673042633L;
	public String id;
	public String utilisateur;
	public String typeCongé;
	public String dateDébut;
	public String nbjour;
	public String dateDemade;
	public String statut;
	
	@Override
	public String toString() {
		return "Congé [id=" + id + ", utilisateur=" + utilisateur + ", typeCongé=" + typeCongé + ", dateDébut="
				+ dateDébut + ", nbjour=" + nbjour + ", dateDemade=" + dateDemade + ", statut=" + statut + "]";
	}

	public Congé(String id, String utilisateur, String typeCongé, String dateDébut, String nbjour, String dateDemade,
			String statut) {
		super();
		this.id = id;
		this.utilisateur = utilisateur;
		this.typeCongé = typeCongé;
		this.dateDébut = dateDébut;
		this.nbjour = nbjour;
		this.dateDemade = dateDemade;
		this.statut = statut;
	}
	
	
}
