package controller.pretraga;

import view.tabbedPanes.PrikazPredmeta;
import view.tabbedPanes.PrikazProfesora;
import view.tabbedPanes.PrikazStudenta;

public class Pretraga {
	
	public static void pretrazi(String kriterijum, int tab) {
		
		if (tab == 0) {
			//student
			PrikazStudenta.getInstance().pretraziTabelu(kriterijum);			
			
		}
		else if (tab == 1) {
			//profesor
			
			PrikazProfesora.getInstance().pretraziTabelu(kriterijum);
			
		}
		else {
			//predmet
			
			PrikazPredmeta.getInstance().pretraziTabelu(kriterijum);
			
		}
		
	}
}
