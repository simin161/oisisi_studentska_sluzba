//vezbe06
package view.tabbedPanes;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import view.tables.AbstractTableModelStudents;
import view.tables.StudentTable;

public class PrikazStudenta extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5823063971835581077L;

	private static PrikazStudenta instance = null;

	public static PrikazStudenta getInstance() {

		if (instance == null) {
			instance = new PrikazStudenta();
		}

		return instance;
	}

	private JTable tableStudent;

	public PrikazStudenta() {
		setLayout(new BorderLayout());
		showTable();
	}

	public void update() {

		AbstractTableModelStudents model = (AbstractTableModelStudents) tableStudent.getModel();

		model.fireTableDataChanged();
		validate();

	}

	private void showTable() {

		tableStudent = new StudentTable();
		JScrollPane sP = new JScrollPane(tableStudent);

		add(sP, BorderLayout.CENTER);

		update();
	}

}
