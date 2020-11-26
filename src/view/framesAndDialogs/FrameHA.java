package view.framesAndDialogs;

import java.awt.Container;

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

	public FrameHA(String title, String path, int width,int height,Container cont)
	{
		JFrame frame = new JFrame();
		frame.setResizable(true);
		frame.setVisible(true);
		frame.setTitle(title);
		frame.setSize(width,height);
		frame.setLocationRelativeTo(cont);
			
		JEditorPane text = new JEditorPane();
		text.setEditable(false);
		text.setText(FileReader.readFromFile(path));

			
		JScrollPane scroll = new JScrollPane(text, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			
		frame.add(scroll);
			
		}

	}
