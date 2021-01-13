package controller.provere;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProveraNazivaPredmeta {

	public static boolean proveriNazivPredmeta(String text) {
		
		Pattern pattern = Pattern.compile("^[\\p{L} -]+(\\s[0-9])*", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(text);
		return matcher.matches();
		
	}
}
