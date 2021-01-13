package view.tables;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableCellRenderer;

public class OcenaTable extends JTable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6602777363641021566L;

	public OcenaTable() {
		setRowSelectionAllowed(true);
		setColumnSelectionAllowed(false);
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		setModel(new AbstractTableModelOcena());

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
