package controller.predmet;

import model.Predmet;
import model.baze.PredmetBaza;
import view.tabbedPanes.PrikazPredmeta;


public class PredmetController {
	
	private Predmet predmet;

	private static PredmetController instance = null;
	
	
	public static PredmetController getInstance() {
		
		if(instance == null) {
			
			instance = new PredmetController();
		}
		
		return instance;
		
	}
	
	public PredmetController() {
		
		
	}
	
	public PredmetController(Predmet predmet) {

		this.predmet = predmet;

	}

	public void dodajPredmet() {
		
		PredmetBaza.getInstance().dodajPredmet(predmet.getSifra(),predmet.getNaziv(), predmet.getSemestar(), predmet.getGodina(), predmet.getProfesor(), predmet.getEspb(),
				predmet.getPolozili(), predmet.getNisuPolozili());
		
		PrikazPredmeta.getInstance().updatePrikaz("DODAT", -1);
		
	}
	
	public void izbrisiPredmet(int rowSelectedIndex) {
		
		if(rowSelectedIndex < 0) {
			
			return;
			
		}
		
		Predmet predmet = PredmetBaza.getInstance().getRow(rowSelectedIndex);
		PredmetBaza.getInstance().izbrisiPredmet(predmet.getSifra());
		PrikazPredmeta.getInstance().updatePrikaz("UKLONJEN", rowSelectedIndex);
		
	}
	
}