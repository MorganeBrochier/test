package com.humanbooster.echeancier.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.humanbooster.echeancier.business.Duree;
import com.humanbooster.echeancier.business.Pret;
import com.humanbooster.echeancier.service.DureeService;

public class DureeServiceImpl implements DureeService {
	List<Duree> periodes = new ArrayList<>();

	@Override
	public Pret ajouterDuree() {
		Pret pret1 = new Pret();
		Duree duree1 = new Duree("12 mois");
		Duree duree2 = new Duree("24 mois");
		Duree duree3 = new Duree("36 mois");
		Duree duree4 = new Duree("48 mois");
		
		periodes.add(duree1);
		periodes.add(duree2);
		periodes.add(duree3);
		periodes.add(duree4);
		
		return pret1;
	}

}
