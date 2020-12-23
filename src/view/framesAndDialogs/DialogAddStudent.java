package view.framesAndDialogs;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.buttonAction.ButtonAction;

public class DialogAddStudent extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4412711478828503212L;

	public DialogAddStudent(int width, int height, Container cont) {
		setTitle("Dodavanje studenta");
		setSize((int) (width * 0.5), height);
		setLocationRelativeTo(cont);
		setModal(true);
		setResizable(false);
		Dimension dim = new Dimension(150, 20);

		JPanel panelMain = new JPanel();
		BoxLayout b = new BoxLayout(panelMain, BoxLayout.Y_AXIS);
		panelMain.setLayout(b);

		JPanel panelName = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel labelName = new JLabel("Ime* ");
		JTextField tFName = new JTextField(10);
		labelName.setPreferredSize(dim);
		tFName.setPreferredSize(dim);
		panelName.add(Box.createHorizontalStrut(65));
		panelName.add(labelName);
		panelName.add(tFName);
		panelMain.add(panelName);

		JPanel panelPrezime = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel labelPrezime = new JLabel("Prezime* ");
		JTextField tFPrezime = new JTextField(10);
		labelPrezime.setPreferredSize(dim);
		tFPrezime.setPreferredSize(dim);
		panelPrezime.add(Box.createHorizontalStrut(65));
		panelPrezime.add(labelPrezime);
		panelPrezime.add(tFPrezime);
		panelMain.add(panelPrezime);

		JPanel panelAdr = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel labelAdr = new JLabel("Adresa stanovanja* ");
		JTextField tFAdr = new JTextField(10);
		labelAdr.setPreferredSize(dim);
		tFAdr.setPreferredSize(dim);
		panelAdr.add(Box.createHorizontalStrut(65));
		panelAdr.add(labelAdr);
		panelAdr.add(tFAdr);
		panelMain.add(panelAdr);

		JPanel panelBr = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel labelBr = new JLabel("Broj telefona* ");
		JTextField tFBr = new JTextField(10);
		labelBr.setPreferredSize(dim);
		tFBr.setPreferredSize(dim);
		panelBr.add(Box.createHorizontalStrut(65));
		panelBr.add(labelBr);
		panelBr.add(tFBr);
		panelMain.add(panelBr);

		JPanel panelEmail = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel labelEmail = new JLabel("E-mail adresa* ");
		JTextField tFEmail = new JTextField(10);
		labelEmail.setPreferredSize(dim);
		tFEmail.setPreferredSize(dim);
		panelEmail.add(Box.createHorizontalStrut(65));
		panelEmail.add(labelEmail);
		panelEmail.add(tFEmail);
		panelMain.add(panelEmail);

		JPanel panelBrI = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel labelBrI = new JLabel("Broj indeksa* ");
		JTextField tFBrI = new JTextField(10);
		labelBrI.setPreferredSize(dim);
		tFBrI.setPreferredSize(dim);
		panelBrI.add(Box.createHorizontalStrut(65));
		panelBrI.add(labelBrI);
		panelBrI.add(tFBrI);
		panelMain.add(panelBrI);

		JPanel panelGodU = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel labelGodU = new JLabel("Godina upisa* ");
		JTextField tFGodU = new JTextField(10);
		labelGodU.setPreferredSize(dim);
		tFGodU.setPreferredSize(dim);
		panelGodU.add(Box.createHorizontalStrut(65));
		panelGodU.add(labelGodU);
		panelGodU.add(tFGodU);
		panelMain.add(panelGodU);

		// https*//docs.oracle.com/javase/tutorial/uiswing/components/combobox.html
		String[] stringTGodS = { "I (prva)", "II (druga)", "III (treća)", "IV (četvrta)" };
		JPanel panelTGodS = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel labelTGodS = new JLabel("Trenutna godina studija* ");
		JComboBox cBTGodS = new JComboBox(stringTGodS);
		cBTGodS.setBackground(new Color(250,250,250));
		labelTGodS.setPreferredSize(dim);
		cBTGodS.setPreferredSize(dim);
		panelTGodS.add(Box.createHorizontalStrut(65));
		panelTGodS.add(labelTGodS);
		panelTGodS.add(cBTGodS);
		panelMain.add(panelTGodS);

		String[] stringFin = { "Budžet", "Samofinansiranje" };
		JPanel panelFin = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel labelFin = new JLabel("Način finansiranja* ");
		JComboBox cBFin = new JComboBox(stringFin);
		cBFin.setBackground(new Color(250,250,250));
		labelFin.setPreferredSize(dim);
		cBFin.setPreferredSize(dim);
		panelFin.add(Box.createHorizontalStrut(65));
		panelFin.add(labelFin);
		panelFin.add(cBFin);
		panelMain.add(panelFin);

		JPanel panelButton = new JPanel();
		BoxLayout button = new BoxLayout(panelButton, BoxLayout.X_AXIS);
		panelButton.setLayout(button);
		JButton buttonPotvrdi = new JButton("Potvrdi");
		JButton buttonPonisti = new JButton("Poništi");
		ButtonAction.cancelAction(buttonPonisti, this);
		//ButtonAction.potvrdiButton(buttonPotvrdi);
		panelButton.add(buttonPotvrdi);
		panelButton.add(Box.createHorizontalStrut(35));
		panelButton.add(buttonPonisti);
		panelMain.add(panelButton);

		add(panelMain);
		setVisible(true);

	}

}
