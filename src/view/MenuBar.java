package view;

import java.awt.Container;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import controller.AbstractActionDelete;
import controller.AbstractActionEdit;
import controller.AbstractActionNew;
import controller.ActionClose;
import controller.ActionHelpAbout;

public class MenuBar extends JMenuBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = -310993895391393553L;

	private int rbr = 0;
	private AbstractActionNew actionNew;
	private AbstractActionDelete actionDelete;
	private AbstractActionEdit actionEdit;

	@SuppressWarnings("deprecation")
	public MenuBar(int width, int height, Container cont) {

		JMenu menuFile = new JMenu("File");
		menuFile.setMnemonic('F');
		add(menuFile);

		JMenu menuEdit = new JMenu("Edit");
		menuEdit.setMnemonic('E');
		add(menuEdit);

		JMenu menuHelp = new JMenu("Help");
		menuHelp.setMnemonic('H');
		add(menuHelp);

		// menu bar file

		JMenuItem menuItemNew = new JMenuItem("New", new ImageIcon("images" + File.separator + "add3.png"));
		menuItemNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
		menuItemNew.setMnemonic('N');
		actionNew = new AbstractActionNew(rbr, cont);
		menuItemNew.addActionListener(actionNew);
		menuFile.add(menuItemNew);

		menuFile.addSeparator();

		JMenuItem menuItemClose = new JMenuItem("Close", new ImageIcon("images" + File.separator + "close2.png"));
		menuItemClose.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
		menuItemClose.setMnemonic('C');
		ActionClose.closeAction(menuItemClose);
		menuFile.add(menuItemClose);

		// menu bar edit

		JMenuItem menuItemEdit = new JMenuItem("Edit", new ImageIcon("images" + File.separator + "edit3.png"));
		menuItemEdit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_MASK));
		menuItemEdit.setMnemonic('E');
		actionEdit = new AbstractActionEdit(rbr, cont);
		menuItemEdit.addActionListener(actionEdit);
		menuEdit.add(menuItemEdit);

		menuEdit.addSeparator();

		JMenuItem menuItemDelete = new JMenuItem("Delete", new ImageIcon("images" + File.separator + "delete3.png"));
		menuItemDelete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_MASK));
		menuItemDelete.setMnemonic('D');
		actionDelete = new AbstractActionDelete(rbr, cont);
		menuItemDelete.addActionListener(actionDelete);
		menuEdit.add(menuItemDelete);

		// menu bar help

		JMenuItem menuItemHelp = new JMenuItem("Help", new ImageIcon("images" + File.separator + "help2.png"));
		menuItemHelp.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, InputEvent.CTRL_MASK));
		menuItemHelp.setMnemonic('H');
		ActionHelpAbout.helpAboutAction(menuItemHelp, "Help", "text" + File.separator + "help.txt",
				"images" + File.separator + "help.png", (int) (width * 0.75), (int) (height * 0.85), cont);
		menuHelp.add(menuItemHelp);

		menuHelp.addSeparator();

		JMenuItem menuItemAbout = new JMenuItem("About", new ImageIcon("images" + File.separator + "about2.png"));
		menuItemAbout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
		menuItemAbout.setMnemonic('A');
		ActionHelpAbout.helpAboutAction(menuItemAbout, "About", "text" + File.separator + "about.txt",
				"images" + File.separator + "about.png", (int) (width * 0.75), (int) (height * 0.75), cont);
		menuHelp.add(menuItemAbout);

	}

	public void updateRbr(int rbr) {

		this.rbr = rbr;
		this.actionNew.updateRbr(rbr);
		this.actionDelete.updateRbr(rbr);
		this.actionEdit.updateRbr(rbr);
	}
}
