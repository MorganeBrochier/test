package com.humanbooster.echeancier.service;

import java.util.Date;
import java.util.List;

import com.humanbooster.echeancier.business.Client;
import com.humanbooster.echeancier.business.Pret;
import com.humanbooster.echeancier.exceptions.MaximumNbPretException;
import com.humanbooster.echeancier.exceptions.PretHorsConditionException;

public interface PretService {
	// Interface PretService constitue une roadmap pour les couches supp (ici
	// classe App)

	/**
	 * 
	 * @param clientCible
	 *            : client qui souhaite contracter le pret
	 * @param tauxAnnuel
	 *            : taux fixe
	 * @param nbMensualitesSouhaitees
	 *            : le nombre de mensualités souhaitées pour le capital
	 *            remboursé
	 * @param capitalRembourse
	 *            : lmontanbt du capital a rembourser
	 * @param raisonDuPret:
	 *            raison du pret
	 * @return
	 * @throws PretHorsConditionException
	 *             on cree des exceptions si montant depasse un certain seuil ou
	 *             si montant erst nul ou si le nb de mensualites est depasse
	 *             alors l exception est levee
	 */
	public Pret ajouterPret(Client clientCible, float tauxAnnuel, int nbMensualitesSouhaitees, float capitalRembourse,
			String raisonDuPret, int nbPrets, Date dateEcheance)
			throws PretHorsConditionException, MaximumNbPretException;

	public boolean supprimerPret(Pret pret);

	// Methode qsui renvoie une collection de prets contracté par le client dt
	// le numero client donné en param
	public List<Pret> retrouverPret(int numeroClient);

	public Pret retrouverPret(int numeroClient, Date dateDebutPret);

	public Client recupererClient(int numeroClient);

	public void trierParMontant();

	public void trierParTaux();

	public List<Pret> recupererTousLesPrets();

	public void enregistrerPrets();

	public void chargerPrets();

	public float recupererMoyenneMontantEmprunte();
}
