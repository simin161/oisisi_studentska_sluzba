package view.izmeneDialog;

import java.awt.FlowLayout;
import java.text.DecimalFormat;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.baze.OcenaBaza;
import view.tabbedPanes.PrikazOcene;

public class StudentPolozeni extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8835038055857711937L;

	public StudentPolozeni() {

		BoxLayout b = new BoxLayout(this, BoxLayout.Y_AXIS);
		setLayout(b);
		JPanel panelB = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton buttonPonisti = new JButton("Poništi ocenu");

		add(Box.createVerticalStrut(10));
		panelB.add(buttonPonisti);
		add(panelB);
		add(Box.createVerticalStrut(10));

		PrikazOcene prikaz = new PrikazOcene();
		add(prikaz);

		OcenaBaza o = new OcenaBaza();

		JPanel panelPr = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JLabel lblPr = new JLabel("Prosek: " + o.izracunajProsek());
		panelPr.add(lblPr);

		JPanel panelEspb = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JLabel lblEspb = new JLabel("ESPB: " + o.izracunajEspb());
		panelEspb.add(lblEspb);

		add(panelPr);
		add(panelEspb);
	}

}
