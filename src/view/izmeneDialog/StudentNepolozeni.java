package view.izmeneDialog;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import view.tabbedPanes.PrikazNepolozeni;

public class StudentNepolozeni extends JPanel {

	private static final long serialVersionUID = 3806457541651770421L;

	private static PrikazNepolozeni prikaz;

	public StudentNepolozeni() {

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

			@Override
			public void actionPerformed(ActionEvent e) {

			}

		});

		btnObrisi.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

			}

		});

		btnPolaganje.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

			}

		});

		add(prikaz);

	}

	public static PrikazNepolozeni getPrikaz() {
		return prikaz;
	}
}
