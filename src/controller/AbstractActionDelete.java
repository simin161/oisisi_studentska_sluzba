/* --- inspiracija-> vezbe, apstraktni dogadjaji, 04 --- */

package controller;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;

public class AbstractActionDelete extends AbstractAction{

	private static final long serialVersionUID = 7464546556386918717L;

	private int rbrTaba;
	private Container c;
	private JTable tabela;
	
	@SuppressWarnings("deprecation")
	public AbstractActionDelete(int rbr, Container c, JTable tabela) {
		
		this.rbrTaba = rbr;
		this.c = c;
		this.tabela = tabela;
		
		putValue(NAME, "Delete");
		putValue(MNEMONIC_KEY, KeyEvent.VK_D);
		putValue(SHORT_DESCRIPTION, "Brisanje podataka iz tabele");
		putValue(SMALL_ICON, new ImageIcon("images"+File.separator+"delete2.png"));
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_D, KeyEvent.CTRL_MASK));
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e){
		
		if(this.rbrTaba==0) {
			
			//student
			
		}
		else if(this.rbrTaba==1) {
			
			//profesor
			
		}
		else if(this.rbrTaba==2) {
			
			//if()  provera da li je selektovan red? 
			
			System.out.println(tabela.getSelectedRow());
			
			if(tabela.getSelectedRow()!=-1) {
				
				
				String []opcije = new String[2];
				
				opcije[0]= "Da"; //vrednost 0
				opcije[1]= "Ne"; //vrednost 1
				
				int i = JOptionPane.showOptionDialog(c, "Da li ste sigurni da želite da obrišete predmet?", "Brisanje predmeta", JOptionPane.YES_NO_OPTION, JOptionPane.DEFAULT_OPTION, null, opcije, opcije[0]);
			
				if(i==0) {
					
					//brisanje predmeta
					
					DefaultTableModel model = (DefaultTableModel) tabela.getModel();
					model.removeRow(tabela.getSelectedRow());
					
					
				}
				
				
			}
			
			
		
		}
		else {
			
			JOptionPane.showMessageDialog(this.c, "Greška.", "Greška: ", JOptionPane.ERROR_MESSAGE);
			
		}
		
	}
	
	public void updateRbr(int rbr) {
		
		this.rbrTaba = rbr;
		
	}
	
}
