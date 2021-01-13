package controller.provere;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProveraTelefona {

	public static Boolean proveriTelefon(String telefon) {
		
		Boolean provera = false;
		String phoneNumb = "^(\\d{3}[- ]?){2}\\d{3}$";
		String phoneNumb2 = "^(\\d{3}[- ]?){2}\\d{4}$";
		
		Pattern pattern = Pattern.compile(phoneNumb);
		Matcher matcher = pattern.matcher(telefon);
		Pattern pattern2 = Pattern.compile(phoneNumb2);
		Matcher matcher2 = pattern2.matcher(telefon);
		provera= matcher.find();
		
		if(matcher.find()!= true && matcher2.find()== true)
			provera= true;
		
		return provera;
	}
	
}
