package controller.provere;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.Predmet;

public class ProveraSifrePredmeta {

	public static boolean proveriSifruPredmeta(String text) {
		Pattern pattern = Pattern.compile("[\\p{L}]+[0-9]+", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(text);
		return matcher.matches();
	}
	
	public static boolean checkExists(String text, List<Predmet> predmeti) {
		boolean retVal = false;
		
		for(Predmet p : predmeti) {
			if(text.equals(p.getSifra())) {
				retVal = true;
				break;
			}
		}
		
		return retVal;
	}
}
