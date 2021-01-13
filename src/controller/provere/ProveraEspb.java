package controller.provere;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProveraEspb {

	public static boolean proveriEspb(String text) {
		Pattern pattern = Pattern.compile("[0-9]{1,2}", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(text);
		return matcher.matches();
	}
}
