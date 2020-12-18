package controller;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import view.framesAndDialogs.DialogHA;

public class ActionHelpAbout {

	public static void helpAboutAction(JMenuItem hA, String title, String path, String imagePath, int w, int h,
			Container cont) {
		hA.addActionListener(new ActionListener() {

			@SuppressWarnings("unused")
			@Override
			public void actionPerformed(ActionEvent e) {
				DialogHA frameHA = new DialogHA(title, path, imagePath, w, h, cont);

			}

		});
	}

}
