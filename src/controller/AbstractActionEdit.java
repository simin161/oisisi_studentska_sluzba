/* --- inspiracija-> vezbe, apstraktni dogadjaji, 04 --- */

package controller;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import view.izmeneDialog.IzmenaPredmeta;
import view.izmeneDialog.IzmenaProfesora;
import view.izmeneDialog.IzmenaStudent;
import view.tabbedPanes.PrikazPredmeta;
import view.tabbedPanes.PrikazProfesora;
import view.tabbedPanes.PrikazStudenta;

public class AbstractActionEdit extends AbstractAction {

	private static final long serialVersionUID = 543708384897911092L;

	private int rbrTaba = -1;
	private Container c;

	@SuppressWarnings("deprecation")
	public AbstractActionEdit(int rbr, Container c) {

		this.rbrTaba = rbr;
		this.c = c;

		putValue(NAME, "Edit");
		putValue(MNEMONIC_KEY, KeyEvent.VK_E);
		putValue(SHORT_DESCRIPTION, "Izmena podataka iz tabele");
		putValue(SMALL_ICON, new ImageIcon("images" + File.separator + "edit2.png"));
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_E, KeyEvent.CTRL_MASK));

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (this.rbrTaba == 0) {

			int selectedRow = PrikazStudenta.getInstance().getSelectedRow();

			if (selectedRow != -1) {

				int selRow = PrikazStudenta.getInstance().getTable().convertRowIndexToModel(selectedRow);
				IzmenaStudent izmenaS = new IzmenaStudent(this.c, selRow);
				izmenaS.setVisible(true);

			} else {
				JOptionPane.showMessageDialog(c, "Označite studenta za izmenu.", "Upozorenje",
						JOptionPane.WARNING_MESSAGE);
			}
		} else if (this.rbrTaba == 1) {

			int selectedRow = PrikazProfesora.getInstance().getSelectedRow();

			if (selectedRow >= 0) {

				int selRow = PrikazProfesora.getInstance().getTable().convertRowIndexToModel(selectedRow);

				IzmenaProfesora ip = new IzmenaProfesora(this.c, selRow);
				ip.setVisible(true);

			} else {

				JOptionPane.showMessageDialog(c, "Označite profesora za izmenu.", "Upozorenje",
						JOptionPane.WARNING_MESSAGE);

			}

		} else if (this.rbrTaba == 2) {

			int selectedRow = PrikazPredmeta.getInstance().getSelectedRow();

			if (selectedRow != -1) {

				int selRow = PrikazPredmeta.getInstance().getTable().convertRowIndexToModel(selectedRow);
				IzmenaPredmeta izmenaP = new IzmenaPredmeta(this.c, selRow);
				izmenaP.setVisible(true);
			} else {
				JOptionPane.showMessageDialog(c, "Označite predmet za izmenu.", "Upozorenje",
						JOptionPane.WARNING_MESSAGE);
			}

		} else {

			JOptionPane.showMessageDialog(null, "Greška.", "Greška: ", JOptionPane.ERROR_MESSAGE);

		}

	}

	public void updateRbr(int rbr) {

		this.rbrTaba = rbr;

	}

}
