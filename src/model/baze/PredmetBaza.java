package model.baze;

import java.util.ArrayList;
import java.util.List;

import model.Predmet;
import model.Profesor;
import model.Student;
import model.nabrojiviTipovi.Semestar;

public class PredmetBaza {

	private static PredmetBaza instance = null;

	public static PredmetBaza getInstance() {

		if (instance == null) {

			instance = new PredmetBaza();

		}

		return instance;

	}

	private List<Predmet> predmeti;
	private List<String> zaglavlje;

	private PredmetBaza() {

		initPredmete();

		this.zaglavlje = new ArrayList<String>();
		this.zaglavlje.add("Šifra predmeta");
		this.zaglavlje.add("Naziv predmeta");
		this.zaglavlje.add("Broj ESPB bodova");
		this.zaglavlje.add("Godina na kojoj se predmet izvodi");
		this.zaglavlje.add("Semestar u kome se predmet izvodi");

	}

	private void initPredmete() {

		this.predmeti = new ArrayList<Predmet>();
		
	}

	public List<Predmet> getPredmete() {

		return predmeti;
	}

	public void setPredmete(List<Predmet> predmeti) {

		this.predmeti = predmeti;

	}

	public int getColumnCount() {

		return 5;
	}

	public String getColumnName(int index) {

		return this.zaglavlje.get(index);

	}

	public Predmet getRow(int rowIndex) {

		return this.predmeti.get(rowIndex);

	}

	public Object getValueAt(int row, int column) {

		Predmet predmet = this.predmeti.get(row);
		switch (column) {

		case 0:
			return predmet.getSifra();
		case 1:
			return predmet.getNaziv();
		case 2:
			return predmet.getEspb();
		case 3: {

			switch (predmet.getGodina()) {

			case 1:
				return "I (prva godina)";
			case 2:
				return "II (druga godina)";
			case 3:
				return "III (treća godina)";
			case 4:
				return "IV (četvrta godina)";
			default:
				return "";
			}

		}
		case 4:
			return predmet.getSemestar().toString();
		default:
			return null;

		}

	}

	public void dodajPredmet(String sifra, String naziv, Semestar semestar, int godina, Profesor profesor, int espb,
			List<Student> polozili, List<Student> nisuPolozili) {

		this.predmeti.add(new Predmet(sifra, naziv, semestar, godina, profesor, espb, polozili, nisuPolozili));

	}

	public void izbrisiPredmet(String sifra) {

		for (Predmet p : predmeti) {

			if (p.getSifra().equals(sifra)) {
				predmeti.remove(p);
				break;
			}

		}

	}

	public void izmeniPredmet(String sifra, String naziv, Semestar semestar, int godina, Profesor profesor, int espb,
			String oldId) {
		for (Predmet p : predmeti) {
			if (oldId.equals(p.getSifra())) {
				p.setSifra(sifra);
				p.setNaziv(naziv);
				p.setSemestar(semestar);
				p.setGodina(godina);
				p.setProfesor(profesor);
				p.setEspb(espb);
			}
		}
	}

	public void izbrisiProfesoraSaPredmeta(Profesor p) {
		for (Predmet predmet : predmeti) {
			if (predmet.getProfesor() == null) {
				continue;
			} else {
				if (predmet.getProfesor().getBrLicneKarte().equals(p.getBrLicneKarte())) {
					predmet.setProfesor(null);
				}
			}
		}
	}

	public void izbrisiStudentaIzPolozenih(Student s) {

		for (Predmet p : predmeti) {
			List<Student> polozili = p.getPolozili();

			if (polozili == null || polozili.isEmpty()) {
				continue;
			} else {

				for (Student sPol : polozili) {
					if (sPol.getBrIndeksa().equals(s.getBrIndeksa())) {
						polozili.remove(sPol);
						break;
					}
				}

				p.setPolozili(polozili);
			}
		}

	}

	public void izbrisiStudentaIzNepolozenih(Student s) {

		for (Predmet p : predmeti) {
			List<Student> popadali = p.getNisuPolozili();

			if (popadali == null || popadali.isEmpty()) {
				continue;
			} else {
				for (Student sPop : popadali) {
					if (sPop.getBrIndeksa().equals(s.getBrIndeksa())) {
						popadali.remove(sPop);
						break;
					}
				}
				p.setNisuPolozili(popadali);
			}
		}
	}
	
	public void izmeniStudenta(Student s, String oldId) {
		
		for(Predmet p : predmeti) {
			
			for(Student s1 : p.getNisuPolozili()) {
				if(s1.getBrIndeksa().equals(oldId)) {
					s1.setIme(s.getIme());
					s1.setPrezime(s.getPrezime());
					s1.setAdresaStanovanja(s.getAdresaStanovanja());
					s1.setDatumRodjenja(s.getDatumRodjenja());
					s1.setEmail(s.getEmail());
					s1.setGodinaUpisa(s.getGodinaUpisa());
					s1.setStatus(s.getStatus());
					s1.setTelefon(s.getTelefon());
					s1.setTrenutnaGodina(s.getTrenutnaGodina());
					s1.setBrIndeksa(s.getBrIndeksa());
					s1.setPolozeno(s.getPolozeno());
					s1.setNepolozeno(s.getNepolozeno());
					break;
				}
			}
			
			for(Student s1 : p.getPolozili()) {
				if(s1.getBrIndeksa().equals(oldId)) {
					s1.setIme(s.getIme());
					s1.setPrezime(s.getPrezime());
					s1.setAdresaStanovanja(s.getAdresaStanovanja());
					s1.setDatumRodjenja(s.getDatumRodjenja());
					s1.setEmail(s.getEmail());
					s1.setGodinaUpisa(s.getGodinaUpisa());
					s1.setStatus(s.getStatus());
					s1.setTelefon(s.getTelefon());
					s1.setTrenutnaGodina(s.getTrenutnaGodina());
					s1.setBrIndeksa(s.getBrIndeksa());
					s1.setPolozeno(s.getPolozeno());
					s1.setNepolozeno(s.getNepolozeno());
					break;
				}
			}
		}
	}

}
