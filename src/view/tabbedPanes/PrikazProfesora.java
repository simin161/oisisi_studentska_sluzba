//inspiracija -> vezbe 06, JTableMVCSimple & JTableMVCAdvanced

package view.tabbedPanes;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import view.tables.AbstractTableModelProfesori;
import view.tables.ProfesorTable;

public class PrikazProfesora extends JPanel{

	private static final long serialVersionUID = -6834900871741252078L;
	
	private static PrikazProfesora instance = null;
	
	public static PrikazProfesora getInstance() {
		
		if(instance == null) {
			
			instance = new PrikazProfesora();
			
		}
		
		return instance;
		
	}
	
	private JTable tabelaProfesora;
	
	public PrikazProfesora() {
		
		setLayout(new BorderLayout());
		prikaziTabelu();
		
	}
	
	public void updatePrikaz(String akcija, int vrednost) {
		
		AbstractTableModelProfesori model = (AbstractTableModelProfesori) tabelaProfesora.getModel();
		
		model.fireTableDataChanged();
		validate();
		
	}
	
	private void prikaziTabelu() {
		
		tabelaProfesora = new ProfesorTable();
		JScrollPane sPane = new JScrollPane(tabelaProfesora);
		
		add(sPane, BorderLayout.CENTER);	
		
		this.updatePrikaz(null, -1);
		
	}
	
	public int getSelectedRow() {
		
		
		return tabelaProfesora.getSelectedRow();
	}
	
}
