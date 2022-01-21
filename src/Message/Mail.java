package Message;

import javax.mail.internet.*;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.*;

public class Mail   extends Thread{
	
	public Mail() {
		
	}
	
	public static void send(String from, String pwd, String to, String sub, String msg) {
		// Propriétés
		Properties p = new Properties();
		p.put("mail.smtp.host", "smtp.gmail.com");
		p.put("mail.smtp.socketFactory.port", "465");
		p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		p.put("mail.smtp.auth", "true");
		p.put("mail.smtp.port", "465");
		// Session
		Session s = Session.getDefaultInstance(p, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, pwd);
			}
		});
		// composer le message

		try {
			MimeMessage m = new MimeMessage(s);
			m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			m.setSubject(sub);
			m.setText(msg);
			// envoyer le message
			Transport.send(m);
			System.out.println("Message envoyé avec succès");
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void Envoie(String to, String sub, String msg) {
		send("enssat.holygl@gmail.com", "holy_gl1234", to, sub, msg);
	}

	public static void main(String[] arg) {

		Envoie("afaugere@enssat.fr", "Hello", "World");
	}
}