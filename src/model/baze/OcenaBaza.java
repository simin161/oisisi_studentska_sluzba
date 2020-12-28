package model.baze;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import model.Ocena;
import view.tabbedPanes.PrikazStudenta;

public class OcenaBaza {
	

	private List<Ocena>  ocena;
	private List<String> header;

	public OcenaBaza() {

		initializeOcena();

		this.header = new ArrayList<String>();
		this.header.add("Sifra predmeta");
		this.header.add("Naziv predmeta");
		this.header.add("ESPB");
		this.header.add("Ocena");
		this.header.add("Datum");
	}

	private void initializeOcena() {
		this.ocena = StudentBaza.getInstance().getRow(PrikazStudenta.getInstance().getSelectedRow()).getPolozeno();

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

		if(ocena == null) {
			return 0;
		}
		
		int i = 0;
		double sum = 0;
		for(Ocena o : ocena) {
			sum += o.getOcena();
			++i;
		}
		
		return sum/(double)i;
	}
	
	public int izracunajEspb() {
		if(ocena == null) {
			return 0;
		}
		
		int sum = 0;
		for(Ocena o: ocena) {
			sum += o.getPredmet().getEspb();
		}
		
		return sum;
	}
}
