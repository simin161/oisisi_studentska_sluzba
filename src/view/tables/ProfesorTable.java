//inspiracija -> vezbe 06, JTableMVCSimple & JTableMVCAdvanced

package view.tables;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableCellRenderer;

public class ProfesorTable extends JTable{

	private static final long serialVersionUID = -1028457065900424585L;

	public ProfesorTable() {
		
		this.setRowSelectionAllowed(true);
		this.setColumnSelectionAllowed(false);
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.setModel(new AbstractTableModelProfesori());
		
	}
	
	public Component prepRenderer(TableCellRenderer renderer, int row, int column) {
		
		Component c = super.prepareRenderer(renderer, row, column);
		
		if(isRowSelected(row)) {
			
			c.setBackground(Color.CYAN);
	
		}
		else {
			
			c.setBackground(Color.WHITE);
		
		}
		
		return c;
		
	}
	
}
