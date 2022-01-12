package Serveur;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Serveur {

	public static void main(String[] arg) {
		int portEcoute = 10302;
		ServerSocket standardiste;
		Socket socket;

		try {
			standardiste = new ServerSocket(portEcoute);
			while (true) {
				socket = standardiste.accept();
				new Service(socket);
			}
		} catch (IOException exc) {
			System.out.println("probleme de connexion");
		}
	}

}
