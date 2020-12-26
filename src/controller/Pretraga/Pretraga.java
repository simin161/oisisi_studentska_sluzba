package controller.Pretraga;

public class Pretraga {

	private static Pretraga instance = null;
	
	public static Pretraga getInstance() {
		
		if(instance == null) {
			
			instance = new Pretraga();
			
		}
		
		return instance;
	}
	
	public void pretrazi(String kriterijum) {
		
		
		
	}
}
