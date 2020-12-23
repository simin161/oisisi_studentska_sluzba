//inspiracija -> vezbe 06, JTableMVCSimple & JTableMVCAdvanced

package model.baze;

import java.util.ArrayList;
import java.util.List;

import model.Profesor;

public class ProfesorBaza {

	private static ProfesorBaza instance = null;
	
	public static ProfesorBaza getInstance() {
		
		if(instance == null)
		{
			instance = new ProfesorBaza();
		}
		
		return instance;
		
	}
	
	private List<Profesor> profesori;
	private List<String> zaglavlje;
	
	private ProfesorBaza() {
		
		initProfesore();
		
		this.zaglavlje= new ArrayList<String>();
		this.zaglavlje.add("Ime");
		this.zaglavlje.add("Prezime");
		this.zaglavlje.add("Titula");
		this.zaglavlje.add("Zvanje");
		
	}
	
	private void initProfesore() {
		
		this.profesori= new ArrayList<Profesor>();
		//test primer, kasnije ce biti implementirano zapravo iz datoteke
		profesori.add(new Profesor("Petar", "Petrovic", "10.10.1985.", "Negde daleko 10", "02144444", "jasmo@ja.com", "Negde blizu 10", "025161", "Doktor", "Docent", null));
		profesori.add(new Profesor("Petar", "Markovic", "10.10.1985.", "Negde daleko 10", "02144444", "jasmo@ja.com", "Negde blizu 10", "025161", "Doktor", "Docent", null));
		
	}
	
	public List<Profesor> getProfesore(){
		
		return profesori;
	}
	
	public void setProfesori(List<Profesor> profesori) {
		
		this.profesori= profesori;
		
	}
	
	public int getColumnCount() {
		
		return 4;
		
	}
	
	public String getColumnName(int index) {
		
		return this.zaglavlje.get(index);
		
	}
	
	public Profesor getRow(int rowIndex)
	{
		return this.profesori.get(rowIndex);
	}
	
	public String getValueAt(int row, int column) {
		
		Profesor profesor = this.profesori.get(row);
		switch(column) {
			
			case 0: return profesor.getIme();
			case 1: return profesor.getPrezime();
			case 2: return profesor.getTitula();
			case 3: return profesor.getZvanje();
			default: return null;
		
		}
		
	}
	
	
	
}
