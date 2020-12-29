package view.izmeneDialog;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

import javax.swing.Box;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controller.buttonAction.ButtonAction;
import controller.predmet.PredmetController;
import model.Predmet;
import model.Profesor;
import model.baze.PredmetBaza;
import model.baze.ProfesorBaza;
import view.tabbedPanes.PrikazPredmeta;

//https://www.geeksforgeeks.org/java-swing-jlist-with-examples/
//https://docs.oracle.com/javase/tutorial/uiswing/components/list.html
public class DodajProfNaPredmet extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3187216741915437719L;
	private int selected;

	DodajProfNaPredmet(Container cont, int r) {

		setSize((int) (cont.getWidth() * 0.75), (int) (cont.getHeight() * 0.5));
		setModal(true);
		setResizable(false);
		setLayout(new BorderLayout());
		setTitle("Odaberi profesora");
		selected = -1;
		Component rigidArea = Box.createRigidArea(new Dimension(15, 15));
		Component rigidArea2 = Box.createRigidArea(new Dimension(50, 50));
		add(rigidArea, BorderLayout.NORTH);
		add(rigidArea2, BorderLayout.SOUTH);
		add(Box.createHorizontalStrut(20), BorderLayout.WEST);
		add(Box.createHorizontalStrut(20), BorderLayout.EAST);

		List<Profesor> profe = ProfesorBaza.getInstance().getProfesore();
		Profesor[] arr = profe.toArray(new Profesor[0]);
		// https://stackoverflow.com/questions/1018750/how-to-convert-object-array-to-string-array-in-java
		String[] strings = Arrays.stream(arr).map(Profesor::toString).toArray(String[]::new);

		JList<String> list = new JList<String>(strings);

		// https://stackoverflow.com/questions/21029653/java-jlist-text-center-align
		DefaultListCellRenderer renderer = (DefaultListCellRenderer) list.getCellRenderer();
		renderer.setHorizontalAlignment(SwingConstants.CENTER);
		JScrollPane sP = new JScrollPane(list);

		JPanel panelButt = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JButton buttonPotvrdi = new JButton("Potvrdi");
		JButton buttonOdustani = new JButton("Odustani");
		buttonPotvrdi.setPreferredSize(new Dimension(90, 20));
		buttonOdustani.setPreferredSize(new Dimension(90, 20));

		panelButt.add(buttonPotvrdi);
		panelButt.add(Box.createHorizontalStrut(20));
		panelButt.add(buttonOdustani);

		ButtonAction.cancelAction(buttonOdustani, this);
		list.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				selected = list.getSelectedIndex();

			}

		});

		buttonPotvrdi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (selected != -1) {

					Predmet p = PredmetBaza.getInstance().getRow(r);
					p.setProfesor(arr[selected]);
					PredmetController.getInstance().izmeniPredmet(p, p.getSifra());
					IzmenaPredmeta.get().setText(strings[selected]);
					JOptionPane.showMessageDialog(DodajProfNaPredmet.this.getContentPane(), "Profesor je uspešno dodat na predmet.");
					dispose();
				} else {
					JOptionPane.showMessageDialog(DodajProfNaPredmet.this.getContentPane(),
							"Označite profesora da bi ga dodali na predmet", "Upozorenje", JOptionPane.WARNING_MESSAGE);
				}

			}

		});

		add(sP, BorderLayout.CENTER);
		add(panelButt, BorderLayout.SOUTH);
		setLocationRelativeTo(cont);
		setVisible(true);

	}
}
