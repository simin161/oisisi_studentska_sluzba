package view.tabbedPanes;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import view.tables.AbstractTableModelProfPred;
import view.tables.ProfPredTable;

public class PrikazProfPredmeta extends JPanel{
	
	private static final long serialVersionUID = -1880794585806883441L;

	private JTable tabelaPredmeti;
	
	public PrikazProfPredmeta() {
		
		setLayout(new BorderLayout());
		prikaziTabelu();
		
	}
	
	public void azuriraj(String akcija, int r) {
		
		AbstractTableModelProfPred model = (AbstractTableModelProfPred) tabelaPredmeti.getModel();
		
		model.fireTableDataChanged();
		validate();
		
	}
	
	public AbstractTableModelProfPred getModel() {
		
		return (AbstractTableModelProfPred) tabelaPredmeti.getModel();
		
	}
	
	private void prikaziTabelu() {
		
		tabelaPredmeti = new ProfPredTable();
		JScrollPane sp = new JScrollPane(tabelaPredmeti);
		
		add(sp, BorderLayout.CENTER);
		
		azuriraj(null, -1);
		
	}
	
	public JTable getTable() {
		
		return tabelaPredmeti;
	}
	
	public int getSelectedRow() {
		return tabelaPredmeti.getSelectedRow();
	}
	
}
