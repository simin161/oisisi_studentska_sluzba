package view.framesAndDialogs;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import controller.buttonAction.ButtonAction;
import controller.profesor.ProfesoriController;
import controller.provere.ProveraGodine;
import model.Profesor;

public class DialogDodavanjeProfesora extends JDialog{

	private static final long serialVersionUID = 3844060477268188498L;
	
	private Profesor profesor;
	private Boolean []provera= {false, false, false, false, false, false, false, false};
	
	
	public DialogDodavanjeProfesora(Container c)
	{
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize= kit.getScreenSize();
		int screenHeight = (int) (screenSize.height * 0.75 * 0.8);
		int screenWidth = (int) (screenSize.width * 0.75 * 0.4);

		setTitle("Dodavanje profesora");
		setSize(screenWidth, screenHeight);
		setLocationRelativeTo(c);
		setModal(true);
		setResizable(false);
		Dimension dim= new Dimension(150,20);
		
		JPanel panelMain = new JPanel();
		BoxLayout box = new BoxLayout(panelMain, BoxLayout.Y_AXIS);
		panelMain.setLayout(box);
		
		JPanel panelIme = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel lblIme = new JLabel("Ime* ");
		JTextField txtIme = new JTextField();
		lblIme.setPreferredSize(dim);
		txtIme.setPreferredSize(dim);
		panelIme.add(Box.createHorizontalStrut(65));
		panelIme.add(lblIme);
		panelIme.add(txtIme);
		panelMain.add(panelIme);
		
		JPanel panelPrezime = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel lblPrezime = new JLabel("Prezime*");
		JTextField txtPrezime = new JTextField();
		lblPrezime.setPreferredSize(dim);
		txtPrezime.setPreferredSize(dim);
		panelPrezime.add(Box.createHorizontalStrut(65));
		panelPrezime.add(lblPrezime);
		panelPrezime.add(txtPrezime);
		panelMain.add(panelPrezime);
		
		JPanel panelDatum= new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel lblDatum = new JLabel("Datum rođenja* ");
		
		JFormattedTextField txtDatum = new JFormattedTextField();
		
		lblDatum.setPreferredSize(dim);
		txtDatum.setPreferredSize(dim);
		txtDatum.setToolTipText("Dan/Mesec/Godina");
		panelDatum.add(Box.createHorizontalStrut(65));
		panelDatum.add(lblDatum);
		panelDatum.add(txtDatum);
		panelMain.add(panelDatum);
		
		
		JPanel panelAdresa = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel lblAdresa= new JLabel("Adresa stanovanja* ");
		JTextField txtAdresa = new JTextField();
		lblAdresa.setPreferredSize(dim);
		txtAdresa.setPreferredSize(dim);
		panelAdresa.add(Box.createHorizontalStrut(65));
		panelAdresa.add(lblAdresa);
		panelAdresa.add(txtAdresa);
		panelMain.add(panelAdresa);
		
		JPanel panelTelefon = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel lblTelefon = new JLabel("Kontakt telefon* ");
		
		JFormattedTextField txtTelefon = new JFormattedTextField();
		lblTelefon.setPreferredSize(dim);
		txtTelefon.setPreferredSize(dim);
		panelTelefon.add(Box.createHorizontalStrut(65));
		panelTelefon.add(lblTelefon);
		panelTelefon.add(txtTelefon);
		panelMain.add(panelTelefon);
		
		JPanel panelEmail = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel lblEmail = new JLabel("E-mail adresa* ");
		JTextField txtEmail = new JTextField();
		lblEmail.setPreferredSize(dim);
		txtEmail.setPreferredSize(dim);
		panelEmail.add(Box.createHorizontalStrut(65));
		panelEmail.add(lblEmail);
		panelEmail.add(txtEmail);
		panelMain.add(panelEmail);
		
		JPanel panelKancelarija = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel lblKancelarija = new JLabel("Adresa kancelarije* ");
		JTextField txtKancelarija = new JTextField();
		lblKancelarija.setPreferredSize(dim);
		txtKancelarija.setPreferredSize(dim);
		panelKancelarija.add(Box.createHorizontalStrut(65));
		panelKancelarija.add(lblKancelarija);
		panelKancelarija.add(txtKancelarija);
		panelMain.add(panelKancelarija);
		
		JPanel panelLk = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel lblLk = new JLabel("Broj lične karte* ");
		JTextField txtLk = new JTextField();
		lblLk.setPreferredSize(dim);
		txtLk.setPreferredSize(dim);
		panelLk.add(Box.createHorizontalStrut(65));
		panelLk.add(lblLk);
		panelLk.add(txtLk);
		panelMain.add(panelLk);
		
		JPanel panelTitula = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel lblTitula = new JLabel("Titula* ");
		String[] titule= {"Master", "Magistar", "Doktor"};
		
		@SuppressWarnings({ "rawtypes", "unchecked" })
		JComboBox cboxTitula = new JComboBox(titule);
		lblTitula.setPreferredSize(dim);
		cboxTitula.setPreferredSize(dim);
		panelTitula.add(Box.createHorizontalStrut(65));
		panelTitula.add(lblTitula);
		panelTitula.add(cboxTitula);
		panelMain.add(panelTitula);
		
		JPanel panelZvanje = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel lblZvanje = new JLabel("Zvanje* ");
		String[] zvanja = {"Redovni profesor","Vanredni profesor","Docent", "Dekan", "Saradnik u nastavi"};
		
		@SuppressWarnings({ "rawtypes", "unchecked" })
		JComboBox cboxZvanje = new JComboBox(zvanja);
		lblZvanje.setPreferredSize(dim);
		cboxZvanje.setPreferredSize(dim);
		panelZvanje.add(Box.createHorizontalStrut(65));
		panelZvanje.add(lblZvanje);
		panelZvanje.add(cboxZvanje);
		panelMain.add(panelZvanje);
		
		JPanel panelBtn = new JPanel();
		BoxLayout btn = new BoxLayout(panelBtn, BoxLayout.X_AXIS);
		panelBtn.setLayout(btn);
		JButton btnPotvrdi = new JButton("Potvrdi");
		btnPotvrdi.setEnabled(false);
		JButton btnPonisti = new JButton("Poništi");
		ButtonAction.cancelAction(btnPonisti, this);
		panelBtn.add(btnPotvrdi);
		panelBtn.add(Box.createHorizontalStrut(35));
		panelBtn.add(btnPonisti);
		panelMain.add(panelBtn);
		
		add(panelMain);
		
		txtIme.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				
				enableBtn();
				
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
			
				enableBtn();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				
				enableBtn();
			}
			
			private void enableBtn() {
				
				Boolean enable = false;
				String imeReg = "^[\\p{L} .'-]+$";
				
				
				if(txtIme.getText().trim().length()==0)
				{
					provera[0]= false;
					lblIme.setText("Ime*");
				}
				else
				{
					Pattern pattern = Pattern.compile(imeReg, Pattern.CASE_INSENSITIVE);
					Matcher matcher = pattern.matcher(txtIme.getText());
					provera[0]= matcher.find();
					if(provera[0]==true)
					{
						lblIme.setText("Ime");
					}
					else
					{
						lblIme.setText("Ime*");
					}
				}
				
				enable= proveraProvere(provera);
				
				btnPotvrdi.setEnabled(enable);
				
			}
			
		});
		
		txtIme.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				
				if(provera[0]==false) {
					
					txtIme.setText("");
					
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
			
				
				if(txtIme.getText().trim().length()!=0)
				{
					String pocetno = txtIme.getText().substring(0,1).toUpperCase();
					String ostatak = txtIme.getText().substring(1);
					
					txtIme.setText(pocetno+ostatak);
					
					/*String ime = txtIme.getText();
					String []parts = ime.split(" ");
					String []novo = parts;
					int i=0;
					for(String s : parts)
					{
						String pocetno = s.substring(0, 1);
						String ostatak = s.substring(1);
						pocetno = pocetno.toUpperCase();
						novo[i]= pocetno+ostatak;
						i++;
					}
					
					String novoIme="";
					i=0;
					
					do {
						
						novoIme= novoIme + novo[i] + " ";
						i++;
						
					}while(i!= novo.length);
					
					txtIme.setText(novoIme);*/
				}
				
				if(provera[0]==false && txtIme.getText().trim().length()!=0) {
					
					JOptionPane.showMessageDialog(DialogDodavanjeProfesora.this, "Greška prilikom unosa imena.", "Greška: ", JOptionPane.ERROR_MESSAGE);
					txtIme.setText("");
					
				}
				
			}
			
		});
		
		txtPrezime.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
			
				enableBtn();
				
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				
				enableBtn();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				
				enableBtn();
			}
			
			private void enableBtn() {
				
				Boolean enable = false;
				String prezimeReg = "^[\\p{L}-]+$";
				
				if(txtPrezime.getText().trim().length()==0)
				{
					provera[1]= false;
					lblPrezime.setText("Prezime*");
				}
				else
				{
					Pattern pattern = Pattern.compile(prezimeReg, Pattern.CASE_INSENSITIVE);
					Matcher matcher = pattern.matcher(txtPrezime.getText());
					provera[1]= matcher.find();
					if(provera[1]==true)
					{
						lblPrezime.setText("Prezime");
					}
					else
					{
						lblPrezime.setText("Prezime*");
					}
				}
				
				enable= proveraProvere(provera);
				
				btnPotvrdi.setEnabled(enable);
			}
			
		});
		
		txtPrezime.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				
				if(provera[1]==false) {
					
					txtPrezime.setText("");
					
				}
				
			}

			@Override
			public void focusLost(FocusEvent e) {
				
				
					if(txtPrezime.getText().trim().length()!=0) {
						
						String []parts = txtPrezime.getText().split("-");
						String []prezime = parts;
						String novoP="";
						int i=0;
						
						for(String s : parts)
						{
							String pocetno= s.substring(0,1).toUpperCase();
							String ostatak= s.substring(1);
							
							prezime[i]= pocetno+ostatak;

							i++;
						}
						
						i=0;
						while(i!=prezime.length)
						{
							if(i==0) {
								
								novoP= prezime[i];
								
							}
							else
							{
								novoP= novoP + '-' + prezime[i];
							}
							
							i++;
						}
						
						txtPrezime.setText(novoP);
						
					}
					
				
					if(provera[1]==false && txtPrezime.getText().trim().length()!=0) {
						
						JOptionPane.showMessageDialog(DialogDodavanjeProfesora.this, "Greška prilikom unosa prezimena. Ukoliko imate dva ili više prezimena, unesite ih sa povlakom između.", "Greška: ", JOptionPane.ERROR_MESSAGE);
						txtPrezime.setText("");
						
					}
				
				}
		});
		
		txtDatum.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				
				enableBtn();
				
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				
				enableBtn();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				
				enableBtn();
			}
			
			private void enableBtn() {
				
				Boolean enable = false;
				String datumReg = "[0-9]{2}[/][0-9]{2}[/][0-9]{4}";
				if(txtDatum.getText().trim().length()==0)
				{
					provera[2]= false;
					lblDatum.setText("Datum rođenja*");
				}
				else
				{
					Pattern pattern = Pattern.compile(datumReg);
					Matcher matcher = pattern.matcher(txtDatum.getText());
					provera[2]= matcher.find();
					
					if(provera[2]==true)
					{
						lblDatum.setText("Datum rođenja");
					}
					else
					{
						lblDatum.setText("Datum rođenja*");
					}
					
				}
				enable= proveraProvere(provera);
				
				btnPotvrdi.setEnabled(enable);
			}
		});
		
		txtDatum.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				
				if(provera[2]==false) {
					
					txtDatum.setText("");
					
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
		
				if(provera[2]==true)
				{
					Boolean check = true;
					check = ProveraGodine.proveri(provera, txtDatum.getText());
					
					if(check==false) {
						
						JOptionPane.showMessageDialog(DialogDodavanjeProfesora.this, "Greška prilikom unosa datuma. Proverite Vaše podatke i probajte ponovo.", "Greška: ", JOptionPane.ERROR_MESSAGE);
						txtDatum.setText("");
						
					}
					
				}
				if(provera[2]==false && txtDatum.getText().trim().length()!=0) {
					
					JOptionPane.showMessageDialog(DialogDodavanjeProfesora.this, "Greška prilikom unosa datuma. Datum uneti u sledećem formatu: DD/MM/YYYY", "Greška: ", JOptionPane.ERROR_MESSAGE);
					txtDatum.setText("");
				}	
			}
		});
		
		txtAdresa.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				
				enableBtn();
				
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
			
				enableBtn();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
			
				enableBtn();
			}
			
			private void enableBtn() {
				
				Boolean enable = false;
				
				if(txtAdresa.getText().trim().length()==0)
				{
					provera[3]= false;
					lblAdresa.setText("Adresa stanovanja*");
				}
				else
				{
					provera[3]= true;
					lblAdresa.setText("Adresa stanovanja");
				}
				
				enable= proveraProvere(provera);
				
				btnPotvrdi.setEnabled(enable);
			}
			
		});
		
		txtAdresa.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
			
				if(provera[3]==false) {
					
					txtAdresa.setText("");
					
				}
				
			}

			@Override
			public void focusLost(FocusEvent e) {
			
				if(provera[3]==false) {
					
					txtAdresa.setText("");
				}
				
				
			}
			
			
			
			
		});
		
		txtTelefon.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				
				enableBtn();
				
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				
				enableBtn();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
			
				enableBtn();
			}
			
			private void enableBtn() {
				
				Boolean enable = false;
				String phoneNumb = "^(\\d{3}[- ]?){2}\\d{3}$";
				String phoneNumb2 = "^(\\d{3}[- ]?){2}\\d{4}$";
				if(txtTelefon.getText().trim().length()==0)
				{
					provera[4]= false;
					lblTelefon.setText("Kontakt telefon*");
				}
				else
				{
					Pattern pattern = Pattern.compile(phoneNumb);
					Matcher matcher = pattern.matcher(txtTelefon.getText());
					Pattern pattern2 = Pattern.compile(phoneNumb2);
					Matcher matcher2 = pattern2.matcher(txtTelefon.getText());
					provera[4]= matcher.find();
					
					if(matcher.find()!= true && matcher2.find()== true)
						provera[4]= true;
					
					
					if(provera[4]==true)
					{
						lblTelefon.setText("Kontakt telefon");
					}
					else
					{
						lblTelefon.setText("Kontakt telefon*");
					}
					
				}
				
				enable= proveraProvere(provera);
				
				btnPotvrdi.setEnabled(enable);
			}
			
		});
		
		txtTelefon.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
		
				if(provera[4]==false)
					txtTelefon.setText("");
				
			}

			@Override
			public void focusLost(FocusEvent e) {
				
				if(provera[4]==false && txtTelefon.getText().trim().length()!=0)
				{
					JOptionPane.showMessageDialog(DialogDodavanjeProfesora.this, "Greška prilikom unosa broja telefona. Broj uneti u jednom od sledećih formata: 000 000 0000 | 000-000-0000", "Greška: ", JOptionPane.ERROR_MESSAGE);
					txtTelefon.setText("");
				}
				
			}
		});
		
		txtEmail.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				
				enableBtn();
				
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				
				enableBtn();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				
				enableBtn();
			}
			
			private void enableBtn() {
				
				Boolean enable = false;
				String mailReg = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
				
				if(txtEmail.getText().trim().length()==0)
				{
					provera[5]= false;
					lblEmail.setText("E-mail adresa*");
				}
				else
				{
					
					Pattern pattern = Pattern.compile(mailReg, Pattern.CASE_INSENSITIVE);
					Matcher matcher = pattern.matcher(txtEmail.getText());
					provera[5]= matcher.find();
					
					if(provera[5]== true)
					{
						lblEmail.setText("E-mail adresa");
					}
					else
						lblEmail.setText("E-mail adresa*");
				}
				
				enable= proveraProvere(provera);
				
				btnPotvrdi.setEnabled(enable);
			}
			
		});
		
		txtEmail.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				
				if(provera[5]==false) {
					
					txtEmail.setText("");
					
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				
				if(provera[5]==false && txtEmail.getText().trim().length()!=0) {
					
					JOptionPane.showMessageDialog(DialogDodavanjeProfesora.this, "Greška prilikom unosa e-mail adrese. Unesite validnu e-mail adresu poput: example@something.domain ", "Greška: ", JOptionPane.ERROR_MESSAGE);
					txtEmail.setText("");
					
				}
				
			}
			
			
			
		});
		
		txtKancelarija.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
			
				enableBtn();
				
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
			
				enableBtn();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				
				enableBtn();
			}
			
			private void enableBtn() {
				
				Boolean enable = false;
				
				if(txtKancelarija.getText().trim().length()==0)
				{
					provera[6]= false;
					lblKancelarija.setText("Adresa kancelarije*");
				}
				else
				{
					provera[6]= true;
					lblKancelarija.setText("Adresa kancelarije");
				}
				
				enable= proveraProvere(provera);
				
				btnPotvrdi.setEnabled(enable);
			}
			
		});
		
		txtKancelarija.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
			
				if(provera[6]==false) {
					
					txtKancelarija.setText("");
					
				}
				
			}

			@Override
			public void focusLost(FocusEvent e) {
			
				if(provera[6]==false && txtKancelarija.getText().trim().length()!=0) {
					
					txtKancelarija.setText("");
					
				}
				
			}
			
			
			
		});
		
		txtLk.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
			
				enableBtn();
				
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				
				enableBtn();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				
				enableBtn();
			}
			
			private void enableBtn() {
				
				Boolean enable = false;
				String lkReg= "^\\d{9}$";
				if(txtLk.getText().trim().length()==0)
				{
					provera[7]= false;
					lblLk.setText("Broj lične karte*");
				}
				else
				{

					Pattern pattern = Pattern.compile(lkReg, Pattern.CASE_INSENSITIVE);
					Matcher matcher = pattern.matcher(txtLk.getText());
					provera[7]= matcher.find();
					
					if(provera[7]== true)
					{
						lblLk.setText("Broj lične karte");
					}
					else
						lblLk.setText("Broj lične karte*");
				}
				
				enable= proveraProvere(provera);
				
				btnPotvrdi.setEnabled(enable);
			}
			
		});
		
		txtLk.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				
				if(provera[7]==false) {
					
					txtLk.setText("");
		
				}	
			}
			@Override
			public void focusLost(FocusEvent e) {
				
				if(provera[7]==false && txtLk.getText().trim().length()!=0) {
					
					JOptionPane.showMessageDialog(DialogDodavanjeProfesora.this, "Greška prilikom unosa broja lične karte. Broj mora imati devet cifara.", "Greška: ", JOptionPane.ERROR_MESSAGE);
					txtLk.setText("");	
				}
			}
		});
		
		btnPotvrdi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
			
				
				profesor = new Profesor();
				profesor.setIme(txtIme.getText());
				profesor.setPrezime(txtPrezime.getText());
				profesor.setDatumRodjenja(txtDatum.getText());
				profesor.setAdresaStanovanja(txtAdresa.getText());
				profesor.setTelefon(txtTelefon.getText());
				profesor.setEmail(txtEmail.getText());
				profesor.setAdresaKancelarije(txtKancelarija.getText());
				profesor.setBrLicneKarte(txtLk.getText());
				profesor.setTitula(cboxTitula.getSelectedItem().toString());
				profesor.setZvanje(cboxZvanje.getSelectedItem().toString());
				profesor.setPredmeti(null);
				
				ProfesoriController pc = new ProfesoriController(profesor);
				pc.dodajProfesora();
				
				JOptionPane.showMessageDialog(null, "Unos novog profesora je uspešno izvršen!");
				
				txtIme.setText("");
				txtPrezime.setText("");
				txtDatum.setText("");
				txtAdresa.setText("");
				txtTelefon.setText("");
				txtEmail.setText("");
				txtKancelarija.setText("");
				txtLk.setText("");
				cboxTitula.setSelectedIndex(0);
				cboxZvanje.setSelectedIndex(0);
				
			}
		});
	
	}
	
	private Boolean proveraProvere(Boolean []provera) {
		
		Boolean check= false;
		
		for(int i=0; i<8; i++) {
			
			if(provera[i]==false) {
				
				check = false;
				break;
				
			}
			else {
				
				check = true;
				
			}
		}
		return check;
	}
}
