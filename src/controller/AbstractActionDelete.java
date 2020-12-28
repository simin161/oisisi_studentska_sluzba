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

import controller.predmet.PredmetController;
import controller.profesor.ProfesoriController;
import controller.student.StudentController;
import view.tabbedPanes.PrikazPredmeta;
import view.tabbedPanes.PrikazProfesora;
import view.tabbedPanes.PrikazStudenta;

public class AbstractActionDelete extends AbstractAction {

	private static final long serialVersionUID = 7464546556386918717L;

	private int rbrTaba;
	private Container c;

	@SuppressWarnings("deprecation")
	public AbstractActionDelete(int rbr, Container c) {

		this.rbrTaba = rbr;
		this.c = c;

		putValue(NAME, "Delete");
		putValue(MNEMONIC_KEY, KeyEvent.VK_D);
		putValue(SHORT_DESCRIPTION, "Brisanje podataka iz tabele");
		putValue(SMALL_ICON, new ImageIcon("images" + File.separator + "delete2.png"));
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_D, KeyEvent.CTRL_MASK));

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (this.rbrTaba == 0) {

			int row = PrikazStudenta.getInstance().getSelectedRow();

			if (row != -1) {

				String[] opcije = {"Da", "Ne"};

				int i = JOptionPane.showOptionDialog(c, "Da li ste sigurni da želite da obrišete studenta?",
						"Brisanje studenta", JOptionPane.YES_NO_OPTION, JOptionPane.DEFAULT_OPTION, null, opcije,
						opcije[0]);

				if (i == 0) {

					StudentController.getInstance().izbrisiStudenta(row);

				}

			}
			else {
				
				JOptionPane.showMessageDialog(c, "Označite studenta za brisanje.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
				
			}
		} else if (this.rbrTaba == 1) {

			int row = PrikazProfesora.getInstance().getSelectedRow();

			if (row != -1) {

				String[] opcije = {"Da", "Ne"};

				int i = JOptionPane.showOptionDialog(c, "Da li ste sigurni da želite da obrišete profesora?",
						"Brisanje profesora", JOptionPane.YES_NO_OPTION, JOptionPane.DEFAULT_OPTION, null, opcije,
						opcije[0]);

				if (i == 0) {

					ProfesoriController.getInstance().izbrisiProfesora(row);

				}

			}else {
				
				JOptionPane.showMessageDialog(c, "Označite profesora za brisanje.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
				
			}

		} else if (this.rbrTaba == 2) {

			// if() provera da li je selektovan red?

			int row = PrikazPredmeta.getInstance().getSelectedRow();

			if (row != -1) {

				String[] opcije = new String[2];

				opcije[0] = "Da"; // vrednost 0
				opcije[1] = "Ne"; // vrednost 1

				int i = JOptionPane.showOptionDialog(c, "Da li ste sigurni da želite da obrišete predmet?",
						"Brisanje predmeta", JOptionPane.YES_NO_OPTION, JOptionPane.DEFAULT_OPTION, null, opcije,
						opcije[0]);

				if (i == 0) {

					PredmetController.getInstance().izbrisiPredmet(row);

				}

			}
			else {
				
				JOptionPane.showMessageDialog(c, "Označite predmet za brisanje.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
				
			}

		} else {

			JOptionPane.showMessageDialog(this.c, "Greška.", "Greška: ", JOptionPane.ERROR_MESSAGE);

		}

	}

	public void updateRbr(int rbr) {

		this.rbrTaba = rbr;

	}

}
