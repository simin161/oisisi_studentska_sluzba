package view.tabbedPanes;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import view.tables.AbstractTableModelNepolozeni;
import view.tables.NepolozeniTable;

public class PrikazNepolozeni extends JPanel {
	
	private static final long serialVersionUID = -2342146877025130202L;

	private JTable tabelaNep;
	
	public PrikazNepolozeni() {
		
		setLayout(new BorderLayout());
		prikaziTabelu();
		
	}
	
	public void update(String akcija, int r) {
		
		AbstractTableModelNepolozeni model = (AbstractTableModelNepolozeni)tabelaNep.getModel();
		model.getBaza().updateSBaza();
		model.fireTableDataChanged();
		validate();
		
	}
	
	public AbstractTableModelNepolozeni getModel() {
		
		return (AbstractTableModelNepolozeni) tabelaNep.getModel();
		
	}
	
	private void prikaziTabelu() {
		
		tabelaNep = new NepolozeniTable();
		JScrollPane sp = new JScrollPane(tabelaNep);
		
		add(sp, BorderLayout.CENTER);
		
		update(null, -1);
		
	}
	
	public JTable getTable() {
		
		return tabelaNep;
		
	}
	
	public int getSelectedRow() {
		
		return tabelaNep.getSelectedRow();
		
	}
	
}
