package controller.provere;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProveraDatuma {

	public static Boolean proveriDatum(String datum) {
		
		Boolean provera = false;
		String datumReg = "[0-9]{2}[/][0-9]{2}[/][0-9]{4}";
		
		Pattern pattern = Pattern.compile(datumReg);
		Matcher matcher = pattern.matcher(datum);
		provera= matcher.find();
		
		return provera;
		
	}
	
}
