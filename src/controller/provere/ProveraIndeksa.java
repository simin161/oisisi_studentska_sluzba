package controller.provere;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.Student;


public class ProveraIndeksa {

	public static boolean checkExists(List<Student> students, String index) {
		boolean retVal = false;
		index.toUpperCase();
		
		for (Student s : students) {
			if (s.getBrIndeksa().equals(index)) {
				retVal = true;
				break;
			}
		}
		
		return retVal;
	}
	
	public static boolean proveriIndeks(String index) {
		Pattern pattern = Pattern.compile("([A-ZŠĐČĆŽ]+)?[0-9]+([/][0-9]+)?", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(index);
		return matcher.matches();
	}
}
