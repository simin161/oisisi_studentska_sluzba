package view.izmeneDialog;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.nabrojiviTipovi.Titula;
import model.nabrojiviTipovi.Zvanje;

public class ProfesorInformacije extends JPanel {

	private static final long serialVersionUID = 6158052650225024929L;

	private static ProfesorInformacije instance = null;
	
	public static ProfesorInformacije getInstance() {
		
		if(instance == null) {
			
			instance = new ProfesorInformacije();
			
		}
		
		return instance;
		
	}
	
	private ProfesorInformacije() {
		
		Dimension dim= new Dimension(155,20);
		
		BoxLayout box = new BoxLayout(this, BoxLayout.Y_AXIS);
	
		setLayout(box);
		
		JPanel panelIme = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JLabel lblIme = new JLabel("Ime* ");
		JTextField txtIme = new JTextField();
		lblIme.setPreferredSize(dim);
		txtIme.setPreferredSize(dim);
		panelIme.add(Box.createHorizontalStrut(65));
		panelIme.add(lblIme);
		panelIme.add(txtIme);
		
		add(panelIme);
		
		JPanel panelPrezime = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JLabel lblPrezime = new JLabel("Prezime*");
		JTextField txtPrezime = new JTextField();
		lblPrezime.setPreferredSize(dim);
		txtPrezime.setPreferredSize(dim);
		panelPrezime.add(Box.createHorizontalStrut(65));
		panelPrezime.add(lblPrezime);
		panelPrezime.add(txtPrezime);
		
		add(panelPrezime);
		
		JPanel panelDatum= new JPanel(new FlowLayout(FlowLayout.CENTER));
		JLabel lblDatum = new JLabel("Datum rođenja* ");
		JFormattedTextField txtDatum = new JFormattedTextField();
		lblDatum.setPreferredSize(dim);
		txtDatum.setPreferredSize(dim);
		txtDatum.setToolTipText("Dan/Mesec/Godina");
		panelDatum.add(Box.createHorizontalStrut(65));
		panelDatum.add(lblDatum);
		panelDatum.add(txtDatum);
		
		add(panelDatum);
		
		JPanel panelAdresa = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JLabel lblAdresa= new JLabel("Adresa stanovanja* ");
		JTextField txtAdresa = new JTextField();
		lblAdresa.setPreferredSize(dim);
		txtAdresa.setPreferredSize(dim);
		panelAdresa.add(Box.createHorizontalStrut(65));
		panelAdresa.add(lblAdresa);
		panelAdresa.add(txtAdresa);
		
		add(panelAdresa);
		
		JPanel panelTelefon = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JLabel lblTelefon = new JLabel("Kontakt telefon* ");
		JTextField txtTelefon = new JTextField();
		lblTelefon.setPreferredSize(dim);
		txtTelefon.setPreferredSize(dim);
		panelTelefon.add(Box.createHorizontalStrut(65));
		panelTelefon.add(lblTelefon);
		panelTelefon.add(txtTelefon);
		
		add(panelTelefon);
	
		JPanel panelEmail = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JLabel lblEmail = new JLabel("E-mail adresa* ");
		JTextField txtEmail = new JTextField();
		lblEmail.setPreferredSize(dim);
		txtEmail.setPreferredSize(dim);
		panelEmail.add(Box.createHorizontalStrut(65));
		panelEmail.add(lblEmail);
		panelEmail.add(txtEmail);
		
		add(panelEmail);
		
		JPanel panelKancelarija = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JLabel lblKancelarija = new JLabel("Adresa kancelarije* ");
		JTextField txtKancelarija = new JTextField();
		lblKancelarija.setPreferredSize(dim);
		txtKancelarija.setPreferredSize(dim);
		panelKancelarija.add(Box.createHorizontalStrut(65));
		panelKancelarija.add(lblKancelarija);
		panelKancelarija.add(txtKancelarija);
		
		add(panelKancelarija);
		
		JPanel panelLk = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JLabel lblLk = new JLabel("Broj lične karte* ");
		JTextField txtLk = new JTextField();
		lblLk.setPreferredSize(dim);
		txtLk.setPreferredSize(dim);
		panelLk.add(Box.createHorizontalStrut(65));
		panelLk.add(lblLk);
		panelLk.add(txtLk);
		
		add(panelLk);
		
		JPanel panelTitula = new JPanel(new FlowLayout(FlowLayout.CENTER));
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
		
		add(panelTitula);
		
		JPanel panelZvanje = new JPanel(new FlowLayout(FlowLayout.CENTER));
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
		
		add(panelZvanje);
		
		JPanel panelBtn = new JPanel();
		BoxLayout btn = new BoxLayout(panelBtn, BoxLayout.X_AXIS);
		panelBtn.setLayout(btn);
		JButton btnPotvrdi = new JButton("Potvrdi");
		btnPotvrdi.setEnabled(false);
		JButton btnPonisti = new JButton("Poništi");
		panelBtn.add(btnPotvrdi);
		panelBtn.add(Box.createHorizontalStrut(35));
		panelBtn.add(btnPonisti);
		
		add(panelBtn);
		
		btnPonisti.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
		
				
				
			}
		});
		
	}
	
	
	
}
