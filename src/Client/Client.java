package Client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import Message.*;


public class Client {
	private int portEcouteServeur = 10302;
	private String host = "127.0.0.1";
	private Socket socket = null;
	private Transfert transfert = new Transfert();

	public Client() {
		try {
			socket = new Socket(host, portEcouteServeur);

			Thread recevoir = new Thread(new Recevoir(socket,transfert, this));
			recevoir.start();

		} catch (UnknownHostException exc) {
			System.out.println("destinataire inconnu");
		} catch (IOException exc) {
			System.out.println("probleme d'entree-sortie");
		}
	}

	public void Envoie(Message message) {
		Thread envoyer = new Thread(new Envoyer(socket, message));
		envoyer.start();
	}
	
	
	public Message Recoit_Message() throws InterruptedException {
		return this.transfert.take();
	}

}