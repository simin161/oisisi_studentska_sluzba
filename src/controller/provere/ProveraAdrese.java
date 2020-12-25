package controller.provere;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProveraAdrese {

	public static Boolean proveriAdresu(String adresa) {
		
		Boolean provera=false;
		String adresaReg1= "[\\p{L}\\s]+ \\d*{1,7},[\\p{L}\\s]+";
		String adresaReg2= "[\\p{L}\\s]+ \\d*{1,7},[ \\p{L}\\s]+";
		
		Pattern pattern = Pattern.compile(adresaReg1, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(adresa);
		
		provera = matcher.find();
		
		if(provera == false) {
			
			Pattern pattern2 = Pattern.compile(adresaReg2);
			matcher = pattern2.matcher(adresa);
			
			provera = matcher.find();
			
		}
		
		return provera;
		
	}
	
}
