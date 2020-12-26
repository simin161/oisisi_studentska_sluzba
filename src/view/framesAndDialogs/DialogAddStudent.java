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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import controller.provere.ProveraAdrese;
import controller.provere.ProveraDatuma;
import controller.provere.ProveraEmaila;
import controller.provere.ProveraGodine;
import controller.provere.ProveraImena;
import controller.provere.ProveraIndeksa;
import controller.provere.ProveraPrezimena;
import model.Student;
import model.baze.StudentBaza;
import model.nabrojiviTipovi.Status;
import view.tabbedPanes.PrikazStudenta;

//https://www.experts-exchange.com/questions/21314578/check-if-multiple-textfields-are-empty.html

public class DialogAddStudent extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4412711478828503212L;

	private boolean enable = false;
	private boolean indExists = false;
	private boolean tooYoung = false;
	private boolean tooYoung1 = false;
	private boolean invalidYear = false;
	private boolean validYearDR = false;
	private boolean higherYear = false;
	private boolean higherYearDR = false;
	private int godRodj = -1;
	private int godUpis = -1;
	private boolean shown = false;

	public DialogAddStudent(Container cont) {

		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int height = (int) (screenSize.height * 0.75 * 0.8);
		int width = (int) (screenSize.width * 0.75 * 0.4);

		/*---Pomocne promenljive za provere---*/
		String[] text = { "", "", "", "", "", "", "", "" };
		boolean[] valid = { false, false, false, false, false, false, false, false };
		List<Student> students = StudentBaza.getInstance().getStudents();

		/*-----------------------------------*/

		setTitle("Dodavanje studenta");
		setSize(width, height);
		setLocationRelativeTo(cont);
		setModal(true);
		setResizable(false);
		Dimension dim = new Dimension(150, 20);

		JPanel panelButton = new JPanel();
		BoxLayout button = new BoxLayout(panelButton, BoxLayout.X_AXIS);
		panelButton.setLayout(button);
		JButton buttonPotvrdi = new JButton("Potvrdi");
		JButton buttonPonisti = new JButton("Poništi");
		ButtonAction.cancelAction(buttonPonisti, this);
		buttonPotvrdi.setEnabled(false);

		JPanel panelMain = new JPanel();
		BoxLayout b = new BoxLayout(panelMain, BoxLayout.Y_AXIS);
		panelMain.setLayout(b);

		JPanel panelName = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel labelName = new JLabel("Ime* ");
		labelName.setForeground(Color.red);
		JTextField tFName = new JTextField();
		labelName.setPreferredSize(dim);
		tFName.setPreferredSize(dim);
		panelName.add(Box.createHorizontalStrut(65));
		panelName.add(labelName);
		panelName.add(tFName);
		panelMain.add(panelName);

		JPanel panelPrezime = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel labelPrezime = new JLabel("Prezime* ");
		JTextField tFPrezime = new JTextField();
		labelPrezime.setForeground(Color.red);
		labelPrezime.setPreferredSize(dim);
		tFPrezime.setPreferredSize(dim);
		panelPrezime.add(Box.createHorizontalStrut(65));
		panelPrezime.add(labelPrezime);
		panelPrezime.add(tFPrezime);
		panelMain.add(panelPrezime);

		JPanel panelDatumR = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel labelDatumR = new JLabel("Datum rođenja* ");
		labelDatumR.setForeground(Color.red);
		JTextField tfDatumR = new JTextField();
		labelDatumR.setPreferredSize(dim);
		tfDatumR.setPreferredSize(dim);
		panelDatumR.add(Box.createHorizontalStrut(65));
		panelDatumR.add(labelDatumR);
		panelDatumR.add(tfDatumR);
		panelMain.add(panelDatumR);

		JPanel panelAdr = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel labelAdr = new JLabel("Adresa stanovanja* ");
		labelAdr.setForeground(Color.red);
		JTextField tFAdr = new JTextField();
		labelAdr.setPreferredSize(dim);
		tFAdr.setPreferredSize(dim);
		panelAdr.add(Box.createHorizontalStrut(65));
		panelAdr.add(labelAdr);
		panelAdr.add(tFAdr);
		panelMain.add(panelAdr);

		JPanel panelBr = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel labelBr = new JLabel("Broj telefona* ");
		JTextField tFBr = new JTextField();
		labelBr.setForeground(Color.red);
		labelBr.setPreferredSize(dim);
		tFBr.setPreferredSize(dim);
		panelBr.add(Box.createHorizontalStrut(65));
		panelBr.add(labelBr);
		panelBr.add(tFBr);
		panelMain.add(panelBr);

		JPanel panelEmail = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel labelEmail = new JLabel("E-mail adresa* ");
		labelEmail.setForeground(Color.red);
		JTextField tFEmail = new JTextField();
		labelEmail.setPreferredSize(dim);
		tFEmail.setPreferredSize(dim);
		panelEmail.add(Box.createHorizontalStrut(65));
		panelEmail.add(labelEmail);
		panelEmail.add(tFEmail);
		panelMain.add(panelEmail);

		JPanel panelBrI = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel labelBrI = new JLabel("Broj indeksa* ");
		labelBrI.setForeground(Color.red);
		JTextField tFBrI = new JTextField();
		labelBrI.setPreferredSize(dim);
		tFBrI.setPreferredSize(dim);
		panelBrI.add(Box.createHorizontalStrut(65));
		panelBrI.add(labelBrI);
		panelBrI.add(tFBrI);
		panelMain.add(panelBrI);

		JPanel panelGodU = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel labelGodU = new JLabel("Godina upisa* ");
		labelGodU.setForeground(Color.red);
		JTextField tFGodU = new JTextField();
		labelGodU.setPreferredSize(dim);
		tFGodU.setPreferredSize(dim);
		panelGodU.add(Box.createHorizontalStrut(65));
		panelGodU.add(labelGodU);
		panelGodU.add(tFGodU);
		panelMain.add(panelGodU);

		// https*//docs.oracle.com/javase/tutorial/uiswing/components/combobox.html
		String[] stringTGodS = { "I (prva)", "II (druga)", "III (treća)", "IV (četvrta)" };
		JPanel panelTGodS = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel labelTGodS = new JLabel("Trenutna godina studija ");

		@SuppressWarnings({ "rawtypes", "unchecked" })
		JComboBox cBTGodS = new JComboBox(stringTGodS);
		cBTGodS.setBackground(new Color(250, 250, 250));
		labelTGodS.setPreferredSize(dim);
		cBTGodS.setPreferredSize(dim);
		panelTGodS.add(Box.createHorizontalStrut(65));
		panelTGodS.add(labelTGodS);
		panelTGodS.add(cBTGodS);
		panelMain.add(panelTGodS);

		String[] stringFin = { "Budžet", "Samofinansiranje" };
		JPanel panelFin = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel labelFin = new JLabel("Način finansiranja ");

		@SuppressWarnings({ "rawtypes", "unchecked" })
		JComboBox cBFin = new JComboBox(stringFin);
		cBFin.setBackground(new Color(250, 250, 250));
		labelFin.setPreferredSize(dim);
		cBFin.setPreferredSize(dim);
		panelFin.add(Box.createHorizontalStrut(65));
		panelFin.add(labelFin);
		panelFin.add(cBFin);
		panelMain.add(panelFin);
		panelButton.add(buttonPotvrdi);
		panelButton.add(Box.createHorizontalStrut(35));
		panelButton.add(buttonPonisti);
		panelMain.add(panelButton);

		tFName.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void focusLost(FocusEvent e) {
				text[0] = tFName.getText().trim();

				if (!text[0].equals("")) {
					valid[0] = ProveraImena.proveriIme(tFName.getText());
				} else {
					valid[0] = false;
					tFName.setText("");
				}

				if (!valid[0] && !text[0].equals("")) {
					tFName.setText("");
					text[0] = "";
					// enable = false;

					JOptionPane.showMessageDialog(DialogAddStudent.this,
							"Pogrešno uneto ime! (Primer ispravnog unosa: Petar)", "Greška: ",
							JOptionPane.ERROR_MESSAGE);

				}
				tFName.setText(setString(tFName.getText()));

			}

		});

		tFName.getDocument().addDocumentListener(new DocumentListener() {

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
				valid[0] = ProveraImena.proveriIme(tFName.getText());
				if (tFName.getText().trim().length() == 0) {
					valid[0] = false;
				}
				changeLabel(valid[0], "Ime*", "Ime", labelName);
				buttonPotvrdi.setEnabled(checkValid(valid) && !shown);
			}

		});

		tFPrezime.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void focusLost(FocusEvent e) {
				text[1] = tFPrezime.getText().trim();

				if (!text[1].equals(""))
					valid[1] = ProveraPrezimena.proveriPrezime(tFPrezime.getText());
				else {
					valid[1] = false;
					tFPrezime.setText("");
				}

				tFPrezime.setText(setString(tFPrezime.getText()));

				if (!valid[1] && !text[1].equals("")) {
					tFPrezime.setText("");
					text[1] = "";
					enable = false;
					JOptionPane.showMessageDialog(DialogAddStudent.this,
							"Pogrešno uneto prezime! (Primer ispravnog unosa: Petrović)", "Greška: ",
							JOptionPane.ERROR_MESSAGE);
				}
			}

		});

		tFPrezime.getDocument().addDocumentListener(new DocumentListener() {

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
				valid[1] = ProveraPrezimena.proveriPrezime(tFPrezime.getText());
				changeLabel(valid[1], "Prezime*", "Prezime", labelPrezime);
				buttonPotvrdi.setEnabled(checkValid(valid) && !shown);
			}

		});

		tfDatumR.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				if (shown) {
					tfDatumR.setText("");
					text[2] = "";
					valid[2] = false;
					shown = false;
					godRodj = -1;

					if (godUpis != -1) {
						valid[7] = true;
						invalidYear = false;
					}
				}

			}

			@Override
			public void focusLost(FocusEvent e) {
				text[2] = tfDatumR.getText().trim();

				if (!text[2].equals("")) {
					valid[2] = ProveraDatuma.proveriDatum(tfDatumR.getText());
				} else {
					tfDatumR.setText("");
					valid[2] = false;
					godRodj = -1;
					tooYoung = true;
				}
				godRodj = -1;

				if (valid[2]) {
					if (ProveraGodine.proveri(text[2], 0)) {

						String[] datum = text[2].split("/");
						godRodj = Integer.parseInt(datum[2]);

						DateTimeFormatter dtf = DateTimeFormatter.ofPattern("YYYY");
						LocalDateTime now = LocalDateTime.now();
						String trenutnaGod = dtf.format(now);
						int trGod = Integer.parseInt(trenutnaGod);

						tooYoung = false;
						if (trGod - godRodj < 18) {
							tooYoung = true;
							tfDatumR.setText("");
							text[2] = "";
							godRodj = -1;

							JOptionPane.showMessageDialog(DialogAddStudent.this, "Osoba mora imati više od 18 godina!",
									"Greška: ", JOptionPane.ERROR_MESSAGE);
						} else if (godUpis != -1) {
							if (godRodj >= godUpis) {
								invalidYear = true;
								shown = true;
								JOptionPane.showMessageDialog(DialogAddStudent.this,
										"Godina upisa ne može biti manja ili jednaka od godine rođenja!\nProverite godinu upisa ili godinu rođenja!!",
										"Greška: ", JOptionPane.ERROR_MESSAGE);

							} else if (godUpis - godRodj < 18) {

								invalidYear = true;
								shown = true;
								int pom = godRodj + 18;
								JOptionPane.showMessageDialog(DialogAddStudent.this,
										"Godina upisa mora biti minimalno " + pom
												+ "!\nProverite godinu upisa ili godinu rođenja!",
										"Greška: ", JOptionPane.ERROR_MESSAGE);

							}
						}

					} else {
						valid[2] = false;
						tooYoung = true;
						tfDatumR.setText("");
						text[2] = "";
						godRodj = -1;

						JOptionPane.showMessageDialog(DialogAddStudent.this, "Datum nije validan", "Greška: ",
								JOptionPane.ERROR_MESSAGE);
					}
				}

				if (!valid[2] && !text[2].equals("")) {
					tfDatumR.setText("");
					text[2] = "";
					tooYoung = true;
					enable = false;

					JOptionPane.showMessageDialog(DialogAddStudent.this,
							"Pogrešno unet datum! Ispravan unos : DD/MM/YYYY", "Greška: ", JOptionPane.ERROR_MESSAGE);

				} else {

					setEnable(text, valid);
				}

				buttonPotvrdi.setEnabled(enable && !indExists && !tooYoung && !invalidYear);
			}

		});

		tfDatumR.getDocument().addDocumentListener(new DocumentListener() {

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
				valid[2] = ProveraDatuma.proveriDatum(tfDatumR.getText());
				text[2] = tfDatumR.getText().trim();
				validYearDR = false;
				tooYoung = false;
				tooYoung1 = false;
				invalidYear = false;
				higherYearDR = false;

				if (valid[2]) {
					validYearDR = ProveraGodine.proveri(text[2], 0);
					if (validYearDR) {

						String[] datum = text[2].split("/");
						godRodj = Integer.parseInt(datum[2]);

						DateTimeFormatter dtf = DateTimeFormatter.ofPattern("YYYY");
						LocalDateTime now = LocalDateTime.now();
						String trenutnaGod = dtf.format(now);
						int trGod = Integer.parseInt(trenutnaGod);

						if (trGod - godRodj < 18) {
							tooYoung = true;
							valid[2] = false;

						} else if (godUpis != -1) {
							if (godRodj >= godUpis) {
								invalidYear = true;
								higherYearDR = true;
								valid[2] = false;
							} else if (godUpis - godRodj < 18) {
								tooYoung1 = true;
								invalidYear = true;
								valid[2] = false;
							}
						}

					} else {
						valid[2] = false;
						tooYoung = true;
					}
				}

				changeLabel(valid[2], "Datum rođenja*", "Datum rođenja", labelDatumR);
				changeLabel(valid[7] && !invalidYear && !tooYoung1 && !shown, "Godina upisa*", "Godina upisa",
						labelGodU);
				buttonPotvrdi.setEnabled(checkValid(valid) && !invalidYear && !tooYoung && !shown);

			}

		});

		tFAdr.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void focusLost(FocusEvent e) {
				text[3] = tFAdr.getText().trim();

				if (!text[3].equals(""))
					valid[3] = ProveraAdrese.proveriAdresu(tFAdr.getText());
				else {
					valid[3] = false;
					tFAdr.setText("");
				}

				if (!valid[3] && !text[3].equals("")) {
					tFAdr.setText("");
					text[3] = "";
					enable = false;

					JOptionPane.showMessageDialog(DialogAddStudent.this,
							"Pogrešno uneta adresa! Ispravan unos: ulica i broj, mesto", "Greška: ",
							JOptionPane.ERROR_MESSAGE);

				}
			}

		});

		tFAdr.getDocument().addDocumentListener(new DocumentListener() {

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
				valid[3] = ProveraAdrese.proveriAdresu(tFAdr.getText());
				changeLabel(valid[3], "Adresa stanovanja*", "Adresa stanovanja", labelAdr);
				buttonPotvrdi.setEnabled(checkValid(valid) && !shown);
			}

		});

		tFBr.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void focusLost(FocusEvent e) {
				text[4] = tFBr.getText().trim();
				Pattern pattern = Pattern.compile("[0-9]{3}/[0-9]{3,5}-[0-9]{3,5}");
				Matcher matcher = pattern.matcher(tFBr.getText());

				if (!text[4].equals("")) {
					valid[4] = matcher.matches();
				} else {
					tFBr.setText("");
					valid[4] = false;
				}

				if (!valid[4] && !text[4].equals("")) {
					tFBr.setText("");
					text[4] = "";
					enable = false;

					JOptionPane.showMessageDialog(DialogAddStudent.this,
							"Pogrešno unet broj telefona! (Primer: 123/123-123)", "Greška: ",
							JOptionPane.ERROR_MESSAGE);

				}
			}

		});

		tFBr.getDocument().addDocumentListener(new DocumentListener() {

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
				Pattern pattern = Pattern.compile("[0-9]{3}/[0-9]{3,5}-[0-9]{3,5}");
				Matcher matcher = pattern.matcher(tFBr.getText());
				valid[4] = matcher.matches();
				changeLabel(valid[4], "Broj telefona*", "Broj telefona", labelBr);
				buttonPotvrdi.setEnabled(checkValid(valid) && !shown);
			}

		});

		tFEmail.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void focusLost(FocusEvent e) {
				text[5] = tFEmail.getText().trim();

				if (!text[5].equals(""))
					valid[5] = ProveraEmaila.proveriEmail(tFEmail.getText());
				else {
					valid[5] = false;
					tFEmail.setText("");
				}

				if (!valid[5] && !text[5].equals("")) {
					tFEmail.setText("");
					text[5] = "";
					enable = false;

					JOptionPane.showMessageDialog(DialogAddStudent.this, "Pogrešno uneta e-mail adresa!", "Greška: ",
							JOptionPane.ERROR_MESSAGE);

				}
			}

		});

		tFEmail.getDocument().addDocumentListener(new DocumentListener() {

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
				valid[5] = ProveraEmaila.proveriEmail(tFEmail.getText());
				changeLabel(valid[5], "E-mail adresa*", "E-mail adresa", labelEmail);
				buttonPotvrdi.setEnabled(checkValid(valid) && !shown);
			}

		});

		tFBrI.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void focusLost(FocusEvent e) {
				text[6] = tFBrI.getText().trim();

				if (!text[6].equals("")) {
					valid[6] = ProveraIndeksa.proveriIndeks(tFBrI.getText());
				} else {
					valid[6] = false;
					tFBrI.setText("");
				}

				if (indExists) {
					tFBrI.setText("");
					text[6] = "";
					valid[6] = false;
					JOptionPane.showMessageDialog(DialogAddStudent.this, "Broj indeksa postoji!", "Greška: ",
							JOptionPane.ERROR_MESSAGE);
				}

				if (!valid[6] && !text[6].equals("")) {
					tFBrI.setText("");
					text[6] = "";
					enable = false;
					indExists = true;

					JOptionPane.showMessageDialog(DialogAddStudent.this, "Pogrešno unet broj indeksa!", "Greška: ",
							JOptionPane.ERROR_MESSAGE);
				}
				tFBrI.setText(tFBrI.getText().toUpperCase());
			}

		});

		tFBrI.getDocument().addDocumentListener(new DocumentListener() {

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
				valid[6] = ProveraIndeksa.proveriIndeks(tFBrI.getText());

				if (valid[6]) {
					indExists = ProveraIndeksa.checkExists(students, tFBrI.getText().trim().toUpperCase());
				}

				changeLabel(valid[6] && !indExists, "Broj indeksa*", "Broj indeksa", labelBrI);
				buttonPotvrdi.setEnabled(checkValid(valid) && !shown && !indExists);
			}

		});

		tFGodU.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				if (shown) {
					tFGodU.setText("");
					text[7] = "";
					shown = false;
					godUpis = -1;
				}

			}

			@Override
			public void focusLost(FocusEvent e) {
				text[7] = tFGodU.getText().trim();
				Pattern pattern = Pattern.compile("[0-9]{4,4}"); // NAPOMENA: godina jos dugo nece imati vise od 4 cifre
				Matcher matcher = pattern.matcher(tFGodU.getText());

				if (!text[7].equals("")) {
					valid[7] = matcher.matches();
				} else {
					valid[7] = false;
					tFGodU.setText("");
				}

				if (valid[7]) {
					godUpis = Integer.parseInt(text[7]);
					invalidYear = false;
					DateTimeFormatter dtf = DateTimeFormatter.ofPattern("YYYY");
					LocalDateTime now = LocalDateTime.now();
					String trenutnaGod = dtf.format(now);
					int trGod = Integer.parseInt(trenutnaGod);

					if (godUpis > trGod) {
						tFGodU.setText("");
						text[7] = "";
						invalidYear = true;
						godUpis = -1;
						valid[7] = false;

						JOptionPane.showMessageDialog(DialogAddStudent.this,
								"Godina upisa ne može biti veća od trenutne godine!", "Greška: ",
								JOptionPane.ERROR_MESSAGE);

					} else if (godRodj != -1) {
						if (godUpis <= godRodj) {

							invalidYear = true;
							shown = true;
							JOptionPane.showMessageDialog(DialogAddStudent.this,
									"Godina upisa ne može biti manja ili jednaka od godine rođenja!\nProverite godinu upisa ili godinu rođenja!",
									"Greška: ", JOptionPane.ERROR_MESSAGE);

						} else if (godUpis - godRodj < 18) {

							invalidYear = true;
							shown = true;
							int pom = godRodj + 18;
							JOptionPane.showMessageDialog(DialogAddStudent.this,
									"Godina upisa mora biti minimalno " + pom + "!", "Greška: ",
									JOptionPane.ERROR_MESSAGE);

						}
					}

				}

				if (!valid[7] && !text[7].equals("")) {
					tFGodU.setText("");
					text[7] = "";
					godUpis = -1;
					enable = false;
					invalidYear = true;
					JOptionPane.showMessageDialog(DialogAddStudent.this,
							"Pogrešno uneta godina upisa! Ispravan unos: YYYY", "Greška: ", JOptionPane.ERROR_MESSAGE);

				} else {

					setEnable(text, valid);
				}
			}

		});

		tFGodU.getDocument().addDocumentListener(new DocumentListener() {

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
				Pattern pattern = Pattern.compile("[0-9]{4,4}"); // NAPOMENA: godina jos dugo nece imati vise od 4 cifre
				Matcher matcher = pattern.matcher(tFGodU.getText());
				valid[7] = matcher.matches();
				tooYoung = false;
				invalidYear = false;
				higherYear = false;

				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("YYYY");
				LocalDateTime now = LocalDateTime.now();
				String trenutnaGod = dtf.format(now);
				int trGod = Integer.parseInt(trenutnaGod);
				if (valid[7]) {
					godUpis = Integer.parseInt(tFGodU.getText().trim());
					if (godUpis > trGod) {
						invalidYear = true;
						higherYear = true;
						godUpis = -1;
						valid[7] = false;

					} else if (godRodj != -1) {
						if (godUpis <= godRodj) {
							valid[7] = false;
							invalidYear = true;
							tooYoung = true;

						} else if (godUpis - godRodj < 18) {

							valid[7] = false;
							tooYoung = true;

						}
					}
				}
				changeLabel(valid[7], "Godina upisa*", "Godina upisa", labelGodU);
				changeLabel(valid[2] && !tooYoung && !shown, "Datum rođenja*", "Datum rođenja",
						labelDatumR);
				buttonPotvrdi.setEnabled(checkValid(valid) && !invalidYear && !tooYoung && !shown);
			}

		});

		// https://stackoverflow.com/questions/14902410/switching-jtextfields-by-pressing-enter-key
		ActionChangeTField.changeTField(tFName, tFPrezime);
		ActionChangeTField.changeTField(tFPrezime, tfDatumR);
		ActionChangeTField.changeTField(tfDatumR, tFAdr);
		ActionChangeTField.changeTField(tFAdr, tFBr);
		ActionChangeTField.changeTField(tFBr, tFEmail);
		ActionChangeTField.changeTField(tFEmail, tFBrI);
		ActionChangeTField.changeTField(tFBrI, tFGodU);
		tFGodU.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cBTGodS.requestFocusInWindow();

			}

		});

		buttonPotvrdi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!indExists && !tooYoung && !invalidYear) {
					String status = cBFin.getSelectedItem().toString();
					Status s = status.equals("Budžet") ? Status.B : Status.S;
					String trGod = cBTGodS.getSelectedItem().toString();
					int trenutnaGodina = 1;
					switch (trGod) {
					case "I (prva)":
						trenutnaGodina = 1;
						break;
					case "II (druga)":
						trenutnaGodina = 2;
						break;
					case "III (treća)":
						trenutnaGodina = 3;
						break;
					case "IV (četvrta)":
						trenutnaGodina = 4;
						break;
					}

					Date date1 = null;
					try {
						date1 = new SimpleDateFormat("dd/MM/yyyy").parse(tfDatumR.getText());
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					Student st = new Student(tFPrezime.getText(), tFName.getText(), date1, tFAdr.getText(),
							tFBr.getText(), tFEmail.getText(), tFBrI.getText().toUpperCase(), godUpis, trenutnaGodina,
							s, 0, null, null);

					students.add(st);
					StudentBaza.getInstance().setStudents(students);
					PrikazStudenta.getInstance().update();
					JOptionPane.showMessageDialog(DialogAddStudent.this, "Unos novog studenta je uspešno izvršen!");
					dispose();
				}

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

	private void setEnable(String[] text, boolean[] valid) {
		enable = true;
		for (int i = 0; i < 8; ++i) {
			if (text[i].equals("") || !valid[i]) {
				enable = false;
				break;
			}
		}
	}

	private boolean checkValid(boolean[] valid) {
		boolean retVal = true;
		for (int i = 0; i < 8; ++i) {
			if (!valid[i]) {
				retVal = false;
				break;
			}
		}

		return retVal;
	}

}
