package view.tables;

import javax.swing.table.AbstractTableModel;

import model.baze.ProfPredBaza;

public class AbstractTableModelProfPred extends AbstractTableModel {
	
	private static final long serialVersionUID = 5049299182091885L;

	ProfPredBaza ppb;
	
	public AbstractTableModelProfPred() {
		
		ppb = new ProfPredBaza();
		ppb.initializeProfPredBaza();
		
	}
	
	public ProfPredBaza getBaza() {
		
		return ppb;
		
	}
	
	@Override
	public int getRowCount() {
		
		if(ppb.getPredmete() == null)
			return 0;
		else
			return ppb.getPredmete().size();
	}

	@Override
	public int getColumnCount() {
		return ppb.getColumnCount();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		return ppb.getValueAt(rowIndex, columnIndex);
		
	}

	public String getColumnName(int column) {
		return ppb.getColumnName(column);
	}
	
	
}
