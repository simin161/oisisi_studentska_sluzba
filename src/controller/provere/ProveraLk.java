package controller.provere;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProveraLk {

	public static Boolean proveriBrLk(String lk) {
		
		Boolean provera = false;
		String lkReg= "^\\d{9}$";
		
		Pattern pattern = Pattern.compile(lkReg, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(lk);
		provera= matcher.find();
		
		return provera;
		
	}
	
}
