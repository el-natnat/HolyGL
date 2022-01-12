package Message;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Envoyer implements Runnable {

	private ObjectOutputStream out;

	public Envoyer(Socket socket, Message message) {
		try {
			this.out = new ObjectOutputStream(socket.getOutputStream());
			System.out.println("Envoie = " + message.toString());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("probleme d'entree-sortie  Envoie");
		}
		try {
			this.out.writeObject( message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	@Override
	public void run() {

	}

}
