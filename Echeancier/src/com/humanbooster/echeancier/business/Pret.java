package com.humanbooster.echeancier.business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;



@SuppressWarnings("unused")
public class Pret implements Comparable<Pret>, Comparator<Pret>, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// On creer des variables 
	private float taux = 0;
	private Client client;
	private float montantEmprunte;
	private String raisonDuPret;
	private Duree duree;
	// pret = collection de mensualites
	private List<Mensualite> mensualites = new ArrayList<>();
	
	private List<Pret> prets = new ArrayList<>();
	
	
	public Duree getDuree() {
		return duree;
	}

	public void setDuree(Duree duree) {
		this.duree = duree;
	}


	public void addMensualite(Mensualite mensualite) {
		// On ajoute la mensualite donnée en parametre a la collection privee
		// mensualites
		mensualites.add(mensualite);
	}

	public String getRaisonDuPret() {
		return raisonDuPret;
	}

	public void setRaisonDuPret(String raisonDuPret) {
		this.raisonDuPret = raisonDuPret;
	}

	public float getMontantEmprunte() {
		return montantEmprunte;
	}

	public void setMontantEmprunte(float montantEmprunte) {
		this.montantEmprunte = montantEmprunte;
	}

	public float getTaux() {
		return taux;
	}

	public void setTaux(float taux) {
		this.taux = taux;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public List<Mensualite> getMensualites() {
		return mensualites;
	}

	public void setMensualites(List<Mensualite> mensualites) {
		this.mensualites = mensualites;
	}

	@Override
	public int compareTo(Pret autrePret) {
		return -new Float(this.getMontantEmprunte()).compareTo(autrePret.getMontantEmprunte());
	}

	@Override
	public int compare(Pret pret, Pret autrePret) {
		return -new Float(pret.getTaux()).compareTo(autrePret.getTaux());

	}
	@Override
	public String toString() {
		return "Pret [taux=" + taux + ", montantEmprunte=" + montantEmprunte + ", mensualites=" + mensualites + "]";
	}
}
