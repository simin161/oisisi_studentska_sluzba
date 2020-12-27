package controller.student;

import model.Student;
import model.baze.StudentBaza;
import view.tabbedPanes.PrikazStudenta;

public class StudentController {
	
private static StudentController instance = null;
	
	public static StudentController getInstance() {
		
		if(instance == null) {
			
			instance = new StudentController();
			
		}
		
		return instance;
		
	}

	private Student student;
	
	public StudentController() {}

	public StudentController(Student student) {

		this.student = student;

	}

	public void dodajStudenta() {
		
		StudentBaza.getInstance().dodajStudenta(student.getPrezime(), student.getIme(), student.getDatumRodjenja(), student.getAdresaStanovanja(), student.getTelefon(),
				student.getEmail(), student.getBrIndeksa(), student.getGodinaUpisa(), student.getTrenutnaGodina(),student.getStatus(), student.getProsecnaOcena(),
				student.getPolozeno(),student.getNepolozeno());
		
		PrikazStudenta.getInstance().update("DODAT", -1);
		
	}
	
public void izbrisiStudenta(int row) {
		
		if(row < 0) {
			
			return;
			
		}
		
		Student student = StudentBaza.getInstance().getRow(row);
		StudentBaza.getInstance().izbrisiStudenta(student.getBrIndeksa());
		PrikazStudenta.getInstance().update("UKLONJEN", row);
		
	}
}
