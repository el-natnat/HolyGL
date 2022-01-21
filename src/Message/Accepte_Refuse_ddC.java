package Message;

import java.io.Serializable;

public class Accepte_Refuse_ddC extends Message implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4431753020816928580L;
	private String id_congé;
	private String emetteur;
	private String receveur;
	private boolean accepte;
	private String msg;
	public Accepte_Refuse_ddC(String message, String id_congé, String emetteur, String receveur, boolean accepte,
			String msg) {
		super(message);
		this.id_congé = id_congé;
		this.emetteur = emetteur;
		this.receveur = receveur;
		this.accepte = accepte;
		this.msg = msg;
	}
	@Override
	public String toString() {
		return "Accepte_Refuse_ddC [id_congé=" + id_congé + ", emetteur=" + emetteur + ", receveur=" + receveur
				+ ", accepte=" + accepte + ", msg=" + msg + "]";
	}
	/**
	 * @return the id_congé
	 */
	public String getId_congé() {
		return id_congé;
	}
	/**
	 * @return the emetteur
	 */
	public String getEmetteur() {
		return emetteur;
	}
	/**
	 * @return the receveur
	 */
	public String getReceveur() {
		return receveur;
	}
	/**
	 * @return the accepte
	 */
	public boolean isAccepte() {
		return accepte;
	}
	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}
	
	
	

}
