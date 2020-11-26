/* --- inspiracija-> vezbe, apstraktni dogadjaji, 04 --- */

package controller;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;

public class AbstractActionDelete extends AbstractAction{

	private static final long serialVersionUID = 7464546556386918717L;

	@SuppressWarnings("deprecation")
	public AbstractActionDelete() {
		
		putValue(NAME, "Delete");
		putValue(MNEMONIC_KEY, KeyEvent.VK_D);
		putValue(SHORT_DESCRIPTION, "Brisanje podataka iz tabele");
		putValue(SMALL_ICON, new ImageIcon("images/delete2.png"));
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_D, KeyEvent.CTRL_MASK));
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e){
		
	}
	
}
