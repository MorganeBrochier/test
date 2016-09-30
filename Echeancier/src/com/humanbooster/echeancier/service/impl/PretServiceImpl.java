package com.humanbooster.echeancier.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.humanbooster.echeancier.business.Client;
import com.humanbooster.echeancier.business.Mensualite;
import com.humanbooster.echeancier.business.Pret;
import com.humanbooster.echeancier.exceptions.MaximumNbPretException;
import com.humanbooster.echeancier.exceptions.PretHorsConditionException;
import com.humanbooster.echeancier.service.PretService;

public class PretServiceImpl implements PretService {
	private static final String NOM_FICHIER_BINAIRE = "echeancier.bin";
	private static final float CAPITAL_MAX_AUTORISE = 20000;
	private static final int NB_MENSUALITES_MAX = 48;
	private static final int NB_MAX_PRETS = 2;
	private List<Pret> prets = new ArrayList<>();

	/**
	 * Cette methode creer et renvoie un objet pret en se basant sur tous les
	 * parametres qu'elle reçoit Seul cette methode peut creer un pret Classe
	 * APP FERA appel a cette methode Une appli Web fera aussi appel a cette
	 * methode
	 * @throws MaximumNbPretException 
	 */
	@Override
	public Pret ajouterPret(Client clientCible, float tauxAnnuel, int nbMensualitesSouhaitees, float capitalRembourse,
			String raisonDuPret, int nbPrets, Date dateEcheance) throws PretHorsConditionException, MaximumNbPretException{
		
		// Si le capital depasse 20000 euros on leve uen exception 
		if (capitalRembourse > CAPITAL_MAX_AUTORISE  || capitalRembourse <1) throw new PretHorsConditionException("Montant non autorisé");
		if (nbMensualitesSouhaitees > NB_MENSUALITES_MAX) throw new PretHorsConditionException("mensualites depassées");
		if (nbPrets > NB_MAX_PRETS) throw new MaximumNbPretException("Nombres de prêts maximum atteints");
		// methode dispose de toutes les infos pour creer un objet pret
				// pour rappel, cette methode doit construire un objet pret ds lequel la
				// collection de mensualites contiendra unbe collection de mensualites
				// completes
				// On genere un nouveau pret
				// On genere un nouveau client
				float montantAPrelever = 0;
				float interetRembourse = 0;
				@SuppressWarnings("unused")
				Date datePrelevement;
				Pret pret1 = new Pret();

				pret1.setTaux(tauxAnnuel);

				// On associe le pret au client
				// pret1.setClient(client1);

				// On définit le montant du prêt
				pret1.setMontantEmprunte(capitalRembourse);

				// par polymorphisme c est la methode toString() qui est invoqué
				//pret1.setRaisonDuPret(raisonDuPret);

				// On cree des onbjets mensualites
				Calendar calendar1 = new GregorianCalendar();

				// calendar1.set(Calendar.YEAR, 2016);
				// calendar1.set(Calendar.MONTH, 9);
				// calendar1.set(Calendar.DAY_OF_MONTH, 7);
				//
				//  On definit le montant a prelever par mois car il est fixe par
				// mois

				montantAPrelever = (float) (capitalRembourse * (tauxAnnuel / 12)
						/ (1 - Math.pow(1 + (tauxAnnuel / 12), -nbMensualitesSouhaitees)));

				for (int i = 0; i < nbMensualitesSouhaitees; i++) {
					Mensualite mensualite1 = new Mensualite();

					mensualite1.setMontantPreleve(montantAPrelever);
					// On defint la date de prelevement
					mensualite1.setDatePrelevement(dateEcheance);

					// On ajoute le mois a prelever
					calendar1.add(Calendar.MONTH, 1);

					//  On definit le montant des interets
					// On doit definir pour chaque mensualite ce qui correspond a la
					// part des interets
					// Plus on progresse dans la boucle plus la part diminue

					interetRembourse = (montantAPrelever * 24) - (capitalRembourse) / nbMensualitesSouhaitees;
					mensualite1.setInteret(interetRembourse);

					//  On definit la part du capital remboursé par mois
					mensualite1.setCapitalRembourse((i + 1) * (capitalRembourse / nbMensualitesSouhaitees));
					pret1.addMensualite(mensualite1);

				} // End of for

				// On ajoute un nouveau pret a la collection privée de prets
				prets.add(pret1);
				return pret1;
	}

	// On recupere le montant moyen du capital emprunté pour les prêts ayant une durée de 12 mois
		public float recupererMoyenneMontantEmprunte() {
			// On additionne le prix de tous les téléphones et on divise par le
			// nombre de téléphones correspondants pour obtenir la moyenne
			
			float moyenne = 0;
			float sommeMontantEmprunte = 0;
			
			for (Pret pret : prets) {
				sommeMontantEmprunte = sommeMontantEmprunte + pret.getMontantEmprunte();
			}
			
			return moyenne = (sommeMontantEmprunte / prets.size());
		}

	@Override
	public boolean supprimerPret(Pret pret1) {
		
		boolean supprimerOk = prets.remove(pret1);
		return supprimerOk;
	}

	@Override
	public List<Pret> retrouverPret(int numeroClient) {

		return prets;
	}

	@Override
	public Pret retrouverPret(int numeroClient, Date dateDebutPret) {
		Pret pret1 = new Pret();
		return pret1;
	}

	@Override
	public Client recupererClient(int numeroClient) {
		//Client client1 = new Client(numeroClient);
	return null;
	}

	

	@Override
	public void trierParTaux() {
		Collections.sort(prets);
		
		
	}
	
	@Override
	public void trierParMontant() {
		Collections.sort(prets);
		
		
	}

	@Override
	public List<Pret> recupererTousLesPrets() {
		// On renvoie la collection de prets dans son integralite
		return prets;
	}

	@Override
	public void enregistrerPrets() {
		File fichier = new File(NOM_FICHIER_BINAIRE);
		try {
			FileOutputStream fos = new FileOutputStream(fichier);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(prets);
			oos.close();
			fos.close();
		} catch (Exception e) {
			System.out.print(e.toString());
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public void chargerPrets() {

		File fichier = new File(NOM_FICHIER_BINAIRE);
		try {
			FileInputStream fis = new FileInputStream(fichier);
			ObjectInputStream ois = new ObjectInputStream(fis);
			prets = (List<Pret>) ois.readObject();
			System.out.println("Nombre de prets dans la collection sauvegardee: " + prets.size());
			ois.close();
			fis.close();
		} catch (Exception e) {
			System.out.print(e.toString());
		}
	}




	

	
}
