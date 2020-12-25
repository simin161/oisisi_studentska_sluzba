package controller.provere;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProveraEmaila {

	public static Boolean proveriEmail(String email){
		
		Boolean provera = false;
		String mailReg = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
		
		Pattern pattern = Pattern.compile(mailReg, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(email);
		provera= matcher.find();
		
		return provera;
		
	}
	
}
