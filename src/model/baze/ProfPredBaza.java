package model.baze;

import java.util.ArrayList;
import java.util.List;

import model.Predmet;
import model.Profesor;
import view.tabbedPanes.PrikazProfesora;

public class ProfPredBaza {

	private List<Predmet> predmeti;
	private Profesor profesor;
	private List<String> header;

	int r;

	public ProfPredBaza() {

		initializeProfPredBaza();

		this.r = PrikazProfesora.getInstance().getSelectedRow();

		this.header = new ArrayList<String>();
		this.header.add("Šifra");
		this.header.add("Naziv");
		this.header.add("Godina studija");
		this.header.add("Semestar");

	}

	public void initializeProfPredBaza() {

		this.predmeti = ProfesorBaza.getInstance().getRow(PrikazProfesora.getInstance().getTable()
				.convertRowIndexToModel(r)).getPredmeti();
		this.profesor = ProfesorBaza.getInstance().getRow(PrikazProfesora.getInstance().getTable()
				.convertRowIndexToModel(r));

	}

	public List<Predmet> getPredmete() {

		return this.predmeti;

	}

	public void setPredmete(List<Predmet> predmeti) {

		this.predmeti = predmeti;
	}

	public int getColumnCount() {

		return 4;

	}

	public String getColumnName(int index) {

		return this.header.get(index);

	}

	public Predmet getRow(int rowIndex) {

		return this.predmeti.get(rowIndex);

	}

	public String getValueAt(int row, int column) {

		Predmet p = this.predmeti.get(row);

		switch (column) {

		case 0:
			return p.getSifra();
		case 1:
			return p.getNaziv();
		case 2:
			return String.valueOf(p.getGodina());
		case 3:
			return String.valueOf(p.getSemestar());
		default:
			return null;
		}

	}

	public void dodajPredmet(int selectedRow, Predmet p) {

		ProfesorBaza.getInstance().dodajPredmet(selectedRow, p);

	}

	public void ukloniPredmet(int r, Predmet p) {
		for (Predmet p1 : predmeti) {
			if (p1.getSifra().equals(p.getSifra())) {
				profesor.getPredmeti().remove(p1);
				predmeti.remove(p1);
				ProfesorBaza.getInstance().izmeniProfesora(profesor, profesor.getBrLicneKarte());
				break;
			}
		}

	}
	
	public void izbrisi() {
		predmeti = null;
		profesor = null;
	}
}
