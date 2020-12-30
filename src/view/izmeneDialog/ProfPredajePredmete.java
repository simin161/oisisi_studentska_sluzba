package view.izmeneDialog;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.Predmet;
import model.baze.PredmetBaza;
import model.baze.ProfPredBaza;
import view.tabbedPanes.PrikazProfPredmeta;

public class ProfPredajePredmete extends JPanel {

	private static final long serialVersionUID = 3309819413449241249L;

	private PrikazProfPredmeta prikaz;

	public ProfPredajePredmete() {

		BoxLayout box = new BoxLayout(this, BoxLayout.Y_AXIS);
		setLayout(box);
		JPanel panelBtn = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton btnDodaj = new JButton("Dodaj predmet");
		JButton btnUkloni = new JButton("Ukloni predmet");

		add(Box.createVerticalStrut(10));
		panelBtn.add(btnDodaj);
		panelBtn.add(Box.createHorizontalStrut(15));
		panelBtn.add(btnUkloni);
		add(panelBtn);
		add(Box.createVerticalStrut(10));

		prikaz = new PrikazProfPredmeta();

		ProfPredBaza ppb = prikaz.getModel().getBaza();

		add(prikaz);

		btnDodaj.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int selectedRow = prikaz.getSelectedRow();

				if (selectedRow >= 0) {

					int selRow = prikaz.getTable().convertRowIndexToModel(selectedRow);

					Predmet p = PredmetBaza.getInstance().getRow(selRow);

					ppb.dodajPredmet(selRow, p);

				}

			}

		});

		btnUkloni.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = prikaz.getSelectedRow();

				if (selectedRow != -1) {
					String[] opcije = { "Da", "Ne" };

					int i = JOptionPane.showOptionDialog(prikaz.getParent(),
							"Da li ste sigurni da želite da uklonite predmet?", "Uklanjanje predmeta",
							JOptionPane.YES_NO_OPTION, JOptionPane.DEFAULT_OPTION, null, opcije, opcije[0]);

					if (i == 0) {
						int r = prikaz.getTable().convertColumnIndexToModel(selectedRow);
						Predmet p = ppb.getRow(r);
						ppb.ukloniPredmet(r, p);
						prikaz.azuriraj("UKLONJEN", -1);
					}

				} else {
					JOptionPane.showMessageDialog(prikaz.getParent(), "Označite predmet koji želite da uklonite.",
							"Upozorenje", JOptionPane.WARNING_MESSAGE);
				}

			}

		});

	}

}
