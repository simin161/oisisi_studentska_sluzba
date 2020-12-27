package controller.profesor;

import model.Profesor;
import model.baze.ProfesorBaza;
import view.tabbedPanes.PrikazProfesora;

public class ProfesoriController {

	private Profesor profesor;
	
	private static ProfesoriController instance = null;
	
	public static ProfesoriController getInstance() {
		if(instance == null) {
			instance = new ProfesoriController();
		}
		
		return instance;
	}
	
	public ProfesoriController() {}

	public ProfesoriController(Profesor profesor) {

		this.profesor = profesor;

	}

	public void dodajProfesora() {

		ProfesorBaza.getInstance().dodajProfesora(profesor.getIme(), profesor.getPrezime(), profesor.getDatumRodjenja(),
				profesor.getAdresaStanovanja(), profesor.getTelefon(), profesor.getEmail(),
				profesor.getAdresaKancelarije(), profesor.getBrLicneKarte(), profesor.getTitula(),
				profesor.getZvanje());

		PrikazProfesora.getInstance().updatePrikaz("DODAT", -1);

	}

	public void izbrisiProfesora(int row) {

		if (row < 0) {

			return;

		}

		Profesor prof = ProfesorBaza.getInstance().getRow(row);
		ProfesorBaza.getInstance().izbrisiProfesora(prof.getBrLicneKarte());
		PrikazProfesora.getInstance().updatePrikaz("UKLONJEN", row);

	}

public void izmeniProfesora(int rowSelectedIndex, Profesor prof) {
		
		if(rowSelectedIndex < 0) {
			
			return;
			
		}
		
		Profesor profesor = ProfesorBaza.getInstance().getRow(rowSelectedIndex);
		ProfesorBaza.getInstance().izmeniProfesora(prof);
		
		PrikazProfesora.getInstance().updatePrikaz(null, -1);
		
	}
	
}
