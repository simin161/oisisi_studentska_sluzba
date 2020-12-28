package view.tabbedPanes;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import view.tables.AbstractTableModelOcena;
import view.tables.OcenaTable;

public class PrikazOcene extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2386109019424750955L;

	private JTable tabeleOcena;

	public PrikazOcene() {
		setLayout(new BorderLayout());
		showTable();
	}

	public void update(String akcija, int r) {

		AbstractTableModelOcena model = (AbstractTableModelOcena) tabeleOcena.getModel();

		model.fireTableDataChanged();
		validate();

	}

	private void showTable() {

		tabeleOcena = new OcenaTable();
		JScrollPane sP = new JScrollPane(tabeleOcena);

		add(sP, BorderLayout.CENTER);

		update(null, -1);
	}

	
	public JTable getTable() {
		
		return tabeleOcena;
		
	}
	
	public int getSelectedRow() {
		return tabeleOcena.getSelectedRow();
	}
}
