package controller.serijalizacijaDeserijalizacija;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

import model.Predmet;
import model.Profesor;
import model.Student;
import model.baze.PredmetBaza;
import model.baze.ProfesorBaza;
import model.baze.StudentBaza;

public class Deserijalizacija {

	public static void deserijalizacija() {
		File f = new File("podaci" + File.separator + "studenti.dat");
		File f1 = new File("podaci" + File.separator + "profesori.dat");
		File f2 = new File("podaci" + File.separator + "predmeti.dat");

		ObjectInputStream ois = null;
		ObjectInputStream ois1 = null;
		ObjectInputStream ois2 = null;

		try {
			ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(f)));
			ois1 = new ObjectInputStream(new BufferedInputStream(new FileInputStream(f1)));
			ois2 = new ObjectInputStream(new BufferedInputStream(new FileInputStream(f2)));

			try {
				List<Student> read = (List<Student>) ois.readObject();
				List<Profesor> read1 = (List<Profesor>) ois1.readObject();
				List<Predmet> read2 = (List<Predmet>) ois2.readObject();

				StudentBaza.getInstance().setStudents(read);
				ProfesorBaza.getInstance().setProfesori(read1);
				PredmetBaza.getInstance().setPredmete(read2);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				ois.close();
				ois1.close();
				ois2.close();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
