package model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import model.nabrojiviTipovi.Status;

public class Student implements Serializable{

	
	private static final long serialVersionUID = 1518027390654848009L;
	
	private String prezime;
	private String ime;
	private Date datumRodjenja;
	private String adresaStanovanja;
	private String telefon;
	private String email;
	private String brIndeksa;
	private int godinaUpisa;
	private int trenutnaGodina;
	private Status status;
	private double prosecnaOcena;
	private List<Ocena> polozeno;
	private List<Predmet> nepolozeno;

	public Student(String prezime, String ime, Date datumRodjenja, String adresaStanovanja, String telefon,
			String email, String brIndeksa, int godinaUpisa, int trenutnaGodina, Status status, double prosecnaOcena,
			List<Ocena> polozeno, List<Predmet> nepolozeno) {
		super();
		this.prezime = prezime;
		this.ime = ime;
		this.datumRodjenja = datumRodjenja;
		this.adresaStanovanja = adresaStanovanja;
		this.telefon = telefon;
		this.email = email;
		this.brIndeksa = brIndeksa;
		this.godinaUpisa = godinaUpisa;
		this.trenutnaGodina = trenutnaGodina;
		this.status = status;
		this.prosecnaOcena = prosecnaOcena;
		this.polozeno = polozeno;
		this.nepolozeno = nepolozeno;
	}
	
	public Student() {}

	public String getPrezime() {
		return prezime;
	}

	public String getIme() {
		return ime;
	}

	public Date getDatumRodjenja() {
		return datumRodjenja;
	}

	public String getAdresaStanovanja() {
		return adresaStanovanja;
	}

	public String getTelefon() {
		return telefon;
	}

	public String getEmail() {
		return email;
	}

	public String getBrIndeksa() {
		return brIndeksa;
	}

	public int getGodinaUpisa() {
		return godinaUpisa;
	}

	public int getTrenutnaGodina() {
		return trenutnaGodina;
	}
	
	public String getTrenutnaGodinaAsString() {
		switch(trenutnaGodina) {
		case 1: return "I (prva)";
		case 2: return "II (druga)";
		case 3: return "III (treća)";
		case 4: return "IV (četvrta)";
		default: return "";
		}
	}
	public Status getStatus() {
		return status;
	}
	
	public String getStatusAsString() {
		switch(status)
		{
		case B : return "Budžet";
		case S : return "Samofinansiranje";
		default : return "";
		}
	}

	public double getProsecnaOcena() {
		return prosecnaOcena;
	}

	public List<Ocena> getPolozeno() {
		return polozeno;
	}
	
	public List<Predmet> getNepolozeno(){
		return nepolozeno;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public void setDatumRodjenja(Date datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}

	public void setAdresaStanovanja(String adresaStanovanja) {
		this.adresaStanovanja = adresaStanovanja;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setBrIndeksa(String brIndeksa) {
		this.brIndeksa = brIndeksa;
	}

	public void setGodinaUpisa(int godinaUpisa) {
		this.godinaUpisa = godinaUpisa;
	}

	public void setTrenutnaGodina(int trenutnaGodina) {
		this.trenutnaGodina = trenutnaGodina;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public void setProsecnaOcena(double prosecnaOcena) {
		this.prosecnaOcena = prosecnaOcena;
	}

	public void setPolozeno(List<Ocena> polozeno) {
		this.polozeno = polozeno;
	}
	
	public void setNepolozeno(List<Predmet> nepolozeno) {
		this.nepolozeno = nepolozeno;
	}

}
