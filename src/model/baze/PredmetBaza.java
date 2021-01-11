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

		// test primer
		predmeti.add(new Predmet("E210", "Matematička analiza 1", Semestar.Zimski, 1, null, 9, null, null));
		predmeti.add(new Predmet("E211", "Matematička analiza 2", Semestar.Zimski, 2, null, 9, null, null));

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

	public String getValueAt(int row, int column) {

		Predmet predmet = this.predmeti.get(row);
		switch (column) {

		case 0:
			return predmet.getSifra();
		case 1:
			return predmet.getNaziv();
		case 2:
			return String.valueOf(predmet.getEspb());
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
			case 5:
				return "V (peta godina/master studije)";
			case 6:
				return "VI (šesta godina/master studije)";
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
	
	public void izbrisiStudentaIzPolozenih(Student s) {
		
		for(Predmet p : predmeti) {
			List<Student> polozili = p.getPolozili();
			
			if(polozili == null || polozili.isEmpty()) {
				continue;
			}else {
				
				for(Student sPol : polozili) {
					if(sPol.getBrIndeksa().equals(s.getBrIndeksa())) {
						polozili.remove(sPol);
						break;
					}
				}
				
				p.setPolozili(polozili);
			}	
		}
		
	}
	
	public void izbrisiStudentaIzNepolozenih(Student s) {
		
		for(Predmet p : predmeti) {
			List<Student> popadali = p.getNisuPolozili();
			
			if(popadali == null || popadali.isEmpty()) {
				continue;
			}else {
				for(Student sPop : popadali) {
					if(sPop.getBrIndeksa().equals(s.getBrIndeksa())) {
						popadali.remove(sPop);
						break;
					}
				}
				p.setNisuPolozili(popadali);
			}
		}
	}

}
