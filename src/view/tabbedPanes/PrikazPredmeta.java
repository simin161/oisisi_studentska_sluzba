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
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tabelaPredmet.getModel());
		
		tabelaPredmet.setRowSorter(sorter);
		
		List<RowSorter.SortKey> sortKeys = new ArrayList<>(10);
		sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
		sortKeys.add(new RowSorter.SortKey(0, SortOrder.DESCENDING));
		sortKeys.add(new RowSorter.SortKey(1, SortOrder.ASCENDING));
		sortKeys.add(new RowSorter.SortKey(1, SortOrder.DESCENDING));
		sortKeys.add(new RowSorter.SortKey(2, SortOrder.ASCENDING));
		sortKeys.add(new RowSorter.SortKey(2, SortOrder.DESCENDING));
		sortKeys.add(new RowSorter.SortKey(3, SortOrder.ASCENDING));
		sortKeys.add(new RowSorter.SortKey(3, SortOrder.DESCENDING));
		sortKeys.add(new RowSorter.SortKey(4, SortOrder.ASCENDING));
		sortKeys.add(new RowSorter.SortKey(4, SortOrder.DESCENDING));
		
		sorter.setSortKeys(sortKeys);
		
		JScrollPane sPane = new JScrollPane(tabelaPredmet);

		add(sPane, BorderLayout.CENTER);
		
		this.updatePrikaz(null, -1);
		
	}
	
	public int getSelectedRow() {
		
		return tabelaPredmet.getSelectedRow();
		
	}
	
	public void pretraziTabelu(String kriterijum) {
		
		TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(tabelaPredmet.getModel());
		tabelaPredmet.setRowSorter(rowSorter);
		rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + kriterijum, 1));
		
	}
}
