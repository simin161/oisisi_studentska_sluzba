//inspiracija -> vezbe 06, JTableMVCSimple & JTableMVCAdvanced

package model.baze;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Predmet;
import model.Profesor;
import model.nabrojiviTipovi.Semestar;
import model.nabrojiviTipovi.Titula;
import model.nabrojiviTipovi.Zvanje;

public class ProfesorBaza {

	private static ProfesorBaza instance = null;
	
	public static ProfesorBaza getInstance() {
		
		if(instance == null){
			
			instance = new ProfesorBaza();
		
		}
		
		return instance;
		
	}
	
	private List<Profesor> profesori;
	private List<String> zaglavlje;
	
	private ProfesorBaza() {
		
		initProfesore();
		
		this.zaglavlje= new ArrayList<String>();
		this.zaglavlje.add("Ime");
		this.zaglavlje.add("Prezime");
		this.zaglavlje.add("Titula");
		this.zaglavlje.add("Zvanje");
		
	}
	
	private void initProfesore() {
		
		this.profesori= new ArrayList<Profesor>();
		
	}
	
	public List<Profesor> getProfesore(){
		
		return profesori;
	}
	
	public void setProfesori(List<Profesor> profesori) {
		
		this.profesori= profesori;
		
	}
	
	public int getColumnCount() {
		
		return 4;
		
	}
	
	public String getColumnName(int index) {
		
		return this.zaglavlje.get(index);
		
	}
	
	public Profesor getRow(int rowIndex) {
		
		return this.profesori.get(rowIndex);
	
	}
	
	public String getValueAt(int row, int column) {
		
		Profesor profesor = this.profesori.get(row);
		switch(column) {
			
			case 0: return profesor.getIme();
			case 1: return profesor.getPrezime();
			case 2: {
				
				switch(profesor.getTitula()) {
				
					case profesor_doktor : return "Profesor doktor";
					default: return profesor.getTitula().toString();
					
				}	
			}
			case 3: {
				
				switch(profesor.getZvanje()) {
				
					case Saradnik_u_nastavi : return "Saradnik u nastavi";
					case Asistent_sa_doktoratom : return "Asistent sa doktoratom";
					case Vanredni_profesor : return "Vanredni profesor";
					case Redovni_profesor : return "Redovni profesor";
					case Profesor_emeritus : return "Profesor emeritus";
					default: return profesor.getZvanje().toString();
				
				}
				
			}
			default: return null;
		
		}
		
	}
	
	public void dodajProfesora(String ime, String prezime, Date datum, 
			String adresaS, String telefon, String email, 
			String adresaK, String brLicneKarte, Titula titula,
			Zvanje zvanje) {
		
		if(this.profesori == null) {
			
			this.profesori = new ArrayList<Profesor>();
			
		}
		
		this.profesori.add(new Profesor(ime, prezime, datum, adresaS,
				telefon, email, adresaK, brLicneKarte, titula, zvanje, new ArrayList<Predmet>()));
		
	}
	
	public void izbrisiProfesora(String id) {
		for(Profesor p : profesori) {
			if(id.equals(p.getBrLicneKarte())){
				profesori.remove(p);
				break;
			}
		}
	}
	
	
	public void izmeniProfesora(Profesor p, String staraLicna) {
		
		for(Profesor pr : profesori) {
			
			if(pr.getBrLicneKarte().equals(staraLicna)) {
				
				pr.setIme(p.getIme());
				pr.setPrezime(p.getPrezime());
				pr.setDatumRodjenja(p.getDatumRodjenja());
				pr.setAdresaStanovanja(p.getAdresaStanovanja());
				pr.setTelefon(p.getTelefon());
				pr.setEmail(p.getEmail());
				pr.setAdresaKancelarije(p.getAdresaKancelarije());
				pr.setBrLicneKarte(p.getBrLicneKarte());
				pr.setTitula(p.getTitula());
				pr.setZvanje(p.getZvanje());
				pr.setPredmeti(p.getPredmeti());
				
			}
			
		}
		
	}
	
	public void ukloniPredmet(Predmet p) {
	
		for(Profesor pr : this.profesori) {
			
			for(Predmet prr : pr.getPredmeti()) {
				
				if(prr.getSifra().equals(p.getSifra())) {
					
					if(p.getProfesor().getBrLicneKarte().equals(pr.getBrLicneKarte())) {
						
						pr.getPredmeti().remove(p);
						break;
						
					}
				}
			}
		}
	}
	
	public List<Predmet> predmetKojiNepredaje(Profesor p){
		
		List<Predmet> sviPredmeti = PredmetBaza.getInstance().getPredmete();
		List<Predmet> nepredaje = new ArrayList<Predmet>();
		
		if(sviPredmeti == null) {
			
			return null;
			
		}
		else if(sviPredmeti != null) {
			
			for(Predmet pr : sviPredmeti) {
				
				if(pr.getProfesor()==null) {
					
					nepredaje.add(pr);
					
				}
				
			}
			
		}
		
		return nepredaje;
		
	}
	
}
