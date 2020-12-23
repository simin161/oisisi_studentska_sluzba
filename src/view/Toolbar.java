package view;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import controller.AbstractActionDelete;
import controller.AbstractActionEdit;
import controller.AbstractActionNew;
import controller.AbstractActionPretraga;

public class Toolbar extends JToolBar{

	private static final long serialVersionUID = 4583925805420852995L;

	private int rbrT=0;
	private Container co;
	private AbstractActionNew anew = new AbstractActionNew(rbrT, co);
	private AbstractActionEdit aedit = new AbstractActionEdit();
	private AbstractActionDelete adelete = new AbstractActionDelete();
	
	
	public Toolbar(int rbr, Container c)
	{	
		super(SwingConstants.HORIZONTAL);
		setRollover(true);
		setFloatable(false);
		
		updateRbr(rbr, c);
		
		addSeparator();
		
		anew = new AbstractActionNew(rbrT, co);
		add(anew);
		
		addSeparator();
		
		aedit = new AbstractActionEdit();
		add(aedit);	
		
		addSeparator();
		
		adelete = new AbstractActionDelete();
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
	}
	
	public void updateRbr(int rbr, Container c) {
		
		this.rbrT = rbr;
		this.anew.updateRbr(rbr);
		this.co = c;
		
	}
	
}
