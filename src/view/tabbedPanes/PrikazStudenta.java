//vezbe06
package view.tabbedPanes;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import view.tables.AbstractTableModelStudents;
import view.tables.StudentTable;

public class PrikazStudenta extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5823063971835581077L;

	private static PrikazStudenta instance = null;

	public static PrikazStudenta getInstance() {

		if (instance == null) {
			instance = new PrikazStudenta();
		}

		return instance;
	}

	private JTable tableStudent;
	private TableRowSorter<TableModel> rowSorter = null;
	public PrikazStudenta() {
		setLayout(new BorderLayout());
		showTable();
		rowSorter = new TableRowSorter<>(tableStudent.getModel());
		tableStudent.setRowSorter(rowSorter);

	}

	public void update(String akcija, int r) {

		AbstractTableModelStudents model = (AbstractTableModelStudents) tableStudent.getModel();

		model.fireTableDataChanged();
		validate();

	}

	public void showTable() {

		tableStudent = new StudentTable();
		
		DefaultTableCellRenderer r = new DefaultTableCellRenderer();
		r.setHorizontalAlignment(JLabel.LEFT);
		tableStudent.getColumnModel().getColumn(5).setCellRenderer(r);
		
		JScrollPane sP = new JScrollPane(tableStudent);
		add(sP, BorderLayout.CENTER);

		update(null, -1);
	}

	
	public JTable getTable() {
		
		return tableStudent;
		
	}
	
	public int getSelectedRow() {
		return tableStudent.getSelectedRow();
	}
	
	@SuppressWarnings("unchecked")
	public void pretraziTabelu(String kriterijum) {


		if(kriterijum.trim().length()!=0) {
			
			String []parts = kriterijum.split(" ");
			
			if(parts.length == 1) {
				
				rowSorter = new TableRowSorter<>(tableStudent.getModel());
				tableStudent.setRowSorter(rowSorter);
				rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + parts[0], 2));
			}
			else if(parts.length == 2) {
				
				List<RowFilter<Object, Object>> filteri = new ArrayList<RowFilter<Object, Object>>(2);
				filteri.add(RowFilter.regexFilter("(?i)" + parts[0], 2));
				filteri.add(RowFilter.regexFilter("(?i)" + parts[1], 1));
				
				@SuppressWarnings("rawtypes")
				RowFilter filter = RowFilter.andFilter(filteri);
				
				rowSorter = new TableRowSorter<>(tableStudent.getModel());
				tableStudent.setRowSorter(rowSorter);
				rowSorter.setRowFilter(filter);

			}else if(parts.length == 3) {
				List<RowFilter<Object, Object>> filteri = new ArrayList<RowFilter<Object, Object>>(3);
				filteri.add(RowFilter.regexFilter("(?i)" + parts[0], 2));
				filteri.add(RowFilter.regexFilter("(?i)" + parts[1], 1));
				filteri.add(RowFilter.regexFilter("(?i)" + parts[2], 0));

				
				@SuppressWarnings("rawtypes")
				RowFilter filter = RowFilter.andFilter(filteri);
				
				rowSorter = new TableRowSorter<>(tableStudent.getModel());
				tableStudent.setRowSorter(rowSorter);
				rowSorter.setRowFilter(filter);

			}
		}
		else {
			rowSorter.setRowFilter(null);
			tableStudent.setRowSorter(rowSorter);

		}
	}
}
