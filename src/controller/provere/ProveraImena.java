package controller.provere;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProveraImena {

	public static Boolean proveriIme(String ime) {
		
		Boolean provera= false;
		
		String imeReg = "^[\\p{L} .'-]+$";
		
		Pattern pattern = Pattern.compile(imeReg, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(ime);
		provera= matcher.find();
		
		return provera;
		
	}
	
}
