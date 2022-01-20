package Message;

import java.io.Serializable;
import java.time.LocalDate;

import utils.Congé;

public class Demande_ddc extends Message implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2195685562893869024L;
	private Congé conge;

	public Demande_ddc(String message, Congé conge) {
		super(message);
		this.conge = conge;
		 LocalDate todaysDate = LocalDate.now();
		this.conge.dateDemade = todaysDate.toString();
	}

	@Override
	public String toString() {
		return "Demande_ddc [conge=" + conge + "]";
	}

	/**
	 * @return the conge
	 */
	public Congé getConge() {
		return conge;
	}
	

}
