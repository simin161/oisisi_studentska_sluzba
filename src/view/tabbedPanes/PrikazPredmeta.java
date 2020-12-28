//inspiracija -> vezbe 06, JTableMVCSimple & JTableMVCAdvanced

package view.tabbedPanes;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import view.tables.AbstractTableModelPredmeti;
import view.tables.PredmetTable;


public class PrikazPredmeta extends JPanel{
	
	private static final long serialVersionUID = 4624960353287578931L;

	private static PrikazPredmeta instance = null;
	
	public static PrikazPredmeta getInstance() {
		
		if(instance == null) {
			
			instance = new PrikazPredmeta();
			
		}
		
		return instance;
		
	}
	
	private JTable tabelaPredmet;
	
	public JTable getTable() {
		
		return tabelaPredmet;
		
	}
	
	public PrikazPredmeta() {
		
		setLayout(new BorderLayout());
		prikaziTabelu();
		
	}
	
	public void updatePrikaz(String akcija, int vrednost) {
		
		AbstractTableModelPredmeti model = (AbstractTableModelPredmeti) tabelaPredmet.getModel();
		
		model.fireTableDataChanged();
		validate();
		
	}
	
	public void prikaziTabelu() {
		
		tabelaPredmet= new PredmetTable();
		
		JScrollPane sPane = new JScrollPane(tabelaPredmet);

		add(sPane, BorderLayout.CENTER);
		
		this.updatePrikaz(null, -1);
		
	}
	
	public int getSelectedRow() {
		
		return tabelaPredmet.getSelectedRow();
		
	}
	
	public void pretraziTabelu(String kriterijum) {
		
		if(kriterijum.trim().length()!= 0) {
			
			TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(tabelaPredmet.getModel());
			rowSorter.setRowFilter(RowFilter.regexFilter(kriterijum, 1));
			tabelaPredmet.setRowSorter(rowSorter);
			
		}
		else
		{
			tabelaPredmet.setRowSorter(null);
		}
	}
}
