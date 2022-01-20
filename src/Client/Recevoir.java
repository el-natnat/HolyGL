package Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import Message.Message;

public class Recevoir implements Runnable {

	private Socket socket;
	//private Client client;
	private Transfert transfert;

	public Recevoir(Socket socket,Transfert transfert, Client client) {
		super();
		this.socket = socket;
	//	this.client = client;
		this.transfert=transfert;
	}

	@Override
	public void run() {
		ObjectInputStream in;
		try {

			while (true) {
				in = new ObjectInputStream(socket.getInputStream());
				Message message = (Message) in.readObject();

				System.out.println("Client reçoit = " + message.toString());

				transfert.add(message);
			}

		} catch (IOException e) {
			System.out.println("probleme d'entree-sortie Recevoir");
		} catch (ClassNotFoundException e) {
			System.out.println("Impossible de lire le message");
		}

	}

}
