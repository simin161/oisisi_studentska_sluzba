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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

import controller.ActionChangeTField;
import controller.buttonAction.ButtonAction;
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

	private int num = 1;
	private boolean enable = false;
	private boolean indExists = false;
	private boolean tooYoung = false;
	private boolean invalidYear = false;
	private int godRodj = -1;
	private int godUpis = -1;

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
		// panelButton.add(buttonPotvrdi);

		JPanel panelMain = new JPanel();
		BoxLayout b = new BoxLayout(panelMain, BoxLayout.Y_AXIS);
		panelMain.setLayout(b);

		JPanel panelName = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel labelName = new JLabel("Ime* ");
		labelName.setForeground(Color.red);
		JTextField tFName = new JTextField();

		tFName.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void focusLost(FocusEvent e) {
				text[0] = tFName.getText().trim();
				Pattern pattern = Pattern.compile("[A-ZŠĐČĆŽ][a-zšđčćž]*", Pattern.CASE_INSENSITIVE);
				Matcher matcher = pattern.matcher(tFName.getText());
				valid[0] = matcher.matches();

				tFName.setText(setString(tFName.getText()));
				changeLabel(valid[0], "Ime*", "Ime", labelName);

				if (!valid[0] && !text[0].equals("")) {
					JOptionPane.showMessageDialog(DialogAddStudent.this,
							"Pogrešno uneto ime! (Primer ispravnog unosa: Petar)", "Greška: ",
							JOptionPane.ERROR_MESSAGE);
					enable = false;
				} else {
					enable = true;
					for (int i = 0; i < 8; ++i) {
						if (text[i].equals("") || !valid[i]) {
							enable = false;
							break;
						}
					}
				}
				buttonPotvrdi.setEnabled(enable && !indExists && !tooYoung && !invalidYear);
			}

		});

		buttonPotvrdi.setEnabled(enable);

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
		tFPrezime.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void focusLost(FocusEvent e) {
				text[1] = tFPrezime.getText().trim();
				Pattern pattern = Pattern.compile("[A-ZŠĐČĆŽ][a-zšđčćž]*", Pattern.CASE_INSENSITIVE);
				Matcher matcher = pattern.matcher(tFPrezime.getText());
				valid[1] = matcher.matches();

				tFPrezime.setText(setString(tFPrezime.getText()));
				changeLabel(valid[1], "Prezime*", "Prezime", labelPrezime);

				if (!valid[1] && !text[1].equals("")) {
					JOptionPane.showMessageDialog(DialogAddStudent.this,
							"Pogrešno uneto prezime! (Primer ispravnog unosa: Petrović)", "Greška: ",
							JOptionPane.ERROR_MESSAGE);
					enable = false;
				} else {
					enable = true;
					for (int i = 0; i < 8; ++i) {
						if (text[i].equals("") || !valid[i]) {
							enable = false;
							break;
						}
					}

					buttonPotvrdi.setEnabled(enable && !indExists && !tooYoung && !invalidYear);
				}
			}

		});
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
		tfDatumR.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void focusLost(FocusEvent e) {
				text[2] = tfDatumR.getText().trim();
				Pattern pattern = Pattern.compile("[0-9]{2}[/][0-9]{2}[/][0-9]{4}");
				Matcher matcher = pattern.matcher(tfDatumR.getText());
				valid[2] = matcher.matches();
				godRodj = -1;
				changeLabel(valid[2], "Datum rođenja*", "Datum rođenja", labelDatumR);

				if (!valid[2] && !text[2].equals("")) {
					JOptionPane.showMessageDialog(DialogAddStudent.this,
							"Pogrešno unet datum! Ispravan unos : DD/MM/YYYY", "Greška: ", JOptionPane.ERROR_MESSAGE);
					enable = false;
				} else {
					enable = true;
					for (int i = 0; i < 8; ++i) {
						if (text[i].equals("") || !valid[i]) {
							enable = false;
							break;
						}
					}
				}
				
				if(valid[2]) {
					String[] datum = text[2].split("/");
					godRodj = Integer.parseInt(datum[2]);
					
				}

				buttonPotvrdi.setEnabled(enable && !indExists && !tooYoung && !invalidYear);
			}

		});
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
		tFAdr.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void focusLost(FocusEvent e) {
				text[3] = tFAdr.getText().trim();
				Pattern pattern = Pattern.compile(
						"[ŠĐČĆŽšđčćžA-z]+\\s[0-9]+[,]\\s[ŠĐČĆŽšđčćžA-z]+");
				Matcher matcher = pattern.matcher(tFAdr.getText());
				valid[3] = matcher.find();

				changeLabel(valid[3], "Adresa stanovanja*", "Adresa stanovanja", labelAdr);

				if (!valid[3] && !text[3].equals("")) {
					JOptionPane.showMessageDialog(DialogAddStudent.this,
							"Pogrešno uneta adresa! Ispravan unos: ulica i broj, mesto", "Greška: ",
							JOptionPane.ERROR_MESSAGE);
					enable = false;
				} else {
					enable = true;
					for (int i = 0; i < 8; ++i) {
						if (text[i].equals("") || !valid[i]) {
							enable = false;
							break;
						}
					}
				}

				buttonPotvrdi.setEnabled(enable && !indExists && !tooYoung && !invalidYear);
			}

		});
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
				valid[4] = matcher.matches();

				changeLabel(valid[4], "Broj telefona*", "Broj telefona", labelBr);

				if (!valid[4] && !text[4].equals("")) {
					JOptionPane.showMessageDialog(DialogAddStudent.this,
							"Pogrešno unet broj telefona! (Primer: 123/123-123", "Greška: ", JOptionPane.ERROR_MESSAGE);
					enable = false;
				} else {
					enable = true;
					for (int i = 0; i < 8; ++i) {
						if (text[i].equals("") || !valid[i]) {
							enable = false;
							break;
						}
					}
				}
				buttonPotvrdi.setEnabled(enable && !indExists && !tooYoung && !invalidYear);
			}

		});
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
		tFEmail.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void focusLost(FocusEvent e) {
				text[5] = tFEmail.getText().trim();
				Pattern pattern = Pattern.compile("[A-Za-z0-9._%-]+@[A-Za-z0-9._-]+\\.[A-Za-z]{2,4}",
						Pattern.CASE_INSENSITIVE);
				Matcher matcher = pattern.matcher(tFEmail.getText());
				valid[5] = matcher.matches();
				changeLabel(valid[5], "E-mail adresa*", "E-mail adresa", labelEmail);

				if (!valid[5] && !text[5].equals("")) {
					JOptionPane.showMessageDialog(DialogAddStudent.this, "Pogrešno uneta e-mail adresa!", "Greška: ",
							JOptionPane.ERROR_MESSAGE);
					enable = false;
				} else {
					enable = true;
					for (int i = 0; i < 8; ++i) {
						if (text[i].equals("") || !valid[i]) {
							enable = false;
							break;
						}
					}
				}
				buttonPotvrdi.setEnabled(enable && !indExists && !tooYoung && !invalidYear);
			}

		});
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
		tFBrI.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void focusLost(FocusEvent e) {
				text[6] = tFBrI.getText().trim();
				Pattern pattern = Pattern.compile("([A-ZŠĐČĆŽ]+)?[0-9]+([/][0-9]+)?", Pattern.CASE_INSENSITIVE);
				Matcher matcher = pattern.matcher(tFBrI.getText());
				valid[6] = matcher.matches();
				
				tFBrI.setText(tFBrI.getText().toUpperCase());
				text[6] = text[6].toUpperCase();
				indExists = false;
				for (Student s : students) {
					if (s.getBrIndeksa().equals(text[6])) {
						indExists = true;
						JOptionPane.showMessageDialog(DialogAddStudent.this, "Broj indeksa postoji!", "Greška: ",
								JOptionPane.ERROR_MESSAGE);
						break;
					}
				}

				if (!valid[6] && !text[6].equals("")) {
					JOptionPane.showMessageDialog(DialogAddStudent.this, "Pogrešno unet broj indeksa!", "Greška: ",
							JOptionPane.ERROR_MESSAGE);
					enable = false;
				} else {

					enable = true;
					for (int i = 0; i < 8; ++i) {
						if ((text[i].equals("") || !valid[i])) {
							enable = false;
							break;
						}
					}
				}
				changeLabel(valid[6] && !indExists, "Broj indeksa*", "Broj indeksa", labelBrI);
				buttonPotvrdi.setEnabled(enable && !indExists && !tooYoung && !invalidYear);
			}

		});
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
		tFGodU.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void focusLost(FocusEvent e) {
				text[7] = tFGodU.getText().trim();
				Pattern pattern = Pattern.compile("[0-9]{4,4}"); // NAPOMENA: godina jos dugo nece imati vise od 4 cifre
				Matcher matcher = pattern.matcher(tFGodU.getText());
				valid[7] = matcher.matches();
				
				tooYoung = false;
				invalidYear = false;
				godUpis = -1;
				if (!valid[7] && !text[7].equals("")) {
					JOptionPane.showMessageDialog(DialogAddStudent.this,
							"Pogrešno uneta godina upisa! Ispravan unos: YYYY", "Greška: ", JOptionPane.ERROR_MESSAGE);
					enable = false;
				} else {
					enable = true;
					for (int i = 0; i < 8; ++i) {
						if (text[i].equals("") || !valid[i]) {
							enable = false;
							break;
						}
					}
				}
				
				if(valid[7]) {
					godUpis = Integer.parseInt(text[7]);
					

					DateTimeFormatter dtf = DateTimeFormatter.ofPattern("YYYY");
					LocalDateTime now = LocalDateTime.now();
					String trenutnaGod= dtf.format(now);
					int trGod = Integer.parseInt(trenutnaGod);
					
					if(godUpis > trGod) {
						JOptionPane.showMessageDialog(DialogAddStudent.this,
								"Godina upisa ne može biti veća od trenutne godine!", "Greška: ", JOptionPane.ERROR_MESSAGE);
						invalidYear = true;
					}
					
					if(godRodj != -1  && (godUpis-godRodj < 18)) {
						tooYoung = true;
						JOptionPane.showMessageDialog(DialogAddStudent.this,
								"Osoba mora imati više od 18 godina!", "Greška: ", JOptionPane.ERROR_MESSAGE);
					}
				}
				
				changeLabel(valid[7] && !tooYoung && !invalidYear , "Godina upisa*", "Godina upisa", labelGodU);
				buttonPotvrdi.setEnabled(enable && !indExists && !tooYoung && !invalidYear);
			}

		});
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
				String status = cBFin.getSelectedItem().toString();
				Status s = status.equals("Budžet") ? Status.B : Status.S;
				String trGod = cBTGodS.getSelectedItem().toString();
				int trenutnaGodina = 1;
				switch(trGod) {
				case "I (prva)" : trenutnaGodina = 1;
				break;
				case "II (druga)" : trenutnaGodina = 2;
				break;
				case "III (treća)": trenutnaGodina = 3;
				break;
				case "IV (četvrta)": trenutnaGodina = 4;
				break;
				}
				Student st = new Student(tFPrezime.getText(), tFName.getText(), tfDatumR.getText(), tFAdr.getText(), tFBr.getText(),
						tFEmail.getText(), tFBrI.getText().toUpperCase(), godUpis, trenutnaGodina, s, 0,
						null, null);
				
				students.add(st);
				StudentBaza.getInstance().setStudents(students);
				PrikazStudenta.getInstance().update("", 0);
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

}
