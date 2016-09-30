package com.humanbooster.echeancier.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.humanbooster.echeancier.business.Client;
import com.humanbooster.echeancier.business.Pret;
import com.humanbooster.echeancier.service.ClientService;

public class ClientServiceImpl implements ClientService {
	private static final String NOM_FICHIER_BINAIRE = "echeancier.bin";
	private List<Client> clients = new ArrayList<>();
	private List<Pret> prets = new ArrayList<>();

	@Override
	public Client ajouterClient(String nom, String prenom, int numeroClient) {
	
	// On creer un objet client
	Client client1 = new Client();
	client1.setNom(nom);
	client1.setPrenom(prenom);
	client1.setNumeroClient(String.valueOf(numeroClient));
	
	// On ajoute le nouveau client a la collection de clients
	clients.add(client1);

	return client1;
	}

	public List<Client> getClients() {
		return clients;
	}

	public void setClients(List<Client> clients) {
		this.clients = clients;
	}

	@Override
	public boolean supprimerClient(String numeroClient) {
		for (@SuppressWarnings("rawtypes")
		Iterator iterator = clients.iterator(); iterator.hasNext();) {
			@SuppressWarnings("unused")
			Client clientEnCours = (Client) iterator.next();
		}
		return true;
	}

	@Override
	public Client modifierClient(String nom, String prenom, String numeroClient) {
		
		
		for (@SuppressWarnings("rawtypes")
		Iterator iterator = clients.iterator(); iterator.hasNext();) {
			@SuppressWarnings("unused")
			Client clientEnCours = (Client) iterator.next();
		}
		
		return null;
	}

	@Override
	public Client recupererClient(String numeroClient) {
		// etape 1 : on parcourt la collection du collection
		for (@SuppressWarnings("rawtypes")
		Iterator iterator = clients.iterator(); iterator.hasNext();) {
			Client clientEnCours = (Client) iterator.next();
			System.out.println(clientEnCours);
			// etape2 : pour chaque client on regarde le numero client et on
			// compare le num client avec le numero client en parametre . Si
			// numero sont differents on fait rien et si num egaux on renvoie l
			// objet clioent en cours
			if (clientEnCours.getNumeroClient().equals(numeroClient))
				return clientEnCours;

		}
		return null;
	}

	@Override
	public List<Client> recupererClients(String nom) {

		return clients;
	}

	@Override
	public List<Client> recupererTousLesClients() {

		return clients;
	}

	@Override
	public String toString() {
		return "ClientServiceImpl [clients=" + clients + ", prets=" + prets + "]";
	}

	@Override
	@SuppressWarnings("unchecked")
	public void chargerClients() {
		File fichier = new File(NOM_FICHIER_BINAIRE);
		try {
			FileInputStream fis = new FileInputStream(fichier);
			ObjectInputStream ois = new ObjectInputStream(fis);
			prets = (List<Pret>) ois.readObject();
			System.out.println("Nombre de clients dans la collection sauvegardee: " + prets.size());
			ois.close();
			fis.close();
		} catch (Exception e) {
			System.out.print(e.toString());
		}
	}

	@Override
	public void enregistrerCllients() {
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

	@Override
	public void associerPret(Client clientCible, Pret pret1) {
		for (Client client1 : clients) {
			if (client1.getNumeroClient().equals(clientCible.getNumeroClient())) {
				client1.ajouterPret(pret1);
			}
		}

	}

	
	

}
