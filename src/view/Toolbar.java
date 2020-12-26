package view;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import controller.AbstractActionDelete;
import controller.AbstractActionEdit;
import controller.AbstractActionNew;
import controller.AbstractActionPretraga;
import controller.Pretraga.Pretraga;

public class Toolbar extends JToolBar{

	private static final long serialVersionUID = 4583925805420852995L;

	private int rbrT=0;
	private Container co;
	private JTable tabela = null;
	private AbstractActionNew anew = new AbstractActionNew(rbrT, co);
	private AbstractActionEdit aedit = new AbstractActionEdit();
	private AbstractActionDelete adelete = new AbstractActionDelete(rbrT, co, tabela);
	
	
	public Toolbar(int rbr, Container c, JTable tabela) {
		
		super(SwingConstants.HORIZONTAL);
		setRollover(true);
		setFloatable(false);
		
		updateRbr(rbr, tabela);
		
		addSeparator();
		
		anew = new AbstractActionNew(rbrT, co);
		add(anew);
		
		addSeparator();
		
		aedit = new AbstractActionEdit();
		add(aedit);	
		
		addSeparator();
		
		adelete = new AbstractActionDelete(rbrT, co, tabela);
		add(adelete);
		
		add(Box.createHorizontalGlue());
		
		JTextField txtPretraga = new JTextField(20);
		txtPretraga.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		txtPretraga.setMaximumSize(new Dimension(1200, 32));
		add(txtPretraga);
		
		addSeparator();
		
		AbstractActionPretraga apretraga = new AbstractActionPretraga();
		add(apretraga);
		
		addSeparator();
		addSeparator();
		
		txtPretraga.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				
				pretrazi();
				
				
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				
				pretrazi();
				
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				
				pretrazi();
				
			}
			
			private void pretrazi(){
				
				Pretraga.getInstance().pretrazi("");
				
			}
		});
	}
	
	public void updateRbr(int rbr, JTable tabela) {
		
		this.rbrT = rbr;
		this.anew.updateRbr(rbr);
		this.adelete.updateRbr(rbr);
		this.tabela = tabela;
	}
	
}
