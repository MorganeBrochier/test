package com.humanbooster.echeancier.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileReader {
	public String lireFichierMotDePasse() {
		File file = new File("MotDePasse.txt");
		FileInputStream fis = null;
		BufferedReader br = null;
		String contenu = null;
		try {
			fis = new FileInputStream(file);
			InputStreamReader isr = new InputStreamReader(fis);
			br = new BufferedReader(isr);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			contenu = br.readLine();
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return contenu;
	}
}
