package view.tables;

import javax.swing.table.AbstractTableModel;

import model.baze.NepolozeniBaza;

public class AbstractTableModelNepolozeni extends AbstractTableModel {

	private static final long serialVersionUID = 1371388998871385094L;

	NepolozeniBaza b;
	
	public AbstractTableModelNepolozeni() {
		
		b = new NepolozeniBaza();
		b.initNepolozene();
		
	}
	
	public NepolozeniBaza getBaza() {
		
		return b;
		
	}
	
	@Override
	public int getRowCount() {
		
		if(b.getPredmet() == null)
			return 0;
		
		return b.getPredmet().size();
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
