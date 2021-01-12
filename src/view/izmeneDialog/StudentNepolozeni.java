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
import model.baze.NepolozeniBaza;
import view.tabbedPanes.PrikazNepolozeni;

public class StudentNepolozeni extends JPanel {

	private static final long serialVersionUID = 3806457541651770421L;

	private static PrikazNepolozeni prikaz;

	public StudentNepolozeni(int r) {

		BoxLayout box = new BoxLayout(this, BoxLayout.Y_AXIS);
		setLayout(box);

		JPanel panelBtn = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton btnDodaj = new JButton("Dodaj");
		JButton btnObrisi = new JButton("Obriši");
		JButton btnPolaganje = new JButton("Polaganje");

		add(Box.createVerticalStrut(10));
		panelBtn.add(btnDodaj);
		panelBtn.add(btnObrisi);
		panelBtn.add(btnPolaganje);

		add(panelBtn);
		add(Box.createVerticalStrut(10));

		prikaz = new PrikazNepolozeni();

		btnDodaj.addActionListener(new ActionListener() {

			@SuppressWarnings("unused")
			@Override
			public void actionPerformed(ActionEvent e) {
				DodajUNep d = new DodajUNep(StudentNepolozeni.this.getParent(), r);
				prikaz.update("DODAT", 1);
			}

		});

		btnObrisi.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int r = prikaz.getSelectedRow();
				if (r != -1) {

					NepolozeniBaza n = prikaz.getModel().getBaza();
					Predmet p = n.getRow(r);

					String[] opcije = { "Da", "Ne" };

					int i = JOptionPane.showOptionDialog(prikaz.getParent(),
							"Da li ste sigurni da želite da uklonite predmet?", "Uklanjanje predmeta",
							JOptionPane.YES_NO_OPTION, JOptionPane.DEFAULT_OPTION, null, opcije, opcije[0]);

					if (i == 0) {
						n.izbrisi(p);
						prikaz.update("UKLONJEN", -1);
					}
				}else {
					JOptionPane.showMessageDialog(prikaz.getParent(), "Označite predmet koji želite da uklonite.",
							"Upozorenje", JOptionPane.WARNING_MESSAGE);
				}
			}

		});

		btnPolaganje.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				int selectedRow = prikaz.getSelectedRow();
				
				if(selectedRow != -1) {
					
					UnosOcene unos = new UnosOcene(StudentNepolozeni.this.getParent(), selectedRow, r, prikaz.getModel().getBaza());
					unos.setVisible(true);
				
				}
				else {
					
					JOptionPane.showMessageDialog(prikaz.getParent(), "Označite predmet za upis ocene.",
							"Upozorenje", JOptionPane.WARNING_MESSAGE);
					
				}
			}
		});

		add(prikaz);

	}

	public static PrikazNepolozeni getPrikaz() {
		return prikaz;
	}
}
