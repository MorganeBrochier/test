package com.humanbooster.echeancier.business;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Duree {
	
	private Pret pret;
	private Date dureePret;
	
	
	public Duree(String dureePret) {
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
		try {
			this.dureePret = sdf1.parse(dureePret);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
