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
	
	public void prikaziTabelu() {
		
		tabelaProfesora = new ProfesorTable();
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tabelaProfesora.getModel());
		
		tabelaProfesora.setRowSorter(sorter);
		
		List<RowSorter.SortKey> sortKeys = new ArrayList<>(8);
		sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
		sortKeys.add(new RowSorter.SortKey(0, SortOrder.DESCENDING));
		sortKeys.add(new RowSorter.SortKey(1, SortOrder.ASCENDING));
		sortKeys.add(new RowSorter.SortKey(1, SortOrder.DESCENDING));
		sortKeys.add(new RowSorter.SortKey(2, SortOrder.ASCENDING));
		sortKeys.add(new RowSorter.SortKey(2, SortOrder.DESCENDING));
		sortKeys.add(new RowSorter.SortKey(3, SortOrder.ASCENDING));
		sortKeys.add(new RowSorter.SortKey(3, SortOrder.DESCENDING));
		
		sorter.setSortKeys(sortKeys);
		
		JScrollPane sPane = new JScrollPane(tabelaProfesora);
		
		add(sPane, BorderLayout.CENTER);	
		
		this.updatePrikaz(null, -1);
		
	}
	
	public int getSelectedRow() {
		
		return tabelaProfesora.getSelectedRow();
	
	}
	
	@SuppressWarnings("unchecked")
	public void pretraziTabelu(String kriterijum) {
		
		if(kriterijum.trim().length()!=0) {
			
			String []parts = kriterijum.split(" ");
			
			if(parts.length == 1) {
				
				TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(tabelaProfesora.getModel());
				tabelaProfesora.setRowSorter(rowSorter);
				rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + parts[0], 1));
				
			}
			else if(parts.length == 2) {
				
				List<RowFilter<Object, Object>> filteri = new ArrayList<RowFilter<Object, Object>>(2);
				filteri.add(RowFilter.regexFilter("(?i)" + parts[0], 1));
				filteri.add(RowFilter.regexFilter("(?i)" + parts[1], 0));
				
				@SuppressWarnings("rawtypes")
				RowFilter filter = RowFilter.andFilter(filteri);
				
				TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(tabelaProfesora.getModel());
				tabelaProfesora.setRowSorter(rowSorter);
				rowSorter.setRowFilter(filter);
			}
		}
	}
	
}
