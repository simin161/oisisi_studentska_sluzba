/* --- inspiracija-> vezbe, apstraktni dogadjaji, 04 --- */

package controller;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;

import view.framesAndDialogs.DialogAddStudent;
import view.framesAndDialogs.DialogDodavanjeProfesora;

public class AbstractActionNew extends AbstractAction{

	private static final long serialVersionUID = -5015683075155351255L;

	private int rbrTaba;
	private Container c;
	
	@SuppressWarnings("deprecation")
	public AbstractActionNew(int rbr, Container c) {
		
		this.rbrTaba = rbr;
		this.c = c;
		putValue(NAME, "New");
		putValue(MNEMONIC_KEY, KeyEvent.VK_N);
		putValue(SHORT_DESCRIPTION, "Dodavanje podataka u tabelu");
		putValue(SMALL_ICON, new ImageIcon("images"+ File.separator +"add2.png"));
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_MASK));
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(this.rbrTaba==0)
		{
			DialogAddStudent dialogAddS = new DialogAddStudent(c);
		}
		else if(this.rbrTaba==1)
		{
			DialogDodavanjeProfesora ddp = new DialogDodavanjeProfesora(c);
			ddp.setVisible(true);
		}
		else if(this.rbrTaba==2)
		{
			//predmet
		}
		else
		{
			System.out.println("ERROR");
		}
		
	}

	
	public void updateRbr(int rbr) {
		
		this.rbrTaba = rbr;
		
	}
	
}
