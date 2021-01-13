package view.tables;

import javax.swing.table.AbstractTableModel;

import model.baze.OcenaBaza;

public class AbstractTableModelOcena extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4574936809404435063L;
	OcenaBaza b;

	public AbstractTableModelOcena() {
		b = new OcenaBaza();
		b.initializeOcena();
	}
	
	public OcenaBaza getBaza() {
		return b;
	}

	@Override
	public int getRowCount() {
		if (b.getOcena() == null) {
			return 0;
		}
		
		return b.getOcena().size();
	}

	@Override
	public int getColumnCount() {
		return b.getColumnCount();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return b.getValueAt(rowIndex, columnIndex);
	}

	public String getColumnName(int column) {

		return b.getColumnName(column);

	}

}
