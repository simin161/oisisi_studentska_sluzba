package view;

import java.awt.Container;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import controller.ActionHelpAbout;

public class MenuBar extends JMenuBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = -310993895391393553L;

	@SuppressWarnings("deprecation")
	public MenuBar(int width, int height, Container cont)
	{
		JMenu menuFile = new JMenu("File");
		add(menuFile);
		
		JMenu menuEdit = new JMenu("Edit");
		add(menuEdit);
		
		JMenu menuHelp = new JMenu("Help");
		add(menuHelp);
		
		//menu bar file
		
		JMenuItem menuItemNew = new JMenuItem("New", new ImageIcon("images/add3.png"));
		menuItemNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
		menuFile.add(menuItemNew);
		menuItemNew.setMnemonic('N');
		
		menuFile.addSeparator();

		JMenuItem menuItemClose = new JMenuItem("Close", new ImageIcon("images/close2.png"));
		menuItemClose.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
		menuFile.add(menuItemClose);
		menuItemClose.setMnemonic('C');
		
		//menu bar edit
		
		JMenuItem menuItemEdit = new JMenuItem("Edit", new ImageIcon("images/edit3.png"));
		menuItemEdit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_MASK));
		menuEdit.add(menuItemEdit);
		menuItemEdit.setMnemonic('E');
		
		menuEdit.addSeparator();
		
		JMenuItem menuItemDelete = new JMenuItem("Delete", new ImageIcon("images/delete3.png"));
		menuItemDelete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_MASK));
		menuItemDelete.setMnemonic('D');
		menuEdit.add(menuItemDelete);
		
		//menu bar help
		
		JMenuItem menuItemHelp = new JMenuItem("Help", new ImageIcon("images/help2.png"));
		menuItemHelp.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, InputEvent.CTRL_MASK));
		menuItemHelp.setMnemonic('H');
		menuHelp.add(menuItemHelp);
		ActionHelpAbout.helpAboutAction(menuItemHelp, "Help", "text/help.txt",width, height, cont);
		
		menuHelp.addSeparator();
		
		JMenuItem menuItemAbout = new JMenuItem("About", new ImageIcon("images/about2.png"));
		menuItemAbout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
		menuItemAbout.setMnemonic('A');
		menuHelp.add(menuItemAbout);
		ActionHelpAbout.helpAboutAction(menuItemAbout, "About", "text/about.txt", width, height, cont);

	}
}
