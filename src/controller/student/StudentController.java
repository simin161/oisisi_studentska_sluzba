package controller.student;

import model.Student;
import model.baze.OcenaBaza;
import model.baze.PredmetBaza;
import model.baze.StudentBaza;
import view.tabbedPanes.PrikazStudenta;

public class StudentController {

	private static StudentController instance = null;

	public static StudentController getInstance() {

		if (instance == null) {

			instance = new StudentController();

		}

		return instance;

	}

	private Student student;

	public StudentController() {
	}

	public StudentController(Student student) {

		this.student = student;

	}

	public void dodajStudenta() {

		StudentBaza.getInstance().dodajStudenta(student.getPrezime(), student.getIme(), student.getDatumRodjenja(),
				student.getAdresaStanovanja(), student.getTelefon(), student.getEmail(), student.getBrIndeksa(),
				student.getGodinaUpisa(), student.getTrenutnaGodina(), student.getStatus(), student.getProsecnaOcena(),
				student.getPolozeno(), student.getNepolozeno());

		PrikazStudenta.getInstance().update("DODAT", -1);

	}

	public void izbrisiStudenta(int row) {

		if (row < 0) {

			return;

		}

		Student student = StudentBaza.getInstance().getRow(row);
		OcenaBaza o = new OcenaBaza();
		o.izbrisiOcenuZaStudenta();
		PredmetBaza.getInstance().izbrisiStudentaIzPolozenih(student);
		PredmetBaza.getInstance().izbrisiStudentaIzNepolozenih(student);
		StudentBaza.getInstance().izbrisiStudenta(student.getBrIndeksa());
		PrikazStudenta.getInstance().update("UKLONJEN", row);

	}

	public Student getStudent(int row) {
		return StudentBaza.getInstance().getRow(row);
	}

	public void izmeniStudenta(Student s, String oldId) {
		/*if (rowSelectedIndex < 0) {
			return;
		}*/
		
		//Student s = StudentBaza.getInstance().getRow(rowSelectedIndex);
		StudentBaza.getInstance().izmeniStudenta(s.getPrezime(), s.getIme(), s.getDatumRodjenja(), s.getAdresaStanovanja(), s.getTelefon(),
			s.getEmail(), s.getBrIndeksa(), s.getGodinaUpisa(), s.getTrenutnaGodina(), s.getStatus(), oldId, s.getPolozeno(), s.getNepolozeno());
		
		PrikazStudenta.getInstance().update(null, -1);
	}
}
