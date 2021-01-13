//vezbe06
package view.tables;

import javax.swing.table.AbstractTableModel;

import model.baze.StudentBaza;

public class AbstractTableModelStudents extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5968990415574554110L;

	public AbstractTableModelStudents() {}

	@Override
	public int getRowCount() {
		return StudentBaza.getInstance().getStudents().size();
	}

	@Override
	public int getColumnCount() {
		return StudentBaza.getInstance().getColumnCount();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return StudentBaza.getInstance().getValueAt(rowIndex, columnIndex);
	}

	public String getColumnName(int column) {

		return StudentBaza.getInstance().getColumnName(column);

	}
	
	public Class getColumnClass(int column) {
		if(column == 5)
			return Double.class;
		else
			return String.class;
	}

}
