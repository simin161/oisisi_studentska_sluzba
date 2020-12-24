package controller.profesor;

import model.Profesor;
import model.baze.ProfesorBaza;
import view.tabbedPanes.PrikazProfesora;

public class ProfesoriController {
	
	private Profesor profesor;
	
	
	public ProfesoriController(Profesor profesor) {
		
		this.profesor = profesor;
		
	}
	
	public void dodajProfesora() {
		
		ProfesorBaza.getInstance().dodajProfesora(profesor.getIme(), profesor.getPrezime(), profesor.getDatumRodjenja(),
				profesor.getAdresaStanovanja(), profesor.getTelefon(), profesor.getEmail(), profesor.getAdresaKancelarije(), 
				profesor.getBrLicneKarte(), profesor.getTitula(), profesor.getZvanje());
		
		PrikazProfesora.getInstance().updatePrikaz("DODAT", -1);
		
	}
	
}
