/* --- inspiracija-> vezbe, apstraktni dogadjaji, 04 --- */

package controller;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;

public class AbstractActionEdit extends AbstractAction{
	
	private static final long serialVersionUID = 543708384897911092L;

	@SuppressWarnings("deprecation")
	public AbstractActionEdit() {
		
		putValue(NAME, "Edit");
		putValue(MNEMONIC_KEY, KeyEvent.VK_E);
		putValue(SHORT_DESCRIPTION, "Izmena podataka iz tabele");
		putValue(SMALL_ICON, new ImageIcon("images"+File.separator+"edit2.png"));
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_E, KeyEvent.CTRL_MASK));
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e){
		
	}
	
}
