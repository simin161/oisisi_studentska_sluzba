package view.framesAndDialogs;

import java.awt.Container;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import controller.fileReader.FileReader;

public class FrameHA extends JFrame{
		
		/**
		 * 
		 */
	private static final long serialVersionUID = 3609624215836161096L;

	public FrameHA(String title, String path, String imagePath, int width,int height,Container cont)
	{
		JFrame frame = new JFrame();
		frame.setResizable(true);
		frame.setVisible(true);
		frame.setTitle(title);
		frame.setSize(width,height);
		frame.setLocationRelativeTo(cont);
		
		//https://www.javatpoint.com/how-to-change-titlebar-icon-in-java-awt-swing?fbclid=IwAR02JPEn9b2_SUM4loISHuJAOYCE9wFZ49-eUfDIAbtLn-gBOleIHt5IWkU	
		Image icon = Toolkit.getDefaultToolkit().getImage(imagePath);
		frame.setIconImage(icon);
		
		JEditorPane text = new JEditorPane();
		text.setEditable(false);
		text.setText(FileReader.readFromFile(path));

			
		JScrollPane scroll = new JScrollPane(text, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			
		frame.add(scroll);
			
		}

	}
