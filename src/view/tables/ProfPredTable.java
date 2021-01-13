package view.tables;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableCellRenderer;

public class ProfPredTable extends JTable {

	private static final long serialVersionUID = -2751632491291619835L;
	
	public ProfPredTable() {
		
		setRowSelectionAllowed(true);
		setColumnSelectionAllowed(false);
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		setModel(new AbstractTableModelProfPred());
		
		
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
