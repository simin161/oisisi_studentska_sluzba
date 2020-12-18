package model;

public class Ocena {
	private Student student;
	//fali predmet
	private int	ocena;
	private String datumPolaganja;
	
	public Ocena(Student student, int ocena, String datumPolaganja) {
		super();
		this.student = student;
		this.ocena = ocena;
		this.datumPolaganja = datumPolaganja;
	}

	public Student getStudent() {
		return student;
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

	public void setOcena(int ocena) {
		this.ocena = ocena;
	}

	public void setDatumPolaganja(String datumPolaganja) {
		this.datumPolaganja = datumPolaganja;
	}
	
	

}
