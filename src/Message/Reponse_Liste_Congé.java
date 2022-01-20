package Message;
import utils.*;
import java.io.Serializable;
import java.util.ArrayList; 

public class Reponse_Liste_Congé extends Message implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4787839304081797209L;
	private ArrayList<Congé> conges = new ArrayList<Congé>();

	public Reponse_Liste_Congé(String message, ArrayList<Congé> conges) {
		super(message);
		this.conges = conges;
	}

	@Override
	public String toString() {
		final int maxLen = 10;
		return "Reponse_Liste_Congé [conges="
				+ (conges != null ? conges.subList(0, Math.min(conges.size(), maxLen)) : null) + "]";
	}

	/**
	 * @return the conges
	 */
	public ArrayList<Congé> getConges() {
		return conges;
	}
	
	
}
