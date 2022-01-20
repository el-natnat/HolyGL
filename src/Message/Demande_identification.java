package Message;
import java.io.Serializable;

import interfaceGraphique.Password;

public class Demande_identification extends Message implements Serializable {

	private static final long serialVersionUID = -3652557128538612276L;
	/**
	 * 
	 */
	private String id;
	private String mdp;
	//private Password password =new Password();
	public Demande_identification(String message,String id ,String mdp) {
		super(message);
		try {
			this.mdp= mdp;
			this.id =id;
				
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public String toString() {
		return "Demande_identification [id=" + id + ", mdp=" + mdp + "]";
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @return the mdp
	 */
	public String getMdp() {
		return mdp;
	}

	
	
}
