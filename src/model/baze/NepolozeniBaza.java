package model.baze;

import java.util.ArrayList;
import java.util.List;

import model.Predmet;
import view.tabbedPanes.PrikazStudenta;

public class NepolozeniBaza {

	private List<Predmet> predmet;
	private List<String> header;

	int r;

	public NepolozeniBaza() {

		initNepolozene();
		this.r = PrikazStudenta.getInstance().getSelectedRow();

		this.header = new ArrayList<String>();
		this.header.add("Šifra predmeta");
		this.header.add("Naziv predmeta");
		this.header.add("ESPB");
		this.header.add("Godina studija");
		this.header.add("Semestar");

	}

	public void initNepolozene() {

		this.predmet = StudentBaza.getInstance()
				.getRow(PrikazStudenta.getInstance().getTable().convertRowIndexToModel(r)).getNepolozeno();

	}

	public List<Predmet> getPredmet() {

		return this.predmet;

	}

	public void setPredmet(List<Predmet> predmet) {

		this.predmet = predmet;

	}

	public int getColumnCount() {

		return 5;
	}

	public String getColumnName(int index) {

		return this.header.get(index);

	}

	public Predmet getRow(int rowIndex) {

		return this.predmet.get(rowIndex);

	}

	public String getValueAt(int row, int column) {

		Predmet p = this.predmet.get(row);

		switch (column) {

		case 0:
			return p.getSifra();
		case 1:
			return p.getNaziv();
		case 2:
			return String.valueOf(p.getEspb());
		case 3:
			return String.valueOf(p.getGodina());
		case 4:
			return String.valueOf(p.getSemestar());
		default:
			return null;

		}

	}

	// funkcije
	public void updateSBaza() {
		this.predmet = StudentBaza.getInstance()
				.getRow(PrikazStudenta.getInstance().getTable().convertRowIndexToModel(r)).getNepolozeno();
	}
	
	public void izbrisi(Predmet p) {
		
		for(Predmet p1 : predmet) {
			if(p1.getSifra().equals(p.getSifra())) {
				predmet.remove(p1);
				StudentBaza.getInstance()
				.getRow(PrikazStudenta.getInstance().getTable().convertRowIndexToModel(r)).setNepolozeno(predmet);			
				break;
			}
		}
		
		for(Predmet p1 : PredmetBaza.getInstance().getPredmete()) {
			if(p1.getSifra().equals(p.getSifra())){
				p1.getNisuPolozili().remove(p);
			}
		}
		
	}
}
