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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

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
import view.framesAndDialogs.DialogDodavanjeProfesora;

public class ProfesorInformacije extends JPanel {

	private static final long serialVersionUID = 6158052650225024929L;

	private Boolean []provera = {true, true, true, true, true, true, true, true};
	
	private String staraLicna="";
	private Boolean brLk = false;
	private List<Profesor> profesori = ProfesorBaza.getInstance().getProfesore();
	
	public ProfesorInformacije(int selectedRow) {
		
		Dimension dim= new Dimension(155,20);
		
		BoxLayout box = new BoxLayout(this, BoxLayout.Y_AXIS);
	
		setLayout(box);
		
		JPanel panelIme = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JLabel lblIme = new JLabel("Ime");
		JTextField txtIme = new JTextField();
		lblIme.setPreferredSize(dim);
		txtIme.setPreferredSize(dim);
		panelIme.add(Box.createHorizontalStrut(65));
		panelIme.add(lblIme);
		panelIme.add(txtIme);
		
		add(panelIme);
		
		JPanel panelPrezime = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JLabel lblPrezime = new JLabel("Prezime");
		JTextField txtPrezime = new JTextField();
		lblPrezime.setPreferredSize(dim);
		txtPrezime.setPreferredSize(dim);
		panelPrezime.add(Box.createHorizontalStrut(65));
		panelPrezime.add(lblPrezime);
		panelPrezime.add(txtPrezime);
		
		add(panelPrezime);
		
		JPanel panelDatum= new JPanel(new FlowLayout(FlowLayout.CENTER));
		JLabel lblDatum = new JLabel("Datum rođenja");
		JFormattedTextField txtDatum = new JFormattedTextField();
		lblDatum.setPreferredSize(dim);
		txtDatum.setPreferredSize(dim);
		txtDatum.setToolTipText("Dan/Mesec/Godina");
		panelDatum.add(Box.createHorizontalStrut(65));
		panelDatum.add(lblDatum);
		panelDatum.add(txtDatum);
		
		add(panelDatum);
		
		JPanel panelAdresa = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JLabel lblAdresa= new JLabel("Adresa stanovanja ");
		JTextField txtAdresa = new JTextField();
		lblAdresa.setPreferredSize(dim);
		txtAdresa.setPreferredSize(dim);
		panelAdresa.add(Box.createHorizontalStrut(65));
		panelAdresa.add(lblAdresa);
		panelAdresa.add(txtAdresa);
		
		add(panelAdresa);
		
		JPanel panelTelefon = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JLabel lblTelefon = new JLabel("Kontakt telefon ");
		JTextField txtTelefon = new JTextField();
		lblTelefon.setPreferredSize(dim);
		txtTelefon.setPreferredSize(dim);
		panelTelefon.add(Box.createHorizontalStrut(65));
		panelTelefon.add(lblTelefon);
		panelTelefon.add(txtTelefon);
		
		add(panelTelefon);
	
		JPanel panelEmail = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JLabel lblEmail = new JLabel("E-mail adresa ");
		JTextField txtEmail = new JTextField();
		lblEmail.setPreferredSize(dim);
		txtEmail.setPreferredSize(dim);
		panelEmail.add(Box.createHorizontalStrut(65));
		panelEmail.add(lblEmail);
		panelEmail.add(txtEmail);
		
		add(panelEmail);
		
		JPanel panelKancelarija = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JLabel lblKancelarija = new JLabel("Adresa kancelarije ");
		JTextField txtKancelarija = new JTextField();
		lblKancelarija.setPreferredSize(dim);
		txtKancelarija.setPreferredSize(dim);
		panelKancelarija.add(Box.createHorizontalStrut(65));
		panelKancelarija.add(lblKancelarija);
		panelKancelarija.add(txtKancelarija);
		
		add(panelKancelarija);
		
		JPanel panelLk = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JLabel lblLk = new JLabel("Broj lične karte ");
		JTextField txtLk = new JTextField();
		lblLk.setPreferredSize(dim);
		txtLk.setPreferredSize(dim);
		panelLk.add(Box.createHorizontalStrut(65));
		panelLk.add(lblLk);
		panelLk.add(txtLk);
		
		add(panelLk);
		
		JPanel panelTitula = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JLabel lblTitula = new JLabel("Titula ");
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
		
		add(panelTitula);
		
		JPanel panelZvanje = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JLabel lblZvanje = new JLabel("Zvanje ");
		
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
		
		add(panelZvanje);
		
		JPanel panelBtn = new JPanel();
		BoxLayout btn = new BoxLayout(panelBtn, BoxLayout.X_AXIS);
		panelBtn.setLayout(btn);
		JButton btnPotvrdi = new JButton("Potvrdi");
		btnPotvrdi.setEnabled(true);
		JButton btnPonisti = new JButton("Poništi");
		panelBtn.add(btnPotvrdi);
		panelBtn.add(Box.createHorizontalStrut(35));
		panelBtn.add(btnPonisti);
		
		add(panelBtn);
		
		btnPonisti.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				Window w = SwingUtilities.getWindowAncestor(ProfesorInformacije.this);
				w.dispose();
			
			}
		});
		
		Profesor profesor= ProfesorBaza.getInstance().getRow(selectedRow);
		
		btnPotvrdi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(proveraProvere(provera) == true) {
					
					Date date = null;
					
					try {
						
						date = new SimpleDateFormat("dd/MM/yyyy").parse(txtDatum.getText());
			
					}catch(Exception e1) {
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
					
					Profesor p = new Profesor();
					p.setIme(txtIme.getText());
					p.setPrezime(txtPrezime.getText());
					p.setDatumRodjenja(date);
					p.setAdresaStanovanja(txtAdresa.getText());
					p.setTelefon(txtTelefon.getText());
					p.setEmail(txtEmail.getText());
					p.setAdresaKancelarije(txtKancelarija.getText());
					p.setBrLicneKarte(txtLk.getText());
					p.setTitula(titula[cboxTitula.getSelectedIndex()]);
					p.setZvanje(zvanje[cboxZvanje.getSelectedIndex()]);
					p.setPredmeti(profesor.getPredmeti());
	
					ProfesoriController.getInstance().izmeniProfesora(p, staraLicna);
					
					JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(ProfesorInformacije.this), "Selektovan profesor je uspešno izmenjen.");
					SwingUtilities.getWindowAncestor(ProfesorInformacije.this).dispose();
				}
				
			}
		});
		
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String date = df.format(profesor.getDatumRodjenja());
		
		txtIme.setText(profesor.getIme());
		txtPrezime.setText(profesor.getPrezime());
		txtDatum.setText(date);
		txtAdresa.setText(profesor.getAdresaStanovanja());
		txtTelefon.setText(profesor.getTelefon());
		txtEmail.setText(profesor.getEmail());
		txtKancelarija.setText(profesor.getAdresaKancelarije());
		txtLk.setText(profesor.getBrLicneKarte());
		staraLicna = profesor.getBrLicneKarte();
		cboxTitula.setSelectedItem(profesor.getTitula().toString());
		cboxZvanje.setSelectedItem(profesor.getZvanje().toString());
		
		txtIme.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				
				provera();
				
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				
				provera();
				
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				
				provera();
				
			}
			
			private void provera() {
				
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
				
			}

			@Override
			public void focusLost(FocusEvent e) {
				
				if(provera[0]==false && txtIme.getText().trim().length() != 0) {
					
					JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(ProfesorInformacije.this), "Greška prilikom unosa imena. Proverite Vaše podatke i probajte ponovo.", 
							"Greška: ", JOptionPane.ERROR_MESSAGE);
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
				
			}

			@Override
			public void focusLost(FocusEvent e) {
				
					if(provera[1]==false && txtPrezime.getText().trim().length()!=0) {
						
						JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(ProfesorInformacije.this), "Greška prilikom unosa prezimena. "
								+ "Ukoliko imate dva ili više prezimena, unesite ih sa povlakom između.", "Greška: ", JOptionPane.ERROR_MESSAGE);
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
				
				if(provera[2]==true && ProveraGodine.proveri(txtDatum.getText(), 2)==false && txtDatum.getText().length() == 10) {
					
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
				
				if(txtDatum.getText().equals("")) {
					
					btnPotvrdi.setEnabled(false);
					
				}
				
			}
			@Override
			public void focusLost(FocusEvent e) {
		
				if(provera[2]==true && txtDatum.getText().length()== 10) {
					
					Boolean check = true;
					check = ProveraGodine.proveri(txtDatum.getText(), 2);
					provera[2]=check;
					
					if(check==false) {
						
						JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(ProfesorInformacije.this), "Greška prilikom unosa datuma. "
								+ "Proverite Vaše podatke i probajte ponovo.", "Greška: ", JOptionPane.ERROR_MESSAGE);
						
						
					}
					
				}
				if(provera[2]==false && txtDatum.getText().length()!=0 && txtDatum.getText().length()!= 10) {
					
					JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(ProfesorInformacije.this), "Greška prilikom unosa datuma. "
							+ "Datum uneti u sledećem formatu: DD/MM/YYYY", "Greška: ", JOptionPane.ERROR_MESSAGE);
							
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
				
			}

			@Override
			public void focusLost(FocusEvent e) {
			
				if(provera[3]==false && txtAdresa.getText().trim().length()!=0) {
					
					JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(ProfesorInformacije.this), "Greška prilikom unosa adrese. "
							+ "Adresu je potrebno uneti u sledećem formatu: ULICA I BROJ, MESTO BORAVKA. ", "Greška: ", JOptionPane.ERROR_MESSAGE);
					
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
				
			}

			@Override
			public void focusLost(FocusEvent e) {
				
				if(provera[4]==false && txtTelefon.getText().trim().length()!=0) {
					
					JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(ProfesorInformacije.this), "Greška prilikom unosa broja telefona. "
							+ "Broj uneti u jednom od sledećih formata: 000 000 0000 | 000-000-0000", "Greška: ", JOptionPane.ERROR_MESSAGE);
					
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
				
			}

			@Override
			public void focusLost(FocusEvent e) {
				
				if(provera[5]==false && txtEmail.getText().trim().length()!=0) {
					
					JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(ProfesorInformacije.this), "Greška prilikom unosa e-mail adrese. "
							+ "Unesite validnu e-mail adresu poput: example@something.domain ", "Greška: ", JOptionPane.ERROR_MESSAGE);
					
					
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
	
			}

			@Override
			public void focusLost(FocusEvent e) {
			
				if(provera[6]==false && txtKancelarija.getText().trim().length()!=0) {
					
					JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(ProfesorInformacije.this), "Greška prilikom unosa adrese kancelarije. "
							+ "Proverite Vaše podatke i probajte ponovo.", "Greška: ", JOptionPane.ERROR_MESSAGE);
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
					
					brLk = ProveraLk.postojiLk(profesori, txtLk.getText());
					
				}
				
				if(brLk == true) {
					
					btnPotvrdi.setEnabled(false);
					lblLk.setText("Broj lične karte*");
				}
				
				else {
				
					btnPotvrdi.setEnabled(enable);
					lblLk.setText("Broj lične karte");
				}
			}
			
		});
		
		txtLk.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				
			}
			@Override
			public void focusLost(FocusEvent e) {
				
				if(provera[7]==false && txtLk.getText().trim().length()!=0) {
					
					JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(ProfesorInformacije.this), "Greška prilikom unosa broja lične karte. "
							+ "Broj mora imati devet cifara.", "Greška: ", JOptionPane.ERROR_MESSAGE);
					
				}
				if(brLk == true) {
					
					JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(ProfesorInformacije.this), "Greška prilikom unosa broja lične karte. "
							+ "Broj lične karte je već registrovan u sistemu.", "Greška: ", JOptionPane.ERROR_MESSAGE);	
					
				
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
