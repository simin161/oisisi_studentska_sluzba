package view.izmeneDialog;

import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import model.baze.ProfPredBaza;
import view.tabbedPanes.PrikazProfPredmeta;

public class ProfPredajePredmete extends JPanel {

	private static final long serialVersionUID = 3309819413449241249L;

	private PrikazProfPredmeta prikaz;
	
	public ProfPredajePredmete() {
		
		BoxLayout box = new BoxLayout(this, BoxLayout.Y_AXIS);
		setLayout(box);
		JPanel panelBtn = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton btnDodaj = new JButton("Dodaj predmet");
		JButton btnUkloni = new JButton("Ukloni predmet");
		
		add(Box.createVerticalStrut(10));
		panelBtn.add(btnDodaj);
		panelBtn.add(Box.createHorizontalStrut(15));
		panelBtn.add(btnUkloni);
		add(panelBtn);
		add(Box.createVerticalStrut(10));
		
		prikaz = new PrikazProfPredmeta();
		
		ProfPredBaza ppb = prikaz.getModel().getBaza();
		
		add(prikaz);
		
	}
	
}
