package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import controller.WindowListenerSsluzba;
import view.tabbedPanes.PrikazPredmeta;
import view.tabbedPanes.PrikazProfesora;
import view.tabbedPanes.PrikazStudenta;

public class GlavniProzor extends JFrame{

	private static final long serialVersionUID = -8763285092502046194L;

	private int rbrTaba=0;
	
	public GlavniProzor()
	{
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize= kit.getScreenSize();
		int screenHeight = (int) (screenSize.height * 0.75);
		int screenWidth = (int) (screenSize.width * 0.75);
		
		addWindowListener(new WindowListenerSsluzba());
		
		
		setSize(screenWidth, screenHeight);
		setTitle("Studentska služba");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setLocationRelativeTo(null);
		
		JPanel panelSsluzba = new JPanel();
		getContentPane().add(panelSsluzba);
		panelSsluzba.setLayout(new BorderLayout(0,0));
		
		JPanel panelToolbar = new JPanel();
		panelSsluzba.add(panelToolbar, BorderLayout.NORTH);
		panelToolbar.setLayout(new BorderLayout(0,0));
		
		/* --- Toolbar --- */
		
		Toolbar toolbar = new Toolbar(rbrTaba, this);
		panelToolbar.add(toolbar);
		
		JPanel panelMain = new JPanel();
		panelSsluzba.add(panelMain, BorderLayout.CENTER);
		panelMain.setLayout(new BorderLayout(0,0));
		
		
		Component rigidArea = Box.createRigidArea(new Dimension(30, 30));
		Component rigidArea2 = Box.createRigidArea(new Dimension(10, 10));
		
		panelMain.add(rigidArea, BorderLayout.NORTH);
		panelMain.add(rigidArea2, BorderLayout.SOUTH);
		panelMain.add(Box.createHorizontalStrut(20), BorderLayout.WEST);
		panelMain.add(Box.createHorizontalStrut(20), BorderLayout.EAST);
		
		
		/* --- Menu bar --- */
		MenuBar menuBar = new MenuBar(screenWidth, screenHeight, this);
		this.setJMenuBar(menuBar);
		
		
		/* --- Tabovi --- */
		
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.setBorder(new LineBorder(new Color(0,0,0)));
		
		tabbedPane.add("Studenti", PrikazStudenta.getInstance());
		tabbedPane.add("Profesori", PrikazProfesora.getInstance());
		tabbedPane.add("Predmeti", PrikazPredmeta.getInstance());

		tabbedPane.addChangeListener(new ChangeListener() {
			
			public void stateChanged(ChangeEvent e) {
				
				rbrTaba = tabbedPane.getSelectedIndex();
				
				toolbar.updateRbr(rbrTaba);
				menuBar.updateRbr(rbrTaba);
				
			}
			
		});
		
		
		
		panelMain.add(tabbedPane, BorderLayout.CENTER);
		
		/* --- Status bar --- */
		
		StatusBar statusBar = new StatusBar();
		statusBar.addText("Studentska služba");
		statusBar.addTimeAndDate();	
		statusBar.attach(getContentPane());
		
		
	}
	
}
