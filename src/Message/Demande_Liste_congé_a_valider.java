package Message;

import java.io.Serializable;

public class Demande_Liste_congé_a_valider extends Message implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2185966604067386433L;
	public String id_demandeur;//peut servir pour les N+1 si c'est un RH alors on renvoie la liste de congé qui est en cours de traitement

	public Demande_Liste_congé_a_valider(String message, String id_demandeur) {
		super(message);
		this.id_demandeur = id_demandeur;
	}

	@Override
	public String toString() {
		return "Demande_Liste_congé_a_valider [id_demandeur=" + id_demandeur + "]";
	}
	
	

}
