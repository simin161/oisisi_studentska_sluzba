package controller.fileReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReader {
	
	public static String readFromFile(String filePath)
	{
		String data = "";
		
		try {
		      File about = new File(filePath);
		      Scanner myReader = new Scanner(about);
		      while (myReader.hasNextLine()) {
		        data += myReader.nextLine() + "\n";
		      }
		      myReader.close();
		    } catch (FileNotFoundException ex) {
		    	data = "Greska prilikom ucitavanja tekstualne datoteke!";
		      ex.printStackTrace();
		    }
		
		return data;
	}

}