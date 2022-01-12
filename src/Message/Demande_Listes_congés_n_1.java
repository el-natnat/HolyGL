package Message;

import java.io.Serializable;

public class Demande_Listes_congés_n_1 extends Message implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8741567169605057790L;
	public String id_demandeur;

	public Demande_Listes_congés_n_1(String message, String id_demandeur) {
		super(message);
		this.id_demandeur = id_demandeur;
	}

	@Override
	public String toString() {
		return "Demande_Listes_congés_n_1 [id_demandeur=" + id_demandeur + "]";
	}
	
	

}
