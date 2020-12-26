package controller.student;

import model.Student;
import model.baze.StudentBaza;
import view.tabbedPanes.PrikazStudenta;

public class StudentController {

	private Student student;

	public StudentController(Student student) {

		this.student = student;

	}

	public void dodajStudenta() {
		
		StudentBaza.getInstance().dodajStudenta(student.getPrezime(), student.getIme(), student.getDatumRodjenja(), student.getAdresaStanovanja(), student.getTelefon(),
				student.getEmail(), student.getBrIndeksa(), student.getGodinaUpisa(), student.getTrenutnaGodina(),student.getStatus(), student.getProsecnaOcena(),
				student.getPolozeno(),student.getNepolozeno());
		
		PrikazStudenta.getInstance().update();
		
	}
}
