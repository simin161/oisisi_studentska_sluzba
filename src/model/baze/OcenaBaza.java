package model.baze;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import model.Ocena;
import model.Predmet;
import view.tabbedPanes.PrikazStudenta;

public class OcenaBaza {

	private List<Ocena> ocena;
	private List<Predmet> predmet;
	private List<String> header;

	int r;

	public OcenaBaza() {

		initializeOcena();
		this.r = PrikazStudenta.getInstance().getSelectedRow();

		this.header = new ArrayList<String>();
		this.header.add("Sifra predmeta");
		this.header.add("Naziv predmeta");
		this.header.add("ESPB");
		this.header.add("Ocena");
		this.header.add("Datum");
	}

	public void initializeOcena() {
		this.ocena = StudentBaza.getInstance().getRow(PrikazStudenta.getInstance().getTable().convertRowIndexToModel(r))
				.getPolozeno();
		this.predmet = StudentBaza.getInstance()
				.getRow(PrikazStudenta.getInstance().getTable().convertRowIndexToModel(r)).getNepolozeno();

	}

	public List<Ocena> getOcena() {

		return ocena;
	}

	public void setOcena(List<Ocena> ocena) {

		this.ocena = ocena;

	}

	public int getColumnCount() {

		return 5;

	}

	public String getColumnName(int index) {

		return this.header.get(index);

	}

	public Ocena getRow(int rowIndex) {
		return this.ocena.get(rowIndex);
	}

	public String getValueAt(int row, int column) {

		Ocena o = this.ocena.get(row);
		DateFormat date = new SimpleDateFormat("dd/MM/yyyy");
		String strDate = date.format(o.getDatumPolaganja());

		switch (column) {

		case 0:
			return o.getPredmet().getSifra();
		case 1:
			return o.getPredmet().getNaziv();
		case 2:
			return Integer.toString(o.getPredmet().getEspb());
		case 3:
			return Integer.toString(o.getOcena());
		case 4:
			return strDate;
		default:
			return null;

		}

	}

	public double izracunajProsek() {

		double avg;
		if (ocena == null || ocena.size() == 0) {
			avg = 0;
		} else {

			int i = 0;
			double sum = 0;
			for (Ocena o : ocena) {
				sum += o.getOcena();
				++i;
			}
			avg = sum / (double) i;
		}

		StudentBaza.getInstance().getRow((PrikazStudenta.getInstance().getTable()
				.convertRowIndexToModel(r))).setProsecnaOcena(avg);
		return avg;

	}

	public int izracunajEspb() {
		if (ocena == null || ocena.size() == 0) {
			return 0;
		}

		int sum = 0;
		for (Ocena o : ocena) {
			sum += o.getPredmet().getEspb();
		}

		return sum;
	}

	public void ponistiOcenu(String id) {
		if (predmet == null) {
			predmet = new ArrayList<Predmet>();
		}
		for (Ocena o : ocena) {
			if (o.getPredmet().getSifra().equals(id)) {
				predmet.add(o.getPredmet());
				ocena.remove(o);
				break;
			}
		}

		StudentBaza.getInstance().getRow((PrikazStudenta.getInstance().getTable().convertRowIndexToModel(r)))
				.setPolozeno(ocena);
		StudentBaza.getInstance().getRow((PrikazStudenta.getInstance().getTable().convertRowIndexToModel(r)))
				.setNepolozeno(predmet);

	}
}
