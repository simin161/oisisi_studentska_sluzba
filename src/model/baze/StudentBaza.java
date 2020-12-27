//vezbe06
package model.baze;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
		Date date = null;
		try {
			date = new SimpleDateFormat("dd/MM/yyyy").parse("10/12/1000");
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		students.add(new Student("Prezimic", "Imenko", date , "adresa stanovica 00", "123456789",
				"email@email.com", "XX12345", 1099, 2, Status.B, 8.91, null, null));
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
		for(Student s : students) {
			if(id.equals(s.getBrIndeksa())) {
				students.remove(s);
				break;
			}
		}
	}
	
	public void izmeniStudenta(String prezime, String ime, Date datumRodjenja, String adresaStanovanja, String telefon,
			String email, String brIndeksa, int godinaUpisa, int trenutnaGodina, Status status) {
			for (Student s : students) {
				if (brIndeksa.equals(s.getBrIndeksa())) {
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
}
