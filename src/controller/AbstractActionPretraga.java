/* --- inspiracija-> vezbe, apstraktni dogadjaji, 04 --- */

package controller;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;

import controller.pretraga.Pretraga;

public class AbstractActionPretraga extends AbstractAction{

	private static final long serialVersionUID = 5418007630716582931L;

	private int rbrTaba= -1;
	private String kriterijum="";
	
	@SuppressWarnings("deprecation")
	public AbstractActionPretraga() {
		
		putValue(NAME, "Search");
		putValue(MNEMONIC_KEY, KeyEvent.VK_S);
		putValue(SHORT_DESCRIPTION, "Pretraga podataka");
		putValue(SMALL_ICON, new ImageIcon("images"+File.separator+"search2.png"));
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_MASK));
	
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
			
			Pretraga.pretrazi(this.kriterijum, this.rbrTaba);
		
	}
	
	public void update(String kriterijum, int rbr) {
		
		this.kriterijum= kriterijum;
		this.rbrTaba = rbr;
		
	}

}
