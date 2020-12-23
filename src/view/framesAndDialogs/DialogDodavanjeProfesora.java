package view.framesAndDialogs;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

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

public class DialogDodavanjeProfesora extends JDialog{

	private static final long serialVersionUID = 3844060477268188498L;

	private int dialog=2;
	
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
		
		MaskFormatter mask = null;
		try {
			
			mask= new MaskFormatter("##/##/####");
			mask.setPlaceholderCharacter('_');
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		JFormattedTextField txtDatum = new JFormattedTextField(mask);
		
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
		JButton btnPonisti = new JButton("Poništi");
		ButtonAction.cancelAction(btnPonisti, this);
		ButtonAction.potvrdiButton(btnPotvrdi, dialog);
		panelBtn.add(btnPotvrdi);
		panelBtn.add(Box.createHorizontalStrut(35));
		panelBtn.add(btnPonisti);
		panelMain.add(panelBtn);
		
		add(panelMain);
		
		
	}
	
}
