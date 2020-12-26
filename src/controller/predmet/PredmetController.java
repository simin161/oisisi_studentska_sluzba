package controller.predmet;

import model.Predmet;
import model.baze.PredmetBaza;
import view.tabbedPanes.PrikazPredmeta;


public class PredmetController {
	
	private Predmet predmet;

	public PredmetController(Predmet predmet) {

		this.predmet = predmet;

	}

	public void dodajPredmet() {
		
		PredmetBaza.getInstance().dodajPredmet(predmet.getSifra(),predmet.getNaziv(), predmet.getSemestar(), predmet.getGodina(), predmet.getProfesor(), predmet.getEspb(),
				predmet.getPolozili(), predmet.getNisuPolozili());
		
		PrikazPredmeta.getInstance().updatePrikaz("DODAT", -1);
		
	}

}
