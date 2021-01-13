package controller.provere;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.Profesor;


public class ProveraLk {

	public static Boolean proveriBrLk(String lk) {
		
		Boolean provera = false;
		String lkReg= "^\\d{9}$";
		
		Pattern pattern = Pattern.compile(lkReg, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(lk);
		provera= matcher.find();
	
		return provera;
		
	}
	
	public static Boolean postojiLk(List<Profesor> profesori, String brLk) {
		
		Boolean postoji = false;
		
		for(Profesor p : profesori) {
			
			if(p.getBrLicneKarte().equals(brLk)) {
				
				postoji = true;
				break;
				
			}
			
		}
		
		return postoji;
	}
	
}
