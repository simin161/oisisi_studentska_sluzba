package model;


import java.util.ArrayList;

import model.nabrojiviTipovi.Status;

public class Student {
	
	private String prezime;
	private String ime;
	private String datumRodjenja;
	private String adresaStanovanja;
	private String telefon;
	private String email;
	private String brIndeksa;
	private int godinaUpisa;
	private int trenutnaGodina;
	private Status status;
	private double prosecnaOcena;
	private ArrayList<Ocena> polozeno;
	//fali lista nepolozenih predmeta
	
	Student(){
		
	}

}
