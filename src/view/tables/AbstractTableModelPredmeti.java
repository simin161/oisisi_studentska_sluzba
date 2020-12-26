package view.tables;

import javax.swing.table.AbstractTableModel;

import model.baze.PredmetBaza;

public class AbstractTableModelPredmeti extends AbstractTableModel {

	private static final long serialVersionUID = -3121171824392412452L;

	public AbstractTableModelPredmeti() {
		
		
	}
	
	@Override
	public int getRowCount() {
		
		return PredmetBaza.getInstance().getPredmete().size();
	
	}

	@Override
	public int getColumnCount() {
		
		return PredmetBaza.getInstance().getColumnCount();
		
	}
	
	public String getColumnName(int column) {
		
		return PredmetBaza.getInstance().getColumnName(column);
		
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		return PredmetBaza.getInstance().getValueAt(rowIndex, columnIndex);
	
	}

}
