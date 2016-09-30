package com.humanbooster.echeancier.util;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import com.humanbooster.echeancier.business.Pret;

public class Mailer {
	public Mailer(Pret pret1) {
		String motDePasseUltraSecret;
		FileReader fileReader1 = new FileReader();
		motDePasseUltraSecret = fileReader1.lireFichierMotDePasse();
		Email email = new SimpleEmail();

		email.setHostName("ns0.ovh.net");
		email.setSmtpPort(587);
		email.setAuthenticator(new DefaultAuthenticator("aatanasio@humanbooster.com", motDePasseUltraSecret));
		email.setSSLOnConnect(true);
		email.setSubject("Voici votre écheancier");
		try {
			email.setFrom("aatanasio@humanbooster.com");
		} catch (EmailException e1) {

			e1.printStackTrace();
		}

		try {
			email.addTo("aatanasi@humanbooster.com");
		} catch (EmailException e) {
			e.printStackTrace();
		}
		try {
			email.setMsg(pret1.toString());
		} catch (EmailException e) {

			e.printStackTrace();
		}
		try {
			email.send();
		} catch (EmailException e) {

			e.printStackTrace();
		}
	}
}
