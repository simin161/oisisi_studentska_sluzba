package app;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GlavniProzor extends JFrame{

	private static final long serialVersionUID = -8763285092502046194L;

	public GlavniProzor()
	{
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize= kit.getScreenSize();
		int screenHeight = (int) (screenSize.height * 0.75);
		int screenWidth = (int) (screenSize.width * 0.75);
		
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
		
		JPanel panelMain = new JPanel();
		panelSsluzba.add(panelMain, BorderLayout.CENTER);
		panelMain.setLayout(new BorderLayout(0,0));
		
		
		JLabel lblToDo= new JLabel("TO DO: dodati prikaz entiteta");
		
		
		Component rigidArea = Box.createRigidArea(new Dimension(30, 30));
		Component rigidArea2 = Box.createRigidArea(new Dimension(10, 10));
		
		panelMain.add(rigidArea, BorderLayout.NORTH);
		panelMain.add(rigidArea2, BorderLayout.SOUTH);
		panelMain.add(Box.createHorizontalStrut(20), BorderLayout.WEST);
		panelMain.add(Box.createHorizontalStrut(20), BorderLayout.EAST);
		panelMain.add(lblToDo);
		
		
	}
	
	
	
}
