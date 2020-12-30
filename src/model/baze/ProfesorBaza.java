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
		Date date = null;
		try {
			date = new SimpleDateFormat("dd/MM/yyyy").parse("10/12/1985");
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		//test primer, kasnije ce biti implementirano zapravo iz datoteke
		List<Predmet> predmeti1 = new ArrayList<Predmet>();
		Predmet p = new Predmet("E211", "Algebra", Semestar.Letnji,1, null, 5, null, null);
		Predmet p1 = new Predmet("E212", "Verovatnoca i slucajni procesi", Semestar.Letnji,2, null, 5, null, null);
		
		predmeti1.add(p);
		predmeti1.add(p1);
		predmeti1.add(p);
		predmeti1.add(p1);
		
		List<Predmet> predmeti2 = new ArrayList<Predmet>();
		predmeti2.add(p1);
		predmeti2.add(p1);
		predmeti2.add(p);
		
		profesori.add(new Profesor("Petar", "Petrovic", date, "Negde daleko 10", "02144444", "jasmo@ja.com", "Negde blizu 10", "025161112", Titula.profesor_doktor, Zvanje.Docent, predmeti1));
		profesori.add(new Profesor("Petar", "Markovic", date, "Negde daleko 10", "02144444", "jasmo@ja.com", "Negde blizu 10", "025161231", Titula.Doktor, Zvanje.Docent, predmeti2));
		
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
				telefon, email, adresaK, brLicneKarte, titula, zvanje, null));
		
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
				
			}
			
		}
		
	}
	
	public void dodajPredmet(int selectedRow, Predmet p) {
		
		int i=0;
		
		for(Profesor pr : this.profesori) {
			
			
			if(pr.getPredmeti().get(i).getSifra().equals(p.getSifra()))
			{
				return;
			}
				
			i++;
		}
		
		this.profesori.get(selectedRow).getPredmeti().add(p);
		
	}
	
	public void ukloniPredmet(int selR, Predmet p) {
		int i = 0;
		
		for(Profesor pr : this.profesori) {
			if(pr.getPredmeti().get(i).getSifra().equals(p.getSifra())) {
				this.profesori.get(selR).getPredmeti().remove(p);
				break;
			}
		}
	}
	
}
