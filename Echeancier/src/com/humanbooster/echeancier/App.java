package com.humanbooster.echeancier;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.humanbooster.echeancier.business.Client;
import com.humanbooster.echeancier.business.Pret;
import com.humanbooster.echeancier.exceptions.MaximumNbPretException;
import com.humanbooster.echeancier.exceptions.PretHorsConditionException;
import com.humanbooster.echeancier.service.ClientService;
import com.humanbooster.echeancier.service.PretService;
import com.humanbooster.echeancier.service.impl.ClientServiceImpl;
import com.humanbooster.echeancier.service.impl.PretServiceImpl;
import com.humanbooster.echeancier.util.Mailer;
/**
 * 
 * @author tindus
 *
 */
@SuppressWarnings("unused")
public class App {
	/**.
	 * scanner sc : on instance l'objet sc pour donner accès à l'utilisateur 
	 */
	private static Scanner sc = new Scanner(System.in);
	/**.
	 * pretService1 : on creer l'objet pretService1 
	 */
	private static PretService pretService1 = new PretServiceImpl();
	// Comme les methodes de la classe App sont toutes static, il faut definir
	// aussi l objet clientService1 en static
	
	/**.
	 * clientService1 : on creer l'objet clientService1 pour recuperer le client
	 */
	private static ClientService clientService1 = new ClientServiceImpl();
/**
 * .
 * methode main : methode permettant de lancer l'appli
 * @param args : argument
 */
	public static void main(final String[] args) {
		afficherMenuPrincipal();
		demanderChoixMenuPrincipal();

		sc.close();
	}
/**.
 * methode afficherMenuPrincipal : affiche le menu principal a l'user
 */
	public static void afficherMenuPrincipal() {
		System.out.println("1 - Voir tous les clients");
		System.out.println("2 - Ajouter un client");
		System.out.println("3 - voir tous les prets triées par montant (du plus élevé au plus petit");
		System.out.println("4 - voir tous les prets triées par taux (du plus élevé au plus petit");
		System.out.println("5 - voir liste des prêts quidébutent");
		System.out.println("6 - Ajouter un prêt");
		System.out.println("7 Quitter");
		System.out.println("Merci de saisir votre choix :");
	}
/**.
 * Method demanderChoixMEnuPrincipal : on demande le choix à l'user pour le menu
 */
	public static void demanderChoixMenuPrincipal() {
		String choixSaisi = sc.nextLine();
		String numeroClient = " ";
		int reponse = 0;
		int choix = 0;
		Client client = null;

		try {
			choix = Integer.parseInt(choixSaisi);
		} catch (NumberFormatException e) {
			System.out.println("Vous ne pouvez saisir autre chose que des chiffres");
		}

		switch (choix) {

		case 1:
			afficherClient();
			break;

		case 2:
			ajouterClient();
			break;

		case 3:
			trierParMontant(client);
			break;

		case 4:
			trierParTaux(client);
			break;

		case 5:
			listePretDebute();
			break;

		case 6:
			ajouterPret();
			break;

		default:
			System.out.println("Au revoir et à bientôt ! ");
			sc.close();
			pretService1.enregistrerPrets();
			pretService1.chargerPrets();
			clientService1.chargerClients();
			clientService1.enregistrerCllients();

			System.out.println(pretService1.recupererTousLesPrets());
			System.out.println(clientService1.recupererTousLesClients());
			System.exit(0);
			break;

		}

	}
/**.
 * listePretDebute() : methode qui permet de lister les pret selon la date  
 */
	private static void listePretDebute() {

	}
/**.
 * trierParTaux() : methode qui permet de trier 
 * les prets par taux du plus grand au plus petit 
 * @param client : client cible
 */
	private static void trierParTaux(final Client client) {

		pretService1.trierParTaux();
		System.out.println(pretService1.recupererTousLesPrets());
		main(null);
	}

	/**.
	 * trierParMontant() : methode qui permet de trier 
	 * les prets par montant du plus grand au plus petit 
	 * @param client : client cible
	 */
	private static void trierParMontant(final Client client) {

		pretService1.trierParMontant();
		System.out.println(pretService1.recupererTousLesPrets());
		main(null);
	}

	/**.
	 * affficherClient() : methode qui permet d'affficher
	 * les clients et pour avoir tous les clients on appelle la methode recupererTousLesCLients()
	 * 
	 */
	public static void afficherClient() {

		System.out.println(clientService1.recupererTousLesClients());
		main(null);
	}
	/**.
	 * ajouterClient() : methode qui permet d'ajouter
	 * des clients 
	 *
	 */
	private static void ajouterClient() {

		String reponse = " ";
		int numeroClient = 0;
		String nom = " ";
		String prenom = " ";
		@SuppressWarnings("resource")
		Scanner sc1 = new Scanner(System.in);
		System.out.println("Bienvenue!\nentrez un nom");

		try {
			nom = sc1.nextLine();

		} catch (NumberFormatException e) {
			System.out.println("saisir qu'une chaine de caracteres");
		}

		System.out.println("entrez un prenom ");
		try {
			prenom = sc1.nextLine();
		} catch (NumberFormatException e) {
			System.out.println("saisir qu'une chaine de caracteres");
		}
		System.out.println("Entrez votre numéro Client");

		do {
			try {
				numeroClient = Integer.parseInt(sc1.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("Erreur Saisi");
			}

			System.out.println("Veuillez saisir des chiffres svp");
		}

		while (!(numeroClient > 0));

		Client client1 = clientService1.ajouterClient(nom, prenom, numeroClient);

		main(null);
	}
	// System.out.println("Souhaitez vous revenir au menu Principal ?
	// (oui/non)");
	// if (reponse.equals("oui")) {
	// try {
	// reponse = sc1.nextLine();
	// afficherMenuPrincipal();
	// } catch (NumberFormatException e) {
	// System.out.println("Vous ne pouvez saisir qu'une chaine de caractes sans
	// caracteres speciaux ");
	// } catch (InputMismatchException e) {
	// System.out.println("Vous ne pouvez pas mettre de virgule, point virgule,
	// etc");
	// }
	// }
	// do {
	// System.out.println("Veuillez réitérer votre choix par oui ou non ");
	// reponse = sc.nextLine();
	//
	// } while (!(reponse.equalsIgnoreCase("oui")) &&
	// !(reponse.equalsIgnoreCase("non")));
	//
	// }

	/**.
	 * ajouterPret() : methode qui permet d'ajouter
	 * des prêts 
	 *  
	 * 
	 */
	private static void ajouterPret() {

		float tauxAnnuel = 0.05f;
		float capitalRembourse = 1000;
		int nbMensualitesSouhaitees = 10;
		String numeroClient = " ";
		float reponse = 0;
		String raisonDuPret = " ";
		String reponse1 = " ";
		int nbPrets = 0;

		// Comme on se donne pas ke droit der creer un objet pret on passe
		// systematiquement en pretServiceImpl

		// On delegue la creation de l'objet pret a la couche service

		// System.out.println("Entrez votre numéro Client");
		// try {
		// numeroClient = sc.nextLine();
		// } catch (NumberFormatException e1) {
		// System.out.println("Vous ne pouvez saisir que des chiffres");
		// }
		Client clientCible = clientService1.recupererClient(numeroClient);
		//
		// System.out.println("Bienvenue !!\nVotre numéro est le " +
		// numeroClient);

		System.out.println("Entrez le montant du prêt :");
		try {
			reponse = Integer.parseInt(sc.nextLine());
			capitalRembourse = (int) reponse;
		} catch (NumberFormatException e4) {
			System.out.println("Vous ne pouvez saisir que des chiffres");
		}

		System.out.println("Choisissez la durée du prêt (exprimé en mois) ");
		System.out.println(
				"1. 12 mois, taux annuel : 1%\n2. 24 mois, taux annuel :2%\n3. 36 mois, taux annuel : 3%\n4. 48 mois, taux annuel :3.5%");
		try {
			reponse = Integer.parseInt(sc.nextLine());
			nbMensualitesSouhaitees = (int) reponse;
		} catch (NumberFormatException e) {
			System.out.println("Vous ne pouvez saisir que des chiffres");
		}

		// System.out.println("Quel est le taux annuel ? (exprimé en
		// pourcentage) ");
		// do{
		// try {
		// reponse = Integer.parseInt(sc.nextLine());
		// tauxAnnuel = (int) reponse;
		// }
		//
		// catch (NumberFormatException e3) {
		// System.out.println("Vous ne pouvez saisir que des entiers");
		// }
		// }while (!(reponse >= 0) && !(reponse < 1));
		//
		//
		// System.out.println("Quelle est la raison de votre prêt");
		// try {
		// reponse1 = sc.nextLine();
		// } catch (NumberFormatException e4) {
		// System.out.println("Veuillez saisir qu'une chaine de caractères");
		// } catch (InputMismatchException e) {
		// System.out.println("Vous ne pouvez pas mettre de caractères
		// spéciaux");
		// }
		//
		// raisonDuPret = reponse1;

		// On demande a luser la date de sa premiere echeance
		Date dateEcheance = null;
		// On creer l objet simpledateformat pour le mettre au bon format de
		// date
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
		System.out.println("Entrez la date de la première écheance dans le format jj/mm/aaaa ");

		// on recupere la reponse de l'user
		String reponse2 = sc.nextLine();

		try {
			dateEcheance = sdf1.parse(reponse2);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		// on creer une collection de prets
		List<Pret> prets = new ArrayList<>();
		for (int i = 0; i < prets.size() - 1; i++)

			System.out.println("Voici votre numéro du contrat : " + i);

		Pret pret1 = null;

		try {
			pret1 = pretService1.ajouterPret(clientCible, tauxAnnuel, nbMensualitesSouhaitees, capitalRembourse,
					raisonDuPret, nbPrets, dateEcheance);

			// clientService1.associerPret(clientCible, pret1);
			Mailer mailer1 = new Mailer(pret1);
			System.out.println("Voici  le récapitulatif de votre demande de pret \n" + pret1);
		} catch (PretHorsConditionException e) {
			System.out.println("Votre demande de prêt ne respecte pas les conditions" + " " + e.getMessage());
		}
		// On ajoute l exception maximum nbPret
		catch (MaximumNbPretException e) {
			System.out.println(e.getMessage());
		}

		main(null);

	}
}
