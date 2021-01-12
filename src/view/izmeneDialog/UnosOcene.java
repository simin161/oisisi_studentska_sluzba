package view.izmeneDialog;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import controller.buttonAction.ButtonAction;
import controller.provere.ProveraDatuma;
import model.Ocena;
import model.Predmet;
import model.Student;
import model.baze.NepolozeniBaza;
import model.baze.PredmetBaza;
import model.baze.StudentBaza;
import view.tabbedPanes.PrikazStudenta;

public class UnosOcene extends JDialog {

	private static final long serialVersionUID = -6800913610486865815L;

	public UnosOcene(Container c, int selRow, int selStud,NepolozeniBaza nb) {
		
		Predmet p = nb.getRow(selRow);
		
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenHeight = (int) (screenSize.height * 0.75 * 0.7);
		int screenWidth = (int) (screenSize.width * 0.75 * 0.35);
		
		Dimension dim = new Dimension(140, 20);
		
		setTitle("Unos ocene");
		setSize(screenWidth, screenHeight);
		setLocationRelativeTo(c);
		setModal(true);
		setResizable(false);
		
		JPanel panelMain = new JPanel();
		
		BoxLayout box = new BoxLayout(panelMain, BoxLayout.Y_AXIS);
		
		panelMain.setLayout(box);
		
		JPanel panelSifra = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel lblSifra = new JLabel("Šifra*");
		JTextField txtSifra = new JTextField();
		txtSifra.setText(p.getSifra());
		txtSifra.setEditable(false);
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
		txtNaziv.setEditable(false);
		//txtNaziv.setText(p.getNaziv());
		lblNaziv.setPreferredSize(dim);
		txtNaziv.setPreferredSize(dim);
		panelNaziv.add(Box.createHorizontalStrut(65));
		panelNaziv.add(lblNaziv);
		panelNaziv.add(txtNaziv);
		panelMain.add(panelNaziv);
		
		JPanel panelOcena = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel lblOcena = new JLabel("Ocena*");
		//JTextField txtOcena = new JTextField();
		
		String[] stringOcena = {"6","7","8","9","10"};
		
		@SuppressWarnings({ "unchecked", "rawtypes" })
		JComboBox cboxOcena = new JComboBox(stringOcena);
		
		lblOcena.setPreferredSize(dim);
		cboxOcena.setPreferredSize(dim);
		
		panelOcena.add(Box.createHorizontalStrut(65));
		panelOcena.add(lblOcena);
		panelOcena.add(cboxOcena);
		panelMain.add(panelOcena);
		
		
		JPanel panelDatum = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel lblDatum = new JLabel("Datum*");
		JTextField txtDatum = new JTextField();
		
		lblDatum.setPreferredSize(dim);
		txtDatum.setPreferredSize(dim);
		
		panelDatum.add(Box.createHorizontalStrut(65));
		panelDatum.add(lblDatum);
		panelDatum.add(txtDatum);
		
		panelMain.add(panelDatum);
		
		
		JPanel panelBtn = new JPanel();
		BoxLayout btn = new BoxLayout(panelBtn, BoxLayout.X_AXIS);
		panelBtn.setLayout(btn);
		JButton btnPotvrdi = new JButton("Potvrdi");
		btnPotvrdi.setEnabled(false);
		JButton btnOdustani = new JButton("Odustani");
		panelBtn.add(btnPotvrdi);
		panelBtn.add(Box.createHorizontalStrut(35));
		panelBtn.add(btnOdustani);
		
		panelMain.add(panelBtn);
		
		add(panelMain);
		
		btnPotvrdi.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
			
				
				Student s = StudentBaza.getInstance().getRow(selStud);
				//NepolozeniBaza nb = new NepolozeniBaza();
				//Predmet p = nb.getRow(selRow);
				
				int selected = cboxOcena.getSelectedIndex();
				
				Date date = null;
				
				try {
					
					date = new SimpleDateFormat("dd/MM/yyyy").parse(txtDatum.getText());
		
				}catch(Exception e1) {
					e1.printStackTrace();
				}
				
				Ocena o = new Ocena(s, p, Integer.parseInt(stringOcena[selected]), date);
				
				if(s.getPolozeno()==null) {
					
					s.setPolozeno(new ArrayList<Ocena>());
					
				}
				s.getPolozeno().add(o);
				nb.izbrisi(p);
				
				for(Predmet pr : PredmetBaza.getInstance().getPredmete()) {
					
					if(pr.getSifra().equals(p.getSifra())) {
						
						pr.getPolozili().add(s);
						
					}
					
					
					for(Student st : pr.getNisuPolozili()) {
						
						if(st.getBrIndeksa().equals(s.getBrIndeksa())) {
							
							pr.getNisuPolozili().remove(st);
							break;
						}
						
					}
					
				}
				
				StudentPolozeni.getPrikaz().update("", 0);
				StudentNepolozeni.getPrikaz().update("",0);
				
				StudentPolozeni.getLabelProsek().setText("Prosek: " + StudentPolozeni.getPrikaz().getModel().getBaza().izracunajProsek());
				StudentPolozeni.getLabelEspb().setText("ESPB: " + StudentPolozeni.getPrikaz().getModel().getBaza().izracunajEspb());
				PrikazStudenta.getInstance().update(null, -1);
				
				dispose();
				
			}
			
		});
		
		
		ButtonAction.cancelAction(btnOdustani, this);
		
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
				Boolean provera = false;
				if(txtDatum.getText().trim().length()==0) {
					
					provera= false;
					lblDatum.setText("Datum*");
					enable = false;
				}
				else {
					
					if(txtDatum.getText().length()==10) {
						
						provera = ProveraDatuma.proveriDatum(txtDatum.getText());
						
						if(provera == true) {
							
							enable = true;
							
						}
						
					}
					
				}
				
				btnPotvrdi.setEnabled(enable);
				
				/*else {
					
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
				
				btnPotvrdi.setEnabled(enable);*/
			}
		});
		
	}

}
