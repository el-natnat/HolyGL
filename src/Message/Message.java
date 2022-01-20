package Message;

import java.io.Serializable;

public class Message implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;

	public Message(String message) {
		super();
		this.message = message;
	}

	@Override
	public String toString() {
		return message;
	}

}
