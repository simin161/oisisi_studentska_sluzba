package view.izmeneDialog;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
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
import model.Profesor;
import model.Student;
import model.baze.PredmetBaza;
import model.baze.ProfesorBaza;
import model.baze.StudentBaza;
import model.nabrojiviTipovi.Semestar;

public class IzmenaPredmeta extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3326868121646494256L;

	private boolean less = false;
	private boolean higher = false;
	private boolean exists = false;
	private boolean pressed = false;
	private static JTextArea txtProf;

	public IzmenaPredmeta(Container c, int r) {

		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenHeight = (int) (screenSize.height * 0.75 * 0.8);
		int screenWidth = (int) (screenSize.width * 0.75 * 0.4);

		setTitle("Izmena predmeta");
		setSize(screenWidth, screenHeight);
		setLocationRelativeTo(c);
		setModal(true);
		setResizable(false);
		Dimension dim = new Dimension(155, 20);

		Predmet p = PredmetBaza.getInstance().getRow(r);
		String oldId = p.getSifra();
		boolean[] valid = { true, true, true };

		JPanel panelMain = new JPanel();
		BoxLayout box = new BoxLayout(panelMain, BoxLayout.Y_AXIS);
		panelMain.setLayout(box);

		JPanel panelButton = new JPanel();
		BoxLayout button = new BoxLayout(panelButton, BoxLayout.X_AXIS);
		panelButton.setLayout(button);
		JButton buttonPotvrdi = new JButton("Potvrdi");
		JButton buttonPonisti = new JButton("Poništi");
		ButtonAction.cancelAction(buttonPonisti, this);
		buttonPotvrdi.setEnabled(true);

		JPanel panelSifra = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel lblSifra = new JLabel("Šifra* ");
		JTextField txtSifra = new JTextField();
		txtSifra.setText(p.getSifra());
		lblSifra.setPreferredSize(dim);
		txtSifra.setPreferredSize(dim);
		panelSifra.add(Box.createHorizontalStrut(65));
		panelSifra.add(lblSifra);
		panelSifra.add(txtSifra);
		panelMain.add(panelSifra);

		JPanel panelNaziv = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel lblNaziv = new JLabel("Naziv*");
		JTextField txtNaziv = new JTextField();
		txtNaziv.setText(p.getNaziv());
		lblNaziv.setPreferredSize(dim);
		txtNaziv.setPreferredSize(dim);
		panelNaziv.add(Box.createHorizontalStrut(65));
		panelNaziv.add(lblNaziv);
		panelNaziv.add(txtNaziv);
		panelMain.add(panelNaziv);

		JPanel panelEspb = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel lblEspb = new JLabel("Broj ESPB bodova* ");
		JTextField txtEspb = new JTextField();
		txtEspb.setText(String.valueOf(p.getEspb()));
		lblEspb.setPreferredSize(dim);
		txtEspb.setPreferredSize(dim);
		panelEspb.add(Box.createHorizontalStrut(65));
		panelEspb.add(lblEspb);
		panelEspb.add(txtEspb);
		panelMain.add(panelEspb);

		String[] stringGod = { "I (prva)", "II (druga)", "III (treća)", "IV (četvrta)" };
		JPanel panelGod = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel lblGod = new JLabel("Godina ");

		@SuppressWarnings({ "rawtypes", "unchecked" })
		JComboBox cbGod = new JComboBox(stringGod);
		cbGod.setSelectedItem(p.getGodinaAsString());
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
		cbSem.setSelectedItem(p.getSemestarAsString());
		cbSem.setBackground(new Color(250, 250, 250));
		lblSem.setPreferredSize(dim);
		cbSem.setPreferredSize(dim);
		panelSem.add(Box.createHorizontalStrut(65));
		panelSem.add(lblSem);
		panelSem.add(cbSem);
		panelMain.add(panelSem);

		JPanel panelProf = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel lbl = new JLabel("Profesor");
		
		txtProf = new JTextArea();
		JButton buttonAdd = new JButton();
		JButton buttonDelete = new JButton();
		txtProf.setPreferredSize(new Dimension(100, 20));
		txtProf.setEditable(false);
		buttonAdd.setText("+");
		buttonDelete.setText("-");
		

		if (p.getProfesor() != null) {
			txtProf.setText(p.getProfesor().getIme() + " " + p.getProfesor().getPrezime());
			buttonAdd.setEnabled(false);
			buttonDelete.setEnabled(true);
		} else {
			buttonAdd.setEnabled(true);
			buttonDelete.setEnabled(false);
		}
		
		buttonDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				String[] opcije = { "Da", "Ne" };

				int i = JOptionPane.showOptionDialog(IzmenaPredmeta.this.getParent(),
						"Da li ste sigurni?", "Ukloni profesora",
						JOptionPane.YES_NO_OPTION, JOptionPane.DEFAULT_OPTION, null, opcije, opcije[0]);

				if(i == 0) {
					
					//Predmet p = PredmetBaza.getInstance().getRow(r);
					//String oldId = p.getSifra();
					
					List<Profesor> profesori = ProfesorBaza.getInstance().getProfesore();
					
					for(Profesor pr : profesori) {
						
						if(pr.getBrLicneKarte().equals(p.getProfesor().getBrLicneKarte())) {
							
							for(Predmet prr : pr.getPredmeti()) {
								
								if(prr.getSifra().equals(p.getSifra())) {
									
									pr.getPredmeti().remove(prr);
									break;
								}
								
							}
							
						}
						
					}
					
					p.setProfesor(null);
					txtProf.setText("");
					buttonAdd.setEnabled(true);
					buttonDelete.setEnabled(false);
				}
				
			}
			
			
			
		});

		txtProf.getDocument().addDocumentListener(new DocumentListener() {

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
				if (txtProf.getText().length() != 0) {
					buttonAdd.setEnabled(false);
					buttonDelete.setEnabled(true);
				}
			}

		});

		buttonAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unused")
				DodajProfNaPredmet d = new DodajProfNaPredmet(IzmenaPredmeta.this.getContentPane(), r);
			}

		});

		panelProf.add(Box.createHorizontalStrut(65));
		panelProf.add(lbl);
		panelProf.add(Box.createHorizontalStrut(100));
		panelProf.add(txtProf);
		panelProf.add(buttonAdd);
		panelProf.add(buttonDelete);
		panelMain.add(panelProf);
		/*--------------------------------*/

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
					JOptionPane.showMessageDialog(IzmenaPredmeta.this, "Pogrešno uneta šifra predmeta!", "Greška: ",
							JOptionPane.ERROR_MESSAGE);
				}

				if (exists) {
					txtSifra.setText("");
					JOptionPane.showMessageDialog(IzmenaPredmeta.this, "Šifra predmeta postoji!", "Greška: ",
							JOptionPane.ERROR_MESSAGE);
				}

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
					if (!oldId.equals(txtSifra.getText().toUpperCase())) {
						exists = ProveraSifrePredmeta.checkExists(txtSifra.getText().toUpperCase(),
								PredmetBaza.getInstance().getPredmete());
					}
					if (exists) {
						valid[0] = false;
					}
				}
				if (!pressed)
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
					JOptionPane.showMessageDialog(IzmenaPredmeta.this, "Pogrešno unet naziv predmeta!", "Greška: ",
							JOptionPane.ERROR_MESSAGE);
				}

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
				if (!pressed)
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
					JOptionPane.showMessageDialog(IzmenaPredmeta.this, "Pogrešno uneti ESPB bodovi!", "Greška: ",
							JOptionPane.ERROR_MESSAGE);
				}
				if (less) {
					txtEspb.setText("");
					less = false;
					JOptionPane.showMessageDialog(IzmenaPredmeta.this, "Broj ESPB bodova ne može biti 0!", "Greška: ",
							JOptionPane.ERROR_MESSAGE);
				}
				if (higher) {
					txtEspb.setText("");
					higher = false;
					JOptionPane.showMessageDialog(IzmenaPredmeta.this, "Broj ESPB bodova ne može biti veći od 60!",
							"Greška: ", JOptionPane.ERROR_MESSAGE);
				}

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
				if (!pressed)
					changeLabel(valid[2], "Broj ESPB bodova*", "Broj ESPB bodova", lblEspb);
				buttonPotvrdi.setEnabled(checkValid(valid));
			}

		});

		cbGod.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				buttonPotvrdi.setEnabled(checkValid(valid));

			}

		});

		cbSem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
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
				pressed = true;
				txtEspb.setText(txtEspb.getText().trim());
				txtNaziv.setText(setString(txtNaziv.getText()));
				txtSifra.setText(txtSifra.getText().toUpperCase());

				Predmet p = PredmetBaza.getInstance().getRow(r);

				String semestar = cbSem.getSelectedItem().toString();
				Semestar sem = semestar.equals("Zimski") ? Semestar.Zimski : Semestar.Letnji;
				String god = cbGod.getSelectedItem().toString();
				int godina = 1;
				switch (god) {
				case "I (prva)":
					godina = 1;
					break;
				case "II (druga)":
					godina = 2;
					break;
				case "III (treća)":
					godina = 3;
					break;
				case "IV (četvrta)":
					godina = 4;
					break;
				}

				p.setSifra(txtSifra.getText().trim());
				p.setNaziv(txtNaziv.getText());
				p.setSemestar(sem);
				p.setGodina(godina);
				// p.setProfesor(profesor);
				p.setEspb(Integer.parseInt(txtEspb.getText().trim()));

				PredmetController.getInstance().izmeniPredmet(p, oldId);


				JOptionPane.showMessageDialog(IzmenaPredmeta.this, "Izmena predmeta je uspešno izvršena!");
				dispose();

			}

		});

		panelButton.add(buttonPotvrdi);
		panelButton.add(Box.createHorizontalStrut(35));
		panelButton.add(buttonPonisti);
		panelMain.add(panelButton);

		add(panelMain);
	}

	public static JTextArea get() {
		return txtProf;
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
