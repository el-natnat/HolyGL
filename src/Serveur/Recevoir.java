package Serveur;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import Message.Message;

public class Recevoir implements Runnable {

	private Socket socket;
	private Service service;

	public Recevoir(Socket socket, Service service) {
		this.socket = socket;
		this.service = service;

	}


	@Override
	public void run() {
		// TODO Auto-generated method stub

		ObjectInputStream in;
		try {

			while (true) {
				in = new ObjectInputStream(socket.getInputStream());
				Message message = (Message) in.readObject();

				System.out.println("Serveur reçoit = " + message.toString());

				if (message.toString().equals("Coucou")) {
					this.service.Envoie(new Message("Bonjour"));
				}

			}

			// socket.close();
		} catch (IOException e) {
			System.out.println("Client déconnecté	");
		} catch (ClassNotFoundException e) {
			System.out.println("Impossible de lire le message");
		}

	}

}
