package model;

import java.io.Serializable;
import java.util.Date;

public class Ocena implements Serializable{
	private Student student;
	private Predmet predmet;
	private int ocena;
	private Date datumPolaganja;

	public Ocena(Student student, Predmet predmet, int ocena, Date datumPolaganja) {
		super();
		this.student = student;
		this.predmet = predmet;
		this.ocena = ocena;
		this.datumPolaganja = datumPolaganja;
	}

	public Student getStudent() {
		return student;
	}

	public Predmet getPredmet() {
		return predmet;
	}

	public int getOcena() {
		return ocena;
	}

	public Date getDatumPolaganja() {
		return datumPolaganja;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public void setPredmet(Predmet predmet) {
		this.predmet = predmet;
	}

	public void setOcena(int ocena) {
		this.ocena = ocena;
	}

	public void setDatumPolaganja(Date datumPolaganja) {
		this.datumPolaganja = datumPolaganja;
	}

}
