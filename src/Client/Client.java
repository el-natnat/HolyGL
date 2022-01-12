package Client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import Message.*;
import utils.Congé;
import utils.Utilisateur;

public class Client {
	private int portEcouteServeur = 10302;
	private String host = "127.0.0.1";
	private Socket socket = null;

	public Client() {
		try {
			socket = new Socket(host, portEcouteServeur);

			Thread recevoir = new Thread(new Recevoir(socket, this));
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
	
	public static void main(String[] arg) {

		Client cl =new Client();

		cl.Envoie((Message) new Demande_identification("12", "Anatole", "4561"));
		cl.Envoie(new Reponse_recherche_utilisateur(null,false,new Utilisateur(null, null, null, null, null, null, null)));
		cl.Envoie(new Demande_historique_Congé(null,"1"));
		ArrayList<Congé> conges =new ArrayList<Congé>();
		conges.add(new Congé("1", null, null, null, null, null, null));
		conges.add(new Congé("2", null, null, null, null, null, null));
		
		cl.Envoie(new Reponse_Liste_Congé(null,conges));
		cl.Envoie(new Demande_Liste_congé_a_valider(null,"17"));
		cl.Envoie(new Accepte_Refuse_ddC(null,"1",null,null,true,"noel"));
		cl.Envoie(new Demande_ddc(null,new Congé("1", null, null, null, null, null, null)));
		cl.Envoie(new Demande_Listes_congés_n_1(null,"12"));
		
	}
}