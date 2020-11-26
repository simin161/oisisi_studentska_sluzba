package view;

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

	public Toolbar()
	{
		super(SwingConstants.HORIZONTAL);
		setRollover(true);
		setFloatable(false);
		
		addSeparator();
		
		AbstractActionNew anew = new AbstractActionNew();
		add(anew);
		
		addSeparator();
		
		AbstractActionEdit aedit = new AbstractActionEdit();
		add(aedit);	
		
		addSeparator();
		
		AbstractActionDelete adelete = new AbstractActionDelete();
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
	
}
