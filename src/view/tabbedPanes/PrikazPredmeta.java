//inspiracija -> vezbe 06, JTableMVCSimple & JTableMVCAdvanced

package view.tabbedPanes;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import view.tables.AbstractTableModelPredmeti;
import view.tables.PredmetTable;


public class PrikazPredmeta extends JPanel{
	
	private static final long serialVersionUID = 4624960353287578931L;

	private static PrikazPredmeta instance = null;
	
	private TableRowSorter<TableModel> rowSorter = null;
	
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
		
		rowSorter = new TableRowSorter<>(tabelaPredmet.getModel());
		tabelaPredmet.setRowSorter(rowSorter);
		
	}
	
	public void updatePrikaz(String akcija, int vrednost) {
		
		AbstractTableModelPredmeti model = (AbstractTableModelPredmeti) tabelaPredmet.getModel();
		
		model.fireTableDataChanged();
		validate();
		
	}
	
	public void prikaziTabelu() {
		
		tabelaPredmet= new PredmetTable();
		
        DefaultTableCellRenderer r=new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.LEFT);
        tabelaPredmet.getColumnModel().getColumn(2).setCellRenderer(r);
      
		JScrollPane sPane = new JScrollPane(tabelaPredmet);

		add(sPane, BorderLayout.CENTER);
		
		this.updatePrikaz(null, -1);
		
	}
	
	public int getSelectedRow() {
		
		return tabelaPredmet.getSelectedRow();
		
	}
	
	public void pretraziTabelu(String kriterijum) {
		
		kriterijum = kriterijum.toLowerCase();
		
		if(kriterijum.trim().length()!= 0) {
			
			rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + kriterijum, 1));
			tabelaPredmet.setRowSorter(rowSorter);
			
		}
		else
		{
			rowSorter.setRowFilter(null);
			tabelaPredmet.setRowSorter(rowSorter);
		}
	}
}
