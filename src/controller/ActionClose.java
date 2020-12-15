package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;


public class ActionClose {

	public static void closeAction(JMenuItem closeItem) {
		
		closeItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);

			}
		});
	}
}
