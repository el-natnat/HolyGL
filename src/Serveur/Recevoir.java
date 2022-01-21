package Serveur;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Message.*;
import interfaceGraphique.Password;
import utils.Congé;
import utils.Utilisateur;

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

				//
				Connection con;
				PreparedStatement pst;
				ResultSet rs;

				try {
					// Charger le driver MYSQL
					Class.forName("com.mysql.cj.jdbc.Driver");
					// Pour se connecter à la ("jdbc:mysql://localhost/nom de la base", "User du
					// serveur", "Mot de passe ")

					con = DriverManager.getConnection("jdbc:mysql://localhost/holidays", "root", "0000");

					// message type Demande identification
					if (message instanceof Demande_identification) {

						String idUser = ((Demande_identification) message).getId();
						String pass = ((Demande_identification) message).getMdp();
						Utilisateur utilisateur;

						// Ecrire requete
						String requete1 = "select * from authentification where login=?";
						// Entrer la requete SQL
						pst = con.prepareStatement(requete1);

						pst.setString(1, idUser);

						// exécuté requete SQL quand on fait un SELECT
						rs = pst.executeQuery();

						if (rs.next() && Password.check(pass, rs.getString(2))) {
							/// recherche l'user
							// Ecrire requete
							requete1 = "select * from user where idUser=?";
							// Entrer la requete SQL
							pst = con.prepareStatement(requete1);
							pst.setString(1, idUser);
							// exécuté requete SQL quand on fait un SELECT
							rs = pst.executeQuery();
							if (rs.next()) {

								utilisateur = new Utilisateur(rs.getString(1), rs.getString(2), rs.getString(3),
										rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), null);

								if (utilisateur.getStatut().equals("Classic")) {
									// recherche le nombre de congé dispo
									// Ecrire requete
									requete1 = "SELECT nbJours FROM tableconges WHERE type=? AND id=?;";
									// Entrer la requete SQL
									pst = con.prepareStatement(requete1);
									pst.setString(1, "RTT");
									pst.setString(2, idUser);
									// exécuté requete SQL quand on fait un SELECT
									rs = pst.executeQuery();
									if (rs.next()) {
										utilisateur.setNbr_CongéDispo(rs.getString(1));
									}
								}
								this.service.Envoie(new Reponse_recherche_utilisateur(null, true, utilisateur));
							} else {
								this.service.Envoie(new Reponse_recherche_utilisateur(null, false,
										new Utilisateur(null, null, null, null, null, null, null, null)));
							}
						} else {
							this.service.Envoie(new Reponse_recherche_utilisateur(null, false,
									new Utilisateur(null, null, null, null, null, null, null, null)));

						}
					}

					// Demande_historique des congés
					if (message instanceof Demande_historique_Congé) {
						String id = ((Demande_historique_Congé) message).getId_utilisateur();
						ArrayList<Congé> conges = new ArrayList<Congé>();

						// Ecrire requete
						String requete1 = "SELECT * FROM demandeconges where utilisateur = ?;";
						// Entrer la requete SQL
						pst = con.prepareStatement(requete1);
						pst.setString(1, id);

						// exécuté requete SQL quand on fait un SELECT
						rs = pst.executeQuery();
						while (rs.next()) {
							conges.add(new Congé(rs.getString(1), rs.getString(3), rs.getString(2), rs.getString(4),
									rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9),
									rs.getString(10)));

						}
						this.service.Envoie(new Reponse_Liste_Congé(null, conges));
					}

					// Demande_liste congé a vaider
					if (message instanceof Demande_Liste_congé_a_valider) {
						String id = ((Demande_Liste_congé_a_valider) message).getId_demandeur();
						ArrayList<Congé> conges = new ArrayList<Congé>();

						// Ecrire requete
						String requete1 = "SELECT * FROM demandeconges,user where demandeconges.utilisateur=user.idUser and demandeconges.statut = \"T\";";
						// Entrer la requete SQL
						pst = con.prepareStatement(requete1);
						;

						// exécuté requete SQL quand on fait un SELECT
						rs = pst.executeQuery();
						while (rs.next()) {
							conges.add(new Congé(rs.getString(1), rs.getString(2), rs.getString(4), rs.getString(5),
									rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9),
									rs.getString(10),
									new Utilisateur(rs.getString(11), rs.getString(12), rs.getString(13),
											rs.getString(14), rs.getString(15), rs.getString(16), rs.getString(17))));

						}
						this.service.Envoie(new Reponse_Liste_Congé(null, conges));
					}

					// Entre une demande de congé
					if (message instanceof Demande_ddc) {
						Congé congé = ((Demande_ddc) message).getConge();

						// Ecrire requete
						String requete1 = " INSERT INTO demandeconges (nomDemande,utilisateur,typeConges,dateDebut,nbJours,dateDemande,statut) VALUES (?,? ,?,?,?,curdate(),\"T\");";
						// Entrer la requete SQL
						pst = con.prepareStatement(requete1);
						pst.setString(1, congé.getNomDemande());
						pst.setString(2, congé.getUtilisateur());
						pst.setString(3, congé.getTypeCongé());
						pst.setString(4, congé.getDateDébut());
						pst.setString(5, congé.getNbjour());

						// exécuté requete SQL quand on fait un SELECT
						pst.executeUpdate();

					}

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
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
