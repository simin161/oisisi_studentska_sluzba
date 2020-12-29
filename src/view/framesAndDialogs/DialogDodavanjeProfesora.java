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
import java.util.Date;
import java.util.List;

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
import controller.provere.ProveraAdrese;
import controller.provere.ProveraDatuma;
import controller.provere.ProveraEmaila;
import controller.provere.ProveraGodine;
import controller.provere.ProveraImena;
import controller.provere.ProveraLk;
import controller.provere.ProveraPrezimena;
import controller.provere.ProveraTelefona;
import model.Profesor;
import model.baze.ProfesorBaza;
import model.nabrojiviTipovi.Titula;
import model.nabrojiviTipovi.Zvanje;


public class DialogDodavanjeProfesora extends JDialog{

	private static final long serialVersionUID = 3844060477268188498L;
	
	private Profesor profesor;
	private Boolean []provera= {false, false, false, false, false, false, false, false};
	private Boolean brLk = false;
	
	public DialogDodavanjeProfesora(Container c)
	{
		List<Profesor> profesori = ProfesorBaza.getInstance().getProfesore();
		
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize= kit.getScreenSize();
		int screenHeight = (int) (screenSize.height * 0.75 * 0.8);
		int screenWidth = (int) (screenSize.width * 0.75 * 0.4);

		setTitle("Dodavanje profesora");
		setSize(screenWidth, screenHeight);
		setLocationRelativeTo(c);
		setModal(true);
		setResizable(false);
		Dimension dim= new Dimension(155,20);
		
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
		JTextField txtTelefon = new JTextField();
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
		Titula []titula = Titula.values();
		String []titule= {"","","","",""};
		
		for(int i = 0; i < titula.length; i++) {
			
			switch(titula[i]) {
			
				case profesor_doktor : titule[i] = "Profesor doktor"; break;
				default: titule[i]= titula[i].toString();
		
			}
		}
		
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
		
		Zvanje []zvanje = Zvanje.values();
		String []zvanja= {"","","","","","","",""};
		
		for(int i = 0; i < zvanje.length; i++) {
			
			switch(zvanje[i]) {
			
				case Saradnik_u_nastavi : zvanja[i] = "Saradnik u nastavi"; break;
				case Asistent_sa_doktoratom : zvanja[i]= "Asistent sa doktoratom"; break;
				case Vanredni_profesor : zvanja[i]= "Vanredni profesor"; break;
				case Redovni_profesor : zvanja[i]= "Redovni profesor"; break;
				case Profesor_emeritus : zvanja[i]= "Profesor emeritus"; break;
				default : zvanja[i]= zvanje[i].toString();
		
			}
		}
		
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
				
				if(txtIme.getText().trim().length()==0) {
					
					provera[0]= false;
					lblIme.setText("Ime*");
				}
				else {
					
					provera[0] = ProveraImena.proveriIme(txtIme.getText());
					if(provera[0]==true)
						lblIme.setText("Ime");
					else
						lblIme.setText("Ime*");
					
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
				
				if(provera[0]==false && txtIme.getText().trim().length()!=0) {
					
					JOptionPane.showMessageDialog(DialogDodavanjeProfesora.this, "Greška prilikom unosa imena.", 
							"Greška: ", JOptionPane.ERROR_MESSAGE);
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
				
				if(txtPrezime.getText().trim().length()==0) {
					
					provera[1]= false;
					lblPrezime.setText("Prezime*");
				
				}
				else {
					
					provera[1]= ProveraPrezimena.proveriPrezime(txtPrezime.getText());
					
					if(provera[1]==true)
						lblPrezime.setText("Prezime");
					else
						lblPrezime.setText("Prezime*");
					
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
				
					if(provera[1]==false && txtPrezime.getText().trim().length()!=0) {
						
						JOptionPane.showMessageDialog(DialogDodavanjeProfesora.this, "Greška prilikom unosa prezimena. "
								+ "Ukoliko imate dva ili više prezimena, unesite ih sa povlakom između.", "Greška: ", JOptionPane.ERROR_MESSAGE);
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
				if(txtDatum.getText().trim().length()==0) {
					
					provera[2]= false;
					lblDatum.setText("Datum rođenja*");
					
				}
				else {
					
					if(txtDatum.getText().length()==10) {
						
						provera[2]= ProveraDatuma.proveriDatum(txtDatum.getText());
						if(provera[2]==true) {
							
							if(proveraProvere(provera)==true) {
							
								enable = ProveraGodine.proveri(txtDatum.getText(), 2);
								
							}
							else
								enable = false;
							
						}
						else{

							enable = false;
							
						}
						
					}
					else {
						
						enable = false;
						
					}
					
				}
				
				
				if(provera[2]== true && ProveraGodine.proveri(txtDatum.getText(), 2)==true) {
					
					lblDatum.setText("Datum rođenja");
					
				}
				else {
					
					lblDatum.setText("Datum rođenja*");
					
				}
				
				if(provera[2]==true && ProveraGodine.proveri(txtDatum.getText(), 2)==false) {
					
					txtDatum.setToolTipText("Profesor ne sme imati manje od 23 godine.");
					lblDatum.setForeground(Color.red);
					lblDatum.setToolTipText("Profesor ne sme imati manje od 23 godine.");
					
				}
				else {
					
					txtDatum.setToolTipText(null);
					lblDatum.setForeground(Color.black);
					lblDatum.setToolTipText(null);
				}
				
				btnPotvrdi.setEnabled(enable);
			}
		});
		
		txtDatum.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				
				if(provera[2]==false) {
					
					txtDatum.setText("");
					
				}
				
				if(txtDatum.getText().equals("")) {
					
					btnPotvrdi.setEnabled(false);
					
				}
				
			}
			@Override
			public void focusLost(FocusEvent e) {
		
				if(provera[2]==true) {
					
					Boolean check = true;
					check = ProveraGodine.proveri(txtDatum.getText(), 2);
					provera[2]=check;
					
					if(check==false) {
						
						JOptionPane.showMessageDialog(DialogDodavanjeProfesora.this, "Greška prilikom unosa datuma. "
								+ "Proverite Vaše podatke i probajte ponovo.", "Greška: ", JOptionPane.ERROR_MESSAGE);
						txtDatum.setText("");
						
						
					}
					
				}
				if(provera[2]==false && txtDatum.getText().trim().length()!=0) {
					
					JOptionPane.showMessageDialog(DialogDodavanjeProfesora.this, "Greška prilikom unosa datuma. "
							+ "Datum uneti u sledećem formatu: DD/MM/YYYY", "Greška: ", JOptionPane.ERROR_MESSAGE);
					txtDatum.setText("");
					
					btnPotvrdi.setEnabled(false);
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
				
				if(txtAdresa.getText().trim().length()==0) {
					
					provera[3]= false;
					lblAdresa.setText("Adresa stanovanja*");
				}
				else {
					
					provera[3]= ProveraAdrese.proveriAdresu(txtAdresa.getText());
					if(provera[3]==false)
						lblAdresa.setText("Adresa stanovanja*");
					else
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
			
				if(provera[3]==false && txtAdresa.getText().trim().length()!=0) {
					
					JOptionPane.showMessageDialog(DialogDodavanjeProfesora.this, "Greška prilikom unosa adrese. "
							+ "Adresu je potrebno uneti u sledećem formatu: ULICA I BROJ, MESTO BORAVKA. ", "Greška: ", JOptionPane.ERROR_MESSAGE);
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
				if(txtTelefon.getText().trim().length()==0) {
					
					provera[4]= false;
					lblTelefon.setText("Kontakt telefon*");
				
				}
				else {
					
					provera[4]= ProveraTelefona.proveriTelefon(txtTelefon.getText());
					if(provera[4]==true) {
						
						lblTelefon.setText("Kontakt telefon");
						
					}
					else {
						
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
				
				if(provera[4]==false && txtTelefon.getText().trim().length()!=0) {
					
					JOptionPane.showMessageDialog(DialogDodavanjeProfesora.this, "Greška prilikom unosa broja telefona. "
							+ "Broj uneti u jednom od sledećih formata: 000 000 0000 | 000-000-0000", "Greška: ", JOptionPane.ERROR_MESSAGE);
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
				
				if(txtEmail.getText().trim().length()==0)
				{
					provera[5]= false;
					lblEmail.setText("E-mail adresa*");
				}
				else
				{
					provera[5]= ProveraEmaila.proveriEmail(txtEmail.getText());
					if(provera[5]== true) 
						lblEmail.setText("E-mail adresa");
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
					
					JOptionPane.showMessageDialog(DialogDodavanjeProfesora.this, "Greška prilikom unosa e-mail adrese. "
							+ "Unesite validnu e-mail adresu poput: example@something.domain ", "Greška: ", JOptionPane.ERROR_MESSAGE);
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
				
				if(txtKancelarija.getText().trim().length()==0) {
					
					provera[6]= false;
					lblKancelarija.setText("Adresa kancelarije*");
				
				}
				else {
					
					provera[6]= ProveraAdrese.proveriAdresu(txtKancelarija.getText());
					
					if(provera[6]==false)
						lblKancelarija.setText("Adresa kancelarije*");
					else
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
					
					JOptionPane.showMessageDialog(DialogDodavanjeProfesora.this, "Greška prilikom unosa adrese kancelarije. "
							+ "Proverite Vaše podatke i probajte ponovo.", "Greška: ", JOptionPane.ERROR_MESSAGE);
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
				if(txtLk.getText().trim().length()==0) {
					
					provera[7]= false;
					lblLk.setText("Broj lične karte*");
					
				}
				else {
					
					provera[7]= ProveraLk.proveriBrLk(txtLk.getText());
					
					if(provera[7]== true) 
						lblLk.setText("Broj lične karte");
					else
						lblLk.setText("Broj lične karte*");
				}
				
				enable= proveraProvere(provera);
				
				if(txtLk.getText().length() == 9) {
					
					//povuci bazu podataka
					brLk = ProveraLk.postojiLk(profesori, txtLk.getText());
					
					
				}
				
				if(brLk == true) {
					
					btnPotvrdi.setEnabled(false);
					lblLk.setText("Broj lične karte*");
				}
				else
				{	
					btnPotvrdi.setEnabled(enable);
					lblLk.setText("Broj lične karte");
				}
			}
			
		});
		
		txtLk.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				
				if(provera[7]==false) 
					txtLk.setText("");
				
			}
			@Override
			public void focusLost(FocusEvent e) {
				
				if(provera[7]==false && txtLk.getText().trim().length()!=0) {
					
					JOptionPane.showMessageDialog(DialogDodavanjeProfesora.this, "Greška prilikom unosa broja lične karte. "
							+ "Broj mora imati devet cifara.", "Greška: ", JOptionPane.ERROR_MESSAGE);
					txtLk.setText("");	
				}
				else if(brLk == true) {
					
					JOptionPane.showMessageDialog(DialogDodavanjeProfesora.this, "Greška prilikom unosa broja lične karte. "
							+ "Broj lične karte je već registrovan u sistemu.", "Greška: ", JOptionPane.ERROR_MESSAGE);
					txtLk.setText("");
					
				}
			}
		});
		
		btnPotvrdi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
			
				if(proveraProvere(provera) == true) {
					
					Date date = null;
					
					try {
						date = new SimpleDateFormat("dd/MM/yyyy").parse(txtDatum.getText());
					} catch (ParseException e1) {
						
						e1.printStackTrace();
					}
					
					String []parts = txtPrezime.getText().split("-");
					String []prezime = parts;
					String novoP="";
					int i=0;
					
					for(String s : parts) {
						
						String pocetno= s.substring(0,1).toUpperCase();
						String ostatak= s.substring(1);
						
						prezime[i]= pocetno+ostatak;

						i++;
					}
					
					i=0;
					while(i!=prezime.length) {
						
						if(i==0) {
							
							novoP= prezime[i];
							
						}
						else {
							novoP= novoP + '-' + prezime[i];
						}
						
						i++;
					}
					txtPrezime.setText(novoP);
					
					String pocetno = txtIme.getText().substring(0,1).toUpperCase();
					String ostatak = txtIme.getText().substring(1);
					txtIme.setText(pocetno+ostatak);
					
					profesor = new Profesor();
					profesor.setIme(txtIme.getText());
					profesor.setPrezime(txtPrezime.getText());
					profesor.setDatumRodjenja(date);
					profesor.setAdresaStanovanja(txtAdresa.getText());
					profesor.setTelefon(txtTelefon.getText());
					profesor.setEmail(txtEmail.getText());
					profesor.setAdresaKancelarije(txtKancelarija.getText());
					profesor.setBrLicneKarte(txtLk.getText());
					profesor.setTitula(titula[cboxTitula.getSelectedIndex()]);
					profesor.setZvanje(zvanje[cboxZvanje.getSelectedIndex()]);
					profesor.setPredmeti(null);
					
					ProfesoriController pc = new ProfesoriController(profesor);
					pc.dodajProfesora();
					
					JOptionPane.showMessageDialog(null, "Unos novog profesora je uspešno izvršen!");
					
					dispose();	
					
				}			
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
