//vezbe06
package view.tables;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableCellRenderer;

public class StudentTable extends JTable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4779786442645934948L;

	public StudentTable() {
		setRowSelectionAllowed(true);
		setColumnSelectionAllowed(false);
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		setModel(new AbstractTableModelStudents());

	}

	public Component prepRenderer(TableCellRenderer renderer, int row, int column) {

		Component comp = super.prepareRenderer(renderer, row, column);

		if (isRowSelected(row)) {
			comp.setBackground(Color.CYAN);
		} else {
			comp.setBackground(Color.WHITE);
		}

		return comp;

	}
}
