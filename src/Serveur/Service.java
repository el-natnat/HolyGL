package Serveur;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.net.Socket;

import Message.Envoyer;
import Message.Message;

public class Service extends Thread {
	Socket socket;
	BufferedReader entree;
	PrintStream sortie;

	Service(Socket socket) {
		this.socket = socket;
		this.start();

		Thread recevoir = new Thread(new Recevoir(socket, this));
		recevoir.start();

	}

	public void Envoie(Message message) {
		Thread envoyer = new Thread(new Envoyer(this.socket,(Message) message));
		envoyer.start();
	}

	public void run() {

		 //Envoie(new Message("La reponse"));

	}
}