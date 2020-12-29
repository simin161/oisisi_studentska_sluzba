package view.izmeneDialog;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.baze.OcenaBaza;
import view.tabbedPanes.PrikazOcene;
import view.tabbedPanes.PrikazStudenta;

public class StudentPolozeni extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8835038055857711937L;

	private PrikazOcene prikaz;

	public StudentPolozeni() {

		BoxLayout b = new BoxLayout(this, BoxLayout.Y_AXIS);
		setLayout(b);
		JPanel panelB = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton buttonPonisti = new JButton("Poništi ocenu");

		add(Box.createVerticalStrut(10));
		panelB.add(buttonPonisti);
		add(panelB);
		add(Box.createVerticalStrut(10));

		prikaz = new PrikazOcene();

		OcenaBaza o1 = prikaz.getModel().getBaza();
		JPanel panelPr = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JLabel lblPr = new JLabel("Prosek: " + o1.izracunajProsek());
		panelPr.add(lblPr);

		JPanel panelEspb = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JLabel lblEspb = new JLabel("ESPB: " + o1.izracunajEspb());
		panelEspb.add(lblEspb);

		buttonPonisti.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (prikaz.getSelectedRow() != -1) {
					String[] opcije = { "Da", "Ne" };

					int i = JOptionPane.showOptionDialog(prikaz.getParent(),
							"Da li ste sigurni da želite da poništite ocenu?", "Poništavanje ocene",
							JOptionPane.YES_NO_OPTION, JOptionPane.DEFAULT_OPTION, null, opcije, opcije[0]);
					if (i == 0) {
						o1.ponistiOcenu(o1.getRow(prikaz.getSelectedRow()).getPredmet().getSifra());
						lblPr.setText("Prosek: " + o1.izracunajProsek());
						lblEspb.setText("ESPB: " + o1.izracunajEspb());
						prikaz.update("UKLONJEN", -1);
						PrikazStudenta.getInstance().update("", 0);
				
					}
				} else {
					JOptionPane.showMessageDialog(prikaz.getParent(), "Označite ocenu koju želite da poništite.",
							"Upozorenje", JOptionPane.WARNING_MESSAGE);
				}
			}

		});

		add(prikaz);
		add(panelPr);
		add(panelEspb);
	}

}
