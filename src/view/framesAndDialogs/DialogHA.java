package view.framesAndDialogs;

import java.awt.Container;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import controller.fileReader.FileReader;

public class DialogHA extends JDialog{
		
		/**
		 * 
		 */
	private static final long serialVersionUID = 3609624215836161096L;

	public DialogHA(String title, String path, String imagePath, Container cont)
	{
		JDialog dialog = new JDialog();
		dialog.setVisible(true);
		dialog.setTitle(title);
		dialog.setSize((int) (cont.getWidth()*0.75),(int) (cont.getHeight()*0.75));
		dialog.setLocationRelativeTo(cont);
		
		//https://www.javatpoint.com/how-to-change-titlebar-icon-in-java-awt-swing?fbclid=IwAR02JPEn9b2_SUM4loISHuJAOYCE9wFZ49-eUfDIAbtLn-gBOleIHt5IWkU	
		Image icon = Toolkit.getDefaultToolkit().getImage(imagePath);
		dialog.setIconImage(icon);
		
		JEditorPane text = new JEditorPane();
		text.setEditable(false);
		text.setText(FileReader.readFromFile(path));

			
		JScrollPane scroll = new JScrollPane(text, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			
		dialog.add(scroll);
			
		}

	}
