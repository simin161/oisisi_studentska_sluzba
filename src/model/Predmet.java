package model;

import java.io.Serializable;
import java.util.List;

import model.nabrojiviTipovi.Semestar;

public class Predmet implements Serializable{

	private String sifra;
	private String naziv;
	private Semestar semestar;
	private int godina;
	private Profesor profesor;
	private int espb;
	private List<Student> polozili;
	private List<Student> nisuPolozili;
	
	public Predmet(String sifra, String naziv, Semestar semestar, int godina, Profesor profesor, int espb,
			List<Student> polozili, List<Student> nisuPolozili) {
		super();
		this.sifra = sifra;
		this.naziv = naziv;
		this.semestar = semestar;
		this.godina = godina;
		this.profesor = profesor;
		this.espb = espb;
		this.polozili = polozili;
		this.nisuPolozili = nisuPolozili;
	}

	public String getSifra() {
		return sifra;
	}

	public void setSifra(String sifra) {
		this.sifra = sifra;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public Semestar getSemestar() {
		return semestar;
	}

	public void setSemestar(Semestar semestar) {
		this.semestar = semestar;
	}

	public int getGodina() {
		return godina;
	}

	public void setGodina(int godina) {
		this.godina = godina;
	}

	public Profesor getProfesor() {
		return profesor;
	}

	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}

	public int getEspb() {
		return espb;
	}

	public void setEspb(int espb) {
		this.espb = espb;
	}

	public List<Student> getPolozili() {
		return polozili;
	}

	public void setPolozili(List<Student> polozili) {
		this.polozili = polozili;
	}

	public List<Student> getNisuPolozili() {
		return nisuPolozili;
	}

	public void setNisuPolozili(List<Student> nisuPolozili) {
		this.nisuPolozili = nisuPolozili;
	}
	
	public String getSemestarAsString() {
		switch(semestar)
		{
		case Letnji : return "Letnji";
		case Zimski : return "Zimski";
		default : return "";
		}
	}
	
	public String getGodinaAsString() {
		switch(godina) {

		case 1: return "I (prva)";
		case 2: return "II (druga)";
		case 3: return "III (treća)";
		case 4: return "IV (četvrta)";
		case 5: return "V (peta/master)";
		case 6: return "VI (šesta/master)";
		default: return "";
		}
	}
	
	public String toString() {
		
		return sifra + " - " + naziv;
		
	}
}
