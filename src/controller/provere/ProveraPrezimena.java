package controller.provere;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProveraPrezimena {

	public static Boolean proveriPrezime(String prezime) {
		
		Boolean provera= false;
		
		String prezimeReg = "^[\\p{L}-]+$";
		
		Pattern pattern = Pattern.compile(prezimeReg, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(prezime);
		provera= matcher.find();
		
		return provera;
		
	}
	
}
