package Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import Message.Message;

public class Recevoir implements Runnable {

	private Socket socket;
	private Client client;

	public Recevoir(Socket socket, Client client) {
		super();
		this.socket = socket;
		this.client = client;
	}

	@Override
	public void run() {
		ObjectInputStream in;
		try {

			while (true) {
				in = new ObjectInputStream(socket.getInputStream());
				Message message = (Message) in.readObject();

				System.out.println("Client reçoit = " + message.toString());

				if (message.toString().equals("Bonjour")) {
					Thread.sleep(1000);
					this.client.Envoie(new Message("Comment vas tu ?"));
				}
			}

		} catch (IOException e) {
			System.out.println("probleme d'entree-sortie Recevoir");
		} catch (ClassNotFoundException e) {
			System.out.println("Impossible de lire le message");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
