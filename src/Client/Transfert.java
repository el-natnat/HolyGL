package Client;

import java.util.concurrent.LinkedTransferQueue;

import Message.Message;

public class Transfert extends LinkedTransferQueue<Message> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void Envoie_Signal(Message message) {
		try {
			this.transfer(message);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Message Recoie_Signal() {
		try {

			return this.take();

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return null;
	}

}
