package com.humanbooster.echeancier.service;

import java.util.List;

import com.humanbooster.echeancier.business.Client;
import com.humanbooster.echeancier.business.Pret;

public interface ClientService {
	public Client ajouterClient(String nom, String prenom, int numeroClient);

	public boolean supprimerClient(String numeroClient);

	public Client modifierClient(String nom, String prenom, String numeroClient);

	public Client recupererClient(String numeroClient);

	public List<Client> recupererClients(String nom);

	public List<Client> recupererTousLesClients();

	public void chargerClients();

	public void enregistrerCllients();

	public void associerPret(Client clientCible, Pret pret);
}
