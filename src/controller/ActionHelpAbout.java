package controller;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import view.framesAndDialogs.FrameHA;

public class ActionHelpAbout {

	public static void helpAboutAction(JMenuItem hA, String title, String path, int width, int height, Container cont)
	{
		hA.addActionListener(new ActionListener()
		{

			@SuppressWarnings("unused")
			@Override
			public void actionPerformed(ActionEvent e) {
				FrameHA frameHA = new FrameHA(title, path, width, height, cont);

			}
				
		});
	}
	
}
