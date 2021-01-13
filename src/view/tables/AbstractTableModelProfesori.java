//inspiracija -> vezbe 06, JTableMVCSimple & JTableMVCAdvanced

package view.tables;

import javax.swing.table.AbstractTableModel;

import model.baze.ProfesorBaza;

public class AbstractTableModelProfesori extends AbstractTableModel{

	private static final long serialVersionUID = 4009682820013416129L;

	public AbstractTableModelProfesori() {
		
	}
	
	
	@Override
	public int getRowCount() {
		
		return ProfesorBaza.getInstance().getProfesore().size();
		
	}

	@Override
	public int getColumnCount() {
	
		return ProfesorBaza.getInstance().getColumnCount();
		
	}

	public String getColumnName(int column) {
		
		return ProfesorBaza.getInstance().getColumnName(column);
		
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		return ProfesorBaza.getInstance().getValueAt(rowIndex, columnIndex);
		
	}

	
	
}
