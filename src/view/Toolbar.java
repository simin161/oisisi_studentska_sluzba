package view;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import controller.AbstractActionDelete;
import controller.AbstractActionEdit;
import controller.AbstractActionNew;
import controller.AbstractActionPretraga;
import view.tabbedPanes.PrikazPredmeta;
import view.tabbedPanes.PrikazProfesora;
import view.tabbedPanes.PrikazStudenta;

public class Toolbar extends JToolBar{

	private static final long serialVersionUID = 4583925805420852995L;

	private int rbrT=0;
	private Container co;
	private AbstractActionNew anew = new AbstractActionNew(rbrT, co);
	private AbstractActionEdit aedit = new AbstractActionEdit(rbrT, co);
	private AbstractActionDelete adelete = new AbstractActionDelete(rbrT, co);
	private String kriterijum="";
	private AbstractActionPretraga apretraga = new AbstractActionPretraga();
	
	public Toolbar(int rbr, Container c) {
		
		super(SwingConstants.HORIZONTAL);
		setRollover(true);
		setFloatable(false);
		
		this.co = c;
		
		updateRbr(rbr);
		
		addSeparator();
		
		anew = new AbstractActionNew(rbrT, co);
		add(anew);
		
		addSeparator();
		
		aedit = new AbstractActionEdit(rbrT, co);
		add(aedit);	
		
		addSeparator();
		
		adelete = new AbstractActionDelete(rbrT, co);
		add(adelete);
		
		add(Box.createHorizontalGlue());
		
		JTextField txtPretraga = new JTextField(20);
		txtPretraga.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		txtPretraga.setMaximumSize(new Dimension(1200, 32));
		add(txtPretraga);
		
		addSeparator();
		
		apretraga = new AbstractActionPretraga();	
		
		add(apretraga);
		
		addSeparator();
		addSeparator();
		
		txtPretraga.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				
				update();
				
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				
				update();
				
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				
			}
			
			private void update() {
				
				kriterijum = txtPretraga.getText();
				apretraga.update(kriterijum, rbrT);
			}
			
		});
		
		
		txtPretraga.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				reset();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				reset();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				reset();
			}
			
			
			private void reset(){
				
				if(txtPretraga.getText().trim().length() == 0) {
					
					if(rbrT == 0) {
						
						PrikazStudenta.getInstance().pretraziTabelu("");
					}
					else if(rbrT == 1) {
						
						PrikazProfesora.getInstance().pretraziTabelu("");
					
					}
					else {
						
						PrikazPredmeta.getInstance().pretraziTabelu("");
					}
				}	
			}
		});
	}
	
	public void updateRbr(int rbr) {
		
		this.rbrT = rbr;
		this.anew.updateRbr(rbr);
		this.adelete.updateRbr(rbr);
		this.aedit.updateRbr(rbr);
	}
	
}
