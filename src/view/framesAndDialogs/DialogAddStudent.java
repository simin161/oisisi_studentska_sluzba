package view.framesAndDialogs;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import controller.buttonAction.ButtonAction;

//https://www.experts-exchange.com/questions/21314578/check-if-multiple-textfields-are-empty.html

public class DialogAddStudent extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4412711478828503212L;

	private int num = 1;
	private boolean enable = false;
	private String n = "[A-Za-z]";

	public DialogAddStudent(Container cont) {

		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int height = (int) (screenSize.height * 0.75 * 0.8);
		int width = (int) (screenSize.width * 0.75 * 0.4);

		String[] text = {"", "", "", "", "", "", "", ""};
		boolean[] valid = {false, false, false, false, false, false, false, false};
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
		ButtonAction.potvrdiButton(buttonPotvrdi, num);
		buttonPotvrdi.setEnabled(false);
		// panelButton.add(buttonPotvrdi);

		JPanel panelMain = new JPanel();
		BoxLayout b = new BoxLayout(panelMain, BoxLayout.Y_AXIS);
		panelMain.setLayout(b);

		JPanel panelName = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel labelName = new JLabel("Ime* ");
		JTextField tFName = new JTextField();
		tFName.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void focusLost(FocusEvent e) {
				text[0] = tFName.getText().trim();
				Pattern pattern = Pattern.compile("[A-ZŠĐČĆŽ][a-zšđčćž]*");
			    Matcher matcher = pattern.matcher(tFName.getText());
			    valid[0] =  matcher.matches();

				enable = true;
				for (int i = 0; i < 8; ++i) {
					if (text[i].equals("") || !valid[i]) {
						enable = false;
						break;
					} 
				}
				
				buttonPotvrdi.setEnabled(enable);
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
				Pattern pattern = Pattern.compile("[A-ZŠĐČĆŽ][a-zšđčćž]*");
			    Matcher matcher = pattern.matcher(tFPrezime.getText());
			    valid[1] = matcher.matches();

				enable = true;
				for (int i = 0; i < 8; ++i) {
					if (text[i].equals("") || !valid[i]) {
						enable = false;
						break;
					} 
				}
				
				buttonPotvrdi.setEnabled(enable);
			}

		});
		panelPrezime.add(Box.createHorizontalStrut(65));
		panelPrezime.add(labelPrezime);
		panelPrezime.add(tFPrezime);
		panelMain.add(panelPrezime);

		JPanel panelDatumR = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel labelDatumR = new JLabel("Datum rođenja* ");
		MaskFormatter mask = null;
		try {

			mask = new MaskFormatter("##/##/####");
			mask.setPlaceholderCharacter('_');

		} catch (Exception e) {
			e.printStackTrace();
		}

		JFormattedTextField tfDatumR = new JFormattedTextField(mask);
		labelDatumR.setPreferredSize(dim);
		tfDatumR.setPreferredSize(dim);
		tfDatumR.setToolTipText("Dan-Mesec-Godina");
		tfDatumR.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void focusLost(FocusEvent e) {
				text[2] = tfDatumR.getText().trim();
				Pattern pattern = Pattern.compile("[0-9 ]+");
			    Matcher matcher = pattern.matcher(tfDatumR.getText().replace('/', ' '));
			    valid[2] = matcher.matches();

				enable = true;
				for (int i = 0; i < 8; ++i) {
					if (text[i].equals("") || !valid[i]) {
						enable = false;
						break;
					} 
				}
				
				buttonPotvrdi.setEnabled(enable);
			}

		});
		panelDatumR.add(Box.createHorizontalStrut(65));
		panelDatumR.add(labelDatumR);
		panelDatumR.add(tfDatumR);
		panelMain.add(panelDatumR);

		JPanel panelAdr = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel labelAdr = new JLabel("Adresa stanovanja* ");
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
				Pattern pattern = Pattern.compile("[A-ZŠĐČĆŽa-zšđčćž]+[ ][0-9]+[A-Z]*"); //TODO: prepraviti
			    Matcher matcher = pattern.matcher(tFAdr.getText());
			    valid[3] = matcher.matches();
			    System.out.println("3:" + valid[3]);
				
				enable = true;
				for (int i = 0; i < 8; ++i) {
					if (text[i].equals("") || !valid[i]) {
						enable = false;
						break;
					} 
				}
				
				buttonPotvrdi.setEnabled(enable);
			}

		});
		panelAdr.add(Box.createHorizontalStrut(65));
		panelAdr.add(labelAdr);
		panelAdr.add(tFAdr);
		panelMain.add(panelAdr);

		JPanel panelBr = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel labelBr = new JLabel("Broj telefona* ");
		JTextField tFBr = new JTextField();
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
				Pattern pattern = Pattern.compile("[0-9]{6,7}"); //TODO: odraditi upis sa -/
			    Matcher matcher = pattern.matcher(tFBr.getText());
			    valid[4] = matcher.matches();

				enable = true;
				for (int i = 0; i < 8; ++i) {
					if (text[i].equals("") || !valid[i]) {
						enable = false;
						break;
					} 
				}
				
				buttonPotvrdi.setEnabled(enable);
			}

		});
		panelBr.add(Box.createHorizontalStrut(65));
		panelBr.add(labelBr);
		panelBr.add(tFBr);
		panelMain.add(panelBr);

		JPanel panelEmail = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel labelEmail = new JLabel("E-mail adresa* ");
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
				Pattern pattern = Pattern.compile("[A-ZŠĐČĆŽ][a-zšđčćž]*"); //TODO: naci regex za email!
			    Matcher matcher = pattern.matcher(tFEmail.getText());
			    valid[5] = matcher.matches();

				enable = true;
				for (int i = 0; i < 8; ++i) {
					if (text[i].equals("") || !valid[i]) {
						enable = false;
						break;
					} 
				}
				
				buttonPotvrdi.setEnabled(enable);
			}

		});
		panelEmail.add(Box.createHorizontalStrut(65));
		panelEmail.add(labelEmail);
		panelEmail.add(tFEmail);
		panelMain.add(panelEmail);

		JPanel panelBrI = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel labelBrI = new JLabel("Broj indeksa* ");
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
				Pattern pattern = Pattern.compile("[A-ZŠĐČĆŽa-zšđčćž]+[0-9]+"); //TODO: prepraviti da podrzava / -
			    Matcher matcher = pattern.matcher(tFBrI.getText());
			    valid[6] = matcher.matches();

				enable = true;
				for (int i = 0; i < 8; ++i) {
					if (text[i].equals("") || !valid[i]) {
						enable = false;
						break;
					} 
				}
				
				buttonPotvrdi.setEnabled(enable);
			}

		});
		panelBrI.add(Box.createHorizontalStrut(65));
		panelBrI.add(labelBrI);
		panelBrI.add(tFBrI);
		panelMain.add(panelBrI);

		JPanel panelGodU = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel labelGodU = new JLabel("Godina upisa* ");
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
				Pattern pattern = Pattern.compile("[0-9]{4,4}"); //NAPOMENA: godina jos dugo nece imati vise od 4 cifre
			    Matcher matcher = pattern.matcher(tFGodU.getText());
			    valid[7]= matcher.matches();
				enable = true;
				for (int i = 0; i < 8; ++i) {
					if (text[i].equals("") || !valid[i]) {
						enable = false;
						break;
					} 
				}
				
				buttonPotvrdi.setEnabled(enable);
			}

		});
		panelGodU.add(Box.createHorizontalStrut(65));
		panelGodU.add(labelGodU);
		panelGodU.add(tFGodU);
		panelMain.add(panelGodU);

		// https*//docs.oracle.com/javase/tutorial/uiswing/components/combobox.html
		String[] stringTGodS = { "I (prva)", "II (druga)", "III (treća)", "IV (četvrta)" };
		JPanel panelTGodS = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel labelTGodS = new JLabel("Trenutna godina studija* ");

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
		JLabel labelFin = new JLabel("Način finansiranja* ");

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

		add(panelMain);
		setVisible(true);

	}

}
