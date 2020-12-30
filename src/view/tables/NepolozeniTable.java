package view.tables;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableCellRenderer;

public class NepolozeniTable extends JTable {

	private static final long serialVersionUID = -1894008939434952516L;

	public NepolozeniTable() {
		
		setRowSelectionAllowed(true);
		setColumnSelectionAllowed(false);
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		setModel(new AbstractTableModelNepolozeni());
		
	}
	
	public Component prepRenderer(TableCellRenderer renderer, int row, int column) {
		
		Component c = super.prepareRenderer(renderer, row, column);
		
		if(isRowSelected(row)) {
			
			c.setBackground(Color.cyan);
			
		}
		else {
			
			c.setBackground(Color.white);

		}
		
		return c;
	}
	
}
