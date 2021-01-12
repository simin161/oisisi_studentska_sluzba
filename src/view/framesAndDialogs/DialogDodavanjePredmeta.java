package view.framesAndDialogs;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import controller.ActionChangeTField;
import controller.buttonAction.ButtonAction;
import controller.predmet.PredmetController;
import controller.provere.ProveraEspb;
import controller.provere.ProveraNazivaPredmeta;
import controller.provere.ProveraSifrePredmeta;
import model.Predmet;
import model.Student;
import model.baze.PredmetBaza;
import model.nabrojiviTipovi.Semestar;

public class DialogDodavanjePredmeta extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5390304808129020475L;

	private boolean less = false;
	private boolean higher = false;
	private boolean exists = false;

	public DialogDodavanjePredmeta(Container cont) {

		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenHeight = (int) (screenSize.height * 0.75 * 0.8);
		int screenWidth = (int) (screenSize.width * 0.75 * 0.4);

		setTitle("Dodavanje predmeta");
		setSize(screenWidth, screenHeight);
		setLocationRelativeTo(cont);
		setModal(true);
		setResizable(false);
		Dimension dim = new Dimension(155, 20);

		JPanel panelMain = new JPanel();
		BoxLayout box = new BoxLayout(panelMain, BoxLayout.Y_AXIS);
		panelMain.setLayout(box);

		JPanel panelButton = new JPanel();
		BoxLayout button = new BoxLayout(panelButton, BoxLayout.X_AXIS);
		panelButton.setLayout(button);
		JButton buttonPotvrdi = new JButton("Potvrdi");
		JButton buttonPonisti = new JButton("Poništi");
		ButtonAction.cancelAction(buttonPonisti, this);
		buttonPotvrdi.setEnabled(false);

		JPanel panelSifra = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel lblSifra = new JLabel("Šifra* ");
		JTextField txtSifra = new JTextField();
		lblSifra.setForeground(Color.red);
		lblSifra.setPreferredSize(dim);
		txtSifra.setPreferredSize(dim);
		panelSifra.add(Box.createHorizontalStrut(65));
		panelSifra.add(lblSifra);
		panelSifra.add(txtSifra);
		panelMain.add(panelSifra);

		JPanel panelNaziv = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel lblNaziv = new JLabel("Naziv*");
		JTextField txtNaziv = new JTextField();
		lblNaziv.setForeground(Color.red);
		lblNaziv.setPreferredSize(dim);
		txtNaziv.setPreferredSize(dim);
		panelNaziv.add(Box.createHorizontalStrut(65));
		panelNaziv.add(lblNaziv);
		panelNaziv.add(txtNaziv);
		panelMain.add(panelNaziv);

		JPanel panelEspb = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel lblEspb = new JLabel("Broj ESPB bodova* ");
		JTextField txtEspb = new JTextField();
		lblEspb.setForeground(Color.red);
		lblEspb.setPreferredSize(dim);
		txtEspb.setPreferredSize(dim);
		panelEspb.add(Box.createHorizontalStrut(65));
		panelEspb.add(lblEspb);
		panelEspb.add(txtEspb);
		panelMain.add(panelEspb);

		String[] stringGod = { "I (prva)", "II (druga)", "III (treća)", "IV (četvrta)", "V (peta/master)",
				"VI (šesta/master)" };
		JPanel panelGod = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel lblGod = new JLabel("Trenutna godina studija ");

		@SuppressWarnings({ "rawtypes", "unchecked" })
		JComboBox cbGod = new JComboBox(stringGod);
		cbGod.setBackground(new Color(250, 250, 250));
		lblGod.setPreferredSize(dim);
		cbGod.setPreferredSize(dim);
		panelGod.add(Box.createHorizontalStrut(65));
		panelGod.add(lblGod);
		panelGod.add(cbGod);
		panelMain.add(panelGod);

		String[] stringSem = { "Zimski", "Letnji" };
		JPanel panelSem = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel lblSem = new JLabel("Semestar ");

		@SuppressWarnings({ "rawtypes", "unchecked" })
		JComboBox cbSem = new JComboBox(stringSem);
		cbSem.setBackground(new Color(250, 250, 250));
		lblSem.setPreferredSize(dim);
		cbSem.setPreferredSize(dim);
		panelSem.add(Box.createHorizontalStrut(65));
		panelSem.add(lblSem);
		panelSem.add(cbSem);
		panelMain.add(panelSem);

		panelButton.add(buttonPotvrdi);
		panelButton.add(Box.createHorizontalStrut(35));
		panelButton.add(buttonPonisti);
		panelMain.add(panelButton);

		boolean[] valid = { false, false, false };
		String[] text = { "", "", "" };
		List<Predmet> predmeti = PredmetBaza.getInstance().getPredmete();

		txtSifra.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void focusLost(FocusEvent e) {

				if (txtSifra.getText().trim().length() == 0) {
					txtSifra.setText("");
				}

				if (!txtSifra.getText().trim().equals("") && !valid[0] && !exists) {
					txtSifra.setText("");
					JOptionPane.showMessageDialog(DialogDodavanjePredmeta.this, "Pogrešno uneta šifra predmeta!",
							"Greška: ", JOptionPane.ERROR_MESSAGE);
				}

				if (exists) {
					txtSifra.setText("");
					JOptionPane.showMessageDialog(DialogDodavanjePredmeta.this, "Šifra predmeta postoji!", "Greška: ",
							JOptionPane.ERROR_MESSAGE);
				}

				txtSifra.setText(txtSifra.getText().toUpperCase());

			}

		});

		txtSifra.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				check();

			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				check();

			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				check();

			}

			private void check() {
				valid[0] = ProveraSifrePredmeta.proveriSifruPredmeta(txtSifra.getText());

				exists = false;
				if (valid[0]) {
					exists = ProveraSifrePredmeta.checkExists(txtSifra.getText().toUpperCase(), predmeti);

					if (exists) {
						valid[0] = false;
					}
				}
				changeLabel(valid[0], "Šifra*", "Šifra", lblSifra);
				buttonPotvrdi.setEnabled(checkValid(valid));
			}

		});

		txtNaziv.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtNaziv.getText().trim().length() == 0) {
					txtNaziv.setText("");
				}

				if (!txtNaziv.getText().trim().equals("") && !valid[1]) {
					txtNaziv.setText("");
					JOptionPane.showMessageDialog(DialogDodavanjePredmeta.this, "Pogrešno unet naziv predmeta!",
							"Greška: ", JOptionPane.ERROR_MESSAGE);
				}

				txtNaziv.setText(setString(txtNaziv.getText()));

			}

		});

		txtNaziv.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				check();

			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				check();

			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				check();

			}

			private void check() {
				if (txtNaziv.getText().trim().length() != 0) {
					valid[1] = ProveraNazivaPredmeta.proveriNazivPredmeta(txtNaziv.getText());
				}
				changeLabel(valid[1], "Naziv*", "Naziv", lblNaziv);
				buttonPotvrdi.setEnabled(checkValid(valid));
			}

		});

		txtEspb.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtEspb.getText().trim().length() == 0) {
					txtEspb.setText("");
				}
				if (!valid[2] && !less && !higher && !txtEspb.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(DialogDodavanjePredmeta.this, "Pogrešno uneti ESPB bodovi!",
							"Greška: ", JOptionPane.ERROR_MESSAGE);
				}
				if (less) {
					txtEspb.setText("");
					less = false;
					JOptionPane.showMessageDialog(DialogDodavanjePredmeta.this, "Broj ESPB bodova ne može biti 0!",
							"Greška: ", JOptionPane.ERROR_MESSAGE);
				}
				if (higher) {
					txtEspb.setText("");
					higher = false;
					JOptionPane.showMessageDialog(DialogDodavanjePredmeta.this,
							"Broj ESPB bodova ne može biti veći od 60!", "Greška: ", JOptionPane.ERROR_MESSAGE);
				}

				txtEspb.setText(txtEspb.getText().trim());

			}

		});

		txtEspb.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				check();

			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				check();

			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				check();

			}

			private void check() {
				valid[2] = ProveraEspb.proveriEspb(txtEspb.getText().trim());
				less = false;
				higher = false;
				if (valid[2]) {
					int br = Integer.parseInt(txtEspb.getText().trim());
					if (br <= 0) {
						less = true;
						valid[2] = false;
					}
					if (br > 60) {
						higher = true;
						valid[2] = false;
					}
				}
				changeLabel(valid[2], "Broj ESPB bodova*", "Broj ESPB bodova", lblEspb);
				buttonPotvrdi.setEnabled(checkValid(valid));
			}

		});

		ActionChangeTField.changeTField(txtSifra, txtNaziv);
		ActionChangeTField.changeTField(txtNaziv, txtEspb);
		txtEspb.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cbGod.requestFocusInWindow();

			}

		});

		buttonPotvrdi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int god = 0;
				switch (cbGod.getSelectedItem().toString()) {

				case "I (prva)":
					god = 1;
					break;
				case "II (druga)":
					god = 2;
					break;
				case "III (treća)":
					god = 3;
					break;
				case "IV (četvrta)":
					god = 4;
					break;
				case "V (peta/master)":
					god = 5;
					break;
				case "VI (šesta/master)":
					god = 6;
					break;
				}
				Semestar sem = Semestar.Zimski;
				switch (cbSem.getSelectedItem().toString()) {
				case "Zimski":
					sem = Semestar.Zimski;
					break;
				case "Letnji":
					sem = Semestar.Letnji;
					break;
				}
				int espb = Integer.parseInt(txtEspb.getText());

				PredmetController pc = new PredmetController(new Predmet(txtSifra.getText().trim().toUpperCase(),
						txtNaziv.getText(), sem, god, null, espb, new ArrayList<Student>(), new ArrayList<Student>()));
				pc.dodajPredmet();

				JOptionPane.showMessageDialog(null, "Unos novog predmeta je uspešno izvršen!");

				dispose();

			}

		});

		add(panelMain);
		setVisible(true);

	}

	private String setString(String text) {
		if (text.contentEquals("")) {
			return text;
		} else
			return text.substring(0, 1).toUpperCase() + text.substring(1);
	}

	private void changeLabel(boolean flag, String f, String t, JLabel lbl) {
		if (flag) {
			lbl.setText(t);
			lbl.setForeground(Color.black);
		} else {
			lbl.setText(f);
			lbl.setForeground(Color.red);
		}
	}

	private boolean checkValid(boolean[] valid) {
		boolean retVal = true;
		for (int i = 0; i < valid.length; ++i) {
			if (!valid[i]) {
				retVal = false;
				break;
			}
		}

		return retVal;
	}
}
