package view.izmeneDialog;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import controller.ActionChangeTField;
import controller.provere.ProveraAdrese;
import controller.provere.ProveraDatuma;
import controller.provere.ProveraEmaila;
import controller.provere.ProveraGodine;
import controller.provere.ProveraImena;
import controller.provere.ProveraIndeksa;
import controller.provere.ProveraPrezimena;
import controller.student.StudentController;
import model.Student;
import model.baze.StudentBaza;
import model.nabrojiviTipovi.Status;
import view.tabbedPanes.PrikazStudenta;

public class StudentInfo extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2210595203182573372L;

	private boolean indExists = false;
	private boolean tooYoung = false;
	private boolean invalidYear = false;
	private boolean validYearDR = false;
	private int godRodj = -1;
	private int godUpis = -1;
	private boolean shown = false;

	public StudentInfo() {

		boolean[] valid = { true, true, true, true, true, true, true, true };

		BoxLayout b = new BoxLayout(this, BoxLayout.Y_AXIS);

		Dimension dim = new Dimension(165, 20);

		setLayout(b);
		JPanel panelButton = new JPanel();
		BoxLayout button = new BoxLayout(panelButton, BoxLayout.X_AXIS);
		panelButton.setLayout(button);
		JButton buttonPotvrdi = new JButton("Potvrdi");
		JButton buttonPonisti = new JButton("Poništi");

		buttonPotvrdi.setEnabled(false);
		Student s = StudentBaza.getInstance().getRow(PrikazStudenta.getInstance().getSelectedRow());
		String oldId = s.getBrIndeksa();
		JPanel panelName = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel labelName = new JLabel("Ime* ");
		JTextField tFName = new JTextField();
		tFName.setText(s.getIme());
		labelName.setPreferredSize(dim);
		tFName.setPreferredSize(dim);
		panelName.add(Box.createHorizontalStrut(165));
		panelName.add(labelName);
		panelName.add(tFName);
		add(panelName);

		JPanel panelPrezime = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel labelPrezime = new JLabel("Prezime* ");
		JTextField tFPrezime = new JTextField();
		tFPrezime.setText(s.getPrezime());
		labelPrezime.setPreferredSize(dim);
		tFPrezime.setPreferredSize(dim);
		panelPrezime.add(Box.createHorizontalStrut(165));
		panelPrezime.add(labelPrezime);
		panelPrezime.add(tFPrezime);
		add(panelPrezime);

		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String strDate = dateFormat.format(s.getDatumRodjenja());
		JPanel panelDatumR = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel labelDatumR = new JLabel("Datum rođenja* ");
		JTextField tfDatumR = new JTextField();
		tfDatumR.setText(strDate);
		labelDatumR.setPreferredSize(dim);
		tfDatumR.setPreferredSize(dim);
		panelDatumR.add(Box.createHorizontalStrut(165));
		panelDatumR.add(labelDatumR);
		panelDatumR.add(tfDatumR);
		add(panelDatumR);

		JPanel panelAdr = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel labelAdr = new JLabel("Adresa stanovanja* ");
		JTextField tFAdr = new JTextField();
		tFAdr.setText(s.getAdresaStanovanja());
		labelAdr.setPreferredSize(dim);
		tFAdr.setPreferredSize(dim);
		panelAdr.add(Box.createHorizontalStrut(165));
		panelAdr.add(labelAdr);
		panelAdr.add(tFAdr);
		add(panelAdr);

		JPanel panelBr = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel labelBr = new JLabel("Broj telefona* ");
		JTextField tFBr = new JTextField();
		tFBr.setText(s.getTelefon());
		labelBr.setPreferredSize(dim);
		tFBr.setPreferredSize(dim);
		panelBr.add(Box.createHorizontalStrut(165));
		panelBr.add(labelBr);
		panelBr.add(tFBr);
		add(panelBr);

		JPanel panelEmail = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel labelEmail = new JLabel("E-mail adresa* ");
		JTextField tFEmail = new JTextField();
		tFEmail.setText(s.getEmail());
		labelEmail.setPreferredSize(dim);
		tFEmail.setPreferredSize(dim);
		panelEmail.add(Box.createHorizontalStrut(165));
		panelEmail.add(labelEmail);
		panelEmail.add(tFEmail);
		add(panelEmail);

		JPanel panelBrI = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel labelBrI = new JLabel("Broj indeksa* ");
		JTextField tFBrI = new JTextField();
		tFBrI.setText(s.getBrIndeksa());
		labelBrI.setPreferredSize(dim);
		tFBrI.setPreferredSize(dim);
		panelBrI.add(Box.createHorizontalStrut(165));
		panelBrI.add(labelBrI);
		panelBrI.add(tFBrI);
		add(panelBrI);

		JPanel panelGodU = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel labelGodU = new JLabel("Godina upisa* ");
		JTextField tFGodU = new JTextField();
		tFGodU.setText(String.valueOf(s.getGodinaUpisa()));
		labelGodU.setPreferredSize(dim);
		tFGodU.setPreferredSize(dim);
		panelGodU.add(Box.createHorizontalStrut(165));
		panelGodU.add(labelGodU);
		panelGodU.add(tFGodU);
		add(panelGodU);

		// https*//docs.oracle.com/javase/tutorial/uiswing/components/combobox.html
		String[] stringTGodS = { "I (prva)", "II (druga)", "III (treća)", "IV (četvrta)" };
		JPanel panelTGodS = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel labelTGodS = new JLabel("Trenutna godina studija ");

		@SuppressWarnings({ "rawtypes", "unchecked" })
		JComboBox cBTGodS = new JComboBox(stringTGodS);
		cBTGodS.setBackground(new Color(250, 250, 250));
		cBTGodS.setSelectedItem(s.getTrenutnaGodinaAsString());
		labelTGodS.setPreferredSize(dim);
		cBTGodS.setPreferredSize(dim);
		panelTGodS.add(Box.createHorizontalStrut(165));
		panelTGodS.add(labelTGodS);
		panelTGodS.add(cBTGodS);
		add(panelTGodS);

		String[] stringFin = { "Budžet", "Samofinansiranje" };
		JPanel panelFin = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel labelFin = new JLabel("Način finansiranja ");

		@SuppressWarnings({ "rawtypes", "unchecked" })
		JComboBox cBFin = new JComboBox(stringFin);
		cBFin.setBackground(new Color(250, 250, 250));
		cBFin.setSelectedItem(s.getStatusAsString());
		labelFin.setPreferredSize(dim);
		cBFin.setPreferredSize(dim);
		panelFin.add(Box.createHorizontalStrut(165));
		panelFin.add(labelFin);
		panelFin.add(cBFin);
		add(panelFin);
		panelButton.add(buttonPotvrdi);
		panelButton.add(Box.createHorizontalStrut(35));
		panelButton.add(buttonPonisti);
		add(panelButton);
		
		String[] datum = tfDatumR.getText().trim().split("/");
		godRodj = Integer.parseInt(datum[2]);
		godUpis = Integer.parseInt(tFGodU.getText().trim());

		tFName.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void focusLost(FocusEvent e) {

				if (!tFName.getText().trim().equals("")) {
					valid[0] = ProveraImena.proveriIme(tFName.getText());
				} else {
					valid[0] = false;
					tFName.setText("");
				}

				if (!valid[0] && !tFName.getText().trim().equals("")) {
					tFName.setText("");

					JOptionPane.showMessageDialog(StudentInfo.this,
							"Pogrešno uneto ime! (Primer ispravnog unosa: Petar)", "Greška: ",
							JOptionPane.ERROR_MESSAGE);

				}

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

				if (!tFPrezime.getText().trim().equals(""))
					valid[1] = ProveraPrezimena.proveriPrezime(tFPrezime.getText());
				else {
					valid[1] = false;
					tFPrezime.setText("");
				}

				if (!valid[1] && !tFPrezime.getText().trim().equals("")) {
					tFPrezime.setText("");
					JOptionPane.showMessageDialog(StudentInfo.this,
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

				if (!tfDatumR.getText().trim().equals("")) {
					valid[2] = ProveraDatuma.proveriDatum(tfDatumR.getText());
				} else {
					tfDatumR.setText("");
					valid[2] = false;
					godRodj = -1;
					tooYoung = true;
				}
				godRodj = -1;

				if (valid[2]) {
					if (ProveraGodine.proveri(tfDatumR.getText().trim(), 0)) {

						String[] datum = tfDatumR.getText().trim().split("/");
						godRodj = Integer.parseInt(datum[2]);

						DateTimeFormatter dtf = DateTimeFormatter.ofPattern("YYYY");
						LocalDateTime now = LocalDateTime.now();
						String trenutnaGod = dtf.format(now);
						int trGod = Integer.parseInt(trenutnaGod);

						tooYoung = false;
						if (trGod - godRodj < 18) {
							tooYoung = true;
							tfDatumR.setText("");
							godRodj = -1;

							JOptionPane.showMessageDialog(StudentInfo.this, "Osoba mora imati više od 18 godina!",
									"Greška: ", JOptionPane.ERROR_MESSAGE);
						} else if (godUpis != -1) {
							if (godRodj >= godUpis) {
								invalidYear = true;
								shown = true;
								JOptionPane.showMessageDialog(StudentInfo.this,
										"Godina upisa ne može biti manja ili jednaka od godine rođenja!\nProverite godinu upisa ili godinu rođenja!!",
										"Greška: ", JOptionPane.ERROR_MESSAGE);

							} else if (godUpis - godRodj < 18) {

								invalidYear = true;
								shown = true;
								int pom = godRodj + 18;
								JOptionPane.showMessageDialog(StudentInfo.this,
										"Godina upisa mora biti minimalno " + pom
												+ "!\nProverite godinu upisa ili godinu rođenja!",
										"Greška: ", JOptionPane.ERROR_MESSAGE);

							}
						}

					} else {
						valid[2] = false;
						tooYoung = true;
						tfDatumR.setText("");
						godRodj = -1;

						JOptionPane.showMessageDialog(StudentInfo.this, "Datum nije validan", "Greška: ",
								JOptionPane.ERROR_MESSAGE);
					}
				}

				if (!valid[2] && !tfDatumR.getText().trim().equals("")) {
					tfDatumR.setText("");
					tooYoung = true;

					JOptionPane.showMessageDialog(StudentInfo.this, "Pogrešno unet datum! Ispravan unos : DD/MM/YYYY",
							"Greška: ", JOptionPane.ERROR_MESSAGE);

				}
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
				validYearDR = false;
				tooYoung = false;
				invalidYear = false;
				boolean tooYoung1 = false;
				if (valid[2]) {
					validYearDR = ProveraGodine.proveri(tfDatumR.getText().trim(), 0);
					if (validYearDR) {

						String[] datum = tfDatumR.getText().trim().split("/");
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
								valid[2] = false;
							} else if (godUpis - godRodj < 18) {
								invalidYear = true;
								tooYoung1 = true;
								valid[2] = false;
							}
						}

					} else {
						valid[2] = false;
						tooYoung = true;
					}
				}
				changeLabel(valid[7] && !invalidYear && !tooYoung1 && !shown, "Godina upisa*", "Godina upisa*",
						labelGodU);
				changeLabel(valid[2], "Datum rođenja*", "Datum rođenja", labelDatumR);
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

				if (!tFAdr.getText().trim().equals(""))
					valid[3] = ProveraAdrese.proveriAdresu(tFAdr.getText());
				else {
					valid[3] = false;
					tFAdr.setText("");
				}

				if (!valid[3] && !tFAdr.getText().trim().equals("")) {
					tFAdr.setText("");

					JOptionPane.showMessageDialog(StudentInfo.this,
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
				Pattern pattern = Pattern.compile("[0-9]{3}/[0-9]{3,5}-[0-9]{3,5}");
				Matcher matcher = pattern.matcher(tFBr.getText());

				if (!tFBr.getText().trim().equals("")) {
					valid[4] = matcher.matches();
				} else {
					tFBr.setText("");
					valid[4] = false;
				}

				if (!valid[4] && !tFBr.getText().trim().equals("")) {
					tFBr.setText("");
					
					JOptionPane.showMessageDialog(StudentInfo.this,
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

				if (!tFEmail.getText().trim().equals(""))
					valid[5] = ProveraEmaila.proveriEmail(tFEmail.getText());
				else {
					valid[5] = false;
					tFEmail.setText("");
				}

				if (!valid[5] && !tFEmail.getText().trim().equals("")) {
					tFEmail.setText("");

					JOptionPane.showMessageDialog(StudentInfo.this, "Pogrešno uneta e-mail adresa!", "Greška: ",
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
				
				if (!tFBrI.getText().trim().equals("")) {
					valid[6] = ProveraIndeksa.proveriIndeks(tFBrI.getText());
				} else {
					valid[6] = false;
					tFBrI.setText("");
				}

				if (indExists) {
					tFBrI.setText("");
					valid[6] = false;
					JOptionPane.showMessageDialog(StudentInfo.this, "Broj indeksa postoji!", "Greška: ",
							JOptionPane.ERROR_MESSAGE);
				}

				if (!valid[6] && !tFBrI.getText().trim().equals("")) {
					tFBrI.setText("");
					indExists = true;

					JOptionPane.showMessageDialog(StudentInfo.this, "Pogrešno unet broj indeksa!", "Greška: ",
							JOptionPane.ERROR_MESSAGE);
				}
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
					if (!tFBrI.getText().trim().toUpperCase().equals(StudentBaza.getInstance()
							.getRow(PrikazStudenta.getInstance().getSelectedRow()).getBrIndeksa()))
						indExists = ProveraIndeksa.checkExists(StudentBaza.getInstance().getStudents(),
								tFBrI.getText().trim().toUpperCase());
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
					shown = false;
					godUpis = -1;
				}

			}

			@Override
			public void focusLost(FocusEvent e) {
				Pattern pattern = Pattern.compile("[0-9]{4,4}"); // NAPOMENA: godina jos dugo nece imati vise od 4 cifre
				Matcher matcher = pattern.matcher(tFGodU.getText());

				if (!tFGodU.getText().trim().equals("")) {
					valid[7] = matcher.matches();
				} else {
					valid[7] = false;
					tFGodU.setText("");
				}

				if (valid[7]) {
					godUpis = Integer.parseInt(tFGodU.getText().trim());
					invalidYear = false;
					DateTimeFormatter dtf = DateTimeFormatter.ofPattern("YYYY");
					LocalDateTime now = LocalDateTime.now();
					String trenutnaGod = dtf.format(now);
					int trGod = Integer.parseInt(trenutnaGod);

					if (godUpis > trGod) {
						tFGodU.setText("");
						invalidYear = true;
						godUpis = -1;
						valid[7] = false;

						JOptionPane.showMessageDialog(StudentInfo.this,
								"Godina upisa ne može biti veća od trenutne godine!", "Greška: ",
								JOptionPane.ERROR_MESSAGE);

					} else if (godRodj != -1) {
						if (godUpis <= godRodj) {

							invalidYear = true;
							shown = true;
							JOptionPane.showMessageDialog(StudentInfo.this,
									"Godina upisa ne može biti manja ili jednaka od godine rođenja!\nProverite godinu upisa ili godinu rođenja!",
									"Greška: ", JOptionPane.ERROR_MESSAGE);

						} else if (godUpis - godRodj < 18) {

							invalidYear = true;
							shown = true;
							int pom = godRodj + 18;
							JOptionPane.showMessageDialog(StudentInfo.this,
									"Godina upisa mora biti minimalno " + pom + "!", "Greška: ",
									JOptionPane.ERROR_MESSAGE);

						}
					}

				}

				if (!valid[7] && !tFGodU.getText().trim().equals("")) {
					tFGodU.setText("");
					godUpis = -1;
					invalidYear = true;
					JOptionPane.showMessageDialog(StudentInfo.this, "Pogrešno uneta godina upisa! Ispravan unos: YYYY",
							"Greška: ", JOptionPane.ERROR_MESSAGE);

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

				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("YYYY");
				LocalDateTime now = LocalDateTime.now();
				String trenutnaGod = dtf.format(now);
				int trGod = Integer.parseInt(trenutnaGod);
				if (valid[7]) {
					godUpis = Integer.parseInt(tFGodU.getText().trim());

					if (godUpis > trGod) {
						invalidYear = true;
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
				changeLabel(valid[2] && !tooYoung && !shown, "Datum rođenja*", "Datum rođenja*",
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
		
		buttonPonisti.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				Window w = SwingUtilities.getWindowAncestor(StudentInfo.this);
				w.dispose();
			
			}
		});

		buttonPotvrdi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				tFName.setText(setString(tFName.getText()));
				tFPrezime.setText(setString(tFPrezime.getText()));
				tFBrI.setText(tFBrI.getText().toUpperCase());

				Student s = StudentBaza.getInstance().getRow(PrikazStudenta.getInstance().getSelectedRow());

				String status = cBFin.getSelectedItem().toString();
				Status st = status.equals("Budžet") ? Status.B : Status.S;
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
				s.setIme(tFName.getText());
				s.setPrezime(tFPrezime.getText());
				s.setDatumRodjenja(date1);
				s.setAdresaStanovanja(tFAdr.getText());
				s.setTelefon(tFBr.getText());
				s.setEmail(tFEmail.getText());
				s.setBrIndeksa(tFBrI.getText().toUpperCase());
				s.setGodinaUpisa(Integer.parseInt((tFGodU.getText())));
				s.setTrenutnaGodina(trenutnaGodina);
				s.setStatus(st);
				StudentController.getInstance().izmeniStudenta(s, oldId);

				JOptionPane.showMessageDialog(StudentInfo.this, "Izmena studenta je uspešno izvršena!");
				Window w = SwingUtilities.getWindowAncestor(StudentInfo.this);
				w.dispose();

			}

		});

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
		for (int i = 0; i < 8; ++i) {
			if (!valid[i]) {
				retVal = false;
				break;
			}
		}

		return retVal;
	}
}
