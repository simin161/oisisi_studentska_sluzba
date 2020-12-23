package model;

public class Ocena {
	private Student student;
	private Predmet predmet;
	private int ocena;
	private String datumPolaganja;

	public Ocena(Student student, Predmet predmet, int ocena, String datumPolaganja) {
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

	public String getDatumPolaganja() {
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

	public void setDatumPolaganja(String datumPolaganja) {
		this.datumPolaganja = datumPolaganja;
	}

}
