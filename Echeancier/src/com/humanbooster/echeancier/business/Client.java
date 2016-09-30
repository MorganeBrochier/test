package com.humanbooster.echeancier.business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tindus
 *
 */
public class Client implements Serializable {
	

	private static final long serialVersionUID = 1L;
	/**.
	 * param prets : collection
	 */
	private List<Pret> prets = new ArrayList<>();
	/**.
	 * param nom
	 */
	private String nom;
	/**.
	 * param prenom
	 */
	private String prenom;
	/**.
	 * param numeroClient
	 */
	private String numeroClient;


	public final String getNom() {
		return nom;
	}

	public final void setNom(String nom) {
		this.nom = nom;
	}

	public final String getPrenom() {
		return prenom;
	}

	public final void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public final String getNumeroClient() {
		return numeroClient;
	}

	public final void setNumeroClient(String numeroClient) {
		this.numeroClient = numeroClient;
	}


	public final void ajouterPret(Pret pret1) {
		prets.add(pret1);
		
	}

	@Override
	public final String toString() {
		return "Client [nom=" + nom + ", prenom=" + prenom + ", numeroClient=" + numeroClient
				+ "]";
	}
}
