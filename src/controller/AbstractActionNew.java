/* --- inspiracija-> vezbe, apstraktni dogadjaji, 04 --- */

package controller;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;

public class AbstractActionNew extends AbstractAction{

	private static final long serialVersionUID = -5015683075155351255L;

	@SuppressWarnings("deprecation")
	public AbstractActionNew() {
		
		putValue(NAME, "New");
		putValue(MNEMONIC_KEY, KeyEvent.VK_N);
		putValue(SHORT_DESCRIPTION, "Dodavanje podataka u tabelu");
		putValue(SMALL_ICON, new ImageIcon("images/add2.png"));
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_MASK));
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}

	
	
	
}
