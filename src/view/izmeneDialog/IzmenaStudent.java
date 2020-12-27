package view.izmeneDialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.Box;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.LineBorder;

public class IzmenaStudent extends JDialog {

	/**
		 * 
		 */
	private static final long serialVersionUID = -5350992107196510274L;
	
	public IzmenaStudent(Container c) {

		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenHeight = (int) (screenSize.height * 0.75 * 0.9);
		int screenWidth = (int) (screenSize.width * 0.75 * 0.6);

		setTitle("Izmena studenta");
		setSize(screenWidth, screenHeight);
		setLocationRelativeTo(c);
		setModal(true);
		setResizable(false);

		JPanel panelMain = new JPanel();
		panelMain.setLayout(new BorderLayout(0, 0));

		Component rigidArea2 = Box.createRigidArea(new Dimension(10, 10));
		panelMain.add(rigidArea2, BorderLayout.SOUTH);

		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.setBorder(new LineBorder(new Color(0, 0, 0)));

		tabbedPane.add("Informacije", StudentInfo.getInstance());
		tabbedPane.add("Položeni", new JLabel("TODO: dodati prikaz polozenih"));
		tabbedPane.add("Nepoloženi", new JLabel("TODO: dodati prikaz nepolozenih"));
		panelMain.add(tabbedPane, BorderLayout.CENTER);
		add(panelMain);
	}

}