//vezbe06
package model.baze;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Ocena;
import model.Predmet;
import model.Student;
import model.nabrojiviTipovi.Status;

public class StudentBaza {

	private static StudentBaza instance = null;

	public static StudentBaza getInstance() {

		if (instance == null) {
			instance = new StudentBaza();
		}

		return instance;
	}

	private List<Student> students;
	private List<String> header;

	private StudentBaza() {

		initializeStudents();

		this.header = new ArrayList<String>();
		this.header.add("Broj indeksa");
		this.header.add("Ime");
		this.header.add("Prezime");
		this.header.add("Godina studija");
		this.header.add("Status");
		this.header.add("Prosek");

	}

	private void initializeStudents() {
		this.students = new ArrayList<Student>();
		// test
		/*
		 * Date date = null; try { date = new
		 * SimpleDateFormat("dd/MM/yyyy").parse("10/12/1000"); } catch (ParseException
		 * e) {
		 * 
		 * e.printStackTrace(); } List<Ocena> oc = new ArrayList<Ocena>(); List<Ocena>
		 * oc1 = new ArrayList<Ocena>(); List<Predmet> p = new ArrayList<Predmet>();
		 * p.add(new Predmet("sifra11", "naziv11", null, 10, null, 9, null, null));
		 * Student s = new Student("Prezimic", "Imenko", date, "adresa stanovica 00",
		 * "123456789", "email@email.com", "XX12345", 1099, 2, Status.B, 8.91, null,
		 * null); Student s1 = new Student("Prezimic1", "Imenko1", date,
		 * "adresa stanovica 00, dfasdfa", "123/456-789", "email@email.com", "XX123",
		 * 1099, 3, Status.S, 8.91, null, null); oc.add(new Ocena(s, new
		 * Predmet("sifra", "naziv", null, 9, null, 7, null, null), 9, date));
		 * s.setPolozeno(oc); s.setNepolozeno(p); oc.add(new Ocena(s, new
		 * Predmet("sifra1", "naziv1", null, 10, null, 9, null, null), 8, date));
		 * oc1.add(new Ocena(s, new Predmet("sifra1", "naziv1", null, 7, null, 6, null,
		 * null), 6, date)); s1.setPolozeno(oc1); students.add(s); students.add(s1);
		 */
	}

	public List<Student> getStudents() {

		return students;
	}

	public void setStudents(List<Student> students) {

		this.students = students;

	}

	public int getColumnCount() {

		return 6;

	}

	public String getColumnName(int index) {

		return this.header.get(index);

	}

	public Student getRow(int rowIndex) {
		return this.students.get(rowIndex);
	}

	public String getValueAt(int row, int column) {

		Student student = this.students.get(row);
		switch (column) {

		case 0:
			return student.getBrIndeksa();
		case 1:
			return student.getIme();
		case 2:
			return student.getPrezime();
		case 3:
			return student.getTrenutnaGodinaAsString();
		case 4:
			return student.getStatusAsString();
		case 5:
			return Double.toString(student.getProsecnaOcena());
		default:
			return null;

		}

	}

	public void dodajStudenta(String prezime, String ime, Date datumRodjenja, String adresaStanovanja, String telefon,
			String email, String brIndeksa, int godinaUpisa, int trenutnaGodina, Status status, double prosecnaOcena,
			List<Ocena> polozeno, List<Predmet> nepolozeno) {

		this.students.add(new Student(prezime, ime, datumRodjenja, adresaStanovanja, telefon, email, brIndeksa,
				godinaUpisa, trenutnaGodina, status, prosecnaOcena, polozeno, nepolozeno));

	}

	public void izbrisiStudenta(String id) {
		for (Student s : students) {
			if (id.equals(s.getBrIndeksa())) {
				students.remove(s);
				break;
			}
		}
	}

	public void izmeniStudenta(String prezime, String ime, Date datumRodjenja, String adresaStanovanja, String telefon,
			String email, String brIndeksa, int godinaUpisa, int trenutnaGodina, Status status, String oldId) {
		for (Student s : students) {
			if (oldId.equals(s.getBrIndeksa())) {
				s.setIme(ime);
				s.setPrezime(prezime);
				s.setAdresaStanovanja(adresaStanovanja);
				s.setDatumRodjenja(datumRodjenja);
				s.setEmail(email);
				s.setGodinaUpisa(godinaUpisa);
				s.setStatus(status);
				s.setTelefon(telefon);
				s.setTrenutnaGodina(trenutnaGodina);
				s.setBrIndeksa(brIndeksa);
			}
		}
	}

	public List<Predmet> predmetiKojeNema(Student s) {
		List<Predmet> retVal = new ArrayList<Predmet>();
		List<Predmet> temp = new ArrayList<Predmet>();
		List<Predmet> polPr = null;
		List<Predmet> nep = s.getNepolozeno();
		List<Ocena> pol = s.getPolozeno();
		List<Predmet> pre = PredmetBaza.getInstance().getPredmete();
		
		for(Predmet p : pre) {
			System.out.println(p.getNisuPolozili().toString());
			System.out.println(p.getPolozili().toString());
		}
		
		/*for(Ocena p : pol) {
			System.out.println(p.getPredmet());
		}

		if (pol == null)
			pol = new ArrayList<Ocena>();

		if (nep == null)
			nep = new ArrayList<Predmet>();

		if (!pol.isEmpty()) {
			polPr = new ArrayList<Predmet>();
			System.out.println("PRVI IF POL NIJE EMPTY");
			for (Ocena o : pol) {
				polPr.add(o.getPredmet());
				System.out.println(o.getPredmet());
			}
		}

		if (nep.isEmpty() && !pol.isEmpty()) {
			System.out.println("NEP EMPTY" + pol.toString());

			for (Predmet o : pre) {

				if (!polPr.contains(o)) {
					retVal.add(o);
					temp.add(o);
				}

			}
		} else if (!nep.isEmpty() && pol.isEmpty()) {
			System.out.println("POL EMPTY:" + nep.toString());

			for (Predmet o : pre) {

				if (!nep.contains(o)) {
					retVal.add(o);
					temp.add(o);

				}

			}
		} else if (nep.isEmpty() && pol.isEmpty()) {
			System.out.println("OBA EMPTY" + pre.toString());

			for (Predmet o : pre) {
				retVal.add(o);
				temp.add(o);
			}

		} else {
			System.out.println("ELSE " + pol.toString());

			for (Predmet o : pre) {

				if (!nep.contains(o) && !polPr.contains(o)) {
					System.out.println(o);
					retVal.add(o);
					temp.add(o);

				}

			}
		}*/
		/*
		 * if (pol != null) { polPr = new ArrayList<Predmet>(); for (Ocena o : pol) {
		 * polPr.add(o.getPredmet()); } }
		 * 
		 * if (nep == null && pol != null) { for (Predmet o : pre) {
		 * 
		 * if (!polPr.contains(o)) { retVal.add(o); temp.add(o); }
		 * 
		 * } } else if (nep != null && pol == null) { for (Predmet o : pre) {
		 * 
		 * if (!nep.contains(o)) { retVal.add(o); temp.add(o);
		 * 
		 * }
		 * 
		 * } } else if (nep == null && pol == null) { for (Predmet o : pre) {
		 * retVal.add(o); temp.add(o); }
		 * 
		 * } else { for (Predmet o : pre) {
		 * 
		 * if (!nep.contains(o) && !polPr.contains(o)) { retVal.add(o); temp.add(o);
		 * 
		 * }
		 * 
		 * } }
		 */
		for (Predmet p : temp) {
			if (p.getGodina() > s.getTrenutnaGodina()) {
				retVal.remove(p);
			}
		}

		return pre;
	}

	public void izbrisiNepolozeniPredmet(Predmet p) {

		for (Student s : students) {

			if (s.getNepolozeno() == null) {
				continue;
			} else {
				for (Predmet pr : s.getNepolozeno()) {

					if (pr.getSifra().equals(p.getSifra())) {

						s.getNepolozeno().remove(p);
						break;
					}

				}

			}
		}

	}

}
