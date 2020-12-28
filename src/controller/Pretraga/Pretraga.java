package controller.Pretraga;

import view.tabbedPanes.PrikazProfesora;

public class Pretraga {
	
	public static void pretrazi(String kriterijum, int tab) {
		
		if (tab == 0) {
			//student
			
			
			
		}
		else if (tab == 1) {
			//profesor
			
			PrikazProfesora.getInstance().pretraziTabelu(kriterijum);
			
		}
		else {
			//predmet
			
			
			
		}
		
	}
}
