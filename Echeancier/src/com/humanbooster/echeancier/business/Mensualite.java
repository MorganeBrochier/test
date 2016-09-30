package com.humanbooster.echeancier.business;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Mensualite implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date datePrelevement;
	private float montantPreleve;
	private float capitalRembourse;
	private float interet;

	public float getCapitalRembourse() {
		return capitalRembourse;
	}

	public void setCapitalRembourse(float capitalRembourse) {
		this.capitalRembourse = capitalRembourse;
	}

	public float getInteret() {
		return interet;
	}

	public void setInteret(float interet) {
		this.interet = interet;
	}

	public Date getDatePrelevement() {
		return datePrelevement;
	}

	public void setDatePrelevement(Date datePrelevement) {
		this.datePrelevement = datePrelevement;
	}

	public float getMontantPreleve() {
		return montantPreleve;
	}

	public void setMontantPreleve(float montantPreleve) {
		this.montantPreleve = montantPreleve;
	}

	@Override
	public String toString() {
		SimpleDateFormat sdf1 = new SimpleDateFormat();
		return "Mensualite [datePrelevement=" + sdf1.format(datePrelevement) + ", montantPreleve=" + montantPreleve
				+ ", capitalRembourse = " + capitalRembourse + ", interet=" + interet + "]\n";
	}

}
