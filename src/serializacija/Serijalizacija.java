package serializacija;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import model.baze.PredmetBaza;
import model.baze.ProfesorBaza;
import model.baze.StudentBaza;

public class Serijalizacija {

	public static void Serialization() throws FileNotFoundException, IOException, ClassNotFoundException{
		
		File file = new File("podaci" + File.separator + "studenti.dat");
		File file2= new File("podaci" + File.separator + "profesori.dat");
		File file3= new File("podaci" + File.separator + "predmeti.dat");
		
		ObjectOutputStream stream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
		ObjectOutputStream stream2 = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file2)));
		ObjectOutputStream stream3 = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file3)));
		
		try {
			
			stream.writeObject(StudentBaza.getInstance().getStudents());
			stream2.writeObject(ProfesorBaza.getInstance().getProfesore());
			stream3.writeObject(PredmetBaza.getInstance().getPredmete());
			
		}finally {
			
			stream.close();
			stream2.close();
			stream3.close();
			
		}
		
	}
	
	
	
}
